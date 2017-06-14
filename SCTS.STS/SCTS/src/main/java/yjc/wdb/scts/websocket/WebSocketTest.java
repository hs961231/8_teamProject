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

	// 클라이언트 연결이후에 실행되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		super.afterConnectionEstablished(session);
		
		
		sessionList.add(session);
		System.out.println("연결됨");
	}

	// 클라이언트가 웸 소켓서버로 메세지를 전송했을 때 실행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		super.handleTextMessage(session, message);
		
		List<HashMap> list = billDAO.daySales();
		
		System.out.println(message.getPayload());
		
		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage(list.toString()));
		}
	}
	
	// 클라이언트가 연결을 끊었을 때 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		super.afterConnectionClosed(session, status);
		
		sessionList.remove(session);
		
		System.out.println("연결끊김");
	}
	
	

}
