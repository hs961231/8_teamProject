package yjc.wdb.scts;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.service.GoodsService;

@Controller
public class PosController {

	private static final Logger logger = LoggerFactory.getLogger(PosController.class);
	
	@Inject
	GoodsService goodsService;
	
	
	/********************************* 포스 시스템 부분 ***************************************/
	/********************************* 포스 시스템 부분 ***************************************/
	@RequestMapping(value="posSystem", method=RequestMethod.GET)
	public String posSystem(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "posSystem";

		model.addAttribute("main_content", ContentPage);
		
		

		return "mainPage";
	}
	
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
}
