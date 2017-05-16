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

import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.service.UserService;

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
	
	// ���� ���� ����
	@Inject
	private SalesService salesService;
	
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
	@RequestMapping(value="coupon", method=RequestMethod.GET)
	public String coupon(HttpServletRequest request, HttpSession session, Model model) {
		// ���� ���������� � �������� ���� �� ������ ������ ����.
		String ContentPage = "coupon";

		// ���� �� �������� ���� ������ ������ ������ �Ѱ��ش�.
		model.addAttribute("main_content", ContentPage);
		
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
