package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.BillVO;
import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.dao.AndroidDAO;
import yjc.wdb.scts.service.AndroidService;

@Service
public class AndroidServiceImpl implements AndroidService{
	
	@Inject
	private AndroidDAO dao;
	
	@Override
	public List<BillVO> billList(String user_id, int day) throws Exception {
		
		return dao.billList(user_id, day);
	}

	@Override
	public List<HashMap> billOne(int bill_code) throws Exception {
		
		return dao.billOne(bill_code);
	}

	@Override
	public List<HashMap> settleInfo(String user_id, int bill_code) throws Exception {
		
		return dao.settleInfo(user_id, bill_code);
	}

	@Override
	public List<HashMap> recommandProduct(String user_id) throws Exception {
		
		return dao.recommandProduct(user_id);
	}
	
	
	@Override
	public List<CouponVO> couponBasket(String user_id) throws Exception {
		
		return dao.couponBasket(user_id);
	}

	@Override
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception {
		
		dao.delCouponBasket(coupon_holdVO);
		
	}
	
	@Override
	public CouponVO selectSendAndroidCoupon() throws Exception {
		return dao.selectSendAndroidCoupon();
	}

	@Override
	public List<GoodsVO> productSearch(String productName) throws Exception {
		
		return dao.productSearch(productName);
	}

	@Override
	public List<HashMap> eventList() throws Exception {
		// TODO Auto-generated method stub
		return dao.eventList();
	}

	@Override
	public List<HashMap> eventOne(int bbsctt) throws Exception {
		// TODO Auto-generated method stub
		return dao.eventOne(bbsctt);
	}
	
	


}
