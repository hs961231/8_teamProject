package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.TileVO;
import yjc.wdb.scts.bean.Tile_locationVO;
import yjc.wdb.scts.dao.TileDAO;

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

	@Override
	public List<HashMap<String, String>> selectTileListUp() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".selectTileListUp");
	}
	
	@Override
	public void insertTile(TileVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".insertTile", vo);
	}

	@Override
	public HashMap<String, String> selectTile_LocationOne(HashMap<String, String> Map_XY) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".selectTile_LocationOne", Map_XY);
	}

	@Override
	public void insertDrawingTile(List<TileVO> tileList) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".insertDrawingTile", tileList);
	}

	@Override
	public void insertTile_location(Tile_locationVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".insertTile_location", vo);
	}

	@Override
	public void updateTileBeaconSet(HashMap<String, String> vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE + ".updateTileBeaconSet", vo);
	}

	
}
