package yjc.wdb.scts.service;

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
	
	
}
