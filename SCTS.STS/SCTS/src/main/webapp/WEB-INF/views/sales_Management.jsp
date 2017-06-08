<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#searchSales {
	display: none;
}

#month {
	display: none;
}

#productSalesInfo{
	display: none;
}
#customerProductRankInfo{
	display: none;
}
</style>

<script src="resources/customjs/sales_Management._start.js"></script>
<!-- 일 매출, 월매출, 상품별 매출, 고객별 매출 -->

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa fa-bars"></i>매출관리
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="fa fa-bars"></i>Management</li>
		</ol>

	</div>
</div>

<div class="row">
	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box blue-bg daySales">
			<i class="fa fa-cloud-download"></i>
			<div class="count">2,000</div>
			<div class="title">Day Sales</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box orange-bg2 MonthlySales">
			<i class="fa fa-shopping-cart"></i>
			<div class="count">20,000</div>
			<div class="title">Monthly Sales</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box yellow-bg2 productRank">
			<i class="fa fa-thumbs-o-up"></i>
			<div class="count">3,200</div>
			<div class="title">Monthly Product Rank</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box dark-bg customerProductRank">
			<i class="fa fa-cubes"></i>
			<div class="count">20,000</div>
			<div class="title">Customer Product Rank</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

</div>


<!-- 일매출 div 누를경우 나타남 -->
<div id="searchSales" class="form-group">

	<table class="table">

		<tr>
			<td>주문일</td>
			<td><input type="date" id="date2" /> ~ <input type="date"
				id="date1" /></td>
			<td rowspan="2">
				<button class="btn btn-primary" id="searchDate">검색</button>
			</td>
		</tr>
		<tr>
			<td>결제방식</td>
			<td><select id="setle_mth_code">

					<option value="2">현금</option>
					<option value="1">카드</option>
					<option value="3">상품권</option>
					<option value="4">포인트</option>

			</select></td>
		</tr>
	</table>

</div>


<!-- 처음 로딩시 나타남 -->
<div id="searchYearSales">

	<table class="table">
		<tr>
			<td>연도</td>
			<td><select class="year" id="year1">
			</select> ~ <select class="year" id="year2">
			</select></td>
			<td>
				<button class="btn btn-primary" id="searchYear">검색</button>
			</td>
		</tr>
	</table>

</div>

<div id="month">

	<table class="table">
		<tr>
			<td>날짜</td>
			<td><input type="month" id="month2" /> <input type="month"
				id="month1" />
			<td rowspan="2">
				<button class="btn btn-primary" id="searchMonth">검색</button>
			</td>
		</tr>
		<tr>
			<td>결제수단</td>
			<td><select id="setle_mth_code2">

					<option value="2">현금</option>
					<option value="1">카드</option>
					<option value="3">상품권</option>
					<option value="4">포인트</option>

			</select></td>
		</tr>
	</table>

</div>

<div id="productSalesInfo">
	<table class="table">
		<tr>
			<td width="23%">
				<!-- 아이콘!! < 이모양이랑 > 이모양 -->
				<button id="prev" class="btn btn-default" style="width:70px">&lt</button>
				<button id="next" class="btn btn-default" style="width:70px">&gt</button>
			</td>
			<td width="50%">
				<h3>2017-06</h3>
			</td>
			
			<td><button id="income" class="btn btn-default" style="width:85px">순이익순</button>
				<button id="total" class="btn btn-default" style="width:85px">총매출순</button>
			</td>
			
		</tr>


	</table>

</div>

<div id="customerProductRankInfo">
<table class="table">
		<tr>
			<td width="23%">
				<!-- 아이콘!! < 이모양이랑 > 이모양 -->
				<button id="prev" class="btn btn-default" style="width:70px">&lt</button>
				<button id="next" class="btn btn-default" style="width:70px">&gt</button>
			</td>
			<td width="45%">
				<h3>성별</h3>
			</td>
			
			<td>
				<button id="gender" class="btn btn-default">성별</button>
				<button id="age10" class="btn btn-default">10대</button>
				<button id="age20" class="btn btn-default">20대</button>
				<button id="age30" class="btn btn-default">30대</button>
				<button id="age40" class="btn btn-default">40대</button>
				<button id="age50" class="btn btn-default">50대 이후</button>
				<button id="income" class="btn btn-default" style="width:85px">순이익순</button>
				<button id="total" class="btn btn-default" style="width:85px">총매출순</button>
			</td>
			
		</tr>


	</table>


</div>

<!-- Bar -->
<div class="row">
	<div class="col-lg-12">
		<section class="panel panel-default">
			<header class="panel-heading chartTitle"> 연매출 </header>
			<div class="panel-body text-center">
				<canvas id="bar" height="450" width="800"></canvas>
			</div>
		</section>
	</div>
</div>

<div class="row">
	<table class="table table-striped table-advance table-hover"
		id="settleSales">

		<tr>
			<th>연도</th>
			<th>결제수단</th>
			<th>총매출</th>
		</tr>


	</table>

</div>

<script src="resources/customjs/sales_Management_end.js"></script>

