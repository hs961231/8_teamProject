package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.TileVO;

public interface CourseDAO {
	public void insertCourse(HashMap<String, String> vo) throws Exception;
	public int selectTodayVisitCnt() throws Exception;
	public HashMap<String, String> tileProbability(TileVO vo) throws Exception;
	public List<HashMap<String, String>> tileUserinfo(TileVO vo) throws Exception;
	public void updateStayTime(HashMap<String, String> vo) throws Exception;
	public int monthAvgVisitor() throws Exception;
	public List<HashMap> tileGender(int day) throws Exception;
	
}
