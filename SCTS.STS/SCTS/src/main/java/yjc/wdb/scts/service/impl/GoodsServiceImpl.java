package yjc.wdb.scts.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.asd.GoodsDAO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Inject
	private GoodsDAO dao;
	
	@Override
	public void insertGoods(GoodsVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.insertGoods(vo);
	}

	@Override
	public List<GoodsVO> selectGoodsList() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectGoodsList();
	}

}
