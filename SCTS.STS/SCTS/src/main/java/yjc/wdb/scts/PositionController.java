package yjc.wdb.scts;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.scts.bean.PositionVO;
import yjc.wdb.scts.service.PositionService;

@Controller
public class PositionController {

	@Inject
	PositionService service;

	// 안드로이드에서 비콘정보 전송
	@RequestMapping(value="/setPositionData")
	public void setPositionData(HttpServletRequest request,@ModelAttribute PositionVO vo) throws Exception{
		// 안드로이드에서 넘어온 정보판별
		
		// 디비에 저장
		service.insertPosition(vo);
	}
	
	// 디비에 있는 비콘정보를 안드로이드로 넘겨줌
	@RequestMapping(value="/getPositionData")
	@ResponseBody
	public PositionVO getPositionData(HttpServletRequest request) throws Exception{
		// 디비에서 비콘정보 빼서 바로 리턴
		return service.selectPosition();
	}

}
