package yjc.wdb.scts;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import yjc.wdb.scts.bean.CouponBasketVO;
import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.EventVO;
import yjc.wdb.scts.bean.PositionVO;
import yjc.wdb.scts.service.UserService;
import yjc.wdb.scts.service.CouponService;
import yjc.wdb.scts.service.EventService;
import yjc.wdb.scts.service.PositionService;
import yjc.wdb.scts.service.PurchaseInfoService;

@Controller
@RequestMapping("/android")
public class AndroidController {
	
	@Inject
	private UserService userService;
	
	@Inject
	PositionService positionService;
	
	@Inject
	private EventService eventService;
	
	@Inject
	private CouponService couponService;
	
	@Inject
	private PurchaseInfoService purchaseInfoService;

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
	
	// �̺�Ʈ ����
	@RequestMapping(value="eventList", method=RequestMethod.GET)
	public @ResponseBody String eventList(HttpServletRequest request) throws Exception{
		
		String callback = request.getParameter("callback");
		List<EventVO> list = eventService.eventList();
		JSONObject eventJson;
		JSONObject event;
		JSONArray eventArray = new JSONArray();
		for(int i = 0; i < list.size(); i++){
			eventJson = new JSONObject();
			eventJson.put("e_id", list.get(i).getE_id());
			eventJson.put("e_name", list.get(i).getE_name());
			eventJson.put("e_start", list.get(i).getE_start().toString());
			eventJson.put("e_end", list.get(i).getE_end().toString());
			
			eventArray.add(eventJson);
			
			
		}
		
		event = new JSONObject();
		event.put("data", eventArray);
		
		String eventStr = event.toString();
		
		return callback+"("+eventStr+")";
	}
	
	@RequestMapping(value="eventOne", method=RequestMethod.GET)
	public @ResponseBody String eventOne(String e_id, HttpServletRequest request) throws Exception{
		
		
		String callback = request.getParameter("callback");
		EventVO eventVO = eventService.eventOne(e_id);
		
		JSONObject eventJson = new JSONObject();
		eventJson.put("e_id", eventVO.getE_id());
		eventJson.put("e_name", eventVO.getE_name());
		eventJson.put("e_start", eventVO.getE_start().toString());
		eventJson.put("e_end", eventVO.getE_end().toString());
		eventJson.put("e_content", eventVO.getE_content());
		
	
		
		
		return callback + "(" + eventJson + ")";
		
	}
	
	// ���� �ٱ��Ͽ� ��� ���� ����
	@RequestMapping(value="couponList", method=RequestMethod.GET)
	public @ResponseBody String couponList(String user_id, HttpServletRequest request) throws Exception{
		
		String callback = request.getParameter("callback");
		List<CouponVO> list = couponService.couponList(user_id);
		JSONObject couponJson;
		JSONObject coupon;
		JSONArray couponArray = new JSONArray();
		for(int i = 0; i < list.size(); i++){
			couponJson = new JSONObject();
			couponJson.put("coupon_id", list.get(i).getCoupon_id());
			couponJson.put("coupon_name", list.get(i).getCoupon_name());
			couponJson.put("coupon_content", list.get(i).getCoupon_content());
		
			couponArray.add(couponJson);
			
			
		}
		
		coupon = new JSONObject();
		coupon.put("data", couponArray);
		
		
		
		return callback+"("+coupon+")";
	}
	
	// ���� �ٱ��Ͽ��� ���� ����
	@RequestMapping(value="delCouponBasket", method=RequestMethod.GET)
	public @ResponseBody String delCouponBasket(CouponBasketVO couponBasketVO, HttpServletRequest request) throws Exception{
		
		String callback = request.getParameter("callback");
		couponService.delCouponBasket(couponBasketVO);
		
		// �̺κ� �� ���� �غ���
		JSONObject coupon;
		
		coupon = new JSONObject();
		coupon.put("result", "success");
		

		
		return callback+"("+coupon+")";
	}
	
	// ��꼭 ����
	@RequestMapping(value="billList", method=RequestMethod.GET)
	public @ResponseBody String billList(String user_id, int day, HttpServletRequest request) throws Exception{
		
		String callback = request.getParameter("callback");
		
		List<HashMap> list = purchaseInfoService.billList(user_id,day);
		
	
		JSONObject billJson;
		
		
		
		JSONArray billArray = new JSONArray();
		for(int i=0; i < list.size(); i++){
			billJson = new JSONObject();
			billJson.put("b_id", list.get(i).get("b_id"));	
			billJson.put("publish_date", list.get(i).get("publish_date").toString());	
			billJson.put("user_id", list.get(i).get("user_id"));	
			billJson.put("p_name", list.get(i).get("p_name"));	
			billJson.put("totalPrice", list.get(i).get("totalPrice"));
			
			billArray.add(billJson);
		}
		
		JSONObject billList = new JSONObject();
		billList.put("data", billArray);
		
		System.out.println(billList.toString());

		
		return callback+"("+billList+")";
	}
	// ��꼭 ������
	@RequestMapping(value="billOne", method=RequestMethod.GET)
	public @ResponseBody String billOne(int b_id, HttpServletRequest request) throws Exception{
		
		String callback = request.getParameter("callback");
		
		List<HashMap> list = purchaseInfoService.billOne(b_id); 
		
		// �̺κ� �� ���� �غ���
		JSONObject billJson;
		
		
		
		JSONArray billArray = new JSONArray();
		for(int i=0; i < list.size(); i++){
			billJson = new JSONObject();	
			billJson.put("product_name", list.get(i).get("product_name").toString());	
			billJson.put("amount", list.get(i).get("amount"));	
			billJson.put("price", list.get(i).get("price"));	
		
			
			billArray.add(billJson);
		}
		
		JSONObject billList = new JSONObject();
		billList.put("data", billArray);
		
		System.out.println(billList.toString());

		
		return callback+"("+billList+")";
	}
	
	
	
	
	
	
}
