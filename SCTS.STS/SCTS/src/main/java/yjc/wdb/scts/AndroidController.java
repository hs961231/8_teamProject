package yjc.wdb.scts;

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

import yjc.wdb.scts.bean.Loc_info;
import yjc.wdb.scts.service.Loc_infoService;

@Controller
public class AndroidController {

	@Inject
	private Loc_infoService service;
	
	@RequestMapping(value="/android")
	public String AndroidTest(HttpServletRequest request, Model model){

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
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return "android";
	}


}
