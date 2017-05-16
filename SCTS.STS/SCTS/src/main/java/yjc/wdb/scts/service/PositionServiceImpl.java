package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.DAO.PositionDAO;
import yjc.wdb.scts.DAO.PositionDAOImpl;
import yjc.wdb.scts.bean.PositionVO;

@Service
public class PositionServiceImpl implements PositionService{

	@Inject
	PositionDAO dao;

	@Override
	public void insertPosition(PositionVO vo) throws Exception {
		dao.insertPosition(vo);
	}

	@Override
	public PositionVO selectPosition() throws Exception {
		return dao.selectPosition();
	}
	

	@Override
	public List<HashMap<String, String>> avgStay() throws Exception {
		// TODO Auto-generated method stub
		return dao.avgStay();
	}

	@Override
	public List<HashMap<String, String>> visit_count() throws Exception {
		// TODO Auto-generated method stub
		return dao.visit_count();
	}

	@Override
	public List<HashMap<String, String>> probability() throws Exception {
		// TODO Auto-generated method stub
		return dao.probability();
	}

	@Override
	public int todayCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.todayCount();
	}
}
