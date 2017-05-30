package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

public interface TileDAO {
	public List<HashMap<String, String>> selectTileList() throws Exception;

}
