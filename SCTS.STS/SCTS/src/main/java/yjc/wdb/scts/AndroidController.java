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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
		    java.util.Date parsedDate = dateFormat.parse( (String) positionJson.get("currentTime") );
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    
		    // vo��ü�� �� ����
		    position.setCurrentTime( timestamp );
		    position.setMajor( Integer.parseInt(positionJson.get("major").toString() ) );
		    position.setMinor( Integer.parseInt(positionJson.get("minor").toString() ) );
		    position.setStayTime( Integer.parseInt(positionJson.get("stayTime").toString() ) );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

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
	
}
