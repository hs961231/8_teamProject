package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.dao.BBSDAO;

@Repository
public class BBSDAOImpl implements BBSDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.BBSMapper";

	@Inject
	private SqlSession sql;
	
	@Override
	public List<HashMap> viewCalendar() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".viewCalendar");
	}

}
