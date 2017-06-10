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
	 * ������ ���������� �������� Ÿ���� Ŭ�������� �߻��ϴ� ���۽� ���
	 * �ش� Ÿ���� ������ ��񿡼� �����ͼ� �����ش�
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
	 * ������ ���������� Ÿ��Ŭ�� �� Ÿ�Ͽ� ������ ��ϵǾ� ���� ���� ��
	 * ���ܵ�� ��ư�� ���� �� ��񿡼� ��밡���� ������ �������� �ҷ��ͼ� ���۽��� ���� 
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
	 * �뽬���忡�� ������ Ÿ���� ������ ��� �߻��ϴ� ���۽� ���
	 * ���� �� �޼���� ���� ����� �� �����͸� �ܾ���� ���� �ƴ�
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
		
		// ���� �̺κп� ���� ���������� Ÿ�Ϸ� ������.
		/*
		 *  ���� shop_Register.js �� �̺κа� �����Ǵ� ���۽��� ������
		 *  ����� �ڵ��� ���� ���� ���� �κ� �� �պ�����
		 */
		return "success";
	}

	/* shop_Register.js
	 * ���� ��� ��ư Ŭ���� ���۽��� �����ϴ� �����, �������� db���� �˻��Ͽ� �Ѱ��ִ� ����
	 */

	@RequestMapping(value="shop_RegisterForm", method=RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String shop_RegisterForm() throws Exception{
		
		
		// ���� ������ �ҷ�����
		List<HashMap<String, String>> branchList = branchService.selectBranchNameList();
		
		String str = new Gson().toJson(branchList);
		
		System.out.println(str);
		
		return str;
	}
}
