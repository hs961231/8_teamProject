package scts.wdb.yjc.scts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.logo));*/
        toolbar.setLogo(R.drawable.logo);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "로그아웃 넣을 거다!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.addJavascriptInterface(new JSObject(), "sampleAndroid");


        webView.setWebViewClient(new WebViewClientTest());
        webView.loadUrl("file:///android_asset/index.html");



        SharedPreferences sp = getSharedPreferences("test", 0);
        String str = sp.getString("user_id", "");
        Log.i("user_id", str);



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

            SharedPreferences sp = getSharedPreferences("test", 0);
            String str = sp.getString("user_id", "");
            Log.i("user_id", str);

            webView.loadUrl("javascript:setId('"+str+"')");

        }
    }
}
