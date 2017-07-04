package yjc.wdb.scts.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import yjc.wdb.scts.dao.BBSDAO;


public class EventNotificationSocket extends TextWebSocketHandler{

	private static Logger logger = LoggerFactory.getLogger(MainSocket.class);
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private List<HashMap> eventNotification;
	private List<HashMap> currentSession;

	@Inject
	private BBSDAO bbsDAO;
	
	// Ŭ���̾�Ʈ �������Ŀ� ����Ǵ� �޼ҵ�
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		super.afterConnectionEstablished(session);
		
		
		sessionList.add(session);
		logger.info("{}�� {} �����", session.getUri(), session.getId());
	}

	// Ŭ���̾�Ʈ�� �� ���ϼ����� �޼����� �������� �� ����Ǵ� �޼ҵ�
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		super.handleTextMessage(session, message);
		
		logger.info("{} ����", message.getPayload());
		
		JSONParser jsonp = new JSONParser();
		JSONObject obj = null;
		obj = (JSONObject) jsonp.parse(message.getPayload());
		String branch = obj.get("branch").toString();
		String sender = obj.get("sender").toString();
		String reciever = obj.get("reciever").toString();
	
		
		currentSession.add((HashMap) new HashMap().put(branch, session.getId()));
		
		System.out.println("�����!"+currentSession.toString());
		
		eventNotification = bbsDAO.eventNotification();
		
		JSONArray jArray = new JSONArray();
		
		for(int i = 0; i < eventNotification.size(); i++){
			JSONObject json = new JSONObject();
			json.put("goods_nm", eventNotification.get(i).get("goods_nm"));
			json.put("goods_netIncome", eventNotification.get(i).get("goods_netIncome"));
			json.put("totalPrice", eventNotification.get(i).get("totalPrice"));
			
			jArray.add(json);
		}

		JSONObject result = new JSONObject();
		result.put("eventNotification", jArray);
		
/*		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage(result.toString()));
		}
		*/
	}
	
	// Ŭ���̾�Ʈ�� ������ ������ �� ����Ǵ� �޼ҵ�
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		super.afterConnectionClosed(session, status);
		System.out.println(status);
		sessionList.remove(session);
		logger.info("{} ���� ����", session.getId());
	}
	
	

}