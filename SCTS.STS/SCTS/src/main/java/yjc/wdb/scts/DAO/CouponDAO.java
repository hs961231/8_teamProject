package yjc.wdb.scts.DAO;

import java.util.List;

import yjc.wdb.scts.bean.CouponBasketVO;
import yjc.wdb.scts.bean.CouponVO;

public interface CouponDAO {
	public List<CouponVO> couponList(String user_id) throws Exception;
	public List<CouponVO> listCoupon() throws Exception;
	public void delCouponBasket(CouponBasketVO couponBasketVO) throws Exception;
<<<<<<< HEAD
	public void regiCoupon(CouponVO couponVO) throws Exception;
	public void update(CouponVO couponVO) throws Exception;	
	public void delCoupon(int coupon_id) throws Exception;
}
=======
	public CouponVO selectTest() throws Exception;
}
>>>>>>> origin/master
