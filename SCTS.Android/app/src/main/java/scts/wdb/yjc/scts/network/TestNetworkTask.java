package scts.wdb.yjc.scts.network;

import android.os.AsyncTask;
import android.util.Log;

import scts.wdb.yjc.scts.HttpClient;

/**
 * Created by JYH on 2017-04-06.
 */

public class TestNetworkTask extends AsyncTask<String, String, String> {

    protected  void onPreExcute(){

        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... json) {
        // 되는 코드임.
        //HttpClient.Builder http = new HttpClient.Builder("POST", "http://172.19.2.122:8080/scts/android");
        // 실제 서버구동후 테스트 할 부분
        HttpClient.Builder http = new HttpClient.Builder("POST", "http://zseil.cafe24.com/SCTS/android");

        //http.addOrReplace("json", json[0]);

        // HTTP 요청 전송
        HttpClient post = http.create();

        post.request();

        // 응답 상태코드 가져오기
        int statusCode = post.getHttpStatusCode();

        // 응답 본문 가져오기
        String body = post.getBody();


        return body;
    }

    // jsp에서 리턴한 데이터 처리부분
    protected void onPostExecute(String s){
        Log.d("HTTP_RESULT", s);
    }

}
