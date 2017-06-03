package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.TileVO;

public interface TileDAO {
	public List<HashMap<String, String>> selectTileList() throws Exception;
	public void insertTile(TileVO vo) throws Exception;

}
