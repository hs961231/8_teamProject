package yjc.wdb.scts;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.service.CouponService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} )
public class BaseTest {
	
	@Inject
	private CouponService couponService;
	
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
