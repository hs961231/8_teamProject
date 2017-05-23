package yjc.wdb.scts.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.DAO.ProductDAO;
import yjc.wdb.scts.bean.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	ProductDAO dao;
	
	@Override
	public List<ProductVO> productList() throws Exception {
		// TODO Auto-generated method stub
		return dao.productList();
	}

	@Override
	public ProductVO productOne(int product_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.productOne(product_id);
	}

}
