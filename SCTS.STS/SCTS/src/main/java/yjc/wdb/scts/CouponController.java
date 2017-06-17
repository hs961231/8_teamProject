package yjc.wdb.scts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.service.CouponService;

@Controller
public class CouponController {
	
	@Inject
	private CouponService couponService;
	
	/********************************** 쿠폰 관리 부분***************************************/
	/********************************** 쿠폰 관리 부분***************************************/

	@RequestMapping(value = "insertCoupon", method = RequestMethod.POST)
	public String insertCoupon(CouponVO couponVO, Model model) throws Exception {
		
		String ContentPage = "coupon_Management";
		model.addAttribute("main_content", ContentPage);
		
		System.out.println(couponVO.getCoupon_cntnts());
		couponService.insertCoupon(couponVO);
		
		return "redirect:coupon_Management";
	}
	
	@RequestMapping(value = "deleteCoupon", method = RequestMethod.POST)
	public String remove(int coupon_code) throws Exception {

		System.out.println("넘어온 쿠폰 코드는 " + coupon_code);
		couponService.deleteCoupon(coupon_code);

		return "redirect:coupon_Management";
	}

	@RequestMapping(value = "coupon_Management", method = RequestMethod.GET)
	public String coupon(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		String ContentPage = "coupon_Management";
		model.addAttribute("main_content", ContentPage);

		List<CouponVO> Couponlist = couponService.selectCouponList();
		model.addAttribute("list", Couponlist);
		
		return "mainPage";
	}
	
	@RequestMapping(value="coupon_modify", method=RequestMethod.POST)
	public String couponModify(HttpServletRequest request, HttpSession session, Model model, int coupon_code) throws Exception{
		
		String ContentPage = "coupon_Modify";
		model.addAttribute("main_content", ContentPage);
		
		CouponVO coupon = couponService.selectCouponOne(coupon_code);
		model.addAttribute("coupon",coupon);
		
		return "mainPage";
		
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public String modify(HttpServletRequest request, Model model, CouponVO couponVO) throws Exception{
		
		String ContentPage = "coupon_Management";
		model.addAttribute("main_content", ContentPage);
		
		couponService.updateCoupon(couponVO);
		
		return "redirect:coupon_Management";
		
	}

}
