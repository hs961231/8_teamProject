package yjc.wdb.scts;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.scts.bean.BBScttVO;
import yjc.wdb.scts.bean.EventVO;
import yjc.wdb.scts.service.BBSService;

@Controller
public class EventController {
	
	@Inject
	private BBSService bbsService;
	
	// 캘린더 위 일정 뿌리기
		@RequestMapping(value="viewCalendar", method=RequestMethod.GET)
		public @ResponseBody String viewCalendar(HttpServletRequest request) throws Exception{
			
			String callback = request.getParameter("callback");
			
			List<HashMap> list = bbsService.viewCalendar();
			
			JSONObject viewCalJson;
			JSONArray viewCalArray = new JSONArray();
			
			for(int i=0; i < list.size(); i++){
				
				viewCalJson = new JSONObject();
				
				viewCalJson.put("bbsctt_code", list.get(i).get("bbsctt_code"));
				viewCalJson.put("title", list.get(i).get("bbsctt_sj"));
				viewCalJson.put("bbsctt_cn", list.get(i).get("bbsctt_cn"));
				viewCalJson.put("start", list.get(i).get("event_begin_de").toString());
				viewCalJson.put("end", list.get(i).get("event_end_de").toString());
				
				viewCalArray.add(viewCalJson);
				
			}
			 
			JSONObject json = new JSONObject();
			json.put("result", viewCalArray);
			
			return callback + "(" + json +")";
		}
		

		
		// 이벤트 등록
		@RequestMapping(value="insertEvent", method=RequestMethod.GET)
		public ResponseEntity<String> insertEvent(EventVO eventVO, BBScttVO bbscttVO){
		
			ResponseEntity<String> entity = null;
			
			try {
				bbsService.insertEvent(eventVO, bbscttVO);
				
				
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
				
			} catch (Exception e) {
				
				entity =  new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
				e.printStackTrace();
			}
			
			return entity;
			
			
		}
		

		// 이벤트 수정
		@RequestMapping(value="updateEvent", method=RequestMethod.GET)
		public ResponseEntity<String> updateEvent(EventVO eventVO, BBScttVO bbscttVO){
				
			ResponseEntity<String> entity = null;
					
			try {
					bbsService.updateEvent(eventVO, bbscttVO);						
						
					entity = new ResponseEntity<String>("success", HttpStatus.OK);
						
				} catch (Exception e) {
						
						entity =  new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
						e.printStackTrace();
				}
					
				return entity;
								
		}
		
		@RequestMapping(value="updateDropEvent", method=RequestMethod.GET)
		public ResponseEntity<String> updateDropEvent(EventVO eventVO){
				
			ResponseEntity<String> entity = null;
					
			try {
					bbsService.updateDropEvent(eventVO);					
						
					entity = new ResponseEntity<String>("success", HttpStatus.OK);
						
				} catch (Exception e) {
						
						entity =  new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
						e.printStackTrace();
				}
					
				return entity;
								
		}
		// 이벤트 삭제
		@RequestMapping(value="deleteEvent", method=RequestMethod.GET)
		public ResponseEntity<String> deleteEvent(int bbsctt_code){
				
			ResponseEntity<String> entity = null;
					
			try {
					bbsService.deleteEvent(bbsctt_code);				
						
					entity = new ResponseEntity<String>("success", HttpStatus.OK);
						
				} catch (Exception e) {
						
						entity =  new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
						e.printStackTrace();
				}
					
				return entity;
								
		}
		
		// list event
		
		@RequestMapping(value="listEvent", method=RequestMethod.GET)
		public @ResponseBody String listEvent(HttpServletRequest request, String date1, String date2) throws Exception{
			
			String callback = request.getParameter("callback");
			
			List<HashMap> list = bbsService.listEvent(date1, date2);
			
			JSONObject viewCalJson;
			JSONArray viewCalArray = new JSONArray();
			
			for(int i=0; i < list.size(); i++){
				
				viewCalJson = new JSONObject();
				
				viewCalJson.put("bbsctt_code", list.get(i).get("bbsctt_code"));
				viewCalJson.put("title", list.get(i).get("bbsctt_sj"));
				viewCalJson.put("bbsctt_cn", list.get(i).get("bbsctt_cn"));
				viewCalJson.put("start", list.get(i).get("event_begin_de").toString());
				viewCalJson.put("end", list.get(i).get("event_end_de").toString());
				
				viewCalArray.add(viewCalJson);
				
			}
			 
			JSONObject json = new JSONObject();
			json.put("result", viewCalArray);
			
			return callback + "(" + json +")";
		}
		
		
		@RequestMapping(value="eventOne", method=RequestMethod.GET)
		public @ResponseBody String eventOne(HttpServletRequest request, int code) throws Exception{
			
			String callback = request.getParameter("callback");
			
			List<HashMap> list = bbsService.eventOne(code);
			
			JSONObject viewCalJson;
			JSONArray viewCalArray = new JSONArray();
			
			for(int i=0; i < list.size(); i++){
				
				viewCalJson = new JSONObject();
				
				viewCalJson.put("bbsctt_code", list.get(i).get("bbsctt_code"));
				viewCalJson.put("title", list.get(i).get("bbsctt_sj"));
				viewCalJson.put("bbsctt_cn", list.get(i).get("bbsctt_cn"));
				viewCalJson.put("start", list.get(i).get("event_begin_de").toString());
				viewCalJson.put("end", list.get(i).get("event_end_de").toString());
				
				viewCalArray.add(viewCalJson);
				
			}
			 
			JSONObject json = new JSONObject();
			json.put("result", viewCalArray);
			
			return callback + "(" + json +")";
		}
		
		

}
