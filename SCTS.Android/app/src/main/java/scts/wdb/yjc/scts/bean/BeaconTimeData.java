package scts.wdb.yjc.scts.bean;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by JYH on 2017-04-05.
 */

public class BeaconTimeData {
    private int major;
    private int minor;
    private String user_id;
    private Timestamp current_Timedate;
    private int stay_Time;

    // 초기 객체 생성시 현재 시간 입력
    public BeaconTimeData() {

        // (1) Calendar객체를 얻는다.
        Calendar cal = Calendar.getInstance();

        // 시간 셋팅
        current_Timedate = new Timestamp(cal.getTimeInMillis());

        //currentTime = new Date( new java.util.Date().getTime() );
    }
    public BeaconTimeData(int major, int minor) {
        this();

        this.major = major;
        this.minor = minor;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setStay_Time(int stay_Time) {
        this.stay_Time = stay_Time;

    }

    /*
    public void setStayTimeMil() {
        Calendar cal = Calendar.getInstance();

        // 현재시간 - 감지된 시간을 해서 머문 시간을 구함
        stayTimeMil = (int) (cal.getTimeInMillis() - currentTime.getTime());
    }
    */

    public Timestamp getCurrentTime() {
        return current_Timedate;
    }

    public int getStay_Time() {
        return stay_Time;
    }
}
