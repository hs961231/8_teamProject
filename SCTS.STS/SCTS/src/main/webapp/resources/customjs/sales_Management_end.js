/**
 * 
 */
//일매출 클릭
$('.daySales').click(function(){

	$('.chartTitle').text("일매출");

	$("#searchSales").show();
	$("#searchYearSales").hide();
	$("#month").hide();
	$("#productSalesInfo").hide();
	$("#customerProductRankInfo").hide();

	$("#settleSales").children().remove();


	$("#settleSales").append($("<tr></tr>").addClass('tr'));
	$('.tr').append($("<td></td>").text("연도"));
	$('.tr').append($("<td></td>").text("결제수단"));
	$('.tr').append($("<td></td>").text("총 매출"));


	$.ajax({
		type : "GET",
		url : "daySalesSettleInfo",
		dataType: 'jsonp',
		success : function(data) {
			info(data);	
		}

	});


	$.ajax({
		type : "GET",
		url : "daySales",
		dataType: 'jsonp',
		success : function(data) {
			graphInfo(data);

		}

	});

});

//일매출 검색
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


			$('.chartTitle').text("일매출"); 
			graphInfo(data);

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
			info(data);	
		}

	});
});

//월매출
$(".MonthlySales").click(function(){

	$("#searchSales").hide();
	$("#searchYearSales").hide();
	$("#month").show();
	$("#productSalesInfo").hide();
	$("#customerProductRankInfo").hide();

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

			info(data);

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

			$('.chartTitle').text("월매출");

			graphInfo(data)

		}

	});
}



$(".productRank").click(function(){
	$("#searchSales").hide();
	$("#searchYearSales").hide();
	$("#month").hide();
	$("#productSalesInfo").show();
	$("#customerProductRankInfo").hide();

	var year = new Date().getFullYear();
	var month = new Date().getMonth() + 1;

	if(month <= 10){
		month = "0"+month;
	}

	var date = year + "-" + month;


	productInfo(date, 1);
	productRankInfo(date, 1);





});


$(".customerProductRank").click(function(){

	$("#searchSales").hide();
	$("#searchYearSales").hide();
	$("#month").hide();
	$("#productSalesInfo").hide();
	$("#customerProductRankInfo").show();

	$("#settleSales").children().remove();

	$("#settleSales").append($("<tr></tr>").addClass('tr'));
	$('.tr').append($("<td></td>").text("상품 이름"));
	$('.tr').append($("<td></td>").text("쿠폰 사용량"));
	$('.tr').append($("<td></td>").text("총 판매 수량"));
	$('.tr').append($("<td></td>").text("순이익"));
	$('.tr').append($("<td></td>").text("총 매출"));

});


function info(data){



	var length = data.result.length;

	for(var i = 0; i < length; i++){
		$("#settleSales").append($("<tr></tr>").attr("data", i));

		$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].year));
		$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].setle_mth_nm));
		$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].totalPrice +"원"));

	}

}

function graphInfo(data){
	var length = data.result.length;

	var options = {

			title: {
				text: $('.chartTitle').text()
			},
			subtitle: {
				text: 'Plain'
			}, 
			xAxis:{
				categories:[]
			},
			series:[{
				type: 'column',
				colorByPoint: true,
				data : [],
				showInLegend: false
			}]

	}

	for(var i = 0; i < length; i++){

		options.xAxis.categories[i] = data.result[i].bill_issu_de;
		options.series[0].data[i] = data.result[i].totalPrice;

	}


	Highcharts.chart('salesChart', options);
}


