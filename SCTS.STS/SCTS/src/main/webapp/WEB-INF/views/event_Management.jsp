<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(document).ready(function() {

		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		if (month < 10) {
			month = "0" + month;
		}
		var day = date.getDate();
		
	

		$('#calendar').fullCalendar({
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay,listWeek'
			},
			defaultDate : year + "-" + month + "-" + day,
			navLinks : true, // can click day/week names to navigate views
			selectable : true,
			selectHelper : true,
			select : registerEvent(),
			editable : true,
			eventLimit : true
		});
		
		viewCalendar();
		
	

		/* 취소 버튼 클릭 */
		$('.close').click(function() {
			var object = $('.bgLayer');
			var object2 = $(".modal-layout");

			object.fadeOut(500, function() {
			});

			object2.fadeOut(500, function() {
			});
		});
		
		// 등록된 이벤트 클릭
		$(document).on('click', '.fc-content', function(){
			
			event.stopImmediatePropagation();
			
			if (!$('.bgLayer').length) {
				$('<div class="bgLayer"></div>').appendTo($('body'));
			}
			var object = $(".bgLayer");
			var object2 = $("#modifyEvent");

			var w = $(document).width() + 12;
			var h = $(document).height();

			object.css({
				'width' : w,
				'height' : h
			});

			object.fadeIn(500); // 나타나는 시간 설정

			object2.fadeIn(500);

		});
		
		// 이벤트 더보기
		$(document).on('click', '.fc-more', function(event){
			event.stopImmediatePropagation();
		});
		
		// link로 넘어가는 부분 이벤트 차단
		$(document).on('click', 'a', function(event){
			event.stopImmediatePropagation();
		});
	});
	


	function registerEvent() {
		
		// 빈칸 눌렀을때
		 $(document).on('click', '.fc-widget-content', function(){
			
			if (!$('.bgLayer').length) {
				$('<div class="bgLayer"></div>').appendTo($('body'));
			}
			var object = $(".bgLayer");
			var object2 = $("#registerEvent");

			var w = $(document).width() + 12;
			var h = $(document).height();

			object.css({
				'width' : w,
				'height' : h
			});

			object.fadeIn(500); // 나타나는 시간 설정

			object2.fadeIn(500);

		}); 
		

		
		// 등록
		$('#edit').click(function() {
			
			
			var title = $("#eventName").val();
			var start = $("#eventStart").val(); 
			start = start.replace("T", " ");
			start = start+":00";
		
			var end = $("#eventEnd").val();
			end = end.replace("T", " ")+":00";
			
			
			var eventInfo = $("#eventInfo").val();
			
			$.ajax({
				type:"GET",
				url: "insertEvent",
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "GET"
				},
				data:{
					
					bbsctt_sj : title,
					bbsctt_cn : eventInfo,
					event_begin_de : start,
					event_end_de : end
					
				},
				dataType: "text",
				success: function(result){
					if(result == "success"){
						
						eventData = {
								title : title,
								start : start,
								end : end
						};
						
						
						
						$('#calendar').fullCalendar('renderEvent', eventData, true);
						
						$('.fc-content').append($("<span></span>").text(eventData.end));
						
						
						
					}
				}
			});
			
		
			var object = $('.bgLayer');
			var object2 = $(".modal-layout");

			object.fadeOut(500, function() {
			});

			object2.fadeOut(500, function() {
			});
			
			
		});
		
		
		
		
	}
	
	// 페이지 로딩시 디비에 있는 이벤트 불러옴
	function viewCalendar(){
		$.ajax({
			type:"GET",
			url:"viewCalendar",
			dataType:"jsonp",
			success:function(data){
				
				
				var length = data.result.length;

						
				for(var i=0; i < length; i++){
					var eventData = {
						title: data.result[i].title,
						start: data.result[i].start,
						end:data.result[i].end,
						bbsctt_code : data.result[i].bbsctt_code
						
					}
					
					$('#calendar').fullCalendar('renderEvent', eventData, true);
					
					
				}
	
			}
		});
		

	}
</script>
<style>
body {
	background-color: white;
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>

<div id='calendar'></div>

<div id="registerEvent" class="modal-layout">
	<label for="eventName">이벤트 이름</label><input id="eventName"
		name="eventName" type="text" /> <br> <label for="eventStart">이벤트
		시작일자</label> <input id="eventStart" name="eventStart" type="datetime-local" />
	<br> <label for="eventEnd">이벤트 종료일자</label><input id="eventEnd"
		name="eventEnd" type="datetime-local" /> <br> <label
		for="eventInfo">이벤트 설명</label>
	<textarea id="eventInfo" name="eventInfo" cols="30" rows="5"></textarea>
	<br>

	<button id="edit" class="btn btn-primary">등록</button>
	<button class="btn btn-danger close">닫기</button>
	
</div>



<div id="modifyEvent" class="modal-layout">
	<label for="eventName">이벤트 이름</label><input id="eventName"
		name="eventName" type="text" /> <br> <label for="eventStart">이벤트
		시작일자</label> <input id="eventStart" name="eventStart" type="datetime-local" />
	<br> <label for="eventEnd">이벤트 종료일자</label><input id="eventEnd"
		name="eventEnd" type="datetime-local" /> <br> <label
		for="eventInfo">이벤트 설명</label>
	<textarea id="eventInfo" name="eventInfo" cols="30" rows="5"></textarea>
	<br>

	<button id="modify" class="btn btn-primary">수정</button>
	<button id="delete" class="btn btn-warning">삭제</button>
	<button class="btn btn-danger close">닫기</button>
</div>
