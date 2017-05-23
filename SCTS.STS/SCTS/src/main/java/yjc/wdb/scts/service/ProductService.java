package yjc.wdb.scts.service;

import java.util.List;

import yjc.wdb.scts.bean.ProductVO;

public interface ProductService {
	public List<ProductVO> productList() throws Exception;
	public ProductVO productOne(int product_id) throws Exception;
}
