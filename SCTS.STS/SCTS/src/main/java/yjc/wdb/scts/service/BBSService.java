package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.BBScttVO;
import yjc.wdb.scts.bean.EventVO;

public interface BBSService {
	
	public List<HashMap> viewCalendar() throws Exception;
	
	public void insertEvent(EventVO eventVO, BBScttVO bbscttVO) throws Exception;
	public List<HashMap> eventOne(int code) throws Exception;
	public void updateEvent(EventVO eventVO, BBScttVO bbscttVO) throws Exception;
	public void updateDropEvent(EventVO eventVO) throws Exception;
	public void deleteEvent(int bbsctt_code) throws Exception;
	public List<HashMap> listEvent(String date1, String date2) throws Exception;

}
