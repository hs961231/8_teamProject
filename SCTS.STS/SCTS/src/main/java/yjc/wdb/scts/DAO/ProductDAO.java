package yjc.wdb.scts.DAO;

import java.util.List;

import yjc.wdb.scts.bean.ProductVO;

public interface ProductDAO {
	public List<ProductVO> productList() throws Exception;
	public ProductVO productOne(int product_id) throws Exception;
}
