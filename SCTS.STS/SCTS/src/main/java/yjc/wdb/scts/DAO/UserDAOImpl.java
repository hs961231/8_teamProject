package yjc.wdb.scts.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	private static String namespace ="yjc.wdb.scts.usermapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public int loginUser(UserVO user) throws Exception {
	
		return sqlSession.selectOne(namespace+".loginUser", user);
	}

	@Override
	public void registerUser(UserVO user) throws Exception {
		sqlSession.insert(namespace+".registerUser",user);
	}

	@Override
	public int checkUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".checkUser", id);
	}

}
