package scts.wdb.yjc.scts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import scts.wdb.yjc.scts.hardwaremanager.BeaconM;
import scts.wdb.yjc.scts.hardwaremanager.SensorM;

public class MainActivity extends AppCompatActivity {

    private EditText user_id_input; // 서버 통신 테스트용 아이디 입력 칸
    private EditText user_pw_input; // 서버 통신 테스트용 패스워드 입력 칸
    private String user_id;          // 입력된 아이디
    private String user_pw;          // 입력된 비밀번호

    // 센서 관련 로직 클래스
    private SensorM sensorM;

    // 비콘 관련 로직 클래스
    private BeaconM beaconM;

    //    private TextView tvId;
    private TextView tvArray[] = new TextView [4];  // 디버깅용 텍스트뷰 어레이
    final String TAG = "디버깅";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /******************************************************* 가속도 센서 활용 테스트 **********************************************************************/
        sensorM = new SensorM(this);

        /******************************************************* 네트워크 통신 테스트 **********************************************************************/
        /*
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

        // 텍스트 뷰 어레이 설정
        for(int i=0; i<tvArray.length; i++) {
            tvArray[i] = (TextView) findViewById(R.id.txt1+i);
        }
        *//***************************************************** 비콘 관련 ****************************************************************//*
        // 비콘 매니저를 생성해서 비콘 관리용 클래스에 넣어줌
        beaconM = new BeaconM(new BeaconManager(this), sensorM);

        // 비콘의 리스너를 등록함 ( 시작은 onResume에서 커넥트로 시작해줌 )
        beaconM.BeaconSetListner(tvArray);*/

    }


    @Override
    protected void onResume() {
        super.onResume();
        // 블루투스 권한 및 활성화 코드드
        //SystemRequirementsChecker.checkWithDefaultDialogs(this);

        // 센서 값을 이 컨텍스트에서 받아볼 수 있도록 리스너를 등록한다.
        //m_sensor_manager.registerListener(this, m_accelerometer, SensorManager.SENSOR_DELAY_UI);

        // 센서 시작
        sensorM.sensorStart();
        // 비콘 감지 시작
        beaconM.BeaconConnect();
    }



}
