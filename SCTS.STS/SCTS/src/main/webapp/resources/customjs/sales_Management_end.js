/**
 * 
 */



	$('.daySales').on('click', function(event){
					
				
			$("#searchSales").show();
			$("#searchYearSales").hide();
			$("#month").hide();
			$("#productSalesInfo").hide();
			$("#customerProductLankInfo").hide();
			
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
		$("#productSalesInfo").hide();
		$("#customerProductLankInfo").hide();
		
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
	
	
	
	$(".productLank").click(function(){
		$("#searchSales").hide();
		$("#searchYearSales").hide();
		$("#month").hide();
		$("#productSalesInfo").show();
		$("#customerProductLankInfo").hide();
		
		$("#settleSales").children().remove();
		
		

		$("#settleSales").append($("<tr></tr>").addClass('tr'));
		$('.tr').append($("<td></td>").text("상품 이름"));
		$('.tr').append($("<td></td>").text("재고량"));
		$('.tr').append($("<td></td>").text("공급업체 이름"));
		$('.tr').append($("<td></td>").text("매출 수량"));
		$('.tr').append($("<td></td>").text("총 매출"));
		
		
	});
	
	
	$(".customerProductLank").click(function(){
		
		$("#searchSales").hide();
		$("#searchYearSales").hide();
		$("#month").hide();
		$("#productSalesInfo").hide();
		$("#customerProductLankInfo").show();
		
		$("#settleSales").children().remove();
		
		$("#settleSales").append($("<tr></tr>").addClass('tr'));
		$('.tr').append($("<td></td>").text("상품 이름"));
		$('.tr').append($("<td></td>").text("재고량"));
		$('.tr').append($("<td></td>").text("공급업체 이름"));
		$('.tr').append($("<td></td>").text("매출 수량"));
		$('.tr').append($("<td></td>").text("총 매출"));
		
	});
