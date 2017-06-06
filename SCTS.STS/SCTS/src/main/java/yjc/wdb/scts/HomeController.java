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

import yjc.wdb.scts.bean.BBScttVO;
import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.EventVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.bean.TileVO;
import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.service.BBSService;
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
	private BillService billService;
	
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

		int todayCount = courseService.selectTodayVisitCnt();
		model.addAttribute("todayCount", todayCount);
		
		List<HashMap<String, String>> tileList = tileService.selectTileList();
		model.addAttribute("tileList", tileList);
		
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
		
		List<HashMap<String, String>> tileList = tileService.selectTileList();
		model.addAttribute("tileList", tileList);
		
		// 매장에 등록되어 있는 도면 모델에 저장시켜서 넘김
		int bhf_code = 1;	// 임시로 테스트 위해서 여기서 만들어줌
		List<HashMap<String, String>> drawingList = floor_informationService.selectDrawingList(bhf_code);
		model.addAttribute("drawingList", drawingList);

		return "mainPage";
	}
	
	@RequestMapping(value="shop_RegisterForm", method=RequestMethod.GET)
	public String shop_RegisterForm(HttpServletRequest request, HttpSession session, Model model) {
		// 메인 콘텐츠에서 어떤 페이지를 보여 줄 것인지 저장할 변수.
		String ContentPage = "shop_RegisterForm";

		// 실제 뷰 페이지로 메인 콘텐츠 페이지 정보를 넘겨준다.
		model.addAttribute("main_content", ContentPage);

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

		/*ProductVO product = productService.productOne(product_id);
		
		model.addAttribute("product", product);*/
		
		return "mainPage";
	}
	
	
	// 매출관리
	@RequestMapping(value="sales_Management", method=RequestMethod.GET)
	public String salesManagement(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "sales_Management";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	
	@RequestMapping(value="yearSales", method=RequestMethod.GET)
	public @ResponseBody String yearSales(HttpServletRequest request, int year) throws Exception{


		String callback = request.getParameter("callback");

		List<HashMap> list = billService.yearSales(year);

		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("year", list.get(i).get("year"));
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);

		}
		

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);

		return callback + "(" + jsonObject +")";
	}
	
	
	@RequestMapping(value="searchYear", method=RequestMethod.GET)
	public @ResponseBody String searchYear(HttpServletRequest request, int year1, int year2) throws Exception{


		String callback = request.getParameter("callback");

		List<HashMap> list = billService.searchYear(year1, year2);
		
		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("year", list.get(i).get("year"));
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);

		}
		

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);

		return callback + "(" + jsonObject +")";
	}
	
	
	@RequestMapping(value="settleSalesInfo", method=RequestMethod.GET)
	public @ResponseBody String settleSalesInfo(HttpServletRequest request, int year1, int year2) throws Exception{


		String callback = request.getParameter("callback");

		List<HashMap> list = billService.settleSalesInfo(year1, year2);

		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("year", list.get(i).get("year"));
			salesJson.put("setle_mth_nm", list.get(i).get("setle_mth_nm"));
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);

		}
		

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);

		return callback + "(" + jsonObject +")";
	}
	
	
	

	@RequestMapping(value="daySales", method=RequestMethod.GET)
	public @ResponseBody String daySales(HttpServletRequest request) throws Exception{


		String callback = request.getParameter("callback");

		List<HashMap> list = billService.daySales();

		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("bill_issu_de", list.get(i).get("bill_issu_de").toString());
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);

		return callback + "(" + jsonObject +")";
	}
	

	
	@RequestMapping(value="daySettle", method=RequestMethod.GET)
	public @ResponseBody String daySettle(HttpServletRequest request, Date date1, Date date2, int setle_mth_code) throws Exception{


		String callback = request.getParameter("callback");

		List<HashMap> list = billService.daySettle(date1, date2, setle_mth_code);
		
		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("year", list.get(i).get("year").toString());
			salesJson.put("setle_mth_nm", list.get(i).get("setle_mth_nm"));
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);

		}
		

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);

		return callback + "(" + jsonObject +")";
	}
	
	@RequestMapping(value="searchDaySales", method=RequestMethod.GET)
	public @ResponseBody String searchDaySales(HttpServletRequest request, Date date1, Date date2) throws Exception{


		String callback = request.getParameter("callback");

		List<HashMap> list = billService.searchDaySales(date1, date2);

		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("bill_issu_de", list.get(i).get("bill_issu_de").toString());
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);

		return callback + "(" + jsonObject +")";
	}
	

	
	@RequestMapping(value="daySalesSettleInfo", method=RequestMethod.GET)
	public @ResponseBody String daySalesSettleInfo(HttpServletRequest request) throws Exception{


		String callback = request.getParameter("callback");

		List<HashMap> list = billService.daySalesSettleInfo();
		
		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("year", list.get(i).get("year").toString());
			salesJson.put("setle_mth_nm", list.get(i).get("setle_mth_nm"));
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);

		}
		

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);

		return callback + "(" + jsonObject +")";
	}
	
	/*@RequestMapping(value="monthSales", method=RequestMethod.GET)
	public @ResponseBody String monthSales(HttpServletRequest request, String month1, String month2) throws Exception{


		String callback = request.getParameter("callback");

		List<HashMap> list = billService.monthSales(month1, month2);

		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("bill_issu_de", list.get(i).get("bill_issu_de").toString());
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);

		return callback + "(" + jsonObject +")";
	}
	

	
	@RequestMapping(value="monthSalesSettleInfo", method=RequestMethod.GET)
	public @ResponseBody String monthSalesSettleInfo(HttpServletRequest request, String month1, String month2, int setle_mth_code) throws Exception{


		String callback = request.getParameter("callback");

		List<HashMap> list = billService.monthSalesSettleInfo(month1, month2, setle_mth_code);
		
		JSONObject salesJson;
		JSONArray salesArray = new JSONArray();
		
		for(int i = 0; i < list.size(); i++){
			salesJson = new JSONObject();
			salesJson.put("year", list.get(i).get("year").toString());
			salesJson.put("setle_mth_nm", list.get(i).get("setle_mth_nm"));
			salesJson.put("totalPrice", list.get(i).get("totalPrice"));
			salesArray.add(salesJson);

		}
		

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", salesArray);

		return callback + "(" + jsonObject +")";
	}
	
	/////////////////////////////////////////////재고관리 //////////////////////////
	
	@RequestMapping(value="stock_Management", method=RequestMethod.GET)
	public String stockManagement(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "stock_Management";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
*/

	/********************************* 이벤트 관리 부분 ***************************************/
	/********************************* 이벤트 관리 부분 ***************************************/
	@RequestMapping(value="event_Management", method=RequestMethod.GET)
	public String event(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "event_Management";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	// 캘린더 위 일정 뿌리기
	@RequestMapping(value="viewCalendar", method=RequestMethod.GET)
	public @ResponseBody String viewCalendar(HttpServletRequest request) throws Exception{
		
		String callback = request.getParameter("callback");
		
		List<HashMap> list = bbsService.viewCalendar();
		
		JSONObject viewCalJson;
		JSONArray viewCalArray = new JSONArray();
		
		for(int i=0; i < list.size(); i++){
			
			viewCalJson = new JSONObject();
			
			viewCalJson.put("bbsctt_code", list.get(i).get("bbsctt_code"));
			viewCalJson.put("title", list.get(i).get("bbsctt_sj"));
			viewCalJson.put("start", list.get(i).get("event_begin_de").toString());
			viewCalJson.put("end", list.get(i).get("event_end_de").toString());
			
			viewCalArray.add(viewCalJson);
			
		}
		 
		JSONObject json = new JSONObject();
		json.put("result", viewCalArray);
		
		return callback + "(" + json +")";
	}
	
	// 이벤트 등록
	@RequestMapping(value="insertEvent", method=RequestMethod.GET)
	public ResponseEntity<String> insertEvent(EventVO eventVO, BBScttVO bbscttVO){
	
		ResponseEntity<String> entity = null;
		
		try {
			bbsService.insertEvent(eventVO, bbscttVO);
			
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		} catch (Exception e) {
			
			entity =  new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		
		return entity;
		
		
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


	@RequestMapping(value="coupon_Management", method=RequestMethod.GET)
	public String coupon(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		String ContentPage = "coupon_Management";

		model.addAttribute("main_content", ContentPage);
		
		List<CouponVO> Couponlist = couponService.selectCouponList();

		model.addAttribute("list", Couponlist);
		
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
