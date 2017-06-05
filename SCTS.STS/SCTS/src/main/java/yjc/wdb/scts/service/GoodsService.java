package yjc.wdb.scts.service;

import java.util.List;

import yjc.wdb.scts.bean.GoodsVO;

public interface GoodsService {
	public void insertGoods(GoodsVO vo) throws Exception;
	public List<GoodsVO> selectGoodsList() throws Exception;
	public List<GoodsVO> searchGoodsList(String goodsName) throws Exception;
}
