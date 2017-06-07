<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#searchSales {
	display: none;
}
#month{
	display: none;
}
</style>

<script>
	$(document).ready(function(){
		
		// 연매출 barchart!!!
		var year = new Date().getFullYear();
		
		for(var i = year; i >= year-4; i--){
			$(".year").append($("<option value='"+i+"'></option>").text(i));
		}
		

		 $.ajax({
			type : "GET",
			url : "yearSales",
			data : {
				year: year
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
		 
		 yearSalesSettleInfo(year-4, year);
		 
		 $("#searchYear").click(function(){
				
				var year1 = $("#year1 option:selected").val();
				var year2 = $("#year2 option:selected").val();
							
							
				$.ajax({
				type : "GET",
				url : "searchYear",
				data : {
							year1 : year1,
							year2 : year2
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
								
						new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);

					}
				});
			
				 yearSalesSettleInfo(year1, year2);
							
			});
		
	});
	
	function yearSalesSettleInfo(year1, year2){
		
		$("#settleSales").children().remove();
		
		$.ajax({
			type : "GET",
			url : "settleSalesInfo",
			data : {
						year1: year1,
						year2: year2,
					},
			dataType: 'jsonp',
			success : function(data) {
				
			$("#settleSales").append($("<tr></tr>").addClass('tr'));
			$('.tr').append($("<td></td>").text("연도"));
			$('.tr').append($("<td></td>").text("결제수단"));
			$('.tr').append($("<td></td>").text("총 매출"));
				
		
			var length = data.result.length;
							
			for(var i = 0; i < length; i++){
					$("#settleSales").append($("<tr></tr>").attr("data", data.result[i].year));
								
					$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].year));
					$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].setle_mth_nm));
					$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].totalPrice +"원"));
								
				}

			}
		});
	}
	
</script>

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


<!-- 일매출 div 누를경우 나타남 -->
<div id="searchSales" class="form-group">

	<table class="table">

		<tr>
			<td>주문일</td>
			<td><input type="date" id="date2"/> ~ <input type="date" id="date1" /></td>
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
			<td>
				<input type="month" id="month2"/>
				<input type="month" id="month1"/>
			<td rowspan="2">
				<button class="btn btn-primary" id="searchMonth">검색</button>
			</td>
		</tr>
		<tr>
			<td>결제수단</td>
			<td>
				<select id="setle_mth_code2">

					<option value="2">현금</option>
					<option value="1">카드</option>
					<option value="3">상품권</option>
					<option value="4">포인트</option>

			</select>
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
				<canvas id="bar" height="450" width="600"></canvas>
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

<script>	 
	

	$('.daySales').on('click', function(event){
					
				
			$("#searchSales").show();
			$("#searchYearSales").hide();
			$("#month").hide();
			$("#settleSales").children().remove();
			
			

			$("#settleSales").append($("<tr></tr>").addClass('tr'));
			$('.tr').append($("<td></td>").text("연도"));
			$('.tr').append($("<td></td>").text("결제수단"));
			$('.tr').append($("<td></td>").text("총 매출"));

		
					
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
							
						$('.chartTitle').text("일매출"); 
						
						new Chart(document.getElementById("bar").getContext(
								"2d")).Bar(barChartData);

				}
					
			});
			
			
			$.ajax({
				type : "GET",
				url : "daySalesSettleInfo",
				dataType: 'jsonp',
				success : function(data) {
			
	
					var length = data.result.length;
					
					for(var i = 0; i < length; i++){
							$("#settleSales").append($("<tr></tr>").attr("data", data.result[i].year));
										
							$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].year));
							$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].setle_mth_nm));
							$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].totalPrice +"원"));
										
						}
					

				}
					
			});

	});
	
	
	$("#searchDate").click(function(event){
		
		var date1 = $("#date1").val();
		var date2 = $("#date2").val();
		
		var setle_mth_code = $("#setle_mth_code option:selected").val();
				
		$.ajax({
			type : "GET",
			url : "searchDaySales",
			dataType: 'jsonp',
			data : {
				date1 : date1,
				date2 : date2
			},
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
						
					$('.chartTitle').text("일매출"); 
					
					new Chart(document.getElementById("bar").getContext(
							"2d")).Bar(barChartData);

			}
				
		});
		
		
	$("#settleSales").children().remove();
		
		$("#settleSales").append($("<tr></tr>").addClass('tr'));
		$('.tr').append($("<td></td>").text("연도"));
		$('.tr').append($("<td></td>").text("결제수단"));
		$('.tr').append($("<td></td>").text("총 매출"));
		
		
		$.ajax({
			type : "GET",
			url : "daySettle",
			dataType: 'jsonp',
			data : {
				date1 : date1,
				date2 : date2,
				setle_mth_code : setle_mth_code
			},
			success : function(data) {

				var length = data.result.length;
				
				for(var i = 0; i < length; i++){
						$("#settleSales").append($("<tr></tr>").attr("data", data.result[i].year));
									
						$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].year));
						$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].setle_mth_nm));
						$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].totalPrice +"원"));
									
					}

			}
				
		});
	});
	
	$(".MonthlySales").click(function(){
		
		$("#searchSales").hide();
		$("#searchYearSales").hide();
		$("#month").show();
		
		var date = new Date();
		
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		if (month < 10) {
			month = "0" + month;
		}
		
		var mm = date.getMonth() - 3;
		if(mm < 10){
			mm = "0" +  mm;
		}
		
		
		var month1 = year + "-" + month;
		var month2 = year + "-" + mm;
		
		monthlyChart(month1, month2);

		
		
	});
	
	
	$("#searchMonth").click(function(){
		
		var month1 = $("#month1").val();
		var month2 = $("#month2").val();
		var setle_mth_code = $("#setle_mth_code2 option:selected").val();
		
		
		monthlyChart(month1, month2);
		
		$("#settleSales").children().remove();
		
		$("#settleSales").append($("<tr></tr>").addClass('tr'));
		$('.tr').append($("<td></td>").text("연도"));
		$('.tr').append($("<td></td>").text("결제수단"));
		$('.tr').append($("<td></td>").text("총 매출"));
		
		
		$.ajax({
			type : "GET",
			url : "monthSalesSettleInfo",
			dataType: 'jsonp',
			data : {
				month1 : month1,
				month2 : month2,
				setle_mth_code : setle_mth_code
			},
			success : function(data) {

				var length = data.result.length;
				
				for(var i = 0; i < length; i++){
						$("#settleSales").append($("<tr></tr>").attr("data", data.result[i].year));
									
						$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].year));
						$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].setle_mth_nm));
						$("tr[data="+data.result[i].year+"]").append($("<td></td>").text(data.result[i].totalPrice +"원"));
									
					}

			}
				
		});
	
		
	});
	
	
	function monthlyChart(month1, month2){
		$.ajax({
			type : "GET",
			url : "monthSales",
			dataType: 'jsonp',
			data : {
				month1 : month1,
				month2 : month2
			},
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
						
					$('.chartTitle').text("일매출"); 
					
					new Chart(document.getElementById("bar").getContext(
							"2d")).Bar(barChartData);

			}
				
		});
	}
		
</script>

