<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- 이 부분은 일매출, 일 방문자 수 등 보임!!!!!!!!!! -->
<div class="row">
	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box blue-bg">
			<i class="fa fa-cloud-download"></i>
			<div class="count">${ todayCount }</div>
			<div class="title">Today Visitor</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box orange-bg2">
			<i class="fa fa-shopping-cart"></i>
			<div class="count">20,000</div>
			<div class="title">Today Sales</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box yellow-bg2">
			<i class="fa fa-thumbs-o-up"></i>
			<div class="count">3,200</div>
			<div class="title">Monthly Average Visitor</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box dark-bg">
			<i class="fa fa-cubes"></i>
			<div class="count">20,000</div>
			<div class="title">Monthly Average Sales</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

</div>

<!-- 하이차트 : 매출 들어갈곳 -->
<div class="row"
	style="height: 500px; border: solid 1px black; margin-bottom: 20px;">
	<h1>이곳은!!!!!!!!!!!!!!!!!!!!!!!!!매출 하이차트 들어갈곳이다!!!!!!!! 스타일 인라인이다
		태그안에 설정해놓음!!!</h1>
</div>



<!-- 대시보드 -->
<div class="row"
	style="height: 500px; border: solid 1px blue; margin-bottom: 20px;">
	<div
		style="width: 65%; float: left; height: 450px; border: solid 1px black; margin-right: 3%; margin-top: 1%;">
		<h1>이곳은 대시보드 들어갈곳이다!!!!!!</h1>
	</div>
	<div
		style="width: 30%; height: 450px; float: left; border: solid 1px black; margin-top: 1%;">
		<h1>이곳은 대시보드 옆 하이차트 들어갈곳임</h1>
	</div>
</div>



<!-- 타일리스트 -->
<div class="row" style="height: 500px; border: solid 1px red">
	<h1>이곳은 타일리스트 들어갈곳이다!!!!!!!!!!!!!!!</h1>
</div>



<!-- 달력!!!!!!!!-->
<%-- <jsp:include page="${ main_content }" /> --%>
<h2>달력은 곧 사라질 에정!</h2>
<jsp:include page="event.jsp" />