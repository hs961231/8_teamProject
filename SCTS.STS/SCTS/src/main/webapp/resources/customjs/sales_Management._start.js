var sock = new SockJS("/scts/sales-ws");
var searchYearSalesSock = new SockJS("/scts/searchYearSales-ws");

searchYearSalesSock.onmessage = function(event){
	var data = event.data;
	data = JSON.parse(data);

	console.log(data);

	$("#daySales .count").text(data.todaySales);
	$("#MonthlySales .count").text(data.monthTotalSales);

	yearSales(data);

}
searchYearSalesSock.onclose = function(evt){

	alert("ddd연결끊김");

}



sock.onmessage = function(event){
	var data = event.data;
	data = JSON.parse(data);

	console.log(data);

	$("#daySales .count").text(data.todaySales);
	$("#MonthlySales .count").text(data.monthTotalSales);

	yearSales(data);

}
sock.onclose = function(evt){

	alert("연결끊김");

}

sock.onopen = function() {
	console.log('open');
	sendMessage();

};

function sendMessage(){
	var year = parseInt(new Date().getFullYear());
	var json = JSON.stringify({year2 : year, year1 : + (year-4)});

	sock.send(json);

}




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

function yearSales(data){
	
	var length = data.yearSales.length;

	for(var i=0; i < length; i++){

		options.series[0].data[i] = {};
		options.series[0].data[i].name =  data.yearSales[i].year;
		options.series[0].data[i].y = data.yearSales[i].totalPrice;
		options.series[0].data[i].drilldown = data.yearSales[i].year;
		
		options.drilldown.series[i] = {}
		options.drilldown.series[i].name = data.yearSales[i].year;
		options.drilldown.series[i].id = data.yearSales[i].year;
		options.drilldown.series[i].data = [];
		yearToMonth(data.yearSales[i].year, i);

	}
	

	
	Highcharts.chart('salesChart', options);
	
	
	$("#settleSales").children().remove();

	$("#settleSales").append($("<tr></tr>").addClass('tr'));
	$('.tr').append($("<td></td>").text("연도"));
	$('.tr').append($("<td></td>").text("결제수단"));
	$('.tr').append($("<td></td>").text("총 매출"));
	
	var length = data.yearSalesInfo.length;

	for(var i = 0; i < length; i++){
		$("#settleSales").append($("<tr></tr>").attr("data", i));

		$("tr[data="+i+"]").append($("<td></td>").text(data.yearSalesInfo[i].year));
		$("tr[data="+i+"]").append($("<td></td>").text(data.yearSalesInfo[i].setle_mth_nm));
		$("tr[data="+i+"]").append($("<td></td>").text(data.yearSalesInfo[i].totalPrice +"원"));

	}

}

function yearToMonth(year, j){
	
	$.ajax({
		type : "GET",
		url : "yearToMonth",
		data : {
			year : year
		},
		dataType: 'json',
		success : function(data) {
			var length = data.yearToMonth.length;

			for(var i=0; i < length; i++){
				
				options.drilldown.series[j].data[i] = []
				options.drilldown.series[j].data[i][0]= data.yearToMonth[i].bill_issu_de;
				options.drilldown.series[j].data[i][1]= data.yearToMonth[i].totalPrice;


			}


		}
	});
	
}


$(document).ready(function(){

	highchartTheme();

	// 연매출 barchart!!!
	var year = new Date().getFullYear();

	for(var i = year; i >= year-4; i--){
		$(".year").append($("<option value='"+i+"'></option>").text(i));
	}


	$("#searchYear").click(function(){

		var year1 = $("#year1 option:selected").val();
		var year2 = $("#year2 option:selected").val();

		var json = JSON.stringify({
			year1 : year1,
			year2 : year2
		});

		searchYearSalesSock.send(json);
		
		
	});


});

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