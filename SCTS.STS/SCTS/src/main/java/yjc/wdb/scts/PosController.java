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
	
	
	/********************************* ���� �ý��� �κ� ***************************************/
	/********************************* ���� �ý��� �κ� ***************************************/
	@RequestMapping(value="posSystem", method=RequestMethod.GET)
	public String posSystem(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "posSystem";

		model.addAttribute("main_content", ContentPage);
		
		

		return "mainPage";
	}

	/* posSystem.js
	 * ���� ���������� ���ڵ� �Է� �� ��ǰ ����� ������ �� ��� ��ȸ�ؼ� ��ǰ�� ������
	 * ���������� �����ִ� ���۽� ó����
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
	 * ���� ����Ʈ ��� â���� ������� ���̵� �Է��Ͽ��� ��
	 * ����ڰ� ���� ���Ÿ�Ͽ��� ����� �� �ִ� �������� ��� ��ȸ�ؼ�
	 * ���������� �����ִ� ������ �ϴ� ���۽� ó����
	 */
	@RequestMapping(value="getUserCoupon", method=RequestMethod.POST,
			produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> getUserCoupon(@RequestBody JSONObject json) throws Exception {
		String str;
		
		try {
			
			System.out.println(json.toString());
			
			// �ش� �ʿ��� ���� �ֹ���Ͽ� �÷��� �ִ� ��ǰ ��ȣ��, ���� ���̵� ��
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
