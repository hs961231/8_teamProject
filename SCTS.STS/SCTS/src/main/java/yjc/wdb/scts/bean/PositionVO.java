package yjc.wdb.scts.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class PositionVO {
	private int			num;
	private int			major;
	private int			minor;
	private String		user_id;
	private Timestamp	current_Timedate;
	private int			stay_Time;
	
	@Override
	public String toString() {
		String str = "[major: " + major + "]"; 
		str += "[minor: " + minor + "]"; 
		str += "[user_id: " + user_id + "]"; 
		str += "[current_Timedate: " + current_Timedate + "]"; 
		str += "[stay_Time: " + stay_Time + "]"; 
		return str;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Timestamp getCurrent_Timedate() {
		return current_Timedate;
	}
	public void setCurrent_Timedate(Timestamp current_Timedate) {
		this.current_Timedate = current_Timedate;
	}
	public int getStay_Time() {
		return stay_Time;
	}
	public void setStay_Time(int stay_Time) {
		this.stay_Time = stay_Time;
	}
	
}
