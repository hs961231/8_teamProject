package scts.wdb.yjc.scts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import scts.wdb.yjc.scts.bean.IPSetting;
import scts.wdb.yjc.scts.hardwaremanager.BeaconM;
import scts.wdb.yjc.scts.hardwaremanager.SensorM;

public class MainActivity extends AppCompatActivity {

    private EditText user_id_input; // 서버 통신 테스트용 아이디 입력 칸
    private EditText user_pw_input; // 서버 통신 테스트용 패스워드 입력 칸
    private String user_id;          // 입력된 아이디
    private String user_pw;          // 입력된 비밀번호
    SharedPreferences sp;            // 세션 유지하기위한 preference

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

        /****************************************************** 로그인 세션 생성하는법 *********************************************************************/
        loginButton();

        /***************************************************** 회원가입 화면 전환 ****************************************************************/

        findViewById(R.id.join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Join.class);
                startActivity(intent);
            }
        });


        /****************************************************** 가속도 센서 활용 테스트 *********************************************************************/
   /*     sensorM = new SensorM(this);



        // 텍스트 뷰 어레이 설정
        for(int i=0; i<tvArray.length; i++) {
            tvArray[i] = (TextView) findViewById(R.id.txt1+i);
        }
        *//**************************************************** 비콘 관련 ***************************************************************//*
        // 비콘 매니저를 생성해서 비콘 관리용 클래스에 넣어줌
        beaconM = new BeaconM(new BeaconManager(this), sensorM);

        // 비콘의 리스너를 등록함 ( 시작은 onResume에서 커넥트로 시작해줌 )
        beaconM.BeaconSetListner(tvArray);*/


    }


/*    @Override
    protected void onResume() {
        super.onResume();
       *//* // 블루투스 권한 및 활성화 코드드
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        // 센서 값을 이 컨텍스트에서 받아볼 수 있도록 리스너를 등록한다.
        //m_sensor_manager.registerListener(this, m_accelerometer, SensorManager.SENSOR_DELAY_UI);

        sensorM.sensorStart();
        // 비콘 감지 시작
        beaconM.BeaconConnect();*//*
    }*/

  /*  protected void test() {

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BeaconTimeData beaconTimeData = new BeaconTimeData(444, 5555);
                //String json = new Gson().toJson(beaconTimeData);

                String json = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
                        .create()
                        .toJson(beaconTimeData);

                BeaconSet test = new BeaconSet();

                Log.d(TAG, "Time: " + beaconTimeData.getCurrentTime().getTime() );

                Log.d(TAG, "onClick: " + json);
                test.execute(json);
            }
        });
    }*/

    public void loginButton() {

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                user_id_input = (EditText) findViewById(R.id.userId);
                user_id = user_id_input.getText().toString();
                user_pw_input = (EditText) findViewById(R.id.userPw);
                user_pw = user_pw_input.getText().toString();


                sp = getSharedPreferences("test", 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user_id", user_id);
                editor.commit();


                JSONObject  jsonObject = new JSONObject();
                try {
                    jsonObject.put("user_id", user_id);
                    jsonObject.put("user_pw", user_pw);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String json = jsonObject.toString();

                // 네트워크 연결 후 서버 전송
                NetworkTask networkTask = new NetworkTask();
                networkTask.execute(json);

            }
        });
    }

    // 로그인 세션 관련 네트워크 통신
    private class NetworkTask extends AsyncTask<String, String, String> {

        protected  void onPreExcute(){

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... json) {
            // bean 안에 있는 ip 셋팅 정보를 꼭 바꾸도록 할 것
            HttpClient.Builder http = new HttpClient.Builder("POST", IPSetting.getIpAddress() + "androidLogin");

            http.addOrReplace("UserVO", json[0]);

            // HTTP 요청 전송
            HttpClient post = http.create();

            post.request();


            // 응답 상태코드 가져오기
            int statusCode = post.getHttpStatusCode();

            // 응답 본문 가져오기
            String body = post.getBody();


            return body;
        }

        protected void onPostExecute(String s){
            // db에 저장된 유저 정보를 성공적으로 조회햇음
            if(s.equals("SUCCESS")) {
                // 프리퍼런스 설정
                sp = getSharedPreferences("test", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user_id", user_id);
                editor.commit();

                // 로그인 완료 및 웹뷰 창 띄우기 등등 처리해야함

                // 디버깅용
                Log.d(TAG, "프리퍼런스 설정 완료");
                Log.d(TAG, "저장된 아이디 : " + sp.getString("user_id",""));
            }
            // 저장된 유저 정보가 없을 때
            else {
                // 실제 로그인 실패햇을때 아이디 비밀번호 틀렷다는 창을 보여줘야하는 부분을 코딩해야함

                // 디버깅용
                Log.d(TAG, "로그인 실패함");
                Log.d(TAG, "입력 아이디 : " + user_id);
            }

            /**************************** 그전 로그인 세션 생성 ***********************/
/*

            Log.i("JSON/RESULT", s);

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);

                Log.i("json", jsonObject.get("checkUser").toString());


                if(jsonObject.get("checkUser").toString().equals("1")){


                    String str = sp.getString("customer_id", "");
                    if(str.equals(user_id)){
                        Intent intent = new Intent(getApplicationContext(), WebViewTest.class);
                        startActivity(intent);
                    }else{
                        setContentView(R.layout.activity_main);
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "로그인 실패!!!!", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
*/



        }

    }

}
