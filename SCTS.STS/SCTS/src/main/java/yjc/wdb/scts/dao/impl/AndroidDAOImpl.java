package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.BillVO;
import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.dao.AndroidDAO;


@Repository
public class AndroidDAOImpl implements AndroidDAO{
	
	private static final String NAMESPACE = "yjc.wdb.mapper.AndroidMapper";
	
	@Inject
	private SqlSession sql;
	
	@Override
	public List<BillVO> billList(String user_id, int day) throws Exception {
		
		Map map = new HashMap();
		map.put("user_id", user_id);
		map.put("day", day);
		
		return sql.selectList(NAMESPACE + ".billList", map);
	}

	@Override
	public List<HashMap> billOne(int bill_code) throws Exception {
		
		return sql.selectList(NAMESPACE+".billOne", bill_code);
	}

	@Override
	public List<HashMap> settleInfo(String user_id, int bill_code) throws Exception {
		
		Map map = new HashMap();
		map.put("user_id", user_id);
		map.put("bill_code", bill_code);
		
		return sql.selectList(NAMESPACE+".settleInfo", map);
	}

	@Override
	public List<HashMap> recommandProduct(String user_id) throws Exception {
		
		return sql.selectList(NAMESPACE+".recommandProduct", user_id);
	}
	
	
	@Override
	public List<CouponVO> couponBasket(String user_id) throws Exception {
		
		return sql.selectList(NAMESPACE+".couponList", user_id);
	}

	@Override
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception {
		
		sql.delete(NAMESPACE+".delCouponBasket", coupon_holdVO);
		
	}
	
	@Override
	public CouponVO selectSendAndroidCoupon() throws Exception {
	
		return sql.selectOne(NAMESPACE+".selectSendAndroidCoupon");
	}

	@Override
	public List<GoodsVO> productSearch(String productName) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".productSearch", productName);
	}

	@Override
	public List<HashMap> eventList() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".eventList");
	}

	@Override
	public List<HashMap> eventOne(int bbsctt) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".eventOne", bbsctt);
		
		
	}

	@Override
	public void insertCoupon_hold(Coupon_holdVO coupon_holdVO) throws Exception {
		sql.insert(NAMESPACE+".inserCoupon_hold", coupon_holdVO);
	}

}
