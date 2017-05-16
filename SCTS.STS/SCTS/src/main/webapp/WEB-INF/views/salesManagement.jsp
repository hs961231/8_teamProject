<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="/scts/resources/assets/chart-master/Chart.js"></script>

<!-- 일 매출, 월매출, 상품별 매출, 고객별 매출 -->

<div class="row">
	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
		<div class="info-box blue-bg">
			<i class="fa fa-cloud-download"></i>
			<div class="count">2,000</div>
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

<!-- Bar -->
<div class="row">
	<div class="col-lg-6">
		<section class="panel">
			<header class="panel-heading"> Bar </header>
			<div class="panel-body text-center">
				<canvas id="bar" height="300" width="500"></canvas>
			</div>
		</section>
	</div>
</div>

<script>
	$(document).ready(
			function() {

				// 도넛차트
				var doughnutData = [ {
					value : 30,
					color : "#F7464A"
				}, {
					value : 50,
					color : "#46BFBD"
				}, {
					value : 100,
					color : "#FDB45C"
				}, {
					value : 40,
					color : "#949FB1"
				}, {
					value : 120,
					color : "#4D5360"
				}

				];

				var lineChartData = {
					labels : [ "", "", "", "", "", "", "" ],
					datasets : [ {
						fillColor : "rgba(220,220,220,0.5)",
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						data : [ 65, 59, 90, 81, 56, 55, 40 ]
					}, {
						fillColor : "rgba(151,187,205,0.5)",
						strokeColor : "rgba(151,187,205,1)",
						pointColor : "rgba(151,187,205,1)",
						pointStrokeColor : "#fff",
						data : [ 28, 48, 40, 19, 96, 27, 100 ]
					} ]

				};
				var pieData = [ {
					value : 30,
					color : "#F38630"
				}, {
					value : 50,
					color : "#E0E4CC"
				}, {
					value : 100,
					color : "#69D2E7"
				}

				];

				// 연매출 barchart!!!
				var year = new Date().getFullYear();
				alert(year);

				$.ajax({
					type : "GET",
					url : "yearSales",
					data : {
						year : year
					},
					dataType: 'jsonp',
					success : function(data) {
						
						
						var barChartData = {};
						barChartData.labels = [ year - 2, year - 1, year ];
						barChartData.datasets = [];
						barChartData.datasets[0] = {
							fillColor : "#FF3359",
							strokeColor : "#FF3359",
							data : [ data.result[0].totalPrice, data.result[1].totalPrice, data.result[2].totalPrice ]
						}

						new Chart(document.getElementById("bar").getContext(
								"2d")).Bar(barChartData);

					}
				});

				/* var barChartData = {
				    labels : ["2015","2016","2017"],
				    datasets : [
				        {
				            fillColor : "#FF3359",
				            strokeColor : "#FF3359",
				            data : [650,1100, 500]
				        },
				        {
				            fillColor : "#202B37",
				            strokeColor : "#202B37",
				            data : [280,480,400]
				        }
				    ]

				}; */

				var chartData = [ {
					value : Math.random(),
					color : "#D97041"
				}, {
					value : Math.random(),
					color : "#C7604C"
				}, {
					value : Math.random(),
					color : "#21323D"
				}, {
					value : Math.random(),
					color : "#9D9B7F"
				}, {
					value : Math.random(),
					color : "#7D4F6D"
				}, {
					value : Math.random(),
					color : "#584A5E"
				} ];
				var radarChartData = {
					labels : [ "", "", "", "", "", "", "" ],
					datasets : [ {
						fillColor : "rgba(220,220,220,0.5)",
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						data : [ 65, 59, 90, 81, 56, 55, 40 ]
					}, {
						fillColor : "rgba(151,187,205,0.5)",
						strokeColor : "rgba(151,187,205,1)",
						pointColor : "rgba(151,187,205,1)",
						pointStrokeColor : "#fff",
						data : [ 28, 48, 40, 19, 96, 27, 100 ]
					} ]

				};
				/*  new Chart(document.getElementById("doughnut").getContext("2d")).Doughnut(doughnutData);
				 new Chart(document.getElementById("line").getContext("2d")).Line(lineChartData);
				 new Chart(document.getElementById("radar").getContext("2d")).Radar(radarChartData);
				 new Chart(document.getElementById("polarArea").getContext("2d")).PolarArea(chartData); */

				/*     new Chart(document.getElementById("pie").getContext("2d")).Pie(pieData); */

			});
</script>

