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
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.google.gson.Gson;

import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.bean.CouponBasketVO;
import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.EventVO;
import yjc.wdb.scts.bean.PositionVO;
import yjc.wdb.scts.service.UserService;
import yjc.wdb.scts.service.CouponService;
import yjc.wdb.scts.service.CourseService;
import yjc.wdb.scts.service.EventService;
import yjc.wdb.scts.service.PositionService;
import yjc.wdb.scts.service.PurchaseInfoService;

@Controller
@RequestMapping("/android")
public class AndroidController {

	@Inject
	private UserService userService;

	@Inject
	//PositionService positionService;
	CourseService courseService;

	// 여기 주서ㄱ
	//@Inject
	//private EventService eventService;

	@Inject
	private CouponService couponService;

	

	private static final Logger logger = LoggerFactory.getLogger(AndroidController.class);

	// 안드로이드에서 전송되는 로그인 정보를 저장
	@RequestMapping(value="/androidLogin")
	@ResponseBody
	public String androidLogin(HttpServletRequest request) throws Exception{
		// 에러 방지하기 위해 추가함
		// request 객체 안에 넘어오는 파라미터가 원하는 것이 있으면 계속 진행되지만 없을 경우 error 라는 문자열을 리턴함
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


	// 안드로이드에서 비콘정보 전송
	@RequestMapping(value="/setPositionData")
	@ResponseBody
	public String setPositionData(HttpServletRequest request) throws Exception{
		// 에러 방지하기 위해 추가함
		// request 객체 안에 넘어오는 파라미터가 원하는 것이 있으면 계속 진행되지만 없을 경우 error 라는 문자열을 리턴함
		String str = request.getParameter("PositionVO");
		JSONObject json = new JSONObject();
		if(str == null) {
			json.put("status", "ERROR");
			return json.toString();
		}

		// 넘어온 문자열을 json 객체로 변환
		JSONObject positionJson = (JSONObject) new JSONParser().parse( str );

		PositionVO position = new PositionVO();
		try {
			// 문자열 형태의 날짜시간 값을 timestamp값으로 변환
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
			java.util.Date parsedDate = dateFormat.parse( (String) positionJson.get("current_Timedate") );
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

			// vo객체에 값 저장
			position.setCurrent_Timedate( timestamp );
			position.setMajor( Integer.parseInt(positionJson.get("major").toString() ) );
			position.setMinor( Integer.parseInt(positionJson.get("minor").toString() ) );
			position.setUser_id( positionJson.get("user_id").toString() );
			position.setStay_Time( Integer.parseInt(positionJson.get("stay_Time").toString() ) );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.debug("깨짐");
		}

		logger.debug(str);
		logger.debug(position.toString());

		// 디비에 저장
		positionService.insertPosition(position);

		
		// 안드로이드로 쿠폰 정보를 보내기 위해서 사용
		str = new Gson().toJson(couponService.selectTest());
		json = (JSONObject) new JSONParser().parse(str);
		
		json.put("status", "SUCCESS");
		
		return json.toString();
	}

	// 디비에 있는 비콘정보를 안드로이드로 넘겨줌
	@RequestMapping(value="/getPositionData")
	@ResponseBody
	public PositionVO getPositionData(HttpServletRequest request) throws Exception{
		// 디비에서 비콘정보 빼서 바로 리턴
		return positionService.selectPosition();
	}



	// 회원가입

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

	// 이벤트 보기
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

	// 이벤트 상세보기
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

	// 쿠폰 바구니에 담긴 쿠폰 보기
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

	// 쿠폰 바구니에서 쿠폰 삭제
	@RequestMapping(value="delCouponBasket", method=RequestMethod.GET)
	public @ResponseBody String delCouponBasket(CouponBasketVO couponBasketVO, HttpServletRequest request) throws Exception{

		String callback = request.getParameter("callback");
		couponService.delCouponBasket(couponBasketVO);

		// 이부분 더 생각 해보기
		JSONObject coupon;

		coupon = new JSONObject();
		coupon.put("result", "success");



		return callback+"("+coupon+")";
	}

	// 계산서 정보
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
	// 계산서 상세정보
	@RequestMapping(value="billOne", method=RequestMethod.GET)
	public @ResponseBody String billOne(int b_id, HttpServletRequest request) throws Exception{

		String callback = request.getParameter("callback");

		List<HashMap> list = purchaseInfoService.billOne(b_id); 

		// 이부분 더 생각 해보기
		JSONObject billJson;



		JSONArray billArray = new JSONArray();
		for(int i=0; i < list.size(); i++){
			billJson = new JSONObject();	
			billJson.put("product_name", list.get(i).get("product_name"));	
			billJson.put("amount", list.get(i).get("amount"));	
			billJson.put("price", list.get(i).get("price"));	


			billArray.add(billJson);
		}

		JSONObject billList = new JSONObject();
		billList.put("data", billArray);

		System.out.println(billList.toString());


		return callback+"("+billList+")";
	}


	@RequestMapping(value="recommandProduct", method=RequestMethod.GET)
	public @ResponseBody String recommandProduct(String user_id, HttpServletRequest request) throws Exception{

		String callback = request.getParameter("callback");

		List<HashMap> list = purchaseInfoService.recommandList(user_id); 

		// 이부분 더 생각 해보기
		JSONObject recommandJSON;



		JSONArray recommandArray = new JSONArray();
		for(int i=0; i < list.size(); i++){
			recommandJSON = new JSONObject();	
			recommandJSON.put("product_id", list.get(i).get("product_id"));
			recommandJSON.put("product_name", list.get(i).get("product_name"));	
			recommandJSON.put("price", list.get(i).get("product_price"));			

			recommandArray.add(recommandJSON);
		}

		JSONObject recommandList = new JSONObject();
		recommandList.put("data", recommandArray);

		System.out.println(recommandList.toString());


		return callback+"("+recommandList+")";
	}





}
