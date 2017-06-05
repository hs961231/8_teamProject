package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.BBScttVO;
import yjc.wdb.scts.bean.EventVO;
import yjc.wdb.scts.dao.BBSDAO;

@Repository
public class BBSDAOImpl implements BBSDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.BBSMapper";

	@Inject
	private SqlSession sql;
	
	@Override
	public List<HashMap> viewCalendar() throws Exception {
		
		return sql.selectList(NAMESPACE+".viewCalendar");
	}

	@Override
	public void insertBBSctt(BBScttVO bbscttVO) throws Exception {
		
		sql.insert(NAMESPACE+".bbscttInsert", bbscttVO);
	}

	@Override
	public void insertEvent(EventVO eventVO) throws Exception {
		sql.insert(NAMESPACE+".eventInsert", eventVO);
	}

}
