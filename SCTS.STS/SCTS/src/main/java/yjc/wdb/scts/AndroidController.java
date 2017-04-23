package yjc.wdb.scts;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.scts.bean.Customer;
import yjc.wdb.scts.bean.Loc_info;
import yjc.wdb.scts.service.CustomerService;
import yjc.wdb.scts.service.Loc_infoService;

@Controller
public class AndroidController {

	@Inject
	private Loc_infoService service;
	
	@Inject
	private CustomerService customerService;
	
	@RequestMapping(value="/android")
	public String AndroidTest(HttpServletRequest request){

		String json = request.getParameter("json");

		System.out.println(json);
		
		/*JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;
		
		try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			Loc_info loc_info = new Loc_info();
			loc_info.setUser_id((String)jsonObject.get("user_id"));
			loc_info.setUser_pw((String) jsonObject.get("user_pw"));
			
			System.out.println(loc_info.getUser_id());
			System.out.println(loc_info.getUser_pw());
			
			service.insertLoc_info(loc_info);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	*/
		return "android";
	}

	@RequestMapping("/android2")
	public @ResponseBody JSONArray WebToAndroid(HttpServletRequest request) throws Exception{
		JSONArray jsonArray = new JSONArray();
		
		List<Loc_info> list = service.Loc_info_List();
		JSONObject jsonObject;
		for(int i=0; i<list.size(); i++){
			 jsonObject = new JSONObject();
			 jsonObject.put("user_id", list.get(i).getUser_id());
			 jsonArray.add(jsonObject);
		}
		
		return jsonArray;
	}
	
	@RequestMapping("/checkCustomer")
	public @ResponseBody int checkCustomer(Customer customer, HttpServletRequest request) throws Exception{
		System.out.println("아이디 = " + customer.getCustomer_id() + "비밀번호" + customer.getCustomer_pw());
		int checkUser = customerService.checkCustomer(customer);
		return checkUser;
	}
	
	@RequestMapping("/checkUser")
	public @ResponseBody String checkUser(Loc_info vo, HttpServletRequest request) throws Exception{
		System.out.println("리퀘스트 = " + request.getParameter("json"));
		System.out.println("유저쪽 아이디 = " + vo.getUser_id() + "비밀번호" + vo.getUser_pw());
		//int checkUser = customerService.checkCustomer(vo);
		return "1";
	}
	

}
