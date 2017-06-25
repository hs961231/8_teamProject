package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import yjc.wdb.scts.bean.TileVO;

public interface CourseService {
	public void insertCourse(HashMap<String, String> vo) throws Exception;
	public int selectTodayVisitCnt() throws Exception;
	public HashMap<String, String> tileProbability(TileVO vo) throws Exception;
	public List<HashMap<String, String>> tileUserinfo(TileVO vo) throws Exception;
	public void updateStayTime(HashMap<String, String> vo) throws Exception;
	public int monthAvgVisitor() throws Exception;
	public List<HashMap> tileGender(int day) throws Exception;
	public List<HashMap> tileAge(int day) throws Exception;
	public List<HashMap> oneTileAvgTime(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	public List<HashMap> oneTileGender(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	public List<HashMap> oneTileAge(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception;
	public List<HashMap> tileTotal(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y)  throws Exception;
}
