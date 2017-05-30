package yjc.wdb.scts.asd.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.asd.GoodsDAO;
import yjc.wdb.scts.bean.GoodsVO;

@Repository
public class GoodsDAOImpl implements GoodsDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.GoodsMapper";

	@Inject
	private SqlSession sqlSession;

	@Override
	public void insertGoods(GoodsVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".insertGoods", vo);
	}

	@Override
	public List<GoodsVO> selectGoodsList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".selectGoodsList");
	}

}
