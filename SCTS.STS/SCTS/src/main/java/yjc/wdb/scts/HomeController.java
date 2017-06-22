package yjc.wdb.scts;


import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.scts.bean.BeaconVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.bean.TileVO;
import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.service.BBSService;
import yjc.wdb.scts.service.BeaconService;
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
/*	
	@Inject 
	private StockService stockService;
	
	@Inject
	private HelpService helpService;*/
	
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
	public String product_RegisterPost(GoodsVO vo) throws Exception{
		
		System.out.println("GoodsVO 정보 : " + vo.getDetailctgry_code() + "  상품명 : " + vo.getGoods_nm());
		
		goodsService.insertGoods(vo);
		

		return "redirect:product_List";
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
	
	
	
	
	/////////////////////////////////////////// 문의 사항 ///////////////////////////
	// 242 ~ 456번 주석 컨트롤쉬프트 \ 하면 풀림
/*	
	@RequestMapping(value="help_List", method=RequestMethod.GET)
	public String helpList(Model model, @ModelAttribute("cri") PageVO cri) throws Exception{
		String ContentPage = "help_List";

		logger.info(cri.toString());
		System.out.println("�럹�씠吏� : " + cri.getPage() + "媛쒖닔 : " + cri.getPerPageNum() +
							"�꽌移섑��엯 : " + cri.getSearchType() + "�궎�썙�뱶 : " +cri.getKeyword()); 
		 PageVO cri瑜� �뙆�씪誘명꽣濡� �벐怨� Model媛앹껜濡� 諛쒖깮�븯�뒗 PageMaker瑜� ���옣 
		 紐⑸줉 �뜲�씠�꽣瑜� Model�뿉 ���옣�븯怨�, PageMaker瑜� 援ъ꽦�빐�꽌 Model�뿉 �떞�뒗 �옉�뾽 
		
		model.addAttribute("list", helpService.listSearch(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(helpService.countSearch(cri));
		System.out.println(cri.isMsg());
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("main_content", ContentPage);		
		return "mainPage";
	}
	
	@RequestMapping(value="searchHelp", method=RequestMethod.GET)
	public String searchHelp(@ModelAttribute("cri") PageVO cri, Model model, RedirectAttributes rttr) throws Exception{
		String ContentPage = "help_List";
		
		System.out.println("�럹�씠吏� : " + cri.getPage() + "媛쒖닔 : " + cri.getPerPageNum() +
				"�꽌移섑��엯 : " + cri.getSearchType() + "�궎�썙�뱶 : " +cri.getKeyword()); 
		
		model.addAttribute("list", helpService.listSearch(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(helpService.countSearch(cri));
		
		cri.setMsg(false);
		System.out.println("�꽌移� �뿬�봽 " + cri.isMsg());
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute("pageMaker", pageMaker);
		
		model.addAttribute("main_content", ContentPage);
		return "mainPage";
	}
	
	
	 help_Read�뿉�꽌 �떎�떆 �썝�옒 �럹�씠吏�濡� �룎�븘媛��젮硫� �븘�슂�븳 寃� page, perPageNum, bno�씠�떎. 
	@RequestMapping(value="readPage", method=RequestMethod.GET)
	public String readPage(@RequestParam("bno") int bno, @ModelAttribute("cri") PageVO cri, Model model)throws Exception{
		 page�� perPageNum �뙆�씪誘명꽣�뒗 PageVO���엯 媛앹껜濡� 泥섎━. bno留� 蹂꾨룄濡� 諛쏆븘�샂. 
		String ContentPage = "help_Read";
		System.out.println("由щ뱶 �뿬�봽 "+cri.isMsg());
		
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute(helpService.readHelp(bno));
		model.addAttribute("main_content", ContentPage);
		return "mainPage";
	}
	
	@RequestMapping(value="insertHelp", method=RequestMethod.GET)
	public String insertHelpGET(@ModelAttribute("cri") PageVO cri, RedirectAttributes rttr, Model model)throws Exception{
		String ContentPage = "help_Register";
		
		System.out.println("�씤�꽌�듃 寃� �뿬�봽 "+cri.isMsg());
		System.out.println("�럹�씠吏� : " + cri.getPage() + "媛쒖닔 : " + cri.getPerPageNum() +
				"�꽌移섑��엯 : " + cri.getSearchType() + "�궎�썙�뱶 : " +cri.getKeyword());
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	
	@RequestMapping(value="insertHelp", method=RequestMethod.POST)
	public String insertHelpPOST(HelpVO vo, @ModelAttribute("cri") PageVO cri, RedirectAttributes rttr, Model model) throws Exception{
		
		helpService.createHelp(vo);
		
		System.out.println("�씤�꽌�듃 �룷�뒪�듃 �뿬�봽 "+cri.isMsg());
		model.addAttribute("msg", cri.isMsg());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:help_List";
	}

	@RequestMapping(value="updateHelp", method=RequestMethod.GET)
	public String updateHelpGET(@RequestParam ("bno") int bno, @ModelAttribute("cri") PageVO cri, RedirectAttributes rttr, Model model) throws Exception{
		String ContentPage = "help_Update";

		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		System.out.println("�뾽�뜲�씠�듃 �뿬�봽  寃� " + cri.isMsg());
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute(helpService.readHelp(bno));
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	
	@RequestMapping(value="updateHelp", method=RequestMethod.POST)
	public String updateHelpPOST(HelpVO vo, PageVO cri, RedirectAttributes rttr) throws Exception{
		
		helpService.updateHelp(vo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:help_List";
	}
	
	@RequestMapping(value="deleteHelp", method=RequestMethod.GET)
	public String deleteHelpGet(@RequestParam("bno") int bno,@ModelAttribute("cri") PageVO cri , RedirectAttributes rttr, Model model) throws Exception{
		 寃��깋�븯怨� �궘�젣寃쎌슦 
		model.addAttribute("msg", cri.isMsg());
		System.out.println("�뵜由ы듃 �뿬�봽  寃� " + cri.isMsg());
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:searchHelp";
	}
	
	@RequestMapping(value="deleteHelp", method=RequestMethod.POST)
	public String deleteHelpPost(@RequestParam("bno") int bno,@ModelAttribute("cri") PageVO cri , RedirectAttributes rttr, Model model) throws Exception{
		 寃��깋 �븘�땲怨� �궘�젣寃쎌슦 
		helpService.deleteHelp(bno);
		
		model.addAttribute("msg", cri.isMsg());
		System.out.println("�뵜由ы듃 �뿬�봽  �룷�뒪�듃 " + cri.isMsg());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:help_List";
	}
	
	
	
	
	
	
	/////////////////////////////////////////////재고관리 //////////////////////////
	@RequestMapping(value="stock_Management", method=RequestMethod.GET)
	public String stockManagement(@ModelAttribute("cri") PageVO cri, Model model) throws Exception{
		String ContentPage = "stock_Management";

		System.out.println(cri.isMsg());
		
		List<StockVO> StockList = stockService.selectStockList(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(stockService.countSearch(cri));
		
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("main_content", ContentPage);
		model.addAttribute("list", StockList);
		 
		return "mainPage";
	}

	@RequestMapping(value="searchStock", method=RequestMethod.GET)
	public String searchStock(@ModelAttribute("cri") PageVO cri, Model model)throws Exception{
		String ContentPage = "stock_Management";
		System.out.println("여기는 서치 스톡" + "check는 " +cri.getCheck());
		
		List<StockVO> StockList = stockService.searchStockList(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(stockService.countSearch(cri));
		
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("main_content", ContentPage);
		model.addAttribute("list", StockList);

		return "mainPage";
	}
	
	@RequestMapping(value="deleteStock", method=RequestMethod.GET)
	public String deleteStock(String user_id, int goods_code, @ModelAttribute("cri") PageVO cri , RedirectAttributes rttr, Model model)throws Exception{
		
		cri.setMsg(false);
		System.out.println(cri.isMsg());
		List<HashMap> StockList = stockService.deleteStockList(user_id, goods_code);
		
		model.addAttribute("msg", cri.isMsg());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("startAmount",cri.getStartAmount());
		rttr.addAttribute("endAmount",cri.getEndAmount());
		
		
		return "redirect:stock_Management";
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
