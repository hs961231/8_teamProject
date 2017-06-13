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
		
		var options = {
				header : {
					left : 'prev,next today',
					center : 'title',
					right : 'month,basicWeek,agendaDay,listWeek'
				},
				views : {
					month : {
						titleFormat : 'YYYY-MM'
					},
					week : {
						titleFormat : 'YYYY-MM, DD'
					}, 
					day : {
						titleFormat : 'YYYY-MM-D'
					}
				},
				listDayFormat: false,
				timeFormat: 'HH:mm',
				defaultDate : year + "-" + month + "-" + day,
				navLinks : true, // can click day/week names to navigate views
				selectable : true,
				editable : true,
				selectHelper : true,
				select : registerEvent(),
				eventClick : function(event){
					
					alert(event.id);

					$("#modifyEvent").modal();
					
					var start = event.start;
					start = start.format("YYYY-MM-DD HH:mm");
					start = start.replace(" ", "T");
			
					var end = event.end
					end = end.format("YYYY-MM-DD HH:mm");
					end = end.replace(" ", "T");
				
					$("#modifyEvent .code").val(event.id);
					$("#modifyEvent .eventName").val(event.title);
					$("#modifyEvent .eventStart").val(start);
					$("#modifyEvent .eventEnd").val(end);
					$("#modifyEvent .eventInfo").val(event.bbsctt_cn);
					
				
					
					
					
				}
					
				
			}
		
		
		$('#calendar').fullCalendar(options);
		
		
		

		var text = $(".fc-center h2").text();
		
		
		viewCalendar(text);

		year = parseInt(text.split("-")[0]);

		month = parseInt(text.split("-")[1].split("0")[1]);

		$('.fc-prev-button').click(function(){


			month = month - 1;
			if(month <= 0){
				year = year -1;
				month = 12;
			}

			if(month < 10){
				month = "0"+month;
			}

			$(".fc-center h2").text(year+"-"+month);
			
			var date = $(".fc-center h2").text();
			


		});

		$('.fc-next-button').click(function(event){
	
			if(month < 10){
				month = parseInt( $(".fc-center h2").text().split("-")[1].split("0")[1]);

			}else{
				month = parseInt( $(".fc-center h2").text().split("-")[1]);
			}
			
			month = month + 1;
			if(month >= 13){
				year = year +1;
				month = 1;
			}

			if(month < 10){
				month = "0"+month;
			}
		
			
			
			$(".fc-center h2").text(year+"-"+month);
			
			
			if(month < 10){
				month = parseInt(month.split("0")[1]);
			}	

		});
		
	
		$(".fc-basicWeek-button").click(function(){
			
	
			$(".fc-prev-button").attr("disabled", true);
			$(".fc-next-button").attr("disabled", true);
	
				var date = new Date();
				var year = date.getFullYear();
				var month = date.getMonth() + 1;
				
				if(month <= 10){
					month = "0"+month;
				}
				
			

				var i = 3;
				$(".fc-day-header").each(function(){
					date.setDate(new Date().getDate()-i)
					$(this).text(date.getMonth() + 1 + "/" + date.getDate());
					i--;
				});
				
				var a = parseInt($(".fc-day-header:first").text().split("/")[1]);
				var b = parseInt($(".fc-day-header:last").text().split("/")[1]);
				if(a <= 10){
					a = "0"+a;
				}
				if(b <= 10){
					b = "0"+b;
				}
				
				$(".fc-center h2").text(year+"-"+month+", " + a +"-" + b);

		});
		
		$(".fc-agendaDay-button").click(function(){
			
			
			$(".fc-prev-button").attr("disabled", true);
			$(".fc-next-button").attr("disabled", true);


		});
		

		$(".fc-listWeek-button").click(function(){
			
			
			$(".fc-prev-button").attr("disabled", true);
			$(".fc-next-button").attr("disabled", true);
			
			var date = new Date();
			var i = 3;
			
			$(".fc-list-heading").each(function(){
				
				date.setDate(new Date().getDate()-i)
				
				var month = date.getMonth()+1;
				var day = date.getDate();
				
				if(month < 10){
					month = "0"+month;
				}
				if(day < 10){
					day = "0" + day;
				}
				
				$(this).attr("data-date", date.getFullYear()+"-"+month+"-"+day);
				
				i--;
			});
			
			$(".fc-list-heading-main").each(function(){
				
				$(this).attr("data-goto", $(this).parent().parent().attr("data-date"));
			});

			
			$(".fc-list-heading-alt").each(function(){
				
				var dayList = new Array('일', '월', '화', '수', '목', '금', '토');
				
				var day = new Date($(this).parent().parent().attr("data-date")).getDay();
				
					
				
				
				$(this).attr("data-goto", '{"date": "'+$(this).parent().parent().attr("data-date")
					+'", "type": "day"}');
				$(this).text($(this).parent().parent().attr("data-date")+" " + dayList[day]);
			});
			
			
			var year = $(".fc-list-heading:first").attr("data-date").split("-")[0];
			var month = $(".fc-list-heading:first").attr("data-date").split("-")[1];
			var date =  $(".fc-list-heading:first").attr("data-date").split("-")[2];
			
			
			if(date.match("0.")){
				
				alert(date);

				date = parseInt(date.split("0")[1]);
			}else{
				date = parseInt(date);
			}
	
			
			var date2 = date + 7;
			
			if(date < 10){
				date = "0" + date;
			}
			
			if(date2 < 10){
				date2 = "0" + date2;
			}
			
			$(".fc-center h2").text(year+"-"+month+", "+date+" - "+date2);

		});
		

		/* 취소 버튼 클릭 */
		$('.close').click(function() {

			
			$(".modal-layout").modal('hide');

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
			
			$("#registerEvent").modal();

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
	function viewCalendar(date){
		$.ajax({
			type:"GET",
			url:"viewCalendar",
			dataType:"jsonp",
			data:{
				date : date
			},
			success:function(data){
				
				
				var length = data.result.length;
						
				for(var i=0; i < length; i++){
					
					var start = (data.result[i].start).split(".")[0];
					var end = (data.result[i].end).split(".")[0];
				
					
					var eventData = {
						title: data.result[i].title,
						start:moment(start).format(),
						end :moment(end).format(),
						id : data.result[i].bbsctt_code,
						bbsctt_cn : data.result[i].bbsctt_cn
						
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

	<input type ="hidden" value="" class="code"/>
	<label for="eventName">이벤트 이름</label><input class="eventName"
		name="eventName" type="text" /> <br> <label for="eventStart">이벤트
		시작일자</label> <input class="eventStart" name="eventStart" type="datetime-local" />
	<br> <label for="eventEnd">이벤트 종료일자</label><input class="eventEnd"
		name="eventEnd" type="datetime-local" /> <br> <label
		for="eventInfo">이벤트 설명</label>
	<textarea class="eventInfo" name="eventInfo" cols="30" rows="5"></textarea>
	<br>

	<button id="modify" class="btn btn-primary">수정</button>
	<button id="delete" class="btn btn-warning">삭제</button>
	<button class="btn btn-danger close">닫기</button>
</div>

