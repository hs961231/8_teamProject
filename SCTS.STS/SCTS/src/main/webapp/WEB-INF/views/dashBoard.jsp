<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script src="resources/customjs/shop_Register.js"></script>

<style>

.tileMap>div {
	width: 100%;
	height: 12.5%;
}

.tile {
	background-color: rgba(30, 255, 255, 0.3);
	width: 12.5%;
	height: 100%;
	float: left;
}

div.mouseover {
	background-color: blue;
}

div.active {
	background-color: red;
}
</style>
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

	<div class="col-lg-6">
		<section class="panel">
			<header class="panel-heading chartTitle"> 일매출 </header>
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
	<div class="col-lg-9 col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>BluePrint</strong>
				</h2>
			</div>
			<div class="panel-body-map">
				<div id="blueprint"
					style="height: 380px; text-align: center; position: absolute; z-index: 1;">
					<!-- 
					<br> <br> <br> <br> <br> <br> <br>
					<br>
					<p>설계도면 파일을 등록해주세요.</p>
				 -->
					<%-- <img
						src="displayDrawing?fileName=/${ drawingList.get(0).drw_flpth }"
						style="width: 800px; height: 380px;"> --%>

				</div>


				<!-- 전체 타일 영역 잡을 것. 위에 이미지 태그와 겹칠수 있도록 정의해야함 -->
				<div class="tileMap"
					style="position: absolute; width: 800px; height: 380px; z-index: 2;">
					<!-- 타일영역 전체 감싸는 div -->

					<div>
						<!-- 타일영역 1번 줄 -->
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
					</div>
					<!-- 타일영역 1번 줄 -->

					<div>
						<!-- 타일영역 2번 줄 -->
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
					</div>
					<!-- 타일영역 2번 줄 -->

					<div>
						<!-- 타일영역 3번 줄 -->
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
					</div>
					<!-- 타일영역 3번 줄 -->

					<div>
						<!-- 타일영역 4번 줄 -->
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
					</div>
					<!-- 타일영역 4번 줄 -->

					<div>
						<!-- 타일영역 5번 줄 -->
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
					</div>
					<!-- 타일영역 5번 줄 -->

					<div>
						<!-- 타일영역 6번 줄 -->
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
					</div>
					<!-- 타일영역 6번 줄 -->

					<div>
						<!-- 타일영역 7번 줄 -->
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
					</div>
					<!-- 타일영역 7번 줄 -->

					<div>
						<!-- 타일영역 8번 줄 -->
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
						<div class="tile"></div>
					</div>
					<!-- 타일영역 8번 줄 -->

				</div>
				<!-- 타일영역 전체 감싸는 div -->

			</div>

		</div>
	</div>

	<div class="col-md-3" id="tile_info"
		style="background-color: white; width: 280px; height: 417px; right: 20px; border: 1px solid #D5D5D5; text-align: center;">

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br>
		<p>선택된 타일 정보가 없습니다.</p>

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
								평균머문시간</th>
							<th style="text-align: center;"><i class="icon_pin_alt"></i>
								방문횟수</th>
							<th style="text-align: center;"></th>
						</tr>


						<c:forEach items="${ tileList }" var="vo">

							<tr>
								<td style="text-align: center;">${ vo.get("tile_nm") }</td>
								<td style="text-align: center;">${ vo.get("beacon_mjr") }</td>
								<td style="text-align: center;">${ vo.get("beacon_mnr") }</td>
								<td style="text-align: center;">${ vo.get("avg") }</td>
								<td style="text-align: center;">${ vo.get("cnt") }</td>
								<td>
									<div class="btn-group">
										<a class="btn btn-primary" href="product_Info"><i
											class="icon_plus_alt2"></i></a> <a class="btn btn-success"
											href="#"><i class="icon_check_alt2"></i></a> <a
											class="btn btn-danger" href="#"><i
											class="icon_close_alt2"></i></a>
									</div>
								</td>
							</tr>

						</c:forEach>

					</tbody>
				</table>
			</section>
		</div>
	</div>
</div>


<script>
	$(document).ready( function() {
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
					
					barChartData.labels[i] = data.result[i].bill_issu_de;
					barChartData.datasets[0].data[i] = data.result[i].totalPrice;

				}
			
				new Chart(document.getElementById("barDay").getContext(
						"2d")).Bar(barChartData);

			}
		
		});

		$('.daySales').on('click', function(){
				
		});
	});
</script>

