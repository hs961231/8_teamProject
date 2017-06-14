package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.Tile_locationVO;
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

	@Override
	public HashMap<String, String> tileProbability(Tile_locationVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.tileProbability(vo);
	}

	@Override
	public List<HashMap<String, String>> tileUserinfo(Tile_locationVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.tileUserinfo(vo);
	}

	@Override
	public void updateStayTime(HashMap<String, String> vo) throws Exception {
		// TODO Auto-generated method stub
		dao.updateStayTime(vo);
	}

}
