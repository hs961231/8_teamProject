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
	<div class="col-lg-6">
		<section class="panel">
			<header class="panel-heading chartTitle"> 연매출 </header>
			<div class="panel-body text-center">
				<canvas id="barDay" height="300" width="500"></canvas>
			</div>
		</section>
	</div>
	<div class="col-lg-6">
		<section class="panel">
			<header class="panel-heading chartTitle"> 연매출 </header>
			<div class="panel-body text-center">
				<canvas id="barYear" height="300" width="500"></canvas>
			</div>
		</section>
	</div>
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
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>타일 리스트</strong>
				</h2>
				<div class="panel-actions">
					<a href="product_Register" class="btn-setting"><i
						class="fa fa-plus" aria-hidden="true"></i></a>
				</div>
			</div>
			<section class="panel" style="overflow: scroll; height: 430px;">

				<table class="table table-striped table-advance table-hover">
					<tbody>
						<tr>
							<th style="text-align: center;"><i class="icon_profile"></i>
								타일번호</th>
							<th style="text-align: center;"><i class="icon_calendar"></i>
								메이저</th>
							<th style="text-align: center;"><i class="icon_mail_alt"></i>
								마이너</th>
							<th style="text-align: center;"><i class="icon_pin_alt"></i>
								Product_name</th>
							<th style="text-align: center;"><i class="icon_pin_alt"></i>
								Product_Amount</th>
							<th style="text-align: center;"><i class="icon_pin_alt"></i>
								Price(won)</th>
							<th style="text-align: center;"></th>
						</tr>

						<tr>
							<td style="text-align: center;">001</td>
							<td style="text-align: center;">03</td>
							<td style="text-align: center;">가전 제품</td>
							<td style="text-align: center;">** 고데기</td>
							<td style="text-align: center;">15</td>
							<td style="text-align: center;">25000</td>
							<td>
								<div class="btn-group">
									<a class="btn btn-primary" href="product_Info"><i
										class="icon_plus_alt2"></i></a> <a class="btn btn-success"
										href="#"><i class="icon_check_alt2"></i></a> <a
										class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
								</div>
							</td>
						</tr>

						<tr>
							<td style="text-align: center;">002</td>
							<td style="text-align: center;">03</td>
							<td style="text-align: center;">가전 제품</td>
							<td style="text-align: center;">** 고데기</td>
							<td style="text-align: center;">15</td>
							<td style="text-align: center;">25000</td>
							<td>
								<div class="btn-group">
									<a class="btn btn-primary" href="product_Info"><i
										class="icon_plus_alt2"></i></a> <a class="btn btn-success"
										href="#"><i class="icon_check_alt2"></i></a> <a
										class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
								</div>
							</td>
						</tr>

						<tr>
							<td style="text-align: center;">003</td>
							<td style="text-align: center;">03</td>
							<td style="text-align: center;">가전 제품</td>
							<td style="text-align: center;">** 고데기</td>
							<td style="text-align: center;">15</td>
							<td style="text-align: center;">25000</td>
							<td>
								<div class="btn-group">
									<a class="btn btn-primary" href="product_Info"><i
										class="icon_plus_alt2"></i></a> <a class="btn btn-success"
										href="#"><i class="icon_check_alt2"></i></a> <a
										class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
								</div>
							</td>
						</tr>

						<tr>
							<td style="text-align: center;">004</td>
							<td style="text-align: center;">03</td>
							<td style="text-align: center;">가전 제품</td>
							<td style="text-align: center;">** 고데기</td>
							<td style="text-align: center;">15</td>
							<td style="text-align: center;">25000</td>
							<td>
								<div class="btn-group">
									<a class="btn btn-primary" href="product_Info"><i
										class="icon_plus_alt2"></i></a> <a class="btn btn-success"
										href="#"><i class="icon_check_alt2"></i></a> <a
										class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
								</div>
							</td>
						</tr>

						<tr>
							<td style="text-align: center;">005</td>
							<td style="text-align: center;">03</td>
							<td style="text-align: center;">가전 제품</td>
							<td style="text-align: center;">** 고데기</td>
							<td style="text-align: center;">15</td>
							<td style="text-align: center;">25000</td>
							<td>
								<div class="btn-group">
									<a class="btn btn-primary" href="product_Info"><i
										class="icon_plus_alt2"></i></a> <a class="btn btn-success"
										href="#"><i class="icon_check_alt2"></i></a> <a
										class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
								</div>
							</td>
						</tr>

					</tbody>
				</table>
			</section>
		</div>
	</div>
</div>



<script>
	$(document).ready(
			function() {

				// 연매출 barchart!!!
				var year = new Date().getFullYear();

				$.ajax({
					type : "GET",
					url : "yearSales",
					data : {
						year : year
					},
					dataType: 'jsonp',
					success : function(data) {

						var barChartData = {};
						barChartData.labels = [];
						barChartData.datasets = [];
						barChartData.datasets[0] = {};
						barChartData.datasets[0].fillColor = "#FF3359";
						barChartData.datasets[0].strokeColor = "#FF3359";
						barChartData.datasets[0].data = [];
						
						var length = data.result.length;
						
						for(var i=0; i<length; i++){
							
							barChartData.labels[i] = data.result[i].year;
							barChartData.datasets[0].data[i] = data.result[i].totalPrice;

						}
					
						new Chart(document.getElementById("barYear").getContext(
								"2d")).Bar(barChartData);

					}
				});
				

				$('.daySales').on('click', function(){
					
					$.ajax({
						type : "GET",
						url : "daySales",
						dataType: 'jsonp',
						success : function(data) {

							var barChartData = {};
							barChartData.labels = [];
							barChartData.datasets = [];
							barChartData.datasets[0] = {};
							barChartData.datasets[0].fillColor = "#FF3359";
							barChartData.datasets[0].strokeColor = "#FF3359";
							barChartData.datasets[0].data = [];
							
							var length = data.result.length;
							
							for(var i=0; i<length; i++){
								
								barChartData.labels[i] = data.result[i].publish_date;
								barChartData.datasets[0].data[i] = data.result[i].totalPrice;

							}
						
							new Chart(document.getElementById("barDay").getContext(
									"2d")).Bar(barChartData);

						}
					
				});
					
				
			});
	});
</script>

