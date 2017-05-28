package yjc.wdb.scts.dao.impl;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.dao.CourseDAO;

@Repository
public class CourseDAOImpl implements CourseDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.CourseMapper";

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public int selectTodayVisitCnt() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".selectTodayVisitCnt");
	}

}
