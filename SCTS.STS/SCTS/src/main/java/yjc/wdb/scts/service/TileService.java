package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.TileVO;

public interface TileService {
	public List<HashMap<String, String>> selectTileList() throws Exception;
	public void insertTile(TileVO vo) throws Exception;

}
