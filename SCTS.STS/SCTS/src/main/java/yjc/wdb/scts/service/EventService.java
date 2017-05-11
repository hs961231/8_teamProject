package yjc.wdb.scts.service;

import java.util.List;

import yjc.wdb.scts.bean.EventVO;

public interface EventService {

	public List<EventVO> eventList() throws Exception;
	public EventVO eventOne(String e_id) throws Exception;
}
