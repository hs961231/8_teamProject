package yjc.wdb.scts.dao;

import java.util.List;

import yjc.wdb.scts.bean.GoodsVO;

public interface GoodsDAO {
	public void insertGoods(GoodsVO vo) throws Exception;
	public List<GoodsVO> selectGoodsList() throws Exception;
}
