<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa fa-bars"></i> 재고 관리
		</h3>
		<div class="panel panel-default">
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index">Home</a></li>
				<li><i class="fa fa-bars"></i>재고 관리</li>
			</ol>
		</div>
		<div class="navbar-search pull-left">
			<div class="navbar-inner">
				재고수량 <input type="number" name=""> 이상 <input type="number"
					name=""> 이하 <select
					style="width: 150px; height: 29px; margin-left: 25px;">
					<option>카테고리 선택</option>
					<option>냉장고</option>
					<option>세탁기</option>
					<option>에어컨</option>
					<option>삽니다</option>
				</select> <input type="text" style="width:30%; display:inline;" class="form-control" placeholder="Search">
				<input type="button" value="검색">
			</div>
		</div>
		<table class="table table-striped table-advance table-hover">
			<!-- table-bordered -->
			<thead>
				<tr>
					<th>물품 번호</th>
					<th>카테고리 번호</th>
					<th>카테고리 명</th>
					<th>물품 명</th>
					<th>수량</th>
					<th>재고 상태</th>
					<th></th>
				</tr>
			</thead>
			<%-- <c:forEach items="${list }" value="stockList"> --%>
			<tbody>
				<tr>
					<td>001</td>
					<td>03</td>
					<td>가전 제품</td>
					<td>좋은 TV</td>
					<td>5</td>
					<td>정상</td>
					<td><input type="button" value="수정" name="modify"> <input
						type="button" value="삭제" name="delete"></td>
				</tr>
				<tr>
					<td>001</td>
					<td>03</td>
					<td>가전 제품</td>
					<td>좋은 TV</td>
					<td>5</td>
					<td>정상</td>
					<td><input type="button" value="수정" name="modify"> <input
						type="button" value="삭제" name="delete"></td>
				</tr>
				<tr>
					<td>001</td>
					<td>03</td>
					<td>가전 제품</td>
					<td>좋은 TV</td>
					<td>5</td>
					<td>정상</td>
					<td><input type="button" value="수정" name="modify"> <input
						type="button" value="삭제" name="delete"></td>
				</tr>
			</tbody>
			<%-- </c:forEach> --%>
		</table>
	</div>
</div>
