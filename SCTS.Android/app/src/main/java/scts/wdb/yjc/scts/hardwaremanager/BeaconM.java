package scts.wdb.yjc.scts.hardwaremanager;

import android.util.Log;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.Gson;

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
    BeaconManager beaconManager;

    private static final UUID BEACON_UUID = UUID.fromString("20CAE8A0-A9CF-11E3-A5E2-0800200C9A66");

    private Region region = new Region("ranged region", BEACON_UUID, null, null);

    // 그전에 가장 가까운 비콘
    Beacon oldnearBeacon = null;
    // 현재 가장 가까운 비콘(감지시점)
    Beacon currentNearBeacon;
    // 센서 관련 로직 클래스
    private SensorM sensorM;

    // 비콘이 감지된 갯수
    int beaconCnt;

    private BeaconTimeData beaconTimeData;

    // 멈춰 있을 때 비콘에서 머문 시간을 계산하기 위한 시간데이터
    private Timestamp currentTime;
    // 머문 시간 계산용
    private int stayTimeMil = 0;

    public BeaconM(BeaconManager beaconManager) {
        this.beaconManager = beaconManager;
    }

    public BeaconM(BeaconManager beaconManager, SensorM sensorM) {
        this.beaconManager = beaconManager;
        this.sensorM = sensorM;
    }

    public void BeaconSetListner(final TextView[] tvArray) {

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
                    beaconTimeData = new BeaconTimeData(oldnearBeacon.getMajor(), oldnearBeacon.getMinor());
                }
                // 그 전에 감지된 비콘과 다르다
                else if( oldnearBeacon.getMajor() != currentNearBeacon.getMajor() || oldnearBeacon.getMinor() != currentNearBeacon.getMinor() ) {
                    oldnearBeacon = currentNearBeacon;

                    // 처음 감지된 시점에서 비콘의 메이저와, 마이너, 그리고 감지된 시간을 생성
                    beaconTimeData = new BeaconTimeData(oldnearBeacon.getMajor(), oldnearBeacon.getMinor());

                    if(currentTime != null) {
                        Calendar cal = Calendar.getInstance();
                        stayTimeMil += (int) (cal.getTimeInMillis() - currentTime.getTime());
                        currentTime = null;
                        Log.d(TAG, "logic: 움직였는데 다른비콘 감지 stayTimeMil = " + stayTimeMil); // 디버깅용 시스템 로그
                    }
                    if(stayTimeMil != 0) {
                        // 비콘 타임데이터에 머문 시간 저장
                        beaconTimeData.setStayTime( stayTimeMil/1000 );
                        stayTimeMil = 0;

                        // 서버로 전송시키는 부분 코딩해야함
                        String json = new Gson().toJson(beaconTimeData);

                        Log.d(TAG, "logic: 움직였는데 다른비콘 감지 서버로 전송 stayTimeMil = " + json); // 디버깅용 시스템 로그


                        BeaconSet networkTask = new BeaconSet();
                        networkTask.execute(json);
                    }
                }
                // 그 전에 감지된 비콘과 같다
                else {
                    ;                   if(currentTime != null) {
                        Calendar cal = Calendar.getInstance();
                        stayTimeMil += (int) (cal.getTimeInMillis() - currentTime.getTime());
                        currentTime = null;
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
                    if(stayTimeMil != 0) {
                        // 비콘 타임데이터에 머문 시간 저장
                        beaconTimeData.setStayTime( stayTimeMil/1000 );
                        stayTimeMil = 0;
                        // 서버로 전송시키는 부분 코딩해야함
                        String json = new Gson().toJson(beaconTimeData);

                        Log.d(TAG, "logic: 움직였는데 비콘감지 안됨 서버 전송 stayTimeMil = " + json); // 디버깅용 시스템 로그
                    }
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
                    Log.d(TAG, "logic: 지금 멈춰서 시간계산 시작한다" ); // 디버깅용 시스템 로그
                    Log.d(TAG, "oldBeacon = " + oldnearBeacon.toString() ); // 디버깅용 시스템 로그
                }
            }
        }

    }
/*
    public Beacon getNearBeacon() {
        return nearBeacon;
    }

    public int getBeaconCnt() {
        return beaconCnt;
    }
*/
    // 리스너 등록
    // 테스트용 리스너
    /*
    public void BeaconSetListner(final TextView[] tvArray) {

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List< Beacon > list) {
                // 비콘이 감지되었을 때
                if (!list.isEmpty()) {
                    com.estimote.sdk.Beacon nearestBeacon = list.get(0);
                    // 그 전 스캔때 감지된 비콘이 없을 때
                    if(oldBeacon == null) {
                        oldBeacon = new BeaconTimeData();
                        oldBeacon.setMajor(nearestBeacon.getMajor());
                        oldBeacon.setMinor(nearestBeacon.getMinor());
                    }
                    // 그 전 스캔때 감지된 비콘이 있을때
                    else {
                        // 이번에 감지된 비콘이 그 전 비콘과 값이 같을 때
                        if(oldBeacon.getMajor() == nearestBeacon.getMajor() &&
                                oldBeacon.getMinor() == nearestBeacon.getMinor()) {
                            Log.d(TAG, "onBeaconsDiscovered: " + nearestBeacon.toString());
                        }
                        // 같지 않을 때
                        else {
                            // 머문 시간을 저장
                            oldBeacon.setStayTimeMil();

                            // 테스트용 안드로이드에서 값 확인하기 위함

                            tvArray[0].setText( "onEnteredRegion: 메이저=" + oldBeacon.getMajor() +
                                    " 마이너=" + oldBeacon.getMinor() +
                                    " 감지시간=" + oldBeacon.getCurrentTime() +
                                    " 머문시간=" + oldBeacon.getStayTimeMil() );

                            // 테스트용 안드로이드에서 값 확인하기 위함
                            tvArray[0].setText("비콘감지시뮬 : 메이저=" + oldBeacon.getMajor());
                            tvArray[1].setText(" 마이너=" + oldBeacon.getMinor() + " 감지시간=" + oldBeacon.getCurrentTime() + "신호강도=" + nearestBeacon.getRssi());
                            tvArray[2].setText(" 머문시간=" + oldBeacon.getStayTimeMil());
                            tvArray[3].setText(nearestBeacon.toString());
                            // http를 이용해서 데이터를 웹쪽으로 보내서 db에 저장해야함
                            // http 클라이언트 사용할 것
                            // json 사용

                            // oldbeacon 초기화
                            oldBeacon = new BeaconTimeData();
                            oldBeacon.setMajor(nearestBeacon.getMajor());
                            oldBeacon.setMinor(nearestBeacon.getMinor());
                        }
                    }
                }
                // 비콘 감지가 안되었을 때
                else {
                    if(oldBeacon != null) {
                        oldBeacon = null;
                    }
                }
            }
        });
    }*/

    public void BeaconConnect() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

}
