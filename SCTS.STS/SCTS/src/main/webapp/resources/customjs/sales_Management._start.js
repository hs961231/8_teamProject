
$(document).ready(function(){
	
	highchartTheme();

			
			var options = {
				    chart: {
				        type: 'column'
				    },
				    title: {
				        text: '연매출'
				    },
				    xAxis: {
				        type: 'category'
				    },
				    yAxis: {
				        title: {
				            text: '총매출'
				        }

				    },
				    legend: {
				        enabled: false
				    },
				    plotOptions: {
				        series: {
				            borderWidth: 0,
				            dataLabels: {
				                enabled: true
				               
				            }
				        }
				    },

				    tooltip: {
				        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>원<br/>'
				    },
				    series : [{
				    	colorByPoint:true,
				    	data : []
				    }],
				    drilldown : {
				    	series : []
				    }
				}
	
		
		// 연매출 barchart!!!
		var year = new Date().getFullYear();
		
		for(var i = year; i >= year-4; i--){
			$(".year").append($("<option value='"+i+"'></option>").text(i));
		}
		

		 /*$.ajax({
			type : "GET",
			url : "yearSales",
			data : {
				year: year
			},
			dataType: 'jsonp',
			success : function(data) {

				var length = data.yearSales.length;
				
				for(var i=0; i < length; i++){
				
					options.series[0].data[i] = {};
					options.series[0].data[i].name =  data.yearSales[i].year;
					options.series[0].data[i].y = data.yearSales[i].totalPrice;
					options.series[0].data[i].drilldown = data.yearSales[i].year;
				
				}
				
				Highcharts.chart('salesChart', options);

			}
		});
		 
		 $(document).on("click", ".highcharts-drilldown-point", function(event){
			
			 event.preventDefault();
			
			var index = $(".highcharts-drilldown-point").index(this);
			var text = $(".highcharts-xaxis-labels tspan").eq(index).text();
		
			
			$.ajax({
				type : "GET",
				url : "yearToMonth",
				data : {
					year : text
				},
				dataType: 'jsonp',
				success : function(data) {
					var length = data.yearToMonth.length;
					
					options.drilldown.series[index] = {}
					options.drilldown.series[index].name =  text;
					options.drilldown.series[index].id = text;
					
					
					options.drilldown.series[index].data = [];
					
					for(var i=0; i < length; i++){
						options.drilldown.series[index].data[i] = []
						options.drilldown.series[index].data[i][0]= data.yearToMonth[i].bill_issu_de;
						options.drilldown.series[index].data[i][1]= data.yearToMonth[i].totalPrice;
					
						
					}
				
				
				}
			});
			
		 });
		 
		 yearSalesSettleInfo(year-4, year);
		 
		 
		 $("#searchYear").click(function(){
				
				var year1 = $("#year1 option:selected").val();
				var year2 = $("#year2 option:selected").val();
				
				
				$(".chart").children().remove(); 
				
				$(".chart").append($('<div id="salesChart" style="min-width: 310px; height: 400px; margin: 0 auto"></div>'));
							
							
					$.ajax({
					type : "GET",
					url : "searchYear",
					data : {
								year1 : year1,
								year2 : year2
						},
					dataType: 'jsonp',
					success : function(data) {
						
						delete options.series[0].data;
						options.series[0].data = [];
						
						var length = data.yearSales.length;
						
						for(var i=0; i < length; i++){
						
							options.series[0].data[i] = {};
							options.series[0].data[i].name =  data.yearSales[i].year;
							options.series[0].data[i].y = data.yearSales[i].totalPrice;
							options.series[0].data[i].drilldown = data.yearSales[i].year;
						
							alert(options.series[0].data[i].name);
						}
						
						Highcharts.chart('salesChart', options);
						
						
						
						yearSalesSettleInfo(year1, year2);
					
						}
								
				});
		
		 });*/
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
	
	
	
	
	function highchartTheme(){
		Highcharts.theme = {
				   colors: ['#f45b5b', '#8085e9', '#8d4654', '#7798BF', '#aaeeee', '#ff0066', '#eeaaee',
				      '#55BF3B', '#DF5353', '#7798BF', '#aaeeee'],
				   chart: {
				      backgroundColor: null,
				      style: {
				         fontFamily: 'Signika, serif'
				      }
				   },
				   title: {
				      style: {
				         color: 'black',
				         fontSize: '16px',
				         fontWeight: 'bold'
				      }
				   },
				   subtitle: {
				      style: {
				         color: 'black'
				      }
				   },
				   tooltip: {
				      borderWidth: 0
				   },
				   legend: {
				      itemStyle: {
				         fontWeight: 'bold',
				         fontSize: '13px'
				      }
				   },
				   xAxis: {
				      labels: {
				         style: {
				            color: '#6e6e70'
				         }
				      }
				   },
				   yAxis: {
				      labels: {
				         style: {
				            color: '#6e6e70'
				         }
				      }
				   },
				   plotOptions: {
				      series: {
				         shadow: true
				      },
				      candlestick: {
				         lineColor: '#404048'
				      },
				      map: {
				         shadow: false
				      }
				   },

				   // Highstock specific
				   navigator: {
				      xAxis: {
				         gridLineColor: '#D0D0D8'
				      }
				   },
				   rangeSelector: {
				      buttonTheme: {
				         fill: 'white',
				         stroke: '#C0C0C8',
				         'stroke-width': 1,
				         states: {
				            select: {
				               fill: '#D0D0D8'
				            }
				         }
				      }
				   },
				   scrollbar: {
				      trackBorderColor: '#C0C0C8'
				   },

				   // General
				   background2: '#E0E0E8'

				};

				// Apply the theme
				Highcharts.setOptions(Highcharts.theme);
	}