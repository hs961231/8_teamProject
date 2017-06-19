<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script src="resources/customjs/event_Management.js"></script>
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

<div id="calendar"></div>

<div id="registerEvent" class="modal-layout">
	<label for="eventName">이벤트 이름</label>
	<input class="eventName"
		name="eventName" type="text" /> <br> 
	<label for="eventStart">이벤트
		시작일자</label> 
	<input class="eventStart" name="eventStart" type="datetime-local" />
	<br> <label for="eventEnd">이벤트 종료일자</label>
	<input class="eventEnd"
		name="eventEnd" type="datetime-local" /> <br> <label
		for="eventInfo">이벤트 설명</label>
	<textarea class="eventInfo" name="eventInfo" cols="30" rows="5"></textarea>
	<br>

	<button id="edit" class="btn btn-primary">등록</button>
	<button class="btn btn-danger close">닫기</button>

</div>



<div id="modifyEvent" class="modal-layout">

	<input type="hidden" value="" class="code" /> <label for="eventName">이벤트
		이름</label><input class="eventName" name="eventName" type="text" /> <br>
	<label for="eventStart">이벤트 시작일자</label> <input class="eventStart"
		name="eventStart" type="datetime-local" /> <br> <label
		for="eventEnd">이벤트 종료일자</label><input class="eventEnd" name="eventEnd"
		type="datetime-local" /> <br> <label for="eventInfo">이벤트
		설명</label>
	<textarea class="eventInfo" name="eventInfo" cols="30" rows="5"></textarea>
	<br>

	<button id="modify" class="btn btn-primary">수정</button>
	<button id="delete" class="btn btn-warning">삭제</button>
	<button class="btn btn-danger close">닫기</button>
</div>

