package yjc.wdb.scts;


import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.service.UserService;
import yjc.wdb.scts.service.CouponService;
import yjc.wdb.scts.service.PositionService;
import yjc.wdb.scts.service.SalesService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private UserService userService;
	
	@Inject
	private PositionService positionService;
	
	@Inject
	private CouponService couponService;
	
	@Inject
	private SalesService salesService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	/********************************* 로그인 관련 ***************************************/
	/********************************* 로그인 관련 ***************************************/
	// 처음 접속 시 표시해 주는 로그인 화면
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	// 로그인 요청 받는 부분
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String loginPost(UserVO user, HttpServletRequest request, HttpSession session) throws Exception{
		int chk = userService.loginUser(user);
		
		if(chk == 0)
			return "error";
		else if(chk == 1) {
			session.setAttribute("user_id", user.getUser_id());
			return "success";
		}
		else
			return "error";
	}
	
	
	// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙, 占쎈쉬占쏙옙占쏙옙, 占싸곤옙 클占쏙옙占쏙옙 占쏙옙占쏙옙
	@RequestMapping(value="mainPage", method=RequestMethod.GET)
	public String mainPage(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		// 메인 콘텐츠에서 어떤 페이지를 보여 줄 것인지 저장할 변수.
		String ContentPage = "dashBoard";

		// 실제 뷰 페이지로 메인 콘텐츠 페이지 정보를 넘겨준다.
		model.addAttribute("main_content", ContentPage);
		
		
		int todayCount = positionService.todayCount();
		
		model.addAttribute("todayCount", todayCount);
		
		
		return "mainPage";
	}

	/********************************* 占쏙옙占쏙옙 占쏙옙占쏙옙 占쌨댐옙 ***************************************/
	/********************************* 占쏙옙占쏙옙 占쏙옙占쏙옙 占쌨댐옙 ***************************************/
	// 占쏙옙占쏙옙 占쏙옙占�
	@RequestMapping(value="shopRegister", method=RequestMethod.GET)
	public String shopRegister(HttpServletRequest request, HttpSession session, Model model) {
		// 메인 콘텐츠에서 어떤 페이지를 보여 줄 것인지 저장할 변수.
		String ContentPage = "shopRegister";

		// 실제 뷰 페이지로 메인 콘텐츠 페이지 정보를 넘겨준다.
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}

	// 占쏙옙품 占쏙옙占�
	@RequestMapping(value="productRegister", method=RequestMethod.GET)
	public String productRegister(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "productRegister";

		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	
	// 占쏙옙占쏙옙 占쏙옙占쏙옙
	@RequestMapping(value="salesManagement", method=RequestMethod.GET)
	public String salesManagement(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "salesManagement";
		
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	

	@RequestMapping(value="yearSales", method=RequestMethod.GET)
	public @ResponseBody String yearSales(HttpServletRequest request, int year) throws Exception{
	
	
		String callback = request.getParameter("callback");
		
		List<HashMap> list = salesService.yearSales(year);
		
		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		int i = 0;
		for(i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);
			
		}
		
		
		
		if(i == 2){
			salesJson = new JSONObject();
			salesJson.put("totalPrice", 0);
			salesArray.add(salesJson);
		}
		
		
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);
		
	
		
		return callback + "(" + jsonObject +")";
	}
	
	@RequestMapping(value="stockManagement", method=RequestMethod.GET)
	public String stockManagement(HttpServletRequest request, HttpSession session, Model model) {
		// 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏘떤 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙.
		String ContentPage = "stockManagement";
		
		// 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싼곤옙占쌔댐옙.
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}


	/********************************* 占싱븝옙트 占쏙옙占쏙옙 占쌨댐옙 ***************************************/
	/********************************* 占싱븝옙트 占쏙옙占쏙옙 占쌨댐옙 ***************************************/
	@RequestMapping(value="event", method=RequestMethod.GET)
	public String event(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "event";

		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}

	/********************************* 쿠폰 관리 부분 ***************************************/
	/********************************* 쿠폰 관리 부분 ***************************************/
	
/*	@RequestMapping(value="registCoupon", method=RequestMethod.POST)
	public String registPOST(CouponVO couponVO, Model model) throws Exception{
		logger.info("register post...");
		logger.info(couponVO.toString());
		
		couponService.regist(couponVO);
		
		model.addAttribute("result", "success");
		return "mainPage";
	}
	*/
	
	@RequestMapping(value="remove", method=RequestMethod.POST)
	public String remove(@RequestParam int coupon_id)throws Exception{/*
		System.out.println("remove�떎");
		couponService.remove(coupon_id);*/
		
		System.out.println(coupon_id);
		return "mainPage";
	}
	
	
	@RequestMapping(value="coupon", method=RequestMethod.GET)
	public String coupon(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		String ContentPage = "coupon";

		model.addAttribute("main_content", ContentPage);
		model.addAttribute("list",couponService.listCoupon());
		
		return "mainPage";
	}

	
	/********************************* 포스 시스템 부분 ***************************************/
	/********************************* 포스 시스템 부분 ***************************************/
	@RequestMapping(value="posSystem", method=RequestMethod.GET)
	public String posSystem(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "posSystem";

		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	
	@RequestMapping(value="avgStayTest")
	public String avgStayTest(Model model) throws Exception{
		List<HashMap<String, String>> list = positionService.avgStay();
		logger.debug(list.toString());
		model.addAttribute("list", list);
		return "test/avgStayTest";
	}

	@RequestMapping(value="visit_countTest")
	public String visit_countTest(Model model) throws Exception{
		List<HashMap<String, String>> list = positionService.visit_count();
		logger.debug(list.toString());
		model.addAttribute("list", list);
		return "test/visit_countTest";
	}

	@RequestMapping(value="probabilityTest")
	public String probabilityTest(Model model) throws Exception{
		List<HashMap<String, String>> list = positionService.probability();
		logger.debug(list.toString());
		model.addAttribute("list", list);
		return "test/probabilityTest";
	}

	/****************************** 예지쓰 *************************************/
	/****************************** 예지쓰 *************************************/

	@RequestMapping(value="widgets")
	public String widgets() {
		return "NiceAdmin/widgets";
	}
	
	@RequestMapping(value="404")
	public String errorPage() {
		return "NiceAdmin/404";
	}
	
	@RequestMapping(value="register_shop")
	public String register_shop() {
		return "NiceAdmin/register_shop";
	}
	
	@RequestMapping(value="register_shopForm")
	public String register_shopForm() {
		return "NiceAdmin/register_shopForm";
	}
	
	@RequestMapping(value="form")
	public String form(){
		return "NiceAdmin/form";
		
	}
	
	@RequestMapping(value="register_tileInfo")
	public String register_tileInfo() {
		return "NiceAdmin/register_tileInfo";
	}
	
	@RequestMapping(value="register_product")
	public String register_product() {
		return "NiceAdmin/register_product";
	}
	
	@RequestMapping(value="product_list")
	public String product_list() {
		return "NiceAdmin/product_list";
	}
	
	@RequestMapping(value="product_info")
	public String product_info() {
		return "NiceAdmin/product_info";
	}
	
	
}
