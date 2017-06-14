package yjc.wdb.scts.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import yjc.wdb.scts.dao.BillDAO;

public class WebSocketTest extends TextWebSocketHandler{

	private static Logger logger = LoggerFactory.getLogger(WebSocketTest.class);
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	private BillDAO billDAO;

	public void setBillDAO(BillDAO billDAO) {
		this.billDAO = billDAO;
	}

	// Ŭ���̾�Ʈ �������Ŀ� ����Ǵ� �޼ҵ�
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		super.afterConnectionEstablished(session);
		
		
		sessionList.add(session);
		System.out.println("�����");
	}

	// Ŭ���̾�Ʈ�� �� ���ϼ����� �޼����� �������� �� ����Ǵ� �޼ҵ�
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		super.handleTextMessage(session, message);
		
		List<HashMap> list = billDAO.daySales();
		
		System.out.println(message.getPayload());
		
		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage(list.toString()));
		}
	}
	
	// Ŭ���̾�Ʈ�� ������ ������ �� ����Ǵ� �޼ҵ�
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		super.afterConnectionClosed(session, status);
		
		sessionList.remove(session);
		
		System.out.println("�������");
	}
	
	

}
