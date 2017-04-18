package yjc.wdb.scts.DAO;

import java.util.List;

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

	@Override
	public List<Loc_info> Loc_info_List() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".Loc_info_List");
	}

}
