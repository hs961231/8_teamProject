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
	public void insertCoupon(CouponVO couponVO) throws Exception {
		// TODO Auto-generated method stub
		dao.insertCoupon(couponVO);
	}

	

	@Override
	public List<CouponVO> selectCouponList() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCouponList();
	}
	
	@Override
	public void updateCoupon(CouponVO couponVO) throws Exception {
		// TODO Auto-generated method stub
		dao.updateCoupon(couponVO);
	}

	@Override
	public void deleteCoupon(int coupon_code) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteCoupon(coupon_code);
	}


}
