package yjc.wdb.scts.service;

import java.util.List;

import yjc.wdb.scts.bean.CouponBasketVO;
import yjc.wdb.scts.bean.CouponVO;

public interface CouponService {
	public List<CouponVO> couponList(String user_id) throws Exception;
	public void delCouponBasket(CouponBasketVO couponBasketVO) throws Exception;
}
