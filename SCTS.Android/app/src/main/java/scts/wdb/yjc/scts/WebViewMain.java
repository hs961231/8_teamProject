package scts.wdb.yjc.scts;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.estimote.sdk.BeaconManager;

import scts.wdb.yjc.scts.hardwaremanager.BeaconM;
import scts.wdb.yjc.scts.hardwaremanager.SensorM;

public class WebViewMain extends AppCompatActivity {

    private WebView webView;

    // 센서 관련 로직 클래스
    private SensorM sensorM;
    // 비콘 관련 로직 클래스
    private BeaconM beaconM;
    private SharedPreferences sp;
    private String str;
    private SharedPreferences.Editor editor;

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
                            android.os.Process.killProcess(android.os.Process.myPid());
                            editor = sp.edit();
                            editor.remove("user_id");

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


            webView.loadUrl("javascript:setId('"+str+"')");

        }
    }
}
