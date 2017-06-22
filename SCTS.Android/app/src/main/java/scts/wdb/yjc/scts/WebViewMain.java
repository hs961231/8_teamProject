package scts.wdb.yjc.scts;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.estimote.sdk.BeaconManager;
import com.google.gson.JsonObject;

import scts.wdb.yjc.scts.bean.IPSetting;
import scts.wdb.yjc.scts.hardwaremanager.BeaconM;
import scts.wdb.yjc.scts.hardwaremanager.SensorM;

public class WebViewMain extends AppCompatActivity{

    private WebView webView;

    // 센서 관련 로직 클래스
    private SensorM sensorM;
    // 비콘 관련 로직 클래스
    private BeaconM beaconM;
    private SharedPreferences sp;
    private String str;
    private String point;
    private SharedPreferences.Editor editor;


    private EditText productInput;
    private String productName;
    private Button button;

    private final static String MAIN_URL = "file:///android_asset/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);


        sp = getSharedPreferences("test", 0);

        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.addJavascriptInterface(new JSObject(), "sampleAndroid");

        webView.setWebViewClient(new WebViewClientTest());
        webView.loadUrl(MAIN_URL);

        productInput = (EditText) findViewById(R.id.productInput);

        button = (Button) findViewById(R.id.productSearch);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productName = productInput.getText().toString();
                NetworkTask networkTask = new NetworkTask();
                networkTask.execute(productName);

            }
        });






        /****************************************************** 가속도 센서 활용 테스트 *********************************************************************/
        sensorM = new SensorM(this);

        // 센서 값을 이 컨텍스트에서 받아볼 수 있도록 리스너를 등록한다.
        sensorM.sensorStart();
        /*************************************************** 비콘 관련 **************************************************************/
        // 비콘 매니저를 생성해서 비콘 관리용 클래스에 넣어줌
        beaconM = new BeaconM(new BeaconManager(this), sensorM);
        beaconM.SetContext(this);
        beaconM.BeaconSetListner();
        beaconM.BeaconConnect();



    }

    public void setCoupon(JsonObject json) {
        webView.loadUrl("javascript:coupon('"+ json +"')");
    }
    public void testCoupon() {
        String data = "{\"coupon_dscnt\":\"30%\",\"status\":\"SUCCESS\",\"command\":\"fullcoupon\",\"coupon_cntnts\":\"안드로이드 쿠폰 수신용 첫번째 테스트 쿠폰입니다.\",\"coupon_code\":1,\"coupon_begin_de\":\"6월 22, 2017\",\"coupon_nm\":\"첫시험쿠폰\"}";

        webView.loadUrl("javascript:coupon('"+ data +"')");
    }
    // 뒤로 가기 버튼
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {

            webView.goBack();
            return true;
        }

        if((keyCode == KeyEvent.KEYCODE_BACK) && (webView.canGoBack() == false)){

            //다이아로그박스 출력
            new AlertDialog.Builder(this)
                    .setTitle("프로그램 종료")
                    .setMessage("프로그램을 종료하시겠습니까?")
                    .setPositiveButton("예", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            editor = sp.edit();
                            editor.remove("user_id");
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());

                        }
                    })
                    .setNegativeButton("아니오",  null).show();



        }
        return super.onKeyDown(keyCode, event);
    }



    class JSObject {
        @JavascriptInterface
        public void sampleAction(String str) {

            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

        }


    }

    class WebViewClientTest extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url){

            super.onPageFinished(view, url);

            sp = getSharedPreferences("test", 0);
            str = sp.getString("user_id", "");

            point = sp.getString("point", "0");


            webView.loadUrl("javascript:setId('"+str+"', '"+point+"')");


            // 이곳이 바로 쿠폰 보내는 곳!!!!!!!!!!!!!!!!!!!!!!!!!
            /*
            String data = "이것이바로 쿠폰!쿠폰! 왔다! 왔다 ";
            webView.loadUrl("javascript:coupon('"+ data +"')");*/

        }
    }

    private class NetworkTask extends AsyncTask<String, String, String> {

        protected  void onPreExcute(){

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... json) {
            // bean 안에 있는 ip 셋팅 정보를 꼭 바꾸도록 할 것
            HttpClient.Builder http = new HttpClient.Builder("POST", IPSetting.getIpAddress() + "productSearch");

            http.addOrReplace("productName", json[0]);

            Log.d("param", json[0]);

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

        Log.i("json", s);

        }

    }
}
