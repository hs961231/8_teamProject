package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.dao.BBSDAO;
import yjc.wdb.scts.service.BBSService;

@Service
public class BBSServiceImpl implements BBSService {
	
	@Inject
	private BBSDAO dao;

	@Override
	public List<HashMap> viewCalendar() throws Exception {
		// TODO Auto-generated method stub
		return dao.viewCalendar();
	}

}
