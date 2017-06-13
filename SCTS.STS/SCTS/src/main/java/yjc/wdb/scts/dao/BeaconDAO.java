package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.BeaconVO;

public interface BeaconDAO {
	public List<BeaconVO> selectBeaconList(int bhf_code) throws Exception;
	public void updateBeaconSttus(HashMap<String, String> vo) throws Exception;
}
