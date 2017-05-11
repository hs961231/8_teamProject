package yjc.wdb.scts.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.DAO.EventDAO;
import yjc.wdb.scts.bean.EventVO;

@Service
public class EventServiceImpl implements EventService {
	
	@Inject
	private EventDAO dao;

	@Override
	public List<EventVO> eventList() throws Exception {
		// TODO Auto-generated method stub
		return dao.eventList();
	}

	@Override
	public EventVO eventOne(String e_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.eventOne(e_id);
	}

}
