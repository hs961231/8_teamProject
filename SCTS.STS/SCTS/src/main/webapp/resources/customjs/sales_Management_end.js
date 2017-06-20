/**
 * 
 */

//---------------------------------------------------------- 웹소켓 -------------------------
var daySalesSocket = new SockJS("/scts/daySales-ws");
var monthSalesSocket = new SockJS("/scts/monthSales-ws");
var productRankSocket = new SockJS("/scts/productRank-ws");
var customerRankSocket = new SockJS("/scts/customerRank-ws");
var searchDaySalesSocket = new SockJS("/scts/searchDaySales-ws");

searchDaySalesSocket.onmessage = function(event){

	var data = event.data;
	data = JSON.parse(data);

	$("#daySales .count").text(data.todaySales);
	$("#MonthlySales .count").text(data.monthTotalSales);

	console.log(data);

	graphInfo(data);


}

searchDaySalesSocket.onclose = function(event){
	alert("searchDay 연결 ㄴㄴ");
}


customerRankSocket.onmessage = function(event){

	var data = event.data;
	data = JSON.parse(data);

	$("#daySales .count").text(data.todaySales);
	$("#MonthlySales .count").text(data.monthTotalSales);

	console.log(data);

	ageSales(data);

}

customerRankSocket.onclose = function(event){
	alert("customerRank ㄴㄴㄴ");
}



productRankSocket.onmessage = function(event){

	var data = event.data;
	data = JSON.parse(data);

	$("#daySales .count").text(data.todaySales);
	$("#MonthlySales .count").text(data.monthTotalSales);

	console.log(data);

	productInfo(data);

}

productRankSocket.onclose = function(event){
	alert("productRank ㄴㄴㄴ");
}

monthSalesSocket.onmessage = function(event){

	var data = event.data;
	data = JSON.parse(data);

	$("#daySales .count").text(data.todaySales);
	$("#MonthlySales .count").text(data.monthTotalSales);

	console.log(data);

	graphInfo(data);


}

monthSalesSocket.onclose = function(event){
	alert("month 연결 ㄴㄴ");
}

daySalesSocket.onmessage = function(event){

	var data = event.data;
	data = JSON.parse(data);

	$("#daySales .count").text(data.todaySales);
	$("#MonthlySales .count").text(data.monthTotalSales);

	console.log(data);

	graphInfo(data);


}

daySalesSocket.onclose = function(event){

	alert("연결 ㄴㄴ");

}
//---------------------------------------------------------- 매출 -------------------------
//일매출
$('#daySales').click(function(){

	$('.chartTitle').text("일매출");

	$("#searchSales").show();
	$("#searchYearSales").hide();
	$("#month").hide();
	$("#productSalesInfo").hide();
	$("#customerProductRankInfo").hide();

	$("#settleSales").children().remove();


	$("#settleSales").append($("<tr></tr>").addClass('tr'));
	$('.tr').append($("<td></td>").text("일"));
	$('.tr').append($("<td></td>").text("결제수단"));
	$('.tr').append($("<td></td>").text("총 매출"));

	daySalesSocket.send('data');

});

//월매출
$(".MonthlySales").click(function(){

	$("#searchSales").hide();
	$("#searchYearSales").hide();
	$("#month").show();
	$("#productSalesInfo").hide();
	$("#customerProductRankInfo").hide();

	$('.chartTitle').text("월매출");

	$("#settleSales").children().remove();

	$("#settleSales").append($("<tr></tr>").addClass('tr'));
	$('.tr').append($("<td></td>").text("월"));
	$('.tr').append($("<td></td>").text("결제수단"));
	$('.tr').append($("<td></td>").text("총 매출"));


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
	var setle_mth_code = null;

	var sendData = JSON.stringify({
		month1 : month1,
		month2 : month2,
		setle_mth_code : setle_mth_code
	});


	monthSalesSocket.send(sendData);




});

//상품 순위

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
	var sendData = JSON.stringify({
		date : date,
		standard : 1

	});

	productRankSocket.send(sendData);
});

//고객이 선호하는 상품 랭킹
//순이익순으로 보여줌
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


	var year = new Date().getFullYear();
	var month = new Date().getMonth() + 1;

	if(month <= 10){
		month = "0"+month;
	}

	var date = year + "-" + month;
	var sendData = JSON.stringify({
		date : date,
		standard : 2

	});

	productRankSocket.send(sendData);

});

//일매출 검색
$("#searchDate").click(function(event){

	var date1 = $("#date1").val();
	var date2 = $("#date2").val();

	var setle_mth_code = $("#setle_mth_code option:selected").val();

	var sendData = JSON.stringify({
		date1 : date1,
		date2 : date2,
		setle_mth_code : setle_mth_code

	});

	searchDaySalesSocket.send(sendData);


});



