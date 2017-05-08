package scts.wdb.yjc.scts.hardwaremanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import scts.wdb.yjc.scts.bean.BeaconTimeData;
import scts.wdb.yjc.scts.network.send.BeaconSet;

import static android.content.ContentValues.TAG;

/**
 * Created by JYH on 2017-04-15.
 * beacon관리하는 클래스 해당 클래스에서 비콘 리스너 및 커넥트를 시작하지만
 * 실제 시작을 해줘야 하는건 액티비티에서 실행을 시켜줘야함
 */

public class BeaconM{
    private BeaconManager beaconManager;
    private static final UUID BEACON_UUID = UUID.fromString("20CAE8A0-A9CF-11E3-A5E2-0800200C9A66");
    private Region region = new Region("ranged region", BEACON_UUID, null, null);

    // 그전에 가장 가까운 비콘
    private Beacon oldnearBeacon = null;
    // 현재 가장 가까운 비콘(감지시점)
    private Beacon currentNearBeacon;
    // 센서 관련 로직 클래스
    private SensorM sensorM;
    // 비콘이 감지된 갯수
    private int beaconCnt;
    // 비콘시간 및 데이터
    private BeaconTimeData beaconTimeData;

    // 멈춰 있을 때 비콘에서 머문 시간을 계산하기 위한 시간데이터
    private Timestamp currentTime;
    // 머문 시간 계산용
    private int stayTimeMil = 0;

    private Context mContext;

    /**
     * 생성자
     * @param beaconManager
     */
    public BeaconM(BeaconManager beaconManager) {
        this.beaconManager = beaconManager;
    }

    /**
     * 생성자
     * @param beaconManager
     * @param sensorM
     */
    public BeaconM(BeaconManager beaconManager, SensorM sensorM) {
        this.beaconManager = beaconManager;
        this.sensorM = sensorM;
    }

    public void SetContext(Context context) {
        this.mContext = context;
    }

    // 리스너 등록 ( 로직까지 등록 )
    public void BeaconSetListner() {

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                beaconCnt = list.size();
                if(!list.isEmpty())
                    currentNearBeacon = list.get(0);
                else
                    currentNearBeacon = null;
                logic();
            }
        });
    }

    public void BeaconConnect() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    public String getId() {
        SharedPreferences sp = mContext.getSharedPreferences("test", 0);
        String str = sp.getString("user_id", "");

        return str;
    }

    public void sendBeaconData() {

        // 비콘 타임데이터에 머문 시간 저장
        beaconTimeData.setStay_Time( stayTimeMil/1000 );
        stayTimeMil = 0;

        // 서버로 전송시키기 위해 비콘 감지 데이터를 json형태 문자열로 변환
        String json = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
                .create()
                .toJson(beaconTimeData);

        // 제이슨 형태 확인
        Toast.makeText(mContext, "logic: 서버전송 stayTimeMil = " + json, Toast.LENGTH_LONG).show();
        // 서버로 전송시킴
        BeaconSet networkTask = new BeaconSet();
        networkTask.execute(json);
    }

    public void logic() {

        Log.d(TAG, "logic: MoveChk = " + sensorM.getMoveChk());
        /********************************************************** 움직일 경우 **********************************************************/
        if(sensorM.getMoveChk()) {
            Log.d(TAG, "logic: beaconCnt = " + beaconCnt);
            /********************************* 비콘 감지됨 *********************************/
            if(beaconCnt > 0) {

                // 그 전에 감지된 비콘이 없다
                if(oldnearBeacon == null) {
                    oldnearBeacon = currentNearBeacon;
                    // 처음 감지된 시점에서 비콘의 메이저와, 마이너, 그리고 감지된 시간을 생성
                    // 감지된 시간은 해당 BeaconTimeData클래스 자체에서 객체 생성시 자동으로 생성함.
                    beaconTimeData = new BeaconTimeData(oldnearBeacon.getMajor(), oldnearBeacon.getMinor());
                    beaconTimeData.setUser_id( getId() );
                }
                // 그 전에 감지된 비콘과 다르다
                else if( oldnearBeacon.getMajor() != currentNearBeacon.getMajor() || oldnearBeacon.getMinor() != currentNearBeacon.getMinor() ) {
                    oldnearBeacon = currentNearBeacon;

                    // 처음 감지된 시점에서 비콘의 메이저와, 마이너, 그리고 감지된 시간을 생성
                    // 감지된 시간은 해당 BeaconTimeData클래스 자체에서 객체 생성시 자동으로 생성함.
                    beaconTimeData = new BeaconTimeData(oldnearBeacon.getMajor(), oldnearBeacon.getMinor());
                    beaconTimeData.setUser_id( getId() );

                    if(currentTime != null) {
                        Calendar cal = Calendar.getInstance();
                        stayTimeMil += (int) (cal.getTimeInMillis() - currentTime.getTime());
                        currentTime = null;
                        Log.d(TAG, "logic: 움직였는데 다른비콘 감지 stayTimeMil = " + stayTimeMil); // 디버깅용 시스템 로그
                    }
                    // 서버로 전송시킴
                    sendBeaconData();

                }
                // 그 전에 감지된 비콘과 같다
                else {
;                   if(currentTime != null) {
                        Calendar cal = Calendar.getInstance();
                        stayTimeMil += (int) (cal.getTimeInMillis() - currentTime.getTime());
                        currentTime = null;
                        Toast.makeText(mContext, "logic: 멈춘 시간 계산 stayTimeMil = " + stayTimeMil, Toast.LENGTH_LONG).show();
                        Log.d(TAG, "logic: 움직였는데 그전과 같은 비콘 stayTimeMil = " + stayTimeMil); // 디버깅용 시스템 로그
                    }
                }

            }
            /********************************* 비콘 감지안됨 *********************************/
            else {
                if(oldnearBeacon != null) {
                    // 시간 계산 해놓고 비콘 널로 바꾸기
                    oldnearBeacon = null;

                    if(currentTime != null) {
                        Calendar cal = Calendar.getInstance();
                        stayTimeMil += (int) (cal.getTimeInMillis() - currentTime.getTime());
                        currentTime = null;
                        Log.d(TAG, "logic: 움직였는데 비콘 감지 안됨 stayTimeMil = " + stayTimeMil); // 디버깅용 시스템 로그
                    }

                    // 서버로 전송시킴
                    sendBeaconData();

                }
            }
        }

        /********************************************************** 멈췃을 경우 **********************************************************/
        else {
            Log.d(TAG, "logic: beaconCnt = " + beaconCnt);
            if(beaconCnt > 0) {
                if(oldnearBeacon != null && currentTime == null) {
                    Calendar cal = Calendar.getInstance();
                    currentTime = new Timestamp(cal.getTimeInMillis());
                    Toast.makeText(mContext, "logic: 멈춘 시간 저장", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "logic: 지금 멈춰서 시간계산 시작한다" ); // 디버깅용 시스템 로그
                    Log.d(TAG, "oldBeacon = " + oldnearBeacon.toString() ); // 디버깅용 시스템 로그
                }
            }
        }

    }

}
