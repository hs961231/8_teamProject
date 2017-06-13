package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.TileVO;
import yjc.wdb.scts.bean.Tile_locationVO;

public interface TileDAO {
	public List<HashMap<String, String>> selectTileList() throws Exception;	// 이전 버전
	public List<HashMap<String, String>> selectTileListUp() throws Exception; // 업글 버전
	public void insertTile(TileVO vo) throws Exception;
	public HashMap<String, String> selectTile_LocationOne(HashMap<String, String> Map_XY) throws Exception;
	public void insertDrawingTile(List<TileVO> tileList) throws Exception;	// 아직 구현 안됨
	public void insertTile_location(Tile_locationVO vo) throws Exception;	// Floor_informationService에 사용됨
	public void updateTileBeaconSet(HashMap<String, String> vo) throws Exception;
	
}
