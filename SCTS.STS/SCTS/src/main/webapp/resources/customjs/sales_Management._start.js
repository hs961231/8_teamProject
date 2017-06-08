
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
		
		$("#settleSales").append($("<tr></tr>").addClass('tr'));
		$('.tr').append($("<td></td>").text("연도"));
		$('.tr').append($("<td></td>").text("결제수단"));
		$('.tr').append($("<td></td>").text("총 매출"));
		
		$.ajax({
			type : "GET",
			url : "settleSalesInfo",
			data : {
						year1: year1,
						year2: year2,
					},
			dataType: 'jsonp',
			success : function(data) {
			
					var length = data.result.length;
							
					for(var i = 0; i < length; i++){
							$("#settleSales").append($("<tr></tr>").attr("data", i));
										
							$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].year));
							$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].setle_mth_nm));
							$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].totalPrice +"원"));
								
					}

		   }
		});
	}
