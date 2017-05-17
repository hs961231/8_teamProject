package yjc.wdb.scts.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.DAO.CouponDAO;
import yjc.wdb.scts.bean.CouponBasketVO;
import yjc.wdb.scts.bean.CouponVO;

@Service
public class CouponServiceImpl implements CouponService {
	
	@Inject
	private CouponDAO dao;

	@Override
	public List<CouponVO> couponList(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.couponList(user_id);
	}

	@Override
	public void delCouponBasket(CouponBasketVO couponBasketVO) throws Exception {
	
		dao.delCouponBasket(couponBasketVO);
		
	}

	@Override
	public List<CouponVO> listCoupon() throws Exception {

		return dao.listCoupon();
	}

	@Override
	public void regist(CouponVO couponVO) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void modify(CouponVO couponVO) throws Exception {
		
		dao.update(couponVO);
	}

	@Override
	public void remove(int coupon_id) throws Exception {
		dao.delCoupon(coupon_id);
		
	}

	public CouponVO selectTest() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectTest();
	}
}
