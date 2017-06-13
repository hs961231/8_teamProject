package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<HashMap> eventOne(int code) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".eventOne", code);
	}

	@Override
	public void updateEvent(EventVO eventVO) throws Exception {
		
		sql.selectList(NAMESPACE+".updateEvent", eventVO);
		
	}

	@Override
	public void updateBbsctt(BBScttVO bbscttVO) throws Exception {
		
		sql.selectList(NAMESPACE+".updateBbsctt", bbscttVO);
	}

	@Override
	public void deleteEvent(int bbsctt_code) throws Exception {
		sql.selectList(NAMESPACE+".deleteEvent", bbsctt_code);
	}

	@Override
	public void deleteBbsctt(int bbsctt_code) throws Exception {
		sql.selectList(NAMESPACE+".deleteBbsctt", bbsctt_code);
	}

	@Override
	public List<HashMap> listEvent(String date1, String date2) throws Exception {
		Map map = new HashMap();
		map.put("date1", date1);
		map.put("date2", date2);
		
		return sql.selectList(NAMESPACE+".listEvent", map);
	}

}
