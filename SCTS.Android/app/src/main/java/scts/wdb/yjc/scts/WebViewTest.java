package scts.wdb.yjc.scts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewTest extends AppCompatActivity {

    WebView webView;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        webView = (WebView) findViewById(R.id.webView);

       webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.addJavascriptInterface(new JSObject(), "sampleAndroid");




        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/test.html");




        SharedPreferences sp = getSharedPreferences("test",  0);
        String str = sp.getString("customer_id", "");

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(str);

        Log.i("customer_id", str);



    }


    class JSObject{
        @JavascriptInterface
        public void sampleAction(String str){

            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();


        }
    }


}
