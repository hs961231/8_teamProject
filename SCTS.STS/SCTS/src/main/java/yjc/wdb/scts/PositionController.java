package yjc.wdb.scts;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.scts.bean.PositionVO;
import yjc.wdb.scts.service.PositionService;

@Controller
public class PositionController {

	@Inject
	PositionService service;

	// �ȵ���̵忡�� �������� ����
	@RequestMapping(value="/setPositionData")
	@ResponseBody
	//public String setPositionData(HttpServletRequest request,@ModelAttribute PositionVO vo) throws Exception{
	//public String setPositionData(HttpServletRequest request, @RequestBody Map<String, Object> positionJson) throws Exception{	
	//public String setPositionData(HttpServletRequest request) throws Exception{
	public String setPositionData(HttpServletRequest request) throws Exception{
		// �Ѿ�� ���ڿ��� json ��ü�� ��ȯ
		JSONObject positionJson = (JSONObject) new JSONParser().parse( request.getParameter("PositionVO") );
		
		PositionVO vo = new PositionVO();
		try {
			// ���ڿ� ������ ��¥�ð� ���� timestamp������ ��ȯ
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		    java.util.Date parsedDate = dateFormat.parse( (String) positionJson.get("currentTime") );
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    
		    // vo��ü�� �� ����
			vo.setCurrentTime( timestamp );
			vo.setMajor( Integer.parseInt(positionJson.get("major").toString() ) );
			vo.setMinor( Integer.parseInt(positionJson.get("minor").toString() ) );
			vo.setStayTime( Integer.parseInt(positionJson.get("stayTime").toString() ) );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// ��� ����
		service.insertPosition(vo);
		
		return "SUCCESS";
	}
	
	// ��� �ִ� ���������� �ȵ���̵�� �Ѱ���
	@RequestMapping(value="/getPositionData")
	@ResponseBody
	public PositionVO getPositionData(HttpServletRequest request) throws Exception{
		// ��񿡼� �������� ���� �ٷ� ����
		return service.selectPosition();
	}

}
