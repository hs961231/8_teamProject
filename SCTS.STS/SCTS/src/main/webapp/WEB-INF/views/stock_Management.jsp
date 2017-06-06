<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa fa-bars"></i>재고 관리
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="fa fa-bars"></i>Management</li>
		</ol>

		<div class="navbar-inner">
			<form class="navbar-form">
			재고수량 <input type="number" id="startAmount"> 이상 <input
				type="number" id="endAmount"> 이하 <select
				id="stockCategory">
				<option>카테고리 선택</option>
				<option>냉장고</option>
				<option>세탁기</option>
				<option>에어컨</option>
				<option>삽니다</option>
			</select>
	
				<input type="text" name="goodsName" class="form-control searchForm"
					placeholder="Search"> <input type="button" id="mySearch"
					value="검색">
			</form>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>StockList</strong>
				</h2>
			</div>
			<section class="panel" style="overflow: scroll; height: 600px;">
				<table class="table table-striped table-advance table-hover">
					<!-- table-bordered -->
					<thead>
						<tr>
							<th>Goods code</th>
							<th>Category code</th>
							<th>Category name</th>
							<th>Goods Name</th>
							<th>수량</th>
							<th>재고 상태</th>
							<th></th>
						</tr>
					</thead>
					<c:forEach items="${list }" var="stockList">
						<tbody>
							<tr class="active">
								<td>${stockList.goods_code }</td>
								<td>${stockList.detailctgry_code }</td>
								<td>가전 제품</td>
								<td>${stockList.goods_nm }</td>
								<td>10</td>
								<td>정상</td>
								<td><input type="submit" class="modiBtn btn btn-primary"
									value="수정"> <input type="submit"
									class="delBtn btn btn-danger" value="삭제" /></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</section>
		</div>
	</div>
</div>
<script src="resources/customjs/stockManagement.js"></script>