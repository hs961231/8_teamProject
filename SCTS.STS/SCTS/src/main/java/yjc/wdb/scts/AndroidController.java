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

import yjc.wdb.scts.bean.User;
import yjc.wdb.scts.bean.Loc_info;
import yjc.wdb.scts.service.UserService;
import yjc.wdb.scts.service.Loc_infoService;

@Controller
public class AndroidController {

	@Inject
	private Loc_infoService service;
	
	@Inject
	private UserService customerService;
	
	
	@RequestMapping("/checkUser")
	public @ResponseBody String checkCustomer(HttpServletRequest request) throws Exception{
		
		String json = request.getParameter("json");

		System.out.println(json);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;
		
		
		
		try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			Loc_info loc_info = new Loc_info();
			loc_info.setUser_id((String)jsonObject.get("user_id"));
			loc_info.setUser_pw((String) jsonObject.get("user_pw"));
			
			System.out.println(loc_info.getUser_id());
			System.out.println(loc_info.getUser_pw());
			
			service.insertLoc_info(loc_info);
			
			User user = new User();
			user.setUser_id((String) jsonObject.get("user_id"));
			user.setUser_pw((String) jsonObject.get("user_pw"));
			
			int checkUser = customerService.checkCustomer(user);
			
			System.out.println(checkUser);
			
			JSONObject jsonObject2  = new JSONObject();
			jsonObject2.put("checkUser", checkUser);
			
			
			String json2 = jsonObject2.toString();
			
			System.out.println(json2);
			
			return json2;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	

}
