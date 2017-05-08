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

public class WebViewMain extends AppCompatActivity {

    private WebView webView;

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


        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/index.html");


        SharedPreferences sp = getSharedPreferences("test", 0);
        String str = sp.getString("user_id", "");
        Log.i("user_id", str);


    }


    class JSObject {
        @JavascriptInterface
        public void sampleAction(String str) {

            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

        }
    }
}
