package yjc.wdb.scts.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.PositionVO;

@Repository
public class PositionDAOImpl implements PositionDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="yjc.wdb.mapper.positionmapper";
	
	@Override
	public void insertPosition(PositionVO vo) throws Exception {
		sqlSession.insert(NAMESPACE+".insertPosition", vo);
	}

	@Override
	public PositionVO selectPosition() throws Exception {
		return sqlSession.selectOne(NAMESPACE+".selectPosition");
	}

}
