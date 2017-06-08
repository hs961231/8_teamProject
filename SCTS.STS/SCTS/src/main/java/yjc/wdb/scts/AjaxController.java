package yjc.wdb.scts;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import yjc.wdb.scts.bean.BeaconVO;
import yjc.wdb.scts.service.BeaconService;
import yjc.wdb.scts.service.TileService;

@Controller
public class AjaxController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Inject
	TileService tileService;
	
	@Inject
	BeaconService beaconService;

	@RequestMapping(value="shopTileClick", method=RequestMethod.POST)
	@ResponseBody
	public String shopTileClick(@RequestParam("X_index") int X_index, @RequestParam("Y_index") int Y_index) throws Exception {
		
		logger.info("X = " + X_index + "  Y = " + Y_index);
		
		HashMap<String, String> Map_XY = new HashMap<String, String>();
		Map_XY.put("tilelc_crdnt_x", "" + X_index);
		Map_XY.put("tilelc_crdnt_y", "" + Y_index);
		
		HashMap<String, String> tile = tileService.selectTile_LocationOne(Map_XY);
		
		String str = new Gson().toJson(tile);
		
		System.out.println(str);
		
		return str;
	}
	

	@RequestMapping(value="getBeaconList", method=RequestMethod.POST)
	@ResponseBody
	public String getBeacon() throws Exception {
		
		int bhf_code = 1;
		List<BeaconVO> beaconList = beaconService.selectBeaconList(bhf_code);
		
		String str = new Gson().toJson(beaconList);
		
		System.out.println(str);
		
		return str;
	}
	

	@RequestMapping(value="setTileBeacon", method=RequestMethod.POST)
	@ResponseBody
	public String setTileBeacon() throws Exception {
		
		// 현재 이부분에 실제 비콘정보가 타일로 들어가야함.
		/*
		 *  현재 shop_Register.js 에 이부분과 연동되는 아작스가 있지만
		 *  제대로 코딩이 되지 않음 양쪽 부분 다 손봐야함
		 */
		return "success";
	}
	
}
