<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.row{
	width: 1000px;
}
.wrap {
	font-family: "Malgun Gothic";
	font-size: 20px;
	width: 1200px;
	height: 100%;
}
.modal-content{
	margin-top: 50%;
}
#editor-title {
	width: 70%;
}
.btn-xs{
    margin-bottom:5px;
	float:right;
}
#editor-content {
	width: 70%;
	background-color:#D8D8D8;
}

.textarea {
	width: 100%;
	max-width: 900px;
	resize: none;
	/* padding: 1.1em; prevents text jump on Enter keypress */
	line-height: 1.6; /* 줄 간격 */
	overflow: visible;
	background-color:#D8D8D8;
}

.title-area {
	resize: none;
	font-size: 12pt;
	border: none;
	overflow y: none;
}

.content-area {
	min-height: 500px;
	font-size: 12pt;
	border: none;
	margin-top: 10px;
}

#editor-textArea {
	margin-top: 10px;
	border-top: 1px solid;
	width: 100%;
	height: 100%;
}
</style>
<!-- 헤더 부분 -->
<div class="row">
	<div class="col-lg-12" style="margin-left:2%;">
		<h3 class="page-header">
			<i class="icon_genius"></i> 고객 센터
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="icon_genius"></i>Help</li>
		</ol>
		<form role="form" action="help_Update" method="post">
			<!-- 컨트롤러에 값을 들고갑시다. -->
			<input type="hidden" name="bno" value="${helpVO.bno }"> 
			<input type="hidden" name="page" value="${cri.page }"> 
			<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
			<input type="hidden" name="searchType" value="${cri.searchType }">
			<input type="hidden" name="keyword" value="${cri.keyword }">
			<input type="hidden" name="msg" value="${cri.msg}">
		</form>
		<!-- 텍스트 에리어 부분 -->
		<div class="wrap" id="wrap">
			<div id="editor-content">
				<div id="editor-title">
					<textarea class="title-area textarea" name="title"
						readonly="readonly">${helpVO.title }</textarea>
				</div>
				<div id="editor-textArea">
					<textarea class="content-area textarea" name="content"
						readonly="readonly">${helpVO.content }</textarea>
				</div>
			</div>
			<div class="editor-footer">
				<button type="submit" id="upList" class="btn btn-primary">수정</button>
				<button type="submit" id="delList" class="btn btn-danger">삭제</button>
				<button type="submit" id="moveList" class="btn btn-primary">리스트로</button>
			</div>
		</div>
	</div>
<!-- 댓글 부분 -->
	<div class="col-md-12">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title" style="margin-left:2%; margin-bottom:2%;">댓글</h3>
			</div>
		</div>
		<div class="box-footer">
			
		</div>
	</div>

<ul class="timeline">
	<li class="time-label" id="repliesDiv"><span class="bg-green">
	</span></li>
</ul>
<!-- <input class="form-control" type="text" placeholder="내용 입력..."
	id="newReplyText"> -->
<%-- <label for="newReplyWriter">Writer</label>
 	 <input class="form-control" type="text" value="${helpVO.writer }"
 	 id="newReplyWriter"> --%>
<div class="box-body" style="margin-left:1%;">
			<label for="newReplyText">내용</label>
				<textarea class="title-area textarea" name="title" id="newReplyText"></textarea>
			<button type="submit" class="btn btn-primary" id="replyAddBtn" style="margin-right:-50%;">댓글 작성</button>
			
</div>
<div class='text-center'>
	<ul id="pagination" class="pagination pagination-sm no-margin">
	
	</ul>
</div>
<!-- 댓글 수정의 모달 부분 -->
<div id="modifyModal" class="modal modal-primary fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title"></h4>
		</div>
		<div class="modal-body" data-rno>
			<p><input type="text" id="replytext" class="form-control"></p>
		</div>
		<div class="modal-footer">  <!-- data-dismiss="modal" 가 있으면... 해결이 됨. -->
			<button type="button" class="btn btn-info" id="replyModBtn" data-dismiss="modal">수정</button>
			<button type="button" class="btn btn-primary" id="replyDelBtn" data-dismiss=modal>삭제</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		</div>
		</div>
	</div>
