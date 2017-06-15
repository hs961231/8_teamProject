<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script src="resources/customjs/dashBoard.js"></script>
<link href="resources/customcss/tileMapClick.css" rel="stylesheet" />


<script>
$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    Highcharts.chart('charts', {
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            renderTo: 'charts',
            defaultSeriesType: 'column',
            width: '670',
            events: {
                load: function () {

                    // set up the updating of the chart each second
                    var series = this.series[0];
                    setInterval(function () {
                        var x = (new Date()).getTime(), // current time
                            y = Math.random();
                        series.addPoint([x, y], true, true);
                    }, 1000);
                }
            }
        },
        title: {
            text: 'Live random data'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Value'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'Random data',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: Math.random()
                    });
                }
                return data;
            }())
        }]
    });
    
    var chart;
    
    $.ajax({
		type : "GET",
		url : "daySales",
		dataType: 'json',
		success : function(data) {
			
			var length = data.result.length;
			
			 var options = {

				        title: {
				            text: '일매출'
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
				        

				        /* xAxis: {
				            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
				        },

				        series: [{
				            type: 'column',
				            colorByPoint: true,
				            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4],
				            showInLegend: false
				        }] */

				    }
			 
			 for(var i = 0; i < length; i++){
				 
				 options.xAxis.categories[i] = data.result[i].bill_issu_de;
				 options.series[0].data[i] = data.result[i].totalPrice;
				 
			 }
			
			 
			 chart = Highcharts.chart('barChart', options);
			 
		
		}
			 

	
	});
    
    
    
    
    
    
   


    $('#plain').click(function () {
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
    
    
    $('#polar').click(function () {
    	
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

    $('#inverted').click(function () {
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

   
    
    
});

</script>
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
				<div id="barChart"></div>
				<button id="plain">Plain</button>
				<button id="inverted">Inverted</button>
				<button id="polar">Polar</button>
			</div>
		</section>
	</div>

	<div class="col-lg-6">
		<section class="panel">
			<header class="panel-heading chartTitle"> 실시간 방문자수 </header>
			<div class="panel-body text-center">
			<div id="charts"
				style="min-width: 550px; height: 400px; margin: 0 auto;"></div>
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
				<div class="panel-actions">
					<a href="#" class="btn-setting" id="leftDrawingBtns"><i id="leftBtns"
						class="fa fa-chevron-left" aria-hidden="true"></i></a>
					<a href="#" class="btn-setting" id="rightDrawingBtns"><i id="rightBtns"
						class="fa fa-chevron-right" aria-hidden="true"></i></a>
				</div>
			</div>
			<div class="panel-body-map">
			 	<input type="hidden" id="countStory" value="${ countStory }">
			 	<input type="hidden" id="floor" value="0">
			 	<input type="hidden" id="drw_code" value="0">
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


