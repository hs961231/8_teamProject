package scts.wdb.yjc.scts.network.send;

import android.os.AsyncTask;
import android.util.Log;

import scts.wdb.yjc.scts.HttpClient;

/**
 * Created by JYH on 2017-04-06.
 */

public class BeaconSet extends AsyncTask<String, String, String> {

    protected  void onPreExcute(){

        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        // 노트북 테스트용 주소, ip가 와이파이 잡을때마다 달라지기 때문에 체크하고 주소 바꿔줘야함
        //HttpClient.Builder http = new HttpClient.Builder("POST", "http://172.19.2.176:8080/scts/setPositionData");
        // 실제 서버구동시 사용하는 주소
        HttpClient.Builder http = new HttpClient.Builder("POST", "http://zseil.cafe24.com/SCTS/setPositionData");

        // http.addOrReplace("json", json[0]);

        http.addOrReplace("PositionVO", params[0]);
        /*
        for(int i=0; i<params.length; i++) {
            http.addOrReplace("" + i,params[i]);
        }
        */
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
