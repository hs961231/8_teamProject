package yjc.wdb.scts.dao;

import java.util.List;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;

public interface CouponDAO {
	public List<CouponVO> couponBasket(String user_id) throws Exception ;
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception;
}
