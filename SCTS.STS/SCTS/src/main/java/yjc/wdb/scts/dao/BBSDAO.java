package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.BBScttVO;
import yjc.wdb.scts.bean.EventVO;

public interface BBSDAO {
	
	
	public List<HashMap> viewCalendar() throws Exception;
	public void insertBBSctt(BBScttVO bbscttVO) throws Exception;
	public void insertEvent(EventVO eventVO) throws Exception;
}
