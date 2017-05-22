<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- 일 매출, 월매출, 상품별 매출, 고객별 매출 -->

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
		<div class="info-box yellow-bg2 ProductLank">
			<i class="fa fa-thumbs-o-up"></i>
			<div class="count">3,200</div>
			<div class="title">Monthly Product Lank</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box dark-bg CustomerProductLank">
			<i class="fa fa-cubes"></i>
			<div class="count">20,000</div>
			<div class="title">Customer Product Lank</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

</div>

<!-- Bar -->
<div class="row">
	<div class="col-lg-6">
		<section class="panel">
			<header class="panel-heading chartTitle"> 연매출 </header>
			<div class="panel-body text-center">
				<canvas id="bar" height="300" width="500"></canvas>
			</div>
		</section>
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
					
						new Chart(document.getElementById("bar").getContext(
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
							
							$('.chartTitle').text("일매출"); 
						
							new Chart(document.getElementById("bar").getContext(
									"2d")).Bar(barChartData);

						}
					
				});
					
				
			});
	});
</script>

