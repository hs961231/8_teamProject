package yjc.wdb.scts.asd.impl;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.asd.UserDAO;
import yjc.wdb.scts.bean.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

private static String namespace ="yjc.wdb.mapper.UserMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	// �α���
	@Override
	public int loginUser(UserVO user) throws Exception {
	
		return sqlSession.selectOne(namespace+".loginUser", user);
	}

	// ȸ������
	@Override
	public void insertUser(UserVO user) throws Exception {
		sqlSession.insert(namespace+".insertUser",user);
	}

	@Override
	public int checkUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".checkUser", id);
	}
	
	
	// ����Ʈ
	@Override
	public int point(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".point", user_id);
	}

}
