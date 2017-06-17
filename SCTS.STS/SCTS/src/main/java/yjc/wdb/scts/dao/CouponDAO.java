package yjc.wdb.scts.dao;

import java.util.List;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;

public interface CouponDAO { 
	public List<CouponVO> selectCouponList() throws Exception;
	public void insertCoupon(CouponVO couponVO) throws Exception;
	public void updateCoupon(CouponVO couponVO) throws Exception;
	public void deleteCoupon(int coupon_code) throws Exception;
}
