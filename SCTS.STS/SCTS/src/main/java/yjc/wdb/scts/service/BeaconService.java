package yjc.wdb.scts.service;

import java.util.List;

import yjc.wdb.scts.bean.BeaconVO;

public interface BeaconService {
	public List<BeaconVO> selectBeaconList(int bhf_code) throws Exception;
}
