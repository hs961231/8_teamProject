package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.Tile_locationVO;

public interface CourseService {
	public void insertCourse(HashMap<String, String> vo) throws Exception;
	public int selectTodayVisitCnt() throws Exception;
	public HashMap<String, String> tileProbability(Tile_locationVO vo) throws Exception;
	public List<HashMap<String, String>> tileUserinfo(Tile_locationVO vo) throws Exception;
}
