package yjc.wdb.scts.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.Loc_info;

@Repository 
public class Loc_infoDAOImpl implements Loc_InfoDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace ="yjc.wdb.Loc_infoMapper";

	@Override
	public void insertLog_info(Loc_info loc_info) throws Exception {
		sqlSession.insert(namespace+".insertLoc_info", loc_info);
	}

}
