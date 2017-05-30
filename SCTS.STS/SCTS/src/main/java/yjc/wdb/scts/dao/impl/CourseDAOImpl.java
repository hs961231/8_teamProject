package yjc.wdb.scts.dao.impl;

import java.util.HashMap;

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

	@Override
	public void insertCourse(HashMap<String, String> vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".insertCourse", vo);
	}

}
