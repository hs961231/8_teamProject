package yjc.wdb.scts;


import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.service.UserService;
import yjc.wdb.scts.service.CouponService;
import yjc.wdb.scts.service.PositionService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private UserService userService;
	@Inject
	PositionService positionService;
	@Inject
	private CouponService couponService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	/********************************* �α��� ���� ***************************************/
	/********************************* �α��� ���� ***************************************/
	// ó�� ���� �� ǥ���� �ִ� �α��� ȭ��
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	// �α��� ��û �޴� �κ�
	@RequestMapping(value="/", method=RequestMethod.POST)
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
	
	
	// ����������, �뽬����, �ΰ� Ŭ���� ����
	@RequestMapping(value="mainPage", method=RequestMethod.GET)
	public String mainPage(HttpServletRequest request, HttpSession session) {
		
		return "mainPage";
	}

	/********************************* ���� ���� �޴� ***************************************/
	/********************************* ���� ���� �޴� ***************************************/
	// ���� ���
	@RequestMapping(value="shopRegister", method=RequestMethod.GET)
	public String shopRegister(HttpServletRequest request, HttpSession session, Model model) {
		// ���� ���������� � �������� ���� �� ������ ������ ����.
		String ContentPage = "shopRegister";

		// ���� �� �������� ���� ������ ������ ������ �Ѱ��ش�.
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}

	// ��ǰ ���
	@RequestMapping(value="productRegister", method=RequestMethod.GET)
	public String productRegister(HttpServletRequest request, HttpSession session, Model model) {
		// ���� ���������� � �������� ���� �� ������ ������ ����.
		String ContentPage = "productRegister";

		// ���� �� �������� ���� ������ ������ ������ �Ѱ��ش�.
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	
	// ���� ����
	@RequestMapping(value="salesManagement", method=RequestMethod.GET)
	public String salesManagement(HttpServletRequest request, HttpSession session, Model model) {
		// ���� ���������� � �������� ���� �� ������ ������ ����.
		String ContentPage = "salesManagement";
		
		// ���� �� �������� ���� ������ ������ ������ �Ѱ��ش�.
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	
	// ��� ����
	@RequestMapping(value="stockManagement", method=RequestMethod.GET)
	public String stockManagement(HttpServletRequest request, HttpSession session, Model model) {
		// ���� ���������� � �������� ���� �� ������ ������ ����.
		String ContentPage = "stockManagement";
		
		// ���� �� �������� ���� ������ ������ ������ �Ѱ��ش�.
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}


	/********************************* �̺�Ʈ ���� �޴� ***************************************/
	/********************************* �̺�Ʈ ���� �޴� ***************************************/
	@RequestMapping(value="event", method=RequestMethod.GET)
	public String event(HttpServletRequest request, HttpSession session, Model model) {
		// ���� ���������� � �������� ���� �� ������ ������ ����.
		String ContentPage = "event";

		// ���� �� �������� ���� ������ ������ ������ �Ѱ��ش�.
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}

	/********************************* ���� ���� �޴� ***************************************/
	/********************************* ���� ���� �޴� ***************************************/
	
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
		System.out.println("remove다");
		couponService.remove(coupon_id);*/
		
		System.out.println(coupon_id);
		return "mainPage";
	}
	
	
	@RequestMapping(value="coupon", method=RequestMethod.GET)
	public String coupon(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		// ���� ���������� � �������� ���� �� ������ ������ ����.
		String ContentPage = "coupon";

		// ���� �� �������� ���� ������ ������ ������ �Ѱ��ش�.
		model.addAttribute("main_content", ContentPage);
		model.addAttribute("list",couponService.listCoupon());
		
		return "mainPage";
	}

	
	/********************************* ���� �ý��� �޴� ***************************************/
	/********************************* ���� �ý��� �޴� ***************************************/
	@RequestMapping(value="posSystem", method=RequestMethod.GET)
	public String posSystem(HttpServletRequest request, HttpSession session, Model model) {
		// ���� ���������� � �������� ���� �� ������ ������ ����.
		String ContentPage = "posSystem";

		// ���� �� �������� ���� ������ ������ ������ �Ѱ��ش�.
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	
	/** 2017_05_01 ����
	 * �� Ÿ�� ( ������, ���̳� )������ ������ ��� �ӹ� �ð���, ����� �ӹ������� �����͸� �̾ƿ��� ��
	 * �ش� jsp���������� ���������� ��µǴ� �� Ȯ�� ����.
	 */
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
	
}