// 월매출 검색
$("#searchMonth").click(function(){

	var month1 = $("#month1").val();
	var month2 = $("#month2").val();
	var setle_mth_code = $("#setle_mth_code2 option:selected").val();
	
	var sendData = JSON.stringify({
		month1 : month1,
		month2 : month2,
		setle_mth_code : setle_mth_code
	});


	monthSalesSocket.send(sendData);

});


function ageForm(){
	$("#salesChart").siblings().remove();
	$(".chart").append($("<button class='btn btn-default m'>남자</button>"));
	$(".chart").append($("<button class='btn btn-default w'>여자</button>"));
	$("#customerProductRankInfo .income").removeAttr("disabled");
	$("#customerProductRankInfo .income").attr("enabled", true);
	$("#customerProductRankInfo .total").removeAttr("disabled");
	$("#customerProductRankInfo .total").attr("enabled", true);

}




$("#age10").click(function(){

	ageForm();

	var date = $("#date").text();
	$("#age").text("10");

	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : 10,
		gender : "null"

	});

	customerRankSocket.send(sendData);
	

});

$("#age20").click(function(){

	ageForm();
	
	var date = $("#date").text();
	$("#age").text("20");
	
	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : 20,
		gender : "null"

	});

	customerRankSocket.send(sendData);

});


$("#age30").click(function(){

	ageForm();

	var date = $("#date").text();
	$("#age").text("30");
	
	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : 30,
		gender : "null"

	});

	customerRankSocket.send(sendData);
	

});

$("#age40").click(function(){

	ageForm();

	var date = $("#date").text();
	$("#age").text("40");
	
	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : 40,
		gender : "null"

	});

	customerRankSocket.send(sendData);

});

$("#age50").click(function(){
	
	ageForm();

	var date = $("#date").text();
	$(" #age").text("50");

	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : 50,
		gender : "null"

	});

	customerRankSocket.send(sendData);

});

$(document).on("click",".m", function(){

	var date = $("#date").text();
	var age = $("#age").text();
	
	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : age,
		gender : "m"

	});

	customerRankSocket.send(sendData);
	
});

$(document).on("click",".w", function(){
	var date = $("#date").text();
	var age = $("#age").text();
	
	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : age,
		gender : "w"

	});

	customerRankSocket.send(sendData);
});


function ageSales(data){


	$('.chartTitle').text($("#age").text() + "대 고객별 선호 상품 순위");

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
	var length = data.ageSales.length;

	for(var i=0; i < length; i++){
		options.xAxis.categories[i] = data.ageSales[i].goods_nm;
		options.series[0].data[i] = data.ageSales[i].goods_netIncome;
		options.series[1].data[i] = data.ageSales[i].totalPrice;

	}



	Highcharts.chart('salesChart', options);


	$("#settleSales").children().remove();

	$("#settleSales").append($("<tr></tr>").addClass('tr'));
	$('.tr').append($("<td></td>").text("상품 이름"));
	$('.tr').append($("<td></td>").text("쿠폰 사용량"));
	$('.tr').append($("<td></td>").text("총 판매 수량"));
	$('.tr').append($("<td></td>").text("순이익"));
	$('.tr').append($("<td></td>").text("총 매출"));



	var length = data.ageSalesInfo.length;

	for(var i = 0; i < length; i++){
		$("#settleSales").append($("<tr></tr>").attr("data", i));

		$("tr[data="+i+"]").append($("<td></td>").text(data.ageSalesInfo[i].goods_nm));
		$("tr[data="+i+"]").append($("<td></td>").text(data.ageSalesInfo[i].totalCouponCount+"개"));
		$("tr[data="+i+"]").append($("<td></td>").text(data.ageSalesInfo[i].totalPurchsgoods_qy+"개"));
		$("tr[data="+i+"]").append($("<td></td>").text(data.ageSalesInfo[i].goods_netIncome+"원"));
		$("tr[data="+i+"]").append($("<td></td>").text(data.ageSalesInfo[i].totalPrice +"원"));

	}

}

