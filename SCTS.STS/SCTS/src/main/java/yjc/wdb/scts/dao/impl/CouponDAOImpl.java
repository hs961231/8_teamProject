package yjc.wdb.scts.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.dao.CouponDAO;


@Repository
public class CouponDAOImpl implements CouponDAO{
	
	private static final String NAMESPACE = "yjc.wdb.mapper.CouponMapper";
	
	@Inject
	private SqlSession sql;


	@Override
	public void insertCoupon(CouponVO couponVO) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(NAMESPACE+".insertCoupon", couponVO);
	}
	
	@Override
	public List<CouponVO> couponBasket(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".couponList", user_id);
	}

	@Override
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception {
		
		sql.delete(NAMESPACE+".delCouponBasket", coupon_holdVO);
		
	}

	@Override
	public List<CouponVO> selectCouponList() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".selectCouponList");
	}

	@Override
	public CouponVO selectSendAndroidCoupon() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".selectSendAndroidCoupon");
	}
	
	@Override
	public void updateCoupon(CouponVO couponVO) throws Exception {
		// TODO Auto-generated method stub
		sql.update(NAMESPACE+".updateCoupon" , couponVO);
	}

	@Override
	public void deleteCoupon(int coupon_code) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(NAMESPACE+".deleteCoupon", coupon_code);
	}

	@Override
	public CouponVO selectCouponOne(int coupon_code) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".selectCouponOne", coupon_code);
	}
}