</div>
</div>
<!-- <a class="btn-xs" data-target="#replyDelBtn">삭제</a>
<a class="btn-xs" data-toggle="modal" data-target="#modifyModal">수정</a> -->
<!-- 밑에 {{prettifyDate에서 regidate는 변수명 또는 DB칼럼과 같아야함. -->
<script id="template" type="text/x-handlebars-template">
{{#each .}}
<li class="replyLi" data-rno={{rno}}>
 <div class="timeline-item" style="border-top-style:dotted;">
 	<span class="time">
	<a class="btn-xs" data-toggle="modal" data-target="#modifyModal">수정</a>
 	<i class="fa fa-clock-o"></i>{{prettifyDate regidate}}</span>
	<h4><div class="timeline-body">{{replytext}}</div></h4>
	<h5 class="timeline-header">작성자 : {{replyer}}</h5>
	 <div class="timeline-footer">
	</div>
   </div>
  </li>
{{/each}}
</script>
<script>
	var bno = ${helpVO.bno};
	var replyPage = 1;   /* 수정이나 삭제한 뒤에 사용자가 보던 댓글의 페이지 번호로 돌아가기 위해 유지되는 데이터 */

	/* 댓글 목록을 띄우는 버튼. */
 	$(document).ready(function(){
 	
		if($(".timeline li").size() > 1){  /* 얘는 뭐야? */
			return;
		}
		getPage("cri/" +bno+ "/1");  /* 넘겨준 bno의 댓글 목록 리스트 중 1을 띄움.*/
	});


	/////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////// handlebars 	   /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	/* handlebars는 helper 기능을 이용해서 데이터 상세 처리에 필요한 기능들을 처리, 기능이 없는 경우 registerHe..처럼 사용자가 새로운 기능 추가 가능  */
	Handlebars.registerHelper("prettifyDate", function(timeValue){
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear(); 
		var month = dateObj.getMonth()+1;
		var date = dateObj.getDate();
		return year+"/"+month+"/"+date;  /* 각 년 월 일 얻어서 넘겨준다. */
	});

	var printData = function(replyArr, target, templateObject){
		
		var template = Handlebars.compile(templateObject.html());
		var html = template(replyArr);
		$(".replyLi").remove();
		target.after(html);
	/* 	console.log(html);   *//* 현재 html 변수에  리스트들이 저장 되있다. */

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////// 위 tample을 이용하여 페이지 처리 //////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	/* getPage() 특정한 게시물에 대한 페이징 처리를 위해 */
	function getPage(pageInfo){  /* [1]pageInfo = 페이지 번호를 파라미터로 전달받은 뒤 */
		/* [2]jquery의 getJSON()으로 JSON 호출해 댓글의 목록 데이터를 처리  */
		$.getJSON(pageInfo,function(data){
			printData(data.list, $("#repliesDiv"), $('#template')); // (1)댓글 목록 데이터는 list랑 pageMake로 구성 후  
			printPaging(data.pageMaker, $(".pagination")); // (2)Data / Paging으로 이를 처리.
		});
	}
	
	/* 페이지네이션 계산 */
	var printPaging = function(pageMaker, target){
		var str ="";
		if(pageMaker.prev){
			str += "<li><a href='" +(pageMaker.startPage-1)+"'> << </a></li>";
		}	
		
		for(var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){
			var strClass= pageMaker.cri.page == i?'class=active':'';    /* 여기서 li태그에 class="active"를 붙여주네 */
			str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";	  /* str에는 <li class="active"><a href="1">1</a></li>가 들어간다. "*/
			/* console.log("읭2"+str); */
		}
		
		if(pageMaker.next){
			str += "<li><a href='"+(pageMaker.endPage +1)+"'> >> </a></li>";
		}
		target.html(str);
		/* console.log("프린트 페인팅 " + str); */
	};

	/* 댓글 목록을 띄우는 버튼. */
	 /* 	$('#repliesDiv').on("click",function(){
		if($(".timeline li").size() > 1){ 
			return;			
		}
		getPage("cri/" +bno+ "/1");    넘겨준 bno의 댓글 목록 리스트 중 1을 띄움.  
	}); */
	
	/* 페이징 처리 (현재 몇 페이지인지) */
	$('.pagination').on("click", "li a", function(event){
		event.preventDefault();
		
		replyPage = $(this).attr("href");
/* 		window.alert(replyPage);*/		
		/* console.log("페이지네이션 : " + replyPage); */
		
		getPage("cri/"+bno+"/" + replyPage);
	});
	
	/* 댓글 등록 작업 */
	$("#replyAddBtn").on("click", function(){
		/* var replyerObj = $("#newReplyWriter"); */
		var replytextObj = $("#newReplyText");
		/* var replyer = replyerObj.val(); */
		var replytext = replytextObj.val();

		
		$.ajax({
			type : 'post',
			url : 'replies/',
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST" },
			dataType : 'text',
			data : JSON.stringify({bno : bno, replytext:replytext}),
			success:function(result){
				console.log("result : " + result);
				if(result == "SUCCESS"){
					window.alert("등록 되었습니다.");
					replyPage = 1;
					getPage("cri/"+bno+"/" +replyPage);
					/* replyerObj.val(""); */
					replytextObj.val("");
				}
		}});
	});
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////// 모달을 이용한 댓글 수,삭,돌 처리 ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	/* 모달 창이 나타나게 하는 처리는 부트스트랩걸 그대로 쓴다. */
	$(".timeline").on("click", ".replyLi", function(event){
		var reply = $(this);
		
		$("#replytext").val(reply.find(".timeline-body").text());
		$(".modal-title").html(reply.attr("data-rno"));
		/* window.alert($("#replytext" + "ㅅㅂ")); */
	});
	
	/* 수정 처리 */
	$("#replyModBtn").on("click", function(){
		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();
		
		$.ajax({
			type : 'put',
			url : 'update/' + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT" },
			data : JSON.stringify({replytext : replytext}),
			dataType : 'text',
			success : function(result){
				console.log("result : " + result);
				if(result == 'SUCESS'){
					getPage("cri/"+bno+"/" + replyPage);
				}
			}
		});
	});
	
	/* 삭제 처리 */
	$("#replyDelBtn").on("click", function(){
		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();
		
		$.ajax({
			type : 'delete',
			url : 'delete/'+ rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result){
				console.log("result : " + result);
				if(result == 'SUCCESS'){
					getPage("cri/"+bno+"/" + replyPage);
				}
			}
		});
	});
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////// 게시글 수정,삭제,돌아가기  		  ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	$(document).ready(function() {
		var msg = ${msg};
		
		var formObj = $("form[role='form']");

		$('#upList').on('click', function() {
			formObj.attr('action', "updateHelp");
			formObj.attr('method', 'get');
			formObj.submit();
		});

		$('#delList').on("click", function() {
			if (confirm("삭제하시겠습니까?")) {
				if (msg == true) {
					formObj.attr('action', 'deleteHelp');
					formObj.attr('method', 'post');
				} else {
					formObj.attr('action', 'deleteHelp');
					formObj.attr('method', 'get');
				}
				formObj.submit();
				window.alert("삭제되었습니다.");
			} else {
				return false;
			}
		});

		$('#moveList').on('click', function() {
			formObj.attr("method", "get");

			if (msg == true) { /* 일반 리스트 컨트롤러에서 넘어 왔을 경우 */
				formObj.attr("action", "help_List");
			} else { /* 검색 컨트롤러에서 넘어 왔을 경우 */
				formObj.attr("action", "searchHelp");
			}
			formObj.submit();
		});

	}); 
	
	
	
</script>