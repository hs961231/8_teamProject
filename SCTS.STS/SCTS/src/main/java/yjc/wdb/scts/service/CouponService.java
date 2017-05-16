package yjc.wdb.scts.service;

import java.util.List;

import yjc.wdb.scts.bean.CouponBasketVO;
import yjc.wdb.scts.bean.CouponVO;

public interface CouponService {
	public List<CouponVO> couponList(String user_id) throws Exception;
	public List<CouponVO> listCoupon() throws Exception;
	public void delCouponBasket(CouponBasketVO couponBasketVO) throws Exception;
<<<<<<< HEAD
	public void regist(CouponVO couponVO) throws Exception;
	public void modify(CouponVO couponVO) throws Exception;
	public void remove(int coupon_id) throws Exception;
=======
	public CouponVO selectTest() throws Exception;
>>>>>>> origin/master
}
