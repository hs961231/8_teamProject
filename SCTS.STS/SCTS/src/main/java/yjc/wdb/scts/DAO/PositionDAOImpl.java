package yjc.wdb.scts.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.PositionVO;

@Repository
public class PositionDAOImpl implements PositionDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="yjc.wdb.mapper.PositionMapper";
	
	@Override
	public void insertPosition(PositionVO vo) throws Exception {
		sqlSession.insert(NAMESPACE+".insertPosition", vo);
	}

	@Override
	public PositionVO selectPosition() throws Exception {
		return sqlSession.selectOne(NAMESPACE+".selectPosition");
	}

	@Override
	public List<HashMap<String, String>> avgStay() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+".avgStay");
	}

	@Override
	public List<HashMap<String, String>> visit_count() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+".visit_count");
	}

	@Override
	public List<HashMap<String, String>> probability() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+".probability");
	}

}
