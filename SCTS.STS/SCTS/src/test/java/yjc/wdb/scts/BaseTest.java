package yjc.wdb.scts;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Tile_locationVO;
import yjc.wdb.scts.dao.CourseDAO;
import yjc.wdb.scts.service.CouponService;
import yjc.wdb.scts.service.CourseService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		//locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} )
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"} )
public class BaseTest {
	
	private static final Logger logger =
			LoggerFactory.getLogger(BaseTest.class);
	
	@Inject
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Inject
	CouponService couponService;

	@Test
	public void couponSend() {

		try {

			CouponVO coupon = couponService.selectSendAndroidCoupon();
			
			JSONObject resultData = new JSONObject();
			
			if(coupon == null) {
				resultData.put("status", "SUCCESS");
				resultData.put("command", "emptycoupon");
				System.out.println(resultData.toJSONString());
				System.out.println(resultData.toString());
				return;
			}
			String str;
			
			str = new Gson().toJson(coupon);
			resultData = (JSONObject) new JSONParser().parse(str);
			
			resultData.put("status", "SUCCESS");
			resultData.put("command", "fullcoupon");
	
			System.out.println(resultData.toJSONString());
			System.out.println(resultData.toString());
			
			logger.debug(resultData.toString());
			
			return;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void asd() {
		try {
			
			String str = "{\"beacon_mjr\":13716,\"beacon_mnr\":14756,\"tile_code\":134}";
			JSONObject json = (JSONObject) new JSONParser().parse(str);
			System.out.println(json);
			HashMap<String, String> vo = new HashMap<String, String>();
			
			System.out.println(json.get("tile_code").toString());
	
			vo.put("tile_code", json.get("tile_code").toString());
			vo.put("beacon_mjr", json.get("beacon_mjr").toString());
			vo.put("beacon_mnr", json.get("beacon_mnr").toString());
			System.out.println("tile_code = " + vo.get("tile_code") + 
					"beacon_mjr = " + vo.get("beacon_mjr") + 
					"beacon_mnr = " + vo.get("beacon_mnr") );
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Inject
	CourseService courseService;
	//CourseDAO dao;
	
	public void kkk() {
		try {
			
			int drw_code = 1;
			int X_index = 0;
			int Y_index = 0;
			
			Tile_locationVO vo = new Tile_locationVO();
			vo.setDrw_code(drw_code);
			vo.setTilelc_crdnt_x(X_index);
			vo.setTilelc_crdnt_y(Y_index);
			
			// HashMap<String, String> tile = tileService.selectTile_LocationOne(Map_XY);

			HashMap<String, String> pro = courseService.tileProbability(vo);
			List<HashMap<String, String>> userinfo = courseService.tileUserinfo(vo);
			//HashMap<String, String> pro = dao.tileProbability(vo);
			//List<HashMap<String, String>> userinfo = dao.tileUserinfo(vo);
	
			JSONObject json = new JSONObject();
			
			JsonArray jsonUser = (JsonArray) new JSONParser().parse(new Gson().toJson(userinfo));
			JSONObject jsonPro = (JSONObject) new JSONParser().parse(new Gson().toJson(pro));
					
			json.put("jsonUser", jsonUser);
			json.put("jsonPro", jsonPro);
			
			System.out.println(json.toString());
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*
	@Test
	public void asdasd(){
		
		String str;
		try {
			JSONObject json = new JSONObject();
			json.put("status", "ERROR");
			
			System.out.println(json.toString());

			str = new Gson().toJson(couponService.selectTest());
			json = (JSONObject) new JSONParser().parse(str);
			
			json.put("status", "success");

			System.out.println(json.toString());
			
			
			str = new Gson().toJson(couponService.selectTest());
			JSONObject coupon = (JSONObject) new JSONParser().parse(str);
			
			coupon.put("status", "success");
			
			System.out.println(coupon);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}
