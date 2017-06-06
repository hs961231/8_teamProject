package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.Floor_informationVO;

public interface Floor_informationService {
	public void register_shop(String drw_flpth, Floor_informationVO vo) throws Exception;
	public List<HashMap<String, String>> selectDrawingList(int bhf_code) throws Exception;
}
