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

}
