package yjc.wdb.scts;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import yjc.wdb.scts.bean.BeaconVO;
import yjc.wdb.scts.service.BeaconService;
import yjc.wdb.scts.service.Branch_officeService;
import yjc.wdb.scts.service.TileService;

@Controller
public class AjaxController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Inject
	TileService tileService;
	
	@Inject
	BeaconService beaconService;
	
	@Inject
	Branch_officeService branchService;

	/* shop_Register.js
	 * 매장등록 페이지에서 도면위의 타일을 클릭햇을때 발생하는 아작스 통신
	 * 해당 타일의 정보를 디비에서 가져와서 보내준다
	 */
	@RequestMapping(value="shopTileClick", method=RequestMethod.POST)
	@ResponseBody
	public String shopTileClick(@RequestParam("floor") int floor,
			@RequestParam("X_index") int X_index, @RequestParam("Y_index") int Y_index) throws Exception {
		
		logger.info("X = " + X_index + "  Y = " + Y_index);
		
		HashMap<String, String> Map_XY = new HashMap<String, String>();
		Map_XY.put("floor", "" + floor);
		Map_XY.put("tilelc_crdnt_x", "" + X_index);
		Map_XY.put("tilelc_crdnt_y", "" + Y_index);
		
		HashMap<String, String> tile = tileService.selectTile_LocationOne(Map_XY);
		
		String str = new Gson().toJson(tile);
		
		System.out.println(str);
		
		return str;
	}
	
	/* shop_Register.js
	 * 매장등록 페이지에서 타일클릭 후 타일에 비콘이 등록되어 있지 않을 때
	 * 비콘등록 버튼을 누를 시 디비에서 사용가능한 비콘의 정보들을 불러와서 아작스로 전송 
	 */
	@RequestMapping(value="getBeaconList", method=RequestMethod.POST)
	@ResponseBody
	public String getBeacon() throws Exception {
		
		int bhf_code = 1;
		List<BeaconVO> beaconList = beaconService.selectBeaconList(bhf_code);
		
		String str = new Gson().toJson(beaconList);
		
		System.out.println(str);
		
		return str;
	}
	

	/* dashBoard.js
	 * 대쉬보드에서 도면의 타일을 선택할 경우 발생하는 아작스 통신
	 * 현재 이 메서드는 아직 제대로 된 데이터를 긁어오는 것이 아님
	 */
	@RequestMapping(value="dashBoardTile", method=RequestMethod.POST)
	@ResponseBody
	public String dashBoardTile(@RequestParam("X_index") int X_index, @RequestParam("Y_index") int Y_index) throws Exception {
		
		logger.info("X = " + X_index + "  Y = " + Y_index);
		
		HashMap<String, String> Map_XY = new HashMap<String, String>();
		Map_XY.put("tilelc_crdnt_x", "" + X_index);
		Map_XY.put("tilelc_crdnt_y", "" + Y_index);
		
		HashMap<String, String> tile = tileService.selectTile_LocationOne(Map_XY);
		
		String str = new Gson().toJson(tile);
		
		System.out.println(str);
		
		return str;
	}
	
	/* shop_Register.js
	 * 
	 */
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

	/* shop_Register.js
	 * 매장 등록 버튼 클릭시 아작스로 존재하는 매장과, 층정보를 db에서 검색하여 넘겨주는 역할
	 */

	@RequestMapping(value="shop_RegisterForm", method=RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String shop_RegisterForm() throws Exception{
		
		
		// 지점 정보들 불러오기
		List<HashMap<String, String>> branchList = branchService.selectBranchNameList();
		
		String str = new Gson().toJson(branchList);
		
		System.out.println(str);
		
		return str;
	}
}