function productInfo(date, standard){
	//그래프
	$.ajax({
		url: "productRank",
		type: "GET",
		data: {
			date : date,
			standard : standard
		},
		dataType : "jsonp",
		success : function(data){

			$('.chartTitle').text("상품별 순위");

			var options = {

					chart: {
						type: 'column'
					},

					title: {
						text: $('.chartTitle').text()
					},

					legend: {
						align: 'right',
						verticalAlign: 'middle',
						layout: 'vertical'
					},
					xAxis : {
						categories : []

					},
					yAxis: {
						allowDecimals: false,
						title: {
							text: 'Amount'
						}
					},
					series: [{
						name: '순이익',
						data: []
					}, {
						name: '총매출',
						data: []
					}],
					responsive: {
						rules: [{
							condition: {
								maxWidth: 500
							},
							chartOptions: {
								legend: {
									align: 'center',
									verticalAlign: 'bottom',
									layout: 'horizontal'
								},
								yAxis: {
									labels: {
										align: 'left',
										x: 0,
										y: -5
									},
									title: {
										text: null
									}
								},
								subtitle: {
									text: null
								},
								credits: {
									enabled: false
								}
							}
						}]
					}
			}
			var length = data.result.length;

			for(var i=0; i < length; i++){
				options.xAxis.categories[i] = data.result[i].goods_nm;

				options.series[0].data[i] = data.result[i].goods_netIncome;
				options.series[1].data[i] = data.result[i].totalPrice;

			}



			Highcharts.chart('salesChart', options);

		}

	});

}

function productRankInfo(date, standard){
	
	$("#settleSales").children().remove();

	$("#settleSales").append($("<tr></tr>").addClass('tr'));
	$('.tr').append($("<td></td>").text("상품 이름"));
	$('.tr').append($("<td></td>").text("쿠폰 사용량"));
	$('.tr').append($("<td></td>").text("총 판매 수량"));
	$('.tr').append($("<td></td>").text("순이익"));
	$('.tr').append($("<td></td>").text("총 매출"));

	$.ajax({

		url : "productRankInfo",
		type:"GET",
		data :{
			date : date,
			standard : standard
		},
		dataType: "jsonp",
		success : function(data){

			var length = data.result.length;

			for(var i = 0; i < length; i++){
				$("#settleSales").append($("<tr></tr>").attr("data", i));

				$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].goods_nm));
				$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].totalCouponCount+"개"));
				$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].totalPurchsgoods_qy+"개"));
				$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].goods_netIncome+"원"));
				$("tr[data="+i+"]").append($("<td></td>").text(data.result[i].totalPrice +"원"));

			}



		}

	});

}

$('.plain').click(function () {
	chart.update({
		chart: {
			inverted: false,
			polar: false
		},
		subtitle: {
			text: 'Plain'
		}
	});
});


$('.polar').click(function () {

	chart.update({
		chart: {
			inverted: false,
			polar: true
		},
		subtitle: {
			text: 'Polar'
		}
	});
});

$('.inverted').click(function () {
	chart.update({
		chart: {
			inverted: true,
			polar: false
		},
		subtitle: {
			text: 'Inverted'
		}
	});
});

$('#productSalesInfo .income').click(function () {

	var date = $("#productSalesInfo h3").text();

	productInfo(date, 2);
	productRankInfo(date, 2);

});

$('#productSalesInfo .total').click(function () {

	var date = $("#productSalesInfo h3").text();
	productInfo(date, 1);
	productRankInfo(date, 1);

});

var text = $("#productSalesInfo h3").text();

var year = parseInt(text.split("-")[0]);

var month = parseInt(text.split("-")[1].split("0")[1]);

$('#productSalesInfo .prev').click(function(){
	

	month = month - 1;
	if(month <= 0){
		year = year -1;
		month = 12;
	}

	if(month < 10){
		month = "0"+month;
	}

	$("#productSalesInfo h3").text(year+"-"+month);
	
	var date = $("#productSalesInfo h3").text();
	
	productInfo(date, 1);
	productRankInfo(date, 1);


});

$('#productSalesInfo .next').click(function(){
	

	if(month < 10){
		month = parseInt( $("#productSalesInfo h3").text().split("-")[1].split("0")[1]);

	}else{
		month = parseInt( $("#productSalesInfo h3").text().split("-")[1]);
	}
	
	
	month = month + 1;
	if(month >= 13){
		year = year +1;
		month = 1;
	}

	if(month < 10){
		month = "0"+month;
	}
	
	
	$("#productSalesInfo h3").text(year+"-"+month);
	
	
	if(month < 10){
		month = parseInt(month.split("0")[1]);
	}
	
	var date = $("#productSalesInfo h3").text();
	
	productInfo(date, 1);
	productRankInfo(date, 1);
	
	


	
	

});