package yjc.wdb.scts;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.service.Coupon_holdService;
import yjc.wdb.scts.service.GoodsService;

@Controller
public class PosController {

	private static final Logger logger = LoggerFactory.getLogger(PosController.class);
	
	@Inject
	GoodsService goodsService;
	
	@Inject
	Coupon_holdService coupon_holdService;
	
	
	/********************************* 포스 시스템 부분 ***************************************/
	/********************************* 포스 시스템 부분 ***************************************/
	@RequestMapping(value="posSystem", method=RequestMethod.GET)
	public String posSystem(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "posSystem";

		model.addAttribute("main_content", ContentPage);
		
		

		return "mainPage";
	}

	/* posSystem.js
	 * 포스 페이지에서 바코드 입력 후 물품 등록을 눌렀을 때 디비에 조회해서 상품의 정보를
	 * 뷰페이지로 보내주는 아작스 처리문
	 */
	@RequestMapping(value="getGoodsAjax", method=RequestMethod.POST,
			produces = "text/plain; charset=UTF-8")
	@ResponseBody 
	public ResponseEntity<String> getGoodsAjax(@RequestParam("goods_code") int goods_code) throws Exception {
		
		GoodsVO vo = goodsService.selectGoodsOne(goods_code);

		if(vo == null) {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		String str = new Gson().toJson(vo);
		
		System.out.println(str);
		
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	

	/* posSystem.js
	 * 쿠폰 포인트 모달 창에서 사용자의 아이디를 입력하였을 때
	 * 사용자가 현재 구매목록에서 사용할 수 있는 쿠폰들을 디비에 조회해서
	 * 뷰페이지로 보내주는 역할을 하는 아작스 처리문
	 */
	@RequestMapping(value="getUserCoupon", method=RequestMethod.POST,
			produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> getUserCoupon(@RequestBody JSONObject json) throws Exception {
		String str;
		
		try {
			
			System.out.println(json.toString());
			
			// 해당 맵에는 현재 주문목록에 올려져 있는 물품 번호와, 유저 아이디가 들어감
			HashMap<String, Object> map = new ObjectMapper().readValue(json.toString(), HashMap.class);
			
			List<HashMap<String, String>> userCouponList = coupon_holdService.selectCouponHoldGoods(map);
	
			if(userCouponList == null) {
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			str = new Gson().toJson(userCouponList);
			
			System.out.println(str);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
}
