package yjc.wdb.scts.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.CouponBasketVO;
import yjc.wdb.scts.bean.CouponVO;

@Repository
public class CouponDAOImpl implements CouponDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.CouponMapper";
	
	@Inject
	private SqlSession sql;
	
	@Override
	public List<CouponVO> couponList(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".couponList", user_id);
	}

	@Override
	public void delCouponBasket(CouponBasketVO couponBasketVO) throws Exception {
		
		sql.delete(NAMESPACE+".delCouponBasket", couponBasketVO);

		
	}

	@Override
	public List<CouponVO> listCoupon() throws Exception {
	
		return sql.selectList(NAMESPACE+".listCoupon");
	}

	@Override
	public void regiCoupon(CouponVO couponVO) throws Exception {
		sql.insert(NAMESPACE+".insertCoupon", couponVO);
	}
	
	@Override
	public void update(CouponVO couponVO) throws Exception {
		sql.update(NAMESPACE+".updateCoupon", couponVO);
		
	}
	
	@Override
	public void delCoupon(int coupon_id) throws Exception {
		sql.delete(NAMESPACE+".deleteCoupon", coupon_id);
	}
	
	public CouponVO selectTest() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".selectTest");
	}

}
