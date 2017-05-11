package yjc.wdb.scts.DAO;

import java.util.List;

import yjc.wdb.scts.bean.EventVO;

public interface EventDAO {
	
	public List<EventVO> eventList() throws Exception;
	public EventVO eventOne(String e_id) throws Exception;

}
