package yjc.wdb.scts;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.bean.PositionVO;
import yjc.wdb.scts.service.UserService;
import yjc.wdb.scts.service.PositionService;

@Controller
@RequestMapping("/android")
public class AndroidController {
	
	@Inject
	private UserService userService;
	
	@Inject
	PositionService positionService;

	private static final Logger logger = LoggerFactory.getLogger(AndroidController.class);
	
	// �ȵ���̵忡�� ���۵Ǵ� �α��� ������ ����
	@RequestMapping(value="/androidLogin")
	@ResponseBody
	public String androidLogin(HttpServletRequest request) throws Exception{
		// ���� �����ϱ� ���� �߰���
		// request ��ü �ȿ� �Ѿ���� �Ķ���Ͱ� ���ϴ� ���� ������ ��� ��������� ���� ��� error ��� ���ڿ��� ������
		String str = request.getParameter("UserVO");
		if(str == null) {
			return "ERROR";
		}
		
		JSONObject userJson = (JSONObject) new JSONParser().parse( str );
		
		UserVO user = new UserVO();
		try {
		    
		    user.setUser_id( userJson.get("user_id").toString() );
		    user.setUser_pw( userJson.get("user_pw").toString() );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if(userService.loginUser(user) == 1)
			return "SUCCESS";
		else
			return "ERROR";
		
	}
	

	// �ȵ���̵忡�� �������� ����
	@RequestMapping(value="/setPositionData")
	@ResponseBody
	public String setPositionData(HttpServletRequest request) throws Exception{
		// ���� �����ϱ� ���� �߰���
		// request ��ü �ȿ� �Ѿ���� �Ķ���Ͱ� ���ϴ� ���� ������ ��� ��������� ���� ��� error ��� ���ڿ��� ������
		String str = request.getParameter("PositionVO");
		if(str == null) {
			return "ERROR";
		}
		
		// �Ѿ�� ���ڿ��� json ��ü�� ��ȯ
		JSONObject positionJson = (JSONObject) new JSONParser().parse( str );
		
		PositionVO position = new PositionVO();
		try {
			// ���ڿ� ������ ��¥�ð� ���� timestamp������ ��ȯ
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		    java.util.Date parsedDate = dateFormat.parse( (String) positionJson.get("current_Timedate") );
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

		    // vo��ü�� �� ����
		    position.setCurrent_Timedate( timestamp );
		    position.setMajor( Integer.parseInt(positionJson.get("major").toString() ) );
		    position.setMinor( Integer.parseInt(positionJson.get("minor").toString() ) );
		    position.setUser_id( positionJson.get("user_id").toString() );
		    position.setStay_Time( Integer.parseInt(positionJson.get("stay_Time").toString() ) );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.debug("����");
		}

		logger.debug(str);
		logger.debug(position.toString());
		
		// ��� ����
		positionService.insertPosition(position);
		
		
		return "SUCCESS";
	}
	
	// ��� �ִ� ���������� �ȵ���̵�� �Ѱ���
	@RequestMapping(value="/getPositionData")
	@ResponseBody
	public PositionVO getPositionData(HttpServletRequest request) throws Exception{
		// ��񿡼� �������� ���� �ٷ� ����
		return positionService.selectPosition();
	}
	
	// ȸ������
	
	@RequestMapping(value="checkUser", method=RequestMethod.POST)
	public @ResponseBody String checkUser(String user_id, HttpServletRequest request) throws Exception{
		System.out.print(request.getParameter("user_id"));
		int checkUser = userService.checkUser(user_id);
		return ""+checkUser;
	}
	
	@RequestMapping(value="signUp", method=RequestMethod.POST)
	public @ResponseBody String signUp(HttpServletRequest request) throws Exception{
		
		System.out.println(request.getParameter("json"));
		String str = request.getParameter("json");
		JSONObject joinJSON = (JSONObject) new JSONParser().parse(str);
		
		UserVO user = new UserVO();
		user.setUser_id(joinJSON.get("user_id").toString());
		user.setUser_pw((String) joinJSON.get("user_pw").toString());
		user.setAge(Integer.parseInt(joinJSON.get("age").toString()));
		user.setGender(joinJSON.get("gender").toString());
		
		
		System.out.println(user.getUser_id());
		System.out.println(user.getUser_pw());
		System.out.println(user.getAge());
		System.out.println(user.getGender());
		
		userService.registerUser(user);
		
		
		
		int checkUser = userService.checkUser(user.getUser_id());
		return ""+checkUser;
	}
	
}
