package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yjc.wdb.scts.bean.BBScttVO;
import yjc.wdb.scts.bean.EventVO;
import yjc.wdb.scts.dao.BBSDAO;
import yjc.wdb.scts.service.BBSService;

@Service
public class BBSServiceImpl implements BBSService {
	
	@Inject
	private BBSDAO dao;

	@Override
	public List<HashMap> viewCalendar() throws Exception {
		
		return dao.viewCalendar();
	}
	
	@Transactional
	@Override
	public void insertEvent(EventVO eventVO, BBScttVO bbscttVO) throws Exception {
		
		dao.insertBBSctt(bbscttVO);
		dao.insertEvent(eventVO);
	}

	@Override
	public List<HashMap> eventOne(int code) throws Exception {
		// TODO Auto-generated method stub
		return dao.eventOne(code);
	}

	
	@Transactional
	@Override
	public void updateEvent(EventVO eventVO, BBScttVO bbscttVO) throws Exception {
		dao.updateEvent(eventVO);
		dao.updateBbsctt(bbscttVO);
	}

	@Transactional
	@Override
	public void deleteEvent(int bbsctt_code) throws Exception {
	
		dao.deleteEvent(bbsctt_code);
		dao.deleteBbsctt(bbsctt_code);
		
	}

	@Override
	public List<HashMap> listEvent(String date1, String date2) throws Exception {
		return dao.listEvent(date1, date2);
	}

	@Override
	public void updateDropEvent(EventVO eventVO) throws Exception {
		dao.updateEvent(eventVO);
	}



}
