package yjc.wdb.scts;


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
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String loginPost(UserVO user) {
		
		
		
		return "mainPage";
	}
	
	/** 2017_05_01 구현
	 * 각 타일 ( 메이저, 마이너 )에서의 고객들의 평균 머문 시간과, 몇명이 머물럿는지 데이터를 뽑아오는 것
	 * 해당 jsp페이지에서 정상적으로 출력되는 것 확인 햇음.
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
