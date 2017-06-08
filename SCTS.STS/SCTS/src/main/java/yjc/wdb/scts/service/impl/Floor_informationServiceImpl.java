package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yjc.wdb.scts.bean.Floor_informationVO;
import yjc.wdb.scts.dao.Floor_informationDAO;
import yjc.wdb.scts.service.Floor_informationService;

@Service
public class Floor_informationServiceImpl implements Floor_informationService {
	
	@Inject
	private Floor_informationDAO dao;

	@Override
	@Transactional
	public void register_shop(String drw_flpth, Floor_informationVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.insertDrawing(drw_flpth);
		
		dao.insertFloor_information(vo);
	}

	@Override
	public List<HashMap<String, String>> selectDrawingList(int bhf_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectDrawingList(bhf_code);
	}

	@Override
	public int selectCountStory(int bhf_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCountStory(bhf_code);
	}

	@Override
	public HashMap<String, String> selectDrawingOne(int bhf_code, int floor) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectDrawingOne(bhf_code, floor);
	}

}
