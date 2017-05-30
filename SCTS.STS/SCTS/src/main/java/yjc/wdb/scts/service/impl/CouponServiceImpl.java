package yjc.wdb.scts.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;
import yjc.wdb.scts.dao.CouponDAO;
import yjc.wdb.scts.service.CouponService;


@Service
public class CouponServiceImpl implements CouponService {
	
	@Inject
	private CouponDAO dao;

	@Override
	public List<CouponVO> couponBasket(String user_id) throws Exception {
		
		return dao.couponBasket(user_id);
	}

	@Override
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception {
		
		dao.delCouponBasket(coupon_holdVO);
		
	}

	@Override
	public List<CouponVO> selectCouponList() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCouponList();
	}

	@Override
	public CouponVO selectSendAndroidCoupon() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectSendAndroidCoupon();
	}

}
