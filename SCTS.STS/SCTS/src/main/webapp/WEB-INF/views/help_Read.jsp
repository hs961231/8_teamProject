<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#wrap {
	font-family: "Malgun Gothic";
	font-size: 20px;
	width: 1200px;
	height: 100%;
}

#editor-title {
	width: 70%;
}

#editor-content {
	width: 70%;
	height: 70%;
}

.textarea {
	width: 100%;
	max-width: 900px;
	resize: none;
	/* padding: 1.1em; prevents text jump on Enter keypress */
	line-height: 1.6; /* 줄 간격 */
	overflow: visible
}

.title-area {
	resize: none;
	font-size: 12pt;
	border: none;
	background-color: #EEEEEE;
	overflow y: none;
}

.content-area {
	min-height: 500px;
	font-size: 12pt;
	border: none;
	margin-top: 10px;
	background-color: #EEEEEE;
}

#editor-textArea {
	margin-top: 10px;
	border-top: 1px solid;
	width: 100%;
	height: 100%;
}
</style>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="icon_genius"></i> 고객 센터
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="icon_genius"></i>Help</li>
		</ol>
		<form role="form" action="help_Update" method="post">
			<input type="hidden" name="bno" value="${helpVO.bno }"> <input
				type="hidden" name="page" value="${cri.page }"> <input
				type="hidden" name="perPageNum" value="${cri.perPageNum}"> <input
				type="hidden" name="searchType" value="${cri.searchType }">
			<input type="hidden" name="keyword" value="${cri.keyword }">
			<input type="hidden" name="msg" value="${cri.msg}">
		</form>
		<div class="wrap">
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
				<button type="submit" class="btn btn-warning">수정</button>
				<button type="submit" class="btn btn-danger">삭제</button>
				<button type="submit" class="btn btn-primary">리스트로</button>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		var msg = ${msg};
		
		var formObj = $("form[role='form']");

		$('.btn-warning').on('click', function() {
			formObj.attr('action', "updateHelp");
			formObj.attr('method', 'get');
			formObj.submit();
		});

		$('.btn-danger').on("click", function() {

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

		$('.btn-primary').on('click', function() {
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