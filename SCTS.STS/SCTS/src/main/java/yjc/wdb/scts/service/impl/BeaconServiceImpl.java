package yjc.wdb.scts.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.BeaconVO;
import yjc.wdb.scts.dao.BeaconDAO;
import yjc.wdb.scts.service.BeaconService;

@Service
public class BeaconServiceImpl implements BeaconService {
	
	@Inject
	private BeaconDAO dao;

	@Override
	public List<BeaconVO> selectBeaconList(int bhf_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectBeaconList(bhf_code);
	}

}
