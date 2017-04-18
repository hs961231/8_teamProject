package yjc.wdb.scts.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class PositionVO {
	private int			num;
	private int			major;
	private int			minor;
	private Timestamp	currentTime;
	private int			stayTime;
	
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
	public Timestamp getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Timestamp currentTime) {
		this.currentTime = currentTime;
	}
	public int getStayTime() {
		return stayTime;
	}
	public void setStayTime(int stayTime) {
		this.stayTime = stayTime;
	}
	
}
