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
import yjc.wdb.scts.service.BBSService;


public class EventNotificationSocket extends TextWebSocketHandler{

	private static Logger logger = LoggerFactory.getLogger(MainSocket.class);
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private List<HashMap> eventNotification;

	@Inject
	private BBSService bbsService;
	
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
		
		int reciever = Integer.parseInt(obj.get("reciever").toString());
		
		eventNotification = bbsService.eventNotification(obj);
		
		System.out.println(eventNotification.toString());
		
		JSONArray jArray = new JSONArray();
		
		for(int i = 0; i < eventNotification.size(); i++){
			JSONObject json = new JSONObject();
			json.put("sender", eventNotification.get(i).get("sender"));
			json.put("reciever", eventNotification.get(i).get("reciever"));
			json.put("bbsctt_code", eventNotification.get(i).get("bbsctt_code"));
			json.put("dateCha", eventNotification.get(i).get("dateCha"));
			json.put("ntcn_code", eventNotification.get(i).get("ntcn_code"));
			json.put("bbsctt_sj", eventNotification.get(i).get("bbsctt_sj"));
			jArray.add(json);
		}

		int notiCnt = bbsService.notiCnt(reciever);
		
		JSONObject result = new JSONObject();
		result.put("eventNotification", jArray);
		result.put("notiCnt", notiCnt);
		
		for(WebSocketSession sess : sessionList){
			sess.sendMessage(new TextMessage(result.toString()));
		}
	
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