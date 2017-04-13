package scts.wdb.yjc.scts;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

import scts.wdb.yjc.scts.bean.BeaconTimeData;
import scts.wdb.yjc.scts.network.TestNetworkTask;

public class MainActivity extends AppCompatActivity {

    private EditText user_id_input; // 서버 통신 테스트용 아이디 입력 칸
    private EditText user_pw_input; // 서버 통신 테스트용 패스워드 입력 칸
    private String user_id;          // 입력된 아이디
    private String user_pw;          // 입력된 비밀번호


    private BeaconManager beaconManager;
    private Region region;

    //    private TextView tvId;
    private TextView tvArray[] = new TextView [4];  // 디버깅용 텍스트뷰 어레이
    private int detCnt = 0;                         // 현재까지 감지된 비콘 갯수, 누적시킴
    final String TAG = "디버깅";

    BeaconTimeData oldBeacon;                       // 가장 마지막으로 감지된 비콘

    private static final UUID BEACON_UUID = UUID.fromString("20CAE8A0-A9CF-11E3-A5E2-0800200C9A66");

    private String TAGA = "Sensors";
    private SensorManager mSensorManager;
//    Sensor accelSen = mSensorManager.getDefaultSensor()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /******************************************************* 네트워크 통신 테스트 **********************************************************************/
        // http 서버 통신 테스트용 클릭 이벤트
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                user_id_input = (EditText) findViewById(R.id.userId);
                user_id = user_id_input.getText().toString();
                user_pw_input = (EditText) findViewById(R.id.userPw);
                user_pw = user_pw_input.getText().toString();


                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("user_id", user_id);
                    jsonObject.put("user_pw", user_pw);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String json = jsonObject.toString();

                // 네트워크 연결
                TestNetworkTask networkTask = new TestNetworkTask();
                networkTask.execute(json);

                Toast.makeText(getApplicationContext(), user_id + " " + user_pw, Toast.LENGTH_LONG).show();

            }
        });

        beaconManager = new BeaconManager(this);
//        beaconManager = new BeaconManager(getApplicationContext());

        for(int i=0; i<tvArray.length; i++) {
            tvArray[i] = (TextView) findViewById(R.id.txt1+i);
        }
        /***************************************************** 비콘 관련 ****************************************************************/
        // add this below:
        // 비콘 리스너 선언
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


                    int i;
                    for (i=0; i < list.size(); i++) {
                        com.estimote.sdk.Beacon beacon = list.get(i);
                        Log.d(TAG, "onEnteredRegion: 메이저=" + beacon.getMajor() + " 마이너=" + beacon.getMinor() + " 신호강도=" + beacon.getRssi());
                        tvArray[i].setText("메이저=" + beacon.getMajor() + " 마이너=" + beacon.getMinor() + " 신호강도=" + beacon.getRssi());
                        detCnt++;
                    }

                    for(;i<tvArray.length-1; i++) {
                        tvArray[i].setText("감지비콘 없음");
                    }
                    tvArray[i].setText(detCnt+"");

                    Log.d("Airport", "Nearest places: " + nearestBeacon.getRssi());

                    // nearestBeacon.getRssi() : 비콘의 수신 강도
//                    tvId.setText(nearestBeacon.getRssi() + "");

                }
                // 비콘 감지가 안되었을 때
                else {
                    if(oldBeacon != null) {
                        oldBeacon = null;
                    }
                }
            }
        });
        // 본인이 연결할 Beacon의 ID와 Major / Minor Code를 알아야 한다. }
        region = new Region("ranged region", BEACON_UUID, null, null);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 블루투스 권한 및 활성화 코드드
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        /*
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
        */
    }

}
