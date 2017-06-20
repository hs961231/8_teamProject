<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="icon_genius"></i> 문의 사항
		</h3>

		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="icon_genius"></i>ServiceCenter</li>
		</ol>
		<div class="box-body">
		<form id="formObj">
			<select name="searchType">
				<option value="n"
				<c:out value="${cri.searchType == null?'selected':''}"/>>
				전체</option>
				<option value="t"
				<c:out value="${cri.searchType eq 't'?'selected':'' }"/>>
				제목</option>
				<option value="c"
				<c:out value="${cri.searchType eq 'c'?'selected':'' }"/>>
				내용</option>
				<option value="w"
				<c:out value="${cri.searchType eq 'w'?'selected':'' }"/>>
				작성자</option>
				<option value="tc"
				<c:out value="${cri.searchType eq 'tc'?'selected':'' }"/>>
				제목 + 내용</option>
			</select>
			<input type="text" name="keyword" id="keywordInput"
				value='${cri.keyword }'>
			<button class="searchBtn">Search</button>
			</form>
		</div>
	</div>
	<div class="col-lg-12">
		<div class="panel panel-default">
		<form class='formObj' method="get">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>문의 사항</strong>
				</h2>
				<div class="panel-actions">
					<a href="insertHelp${pageMaker.makeSearch(pageMaker.cri.page)}" class="btn-setting"><i class="fa fa-plus"
						aria-hidden="true"></i></a>
				</div>
			</div>
			<section class="panel" style="overflow: auto; height: 450px;">
					<table class="table table-hover">
						<tr>
							<th>글 번호</th>
							<th>제목</th>
							<th>등록날짜</th>
							<th>등록자</th>
						</tr>
						<c:forEach items="${list }" var="helpList">
							<tr>
								<td>${helpList.bno }</td>
								<td><a
									href='readPage${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${helpList.bno}&msg=${msg}'>${helpList.title }</a></td>
								<td>${helpList.regidate }</td>
								<td><span class="badge bg-red">${helpList.writer }</span></td>
							</tr>
						</c:forEach>
					</table>
			
			</section>
			</form>
		</div>
	</div>
	<div class="text-center">
		<ul class="pagination">
		<c:if test="${msg == true}">
			<c:if test="${pageMaker.prev }">
				<li><a
					href="help_List?page=${pageMaker.makeSearch(pageMaker.startPage -1)}">&laquo;</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
				var="idx">
				<li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
					<a href="help_List${pageMaker.makeSearch(idx)}">${idx}</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a
					href="help_List${pageMaker.makeSearch(pageMaker.endPage + 1) }">&raquo;</a></li>
			</c:if>
		</c:if>
		<c:if test="${msg == false}">
			<c:if test="${pageMaker.prev }">
				<li><a
					href="searchHelp?page=${pageMaker.makeSearch(pageMaker.startPage -1)}">&laquo;</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
				var="idx">
				<li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
					<a href="searchHelp${pageMaker.makeSearch(idx)}">${idx}</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a
					href="searchHelp${pageMaker.makeSearch(pageMaker.endPage + 1) }">&raquo;</a></li>
			</c:if>		</c:if>
		</ul>
	</div>
</div>
<script>
$(document).ready(function(){
	$('.searchBtn').on("click", function(){
		$('#formObj').attr('method', 'get');
		$('#formObj').attr('action', 'searchHelp');
		$('#formObj').submit();
	});
});
</script>