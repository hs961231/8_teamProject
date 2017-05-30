package yjc.wdb.scts.asd.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.asd.TileDAO;

@Repository
public class TileDAOImpl implements TileDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.TileMapper";

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<HashMap<String, String>> selectTileList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".selectTileList");
	}
	
}
