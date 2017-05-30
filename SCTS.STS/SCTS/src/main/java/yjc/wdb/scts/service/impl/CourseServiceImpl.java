package yjc.wdb.scts.service.impl;

import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.dao.CourseDAO;
import yjc.wdb.scts.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Inject
	private CourseDAO dao;

	@Override
	public int selectTodayVisitCnt() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectTodayVisitCnt();
	}

	@Override
	public void insertCourse(HashMap<String, String> vo) throws Exception {
		// TODO Auto-generated method stub
		dao.insertCourse(vo);
	}

}
