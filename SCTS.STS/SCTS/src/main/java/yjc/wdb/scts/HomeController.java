package yjc.wdb.scts;

import java.security.Provider.Service;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.service.UserService;

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
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@RequestMapping(value="test1")
	public void agebase_page() {
		
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
		return "avgStayTest";
	}

	@RequestMapping(value="visit_countTest")
	public String visit_countTest(Model model) throws Exception{
		List<HashMap<String, String>> list = positionService.visit_count();
		logger.debug(list.toString());
		model.addAttribute("list", list);
		return "visit_countTest";
	}

	@RequestMapping(value="probabilityTest")
	public String probabilityTest(Model model) throws Exception{
		List<HashMap<String, String>> list = positionService.probability();
		logger.debug(list.toString());
		model.addAttribute("list", list);
		return "probabilityTest";
	}
/***************************************** ������ ���� �� �������� ***************************************************/

	@RequestMapping(value="index")
	public String index() {
		return "NiceAdmin/index";
	}

	@RequestMapping(value="basic_table")
	public String basic_table() {
		return "NiceAdmin/basic_table";
	}

	@RequestMapping(value="blank")
	public String blank() {
		return "NiceAdmin/blank";
	}

	@RequestMapping(value="buttons")
	public String buttons() {
		return "NiceAdmin/buttons";
	}

	@RequestMapping(value="chart_chartjs")
	public String chart_chartjs() {
		return "NiceAdmin/chart_chartjs";
	}

	@RequestMapping(value="form_component")
	public String form_component() {
		return "NiceAdmin/form_component";
	}

	@RequestMapping(value="form_validation")
	public String form_validation() {
		return "NiceAdmin/form_validation";
	}

	@RequestMapping(value="general")
	public String general() {
		return "NiceAdmin/general";
	}

	@RequestMapping(value="grids")
	public String grids() {
		return "NiceAdmin/grids";
	}

	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginForm() {
		return "NiceAdmin/login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public @ResponseBody String login(UserVO user) throws Exception{
		
		int loginUser = userService.loginUser(user);
		
		return ""+loginUser;
	}
	
	@RequestMapping(value="signUp", method=RequestMethod.GET)
	public String signUpForm() {
		return "NiceAdmin/signUp";
	}
	
	@RequestMapping(value="signUp", method=RequestMethod.POST)
	public @ResponseBody String signUp(UserVO user) throws Exception{
		userService.registerUser(user);
		int checkUser = userService.checkUser(user.getUser_id());
		return ""+checkUser;
	}
	
	@RequestMapping(value="checkUser", method=RequestMethod.GET)
	public @ResponseBody String checkUser(String user_id) throws Exception{
		
		int checkUser = userService.checkUser(user_id);
		return ""+checkUser;
	}
	
	

	@RequestMapping(value="profile")
	public String profile() {
		return "NiceAdmin/profile";
	}

	@RequestMapping(value="widgets")
	public String widgets() {
		return "NiceAdmin/widgets";
	}
	
	@RequestMapping(value="404")
	public String errorPage() {
		return "NiceAdmin/404";
	}
	
	
	
}
