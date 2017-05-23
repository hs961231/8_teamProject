package yjc.wdb.scts.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	
	private static final String NAMESPACE = "yjc.wdb.mapper.ProductMapper";
	
	@Inject
	private SqlSession sql;
	
	@Override
	public List<ProductVO> productList() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".productList");
	}

	@Override
	public ProductVO productOne(int product_id) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".productOne", product_id);
	}

}
