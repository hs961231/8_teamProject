package yjc.wdb.scts.bean;

import java.sql.Date;

public class EventVO {
	
	private int bbsctt_code;
	private Date event_begin_de;
	private Date event_end_de;
	public int getBbsctt_code() {
		return bbsctt_code;
	}
	public void setBbsctt_code(int bbsctt_code) {
		this.bbsctt_code = bbsctt_code;
	}
	public Date getEvent_begin_de() {
		return event_begin_de;
	}
	public void setEvent_begin_de(Date event_begin_de) {
		this.event_begin_de = event_begin_de;
	}
	public Date getEvent_end_de() {
		return event_end_de;
	}
	public void setEvent_end_de(Date event_end_de) {
		this.event_end_de = event_end_de;
	}

}
