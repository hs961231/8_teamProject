package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.BBScttVO;
import yjc.wdb.scts.bean.EventVO;

public interface BBSDAO {
	
	
	public List<HashMap> viewCalendar() throws Exception;
	public void insertBBSctt(BBScttVO bbscttVO) throws Exception;
	public void insertEvent(EventVO eventVO) throws Exception;
	public List<HashMap> eventOne(int code) throws Exception;
	public void updateEvent(EventVO eventVO) throws Exception;
	public void updateBbsctt(BBScttVO bbscttVO) throws Exception;
	public void deleteEvent(int bbsctt_code) throws Exception;
	public void deleteBbsctt(int bbsctt_code) throws Exception;
	public List<HashMap> listEvent(String date1, String date2) throws Exception;
}