var chart;
function graphInfo(data){
	var length = data.daySales.length;

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

		options.xAxis.categories[i] = data.daySales[i].bill_issu_de;
		options.series[0].data[i] = data.daySales[i].totalPrice;

	}


	chart = Highcharts.chart('salesChart', options);
	
	$("#settleSales").children().remove();

	$("#settleSales").append($("<tr></tr>").addClass('tr'));
	$('.tr').append($("<td></td>").text("월"));
	$('.tr').append($("<td></td>").text("결제수단"));
	$('.tr').append($("<td></td>").text("총 매출"));


	var length = data.daySalesInfo.length;

	for(var i = 0; i < length; i++){
		$("#settleSales").append($("<tr></tr>").attr("data", i));

		$("tr[data="+i+"]").append($("<td></td>").text(data.daySalesInfo[i].day));
		$("tr[data="+i+"]").append($("<td></td>").text(data.daySalesInfo[i].setle_mth_nm));
		$("tr[data="+i+"]").append($("<td></td>").text(data.daySalesInfo[i].totalPrice +"원"));

	}
}


function productInfo(data){
	//그래프

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
	var length = data.productSales.length;

	for(var i=0; i < length; i++){
		options.xAxis.categories[i] = data.productSales[i].goods_nm;

		options.series[0].data[i] = data.productSales[i].goods_netIncome;
		options.series[1].data[i] = data.productSales[i].totalPrice;

	}


	Highcharts.chart('salesChart', options);


	$("#settleSales").children().remove();

	$("#settleSales").append($("<tr></tr>").addClass('tr'));
	$('.tr').append($("<td></td>").text("상품 이름"));
	$('.tr').append($("<td></td>").text("쿠폰 사용량"));
	$('.tr').append($("<td></td>").text("총 판매 수량"));
	$('.tr').append($("<td></td>").text("순이익"));
	$('.tr').append($("<td></td>").text("총 매출"));



	length = data.productSalesInfo.length;

	for(var i = 0; i < length; i++){
		$("#settleSales").append($("<tr></tr>").attr("data", i));

		$("tr[data="+i+"]").append($("<td></td>").text(data.productSalesInfo[i].goods_nm));
		$("tr[data="+i+"]").append($("<td></td>").text(data.productSalesInfo[i].totalCouponCount+"개"));
		$("tr[data="+i+"]").append($("<td></td>").text(data.productSalesInfo[i].totalPurchsgoods_qy+"개"));
		$("tr[data="+i+"]").append($("<td></td>").text(data.productSalesInfo[i].goods_netIncome+"원"));
		$("tr[data="+i+"]").append($("<td></td>").text(data.productSalesInfo[i].totalPrice +"원"));

	}

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

	var sendData = JSON.stringify({
		date : date,
		standard : 2

	});

	productRankSocket.send(sendData);

});

$('#productSalesInfo .total').click(function () {

	var date = $("#productSalesInfo h3").text();
	var sendData = JSON.stringify({
		date : date,
		standard : 1

	});

	productRankSocket.send(sendData);

});

$('#customerProductRankInfo .income').click(function () {

	var date = $("#date").text();
	var age = $("#age").text();

	var sendData = JSON.stringify({
		date : date,
		standard : 2,
		age : age,
		gender : "null"

	});

	customerRankSocket.send(sendData);




});

$('#customerProductRankInfo .total').click(function () {

	var date = $("#date").text();
	var age = $("#age").text();
	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : age,
		gender : "null"

	});

	customerRankSocket.send(sendData);


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
	var sendData = JSON.stringify({
		date : date,
		standard : 1

	});

	productRankSocket.send(sendData);


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


	var sendData = JSON.stringify({
		date : date,
		standard : 1

	});

	productRankSocket.send(sendData);

});




text = $("#customerProductRankInfo #date").text();

year = parseInt(text.split("-")[0]);

month = parseInt(text.split("-")[1].split("0")[1]);

$('#customerProductRankInfo .prev').click(function(){


	month = month - 1;
	if(month <= 0){
		year = year -1;
		month = 12;
	}

	if(month < 10){
		month = "0"+month;
	}

	$("#date").text(year+"-"+month);

	var date = $("#date").text();
	var age = $("#age").text();
	
	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : age,
		gender : "null"

	});

	customerRankSocket.send(sendData);

});

$('#customerProductRankInfo .next').click(function(){


	if(month < 10){
		month = parseInt( $("#date").text().split("-")[1].split("0")[1]);

	}else{
		month = parseInt( $("#date").text().split("-")[1]);
	}


	month = month + 1;
	if(month >= 13){
		year = year +1;
		month = 1;
	}

	if(month < 10){
		month = "0"+month;
	}


	$("#date").text(year+"-"+month);


	if(month < 10){
		month = parseInt(month.split("0")[1]);
	}

	var date = $("#date").text();
	var age = $("#age").text();
	
	
	var sendData = JSON.stringify({
		date : date,
		standard : 1,
		age : age,
		gender : "null"

	});

	customerRankSocket.send(sendData);

});

