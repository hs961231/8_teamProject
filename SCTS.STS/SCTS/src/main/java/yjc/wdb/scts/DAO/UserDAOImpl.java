package yjc.wdb.scts.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private static String namespace ="yjc.wdb.scts.UserMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public int checkCustomer(User customer) throws Exception {
	
		return sqlSession.selectOne(namespace+".checkCustomer", customer);
	}

}
