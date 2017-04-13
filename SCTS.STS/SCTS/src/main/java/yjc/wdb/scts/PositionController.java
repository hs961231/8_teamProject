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

	// �ȵ���̵忡�� �������� ����
	@RequestMapping(value="/setPositionData")
	public void setPositionData(HttpServletRequest request,@ModelAttribute PositionVO vo) throws Exception{
		// �ȵ���̵忡�� �Ѿ�� �����Ǻ�
		
		// ��� ����
		service.insertPosition(vo);
	}
	
	// ��� �ִ� ���������� �ȵ���̵�� �Ѱ���
	@RequestMapping(value="/getPositionData")
	@ResponseBody
	public PositionVO getPositionData(HttpServletRequest request) throws Exception{
		// ��񿡼� �������� ���� �ٷ� ����
		return service.selectPosition();
	}

}
