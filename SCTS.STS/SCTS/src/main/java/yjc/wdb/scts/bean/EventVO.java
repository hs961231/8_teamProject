package yjc.wdb.scts.bean;

import java.sql.Date;

public class EventVO {

	private int e_id;
	private String e_name;
	private Date e_start;
	private Date e_end;
	private String e_content;
	
	public String getE_content() {
		return e_content;
	}
	public void setE_content(String e_content) {
		this.e_content = e_content;
	}
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public Date getE_start() {
		return e_start;
	}
	public void setE_start(Date e_start) {
		this.e_start = e_start;
	}
	public Date getE_end() {
		return e_end;
	}
	public void setE_end(Date e_end) {
		this.e_end = e_end;
	}
	
	
}
