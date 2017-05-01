package yjc.wdb.scts;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import yjc.wdb.scts.service.PositionService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Inject
	PositionService service;
	
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
	
	@RequestMapping(value="avgStayTest")
	public String avgStayTest(Model model) throws Exception{
		model.addAttribute(service.avgStay());
		return "avgStayTest";
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

	@RequestMapping(value="login")
	public String login() {
		return "NiceAdmin/login";
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
