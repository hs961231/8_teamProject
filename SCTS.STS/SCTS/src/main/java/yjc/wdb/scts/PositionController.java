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

	// 안드로이드에서 비콘정보 전송
	@RequestMapping(value="/setPositionData")
	@ResponseBody
	public String setPositionData(HttpServletRequest request) throws Exception{
		// 넘어온 문자열을 json 객체로 변환
		JSONObject positionJson = (JSONObject) new JSONParser().parse( request.getParameter("PositionVO") );
		
		PositionVO vo = new PositionVO();
		try {
			// 문자열 형태의 날짜시간 값을 timestamp값으로 변환
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		    java.util.Date parsedDate = dateFormat.parse( (String) positionJson.get("currentTime") );
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    
		    // vo객체에 값 저장
			vo.setCurrentTime( timestamp );
			vo.setMajor( Integer.parseInt(positionJson.get("major").toString() ) );
			vo.setMinor( Integer.parseInt(positionJson.get("minor").toString() ) );
			vo.setStayTime( Integer.parseInt(positionJson.get("stayTime").toString() ) );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// 디비에 저장
		service.insertPosition(vo);
		
		return "SUCCESS";
	}
	
	// 디비에 있는 비콘정보를 안드로이드로 넘겨줌
	@RequestMapping(value="/getPositionData")
	@ResponseBody
	public PositionVO getPositionData(HttpServletRequest request) throws Exception{
		// 디비에서 비콘정보 빼서 바로 리턴
		return service.selectPosition();
	}

}
