package yjc.wdb.scts;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import yjc.wdb.scts.bean.BBScttVO;
import yjc.wdb.scts.bean.BeaconVO;
import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.EventVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.bean.TileVO;
import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.service.BBSService;
import yjc.wdb.scts.service.BeaconService;
import yjc.wdb.scts.service.BillService;
import yjc.wdb.scts.service.CouponService;
import yjc.wdb.scts.service.CourseService;
import yjc.wdb.scts.service.Floor_informationService;
import yjc.wdb.scts.service.GoodsService;
import yjc.wdb.scts.service.TileService;
import yjc.wdb.scts.service.UserService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Inject
	private UserService userService;

	@Inject
	private TileService tileService;
	
	@Inject
	private CourseService courseService;
	
	@Inject
	private GoodsService goodsService;
	
	@Inject
	private CouponService couponService;
	
	@Inject
	private BBSService bbsService;
	
	@Inject
	private BeaconService beaconService;
	
	
	@Inject
	private Floor_informationService floor_informationService;

	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	/********************************* 로그인 관련 ***************************************/
	/********************************* 로그인 관련 ***************************************/
	// 처음 접속 시 표시해 주는 로그인 화면
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	// 회원가입
	@RequestMapping(value="/signUp", method=RequestMethod.GET)
	public String sineUp() {
		return "signUp";
	}

	// 로그인 요청 받는 부분
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String loginPost(UserVO user, 
							HttpServletRequest request, HttpSession session) throws Exception{
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
	
	// 초기 화면 표시용 메인 페이지
	@RequestMapping(value="mainPage", method=RequestMethod.GET)
	public String mainPage(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		// 메인 콘텐츠에서 어떤 페이지를 보여 줄 것인지 저장할 변수.
		String ContentPage = "dashBoard";

		// 실제 뷰 페이지로 메인 콘텐츠 페이지 정보를 넘겨준다.
		model.addAttribute("main_content", ContentPage);

		/*int todayCount = courseService.selectTodayVisitCnt();
		model.addAttribute("todayCount", todayCount);*/
		
		List<HashMap<String, String>> tileList = tileService.selectTileList();
		model.addAttribute("tileList", tileList);

		// 매장에 등록되어 있는 도면 모델에 저장시켜서 넘김
		int bhf_code = 1;	// 임시로 테스트 위해서 여기서 만들어줌
		int countStory = floor_informationService.selectCountStory(bhf_code);
		model.addAttribute("countStory", countStory);
		
		return "mainPage";
	}

	/********************************* 매장 관리 부분 ***************************************/
	/********************************* 매장 관리 부분 ***************************************/
	// 매장 등록 페이지
	@RequestMapping(value="shop_Register", method=RequestMethod.GET)
	public String shopRegister(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		// 메인 콘텐츠에서 어떤 페이지를 보여 줄 것인지 저장할 변수.
		String ContentPage = "shop_Register";

		// 실제 뷰 페이지로 메인 콘텐츠 페이지 정보를 넘겨준다.
		model.addAttribute("main_content", ContentPage);
		
		// 현재 이부분 수정해야함.
		List<HashMap<String, String>> tileList = tileService.selectTileListUp();
		model.addAttribute("tileList", tileList);

		int bhf_code = 1;	// 임시로 테스트 위해서 여기서 만들어줌
		List<BeaconVO> beaconList = beaconService.selectAllBeaconList(bhf_code);
		model.addAttribute("beaconList", beaconList);
		
		// 매장에 등록되어 있는 도면 모델에 저장시켜서 넘김
		int countStory = floor_informationService.selectCountStory(bhf_code);
		model.addAttribute("countStory", countStory);
		
		return "mainPage";
	}
	
	@RequestMapping(value="tile_RegisterForm", method=RequestMethod.GET)
	public String tile_RegisterForm(HttpServletRequest request, HttpSession session, Model model) {
		// 메인 콘텐츠에서 어떤 페이지를 보여 줄 것인지 저장할 변수.
		String ContentPage = "tile_RegisterForm";

		// 실제 뷰 페이지로 메인 콘텐츠 페이지 정보를 넘겨준다.
		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	@RequestMapping(value="tile_RegisterForm", method=RequestMethod.POST)
	public String tile_RegisterPost(HttpServletRequest request, HttpSession session, TileVO vo) throws Exception {
		
		tileService.insertTile(vo);
		
		logger.debug("타일 정보가 db에 등록 되었음." + vo.getTile_nm() );
		
		return "redirect:mainPage";
	}

	// 상품 리스트
	@RequestMapping(value="product_List", method=RequestMethod.GET)
	public String product_List(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		String ContentPage = "product_List";

		model.addAttribute("main_content", ContentPage);
		
		List<GoodsVO> GoodsList = goodsService.selectGoodsList();
	
		model.addAttribute("GoodsList", GoodsList);
		
		return "mainPage";
	}

	// 상품 등록 폼 호출
	@RequestMapping(value="product_Register", method=RequestMethod.GET)
	public String product_Register(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "product_Register";

		model.addAttribute("main_content", ContentPage);
		

		return "mainPage";
	}
	
	//상품등록 처리
	@RequestMapping(value="product_Register", method=RequestMethod.POST)
	public String product_RegisterPost(HttpServletRequest request, HttpSession session, GoodsVO vo) throws Exception{
		
		System.out.println("GoodsVO 정보 : " + vo.getDetailctgry_code() + "  상품명 : " + vo.getGoods_nm());
		
		goodsService.insertGoods(vo);
		

		return "redirect:mainPage";
	}
	
	// 상품 정보
	@RequestMapping(value="product_Info", method=RequestMethod.GET)
	public String product_Info(HttpServletRequest request, HttpSession session, Model model, @RequestParam int product_id) throws Exception {
		String ContentPage = "product_Info";

		model.addAttribute("main_content", ContentPage);

		GoodsVO goods = goodsService.selectGoodsOne(product_id);
		
		model.addAttribute("goods", goods);
		
		return "mainPage";
	}
	
	
	// 매출관리
	@RequestMapping(value="sales_Management", method=RequestMethod.GET)
	public String salesManagement(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "sales_Management";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	
	
	
	
	
	/////////////////////////////////////////////재고관리 //////////////////////////
	
	@RequestMapping(value="stock_Management", method=RequestMethod.GET)
	public String stockManagement(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		String ContentPage = "stock_Management";
		model.addAttribute("main_content", ContentPage);
		List<GoodsVO> GoodsList = goodsService.selectGoodsList();
		System.out.println(GoodsList);
		 model.addAttribute("list", GoodsList);
		return "mainPage";
	}
	
	
	@RequestMapping(value="searchStock", method=RequestMethod.POST)
	public String searchStock(HttpServletRequest request, HttpSession session, Model model, String goodsName) throws Exception{
		String ContentPage = "stock_Management";
		model.addAttribute("main_content", ContentPage);
		
		List<GoodsVO> GoodsList = goodsService.searchGoodsList(goodsName);
		System.out.println("뽑아온 리스트는 " +GoodsList);
		model.addAttribute("list", GoodsList);

		return "mainPage";
	}

	@RequestMapping(value="deleteStock", method=RequestMethod.POST)
	public String deleteStock(int goods_code)throws Exception{
		
		System.out.println("넘어온 번호 : " + goods_code);
		goodsService.deleteStock(goods_code);
		return "redirect:stock_Management";
	}
	
	/********************************* 이벤트 관리 부분 ***************************************/
	/********************************* 이벤트 관리 부분 ***************************************/
	@RequestMapping(value="event_Management", method=RequestMethod.GET)
	public String event(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "event_Management";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	
	
	/********************************* 쿠폰 관리 부분 ***************************************/
	/********************************* 쿠폰 관리 부분 ***************************************/

	@RequestMapping(value="insertCoupon", method=RequestMethod.POST)
	public String insertCoupon(CouponVO couponVO) throws Exception{
		
		System.out.println(couponVO);
		couponService.insertCoupon(couponVO);
		return "redirect:coupon_Management";
	}
	
	@RequestMapping(value="updateCoupon", method=RequestMethod.POST)
	public String modifyCoupon(CouponVO couponVO, int coupon_code) throws Exception{
		
		return "redirect:coupon_Management";
	}

	@RequestMapping(value="deleteCoupon", method=RequestMethod.POST)
	public String remove(int coupon_code)throws Exception{

		System.out.println("넘어온 쿠폰 코드는 " + coupon_code);
		couponService.deleteCoupon(coupon_code);

		return "redirect:coupon_Management";
	}


	@RequestMapping(value="coupon_Management", method=RequestMethod.GET)
	public String coupon(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		String ContentPage = "coupon_Management";

		model.addAttribute("main_content", ContentPage);
		
		List<CouponVO> Couponlist = couponService.selectCouponList();

		model.addAttribute("list", Couponlist);
		
		return "mainPage";
	}

	
	/********************************* User Profile ***************************************/
	/********************************* User Profile ***************************************/
	@RequestMapping(value="myProfile", method=RequestMethod.GET)
	public String myProfile(HttpServletRequest request, HttpSession session, Model model) {
		
		String ContentPage = "myProfile";
		
		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	/***************************************
	 * 2017_05_09
	 * 테스트용 페이지들
	 * @param model
	 * @return
	 * @throws Exception
	 */
	/*
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
*/
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

	@RequestMapping(value="list_product")
	public String list_product() {
		return "NiceAdmin/product_list";
	}

	@RequestMapping(value="info_product")
	public String info_product() {
		return "NiceAdmin/product_info";
	}

	

}
