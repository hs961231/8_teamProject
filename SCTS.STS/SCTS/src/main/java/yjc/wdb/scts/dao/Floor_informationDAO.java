package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.Floor_informationVO;

public interface Floor_informationDAO {
	public void insertDrawing(String drw_flpth) throws Exception;
	public void insertFloor_information(Floor_informationVO vo) throws Exception;
	public List<HashMap<String, String>> selectDrawingList(int bhf_code) throws Exception;
}
