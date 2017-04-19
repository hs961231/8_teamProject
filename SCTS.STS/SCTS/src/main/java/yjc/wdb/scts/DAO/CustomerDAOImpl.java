package yjc.wdb.scts.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private static String namespace ="yjc.wdb.scts.CustomerMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public int checkCustomer(Customer customer) throws Exception {
	
		return sqlSession.selectOne(namespace+".checkCustomer", customer);
	}

}
