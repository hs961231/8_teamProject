/**
 * 
 */

$(document).ready(function() {
	if($("#countStory").val() > 0)
		imgLoad(0);
	/* ********************************************************************************************************* /
	 * 타일관련 선택시
	 */
	$("div.tileMap").on("mouseover", ".tile", function() {
		$(this).addClass("mouseover");
	});
	
	$("div.tileMap").on("mouseout", ".tile", function() {
		$(this).removeClass("mouseover");
	});
	
	// 버튼 클릭시 업로드 되어있는 사진들 전환
	$("#leftDrawingBtns").on("click", function() {
		var countStory = parseInt($("#countStory").val());
		var floor = parseInt($("#floor").val());

		if(floor > 0) {
			floor--;
			$("#floor").val(floor);
			imgLoad(floor);
		}
	});
	
	$("#rightDrawingBtns").on("click", function() {
		var countStory = parseInt($("#countStory").val());
		var floor = parseInt($("#floor").val());

		if(floor < countStory-1) {
			floor++;
			$("#floor").val(floor);
			imgLoad(floor);
		}
		else if(floor == countStory-1) {
			floor++;
			$("#floor").val(floor);
			$('#blueprint').empty();
			$(".tileMap").empty();
			
			var blueprint = $('#blueprint');
			$("<p>새로운 도면을 등록해주세요 </p>").appendTo(blueprint);
		}
	});
	/* 
	 * 타일관련 선택시
	 * *********************************************************************************************************/
	
	
	/* ********************************************************************************************************* /
	 * 매장 등록 버튼 클릭 시 뜨는 모달창 관련
	 */
	$("#MyBtn").on("click", function() {
		//$('#Modal').css('display', 'block');	// 해당 하는 부분은 shopRegister.js 에서 처리하고 있어서 필요 없음

		var countStory = parseInt($("#countStory").val());
		
		$.ajax({
			url: "shop_RegisterForm",
			type: "get",
			dataType: "json",
			success: function(data) {
				// 지점 정보들 셀렉트 리스트에 등록
				var branchList = $("#bhf_code");
				branchList.empty();
				for(var i=0; i<data.length; i++) {
					$("<option></option>").val(data[i].bhf_code).text(data[i].bhf_nm).appendTo(branchList);
				}
				
				// 층 정보 셀렉트 리스트에 등록
				var floorList = $("#floorinfo_floor");
				floorList.empty();
				for(var i=0; i<countStory+1; i++) {
					$("<option></option>").val(i+1).text((i+1) + "층").appendTo(floorList);
				}
			},
			error : function(data) {
				
			}
		});
	});

	/* 
	 * 매장 등록 버튼 클릭 시 뜨는 모달창 관련
	 * *********************************************************************************************************/
	// 처음 로딩시 성별 그래프
	 var day = $("#duration option:selected").val();
	 tileGenderAll(day);
	
	
	/**
	 * 타일 클릭시 해당 타일 정보를 아작스로 서버에서 가져와서 도면 우측편에 표시하는 것
	 */
	$("div.tileMap").on("click", ".tile", function() {
		$(".tileMap .active").removeClass("active");
		
		$(this).addClass("active");
		
		
		var totalNum = $("div.tile").index($(this))
		var RowNum = $("div.tileMap > div").length;

		var drw_code = parseInt($("#drw_code").val());
		var X_index = parseInt(totalNum / RowNum);
		var Y_index = totalNum % RowNum;
		
		$.ajax({
			url: "shopTileClick",
			type: "post",
			data: {
				drw_code : drw_code,
				X_index : X_index,
				Y_index : Y_index
			},
			dataType: "json",
			success: function(data) {
				var tile_info = $("#tile_info");
				tile_info.empty();
				
				if(data != null) {		
					
					$("<p></p>").text("tile_nm = " + data.tile_nm).appendTo(tile_info);
					if(data.beacon_code != null) {
						//$("<p></p>").text("beacon_code = " + data.beacon_code).appendTo(tile_info);
						$("<p></p>").text("beacon_mjr = " + data.beacon_mjr).appendTo(tile_info);
						$("<p></p>").text("beacon_mnr = " + data.beacon_mnr).appendTo(tile_info);
					}
					else {
						$("<button id='getBeacon'></button>").text("비콘설정").appendTo(tile_info);
					}
					
				}
				else {
					$("<p></p>").text("해당 타일은 등록되어 있지 않습니다.").appendTo(tile_info);
				}
			},
			error: function(data) {
				
			}
		});
		
	});
	
	
	/**
	 * 모달에 출력된 비콘리스트를 하나 클릭하면 서버에 해당 타일에 해당 비콘을 저장시키는 것
	 * 아직 제대로 코딩 안됨
	 */
	$("#beaconList").on("click", ".beacon", function() {
		var tile_code = parseInt($("#tile_code").val());
		var beacon_mjr = parseInt($(this).children(".beacon_mjr").text());
		var beacon_mnr = parseInt($(this).children(".beacon_mnr").text());
		
		console.log(beacon_mjr + "  비콘 정보들  " + beacon_mnr);
		
		$.ajax({
			url: "setTileBeacon",
			type: "post",
			dataType: "text",
			contentType: "application/json",
			data: JSON.stringify({
				tile_code : tile_code,
				beacon_mjr : beacon_mjr,
				beacon_mnr : beacon_mnr
			}),
			success: function(data) {
				if(data == "success") {
					$('#listModal').css('display', 'none');
					var tile_info = $("#tile_info");
					
					//$("<p></p>").text("beacon_code = " + data.beacon_code).appendTo(tile_info);
					$("<p></p>").text("beacon_mjr = " + beacon_mjr).appendTo(tile_info);
					$("<p></p>").text("beacon_mnr = " + beacon_mnr).appendTo(tile_info);
					$("#getBeacon").remove();
				}
			},
			error: function(data) {
				
			}
		});
	});
	
	
	/**
	 * 타일에서 비콘설정버튼을 클릭햇을때 현재 사용할 수 있는 비콘들을 모두 가져와서 리스트 형태로 출력
	 */
	$("#tile_info").on("click", "#getBeacon", function() {
		
		$.ajax({
			url: "getBeaconList",
			type: "post",
			dataType: "json",
			success: function(data) {
				if(data != null) {
					$('#listModal').css('display', 'block');
					//listModal.style.display = "block";
					
					$("#beaconList").empty();
					
					for(var i=0; i<data.length; i++) {
						var beaconItem = $("<tr class='beacon'></tr>");
						//$("<td></td>").attr("id", "beacon_mjr").text(data[i].beacon_mjr).appendTo(beaconItem);
						//$("<td></td>").attr("id", "beacon_mnr").text(data[i].beacon_mnr).appendTo(beaconItem);
						$("<td></td>").addClass("beacon_mjr").text(data[i].beacon_mjr).appendTo(beaconItem);
						$("<td></td>").addClass("beacon_mnr").text(data[i].beacon_mnr).appendTo(beaconItem);
						$("<td></td>").text(data[i].beacon_sttus).appendTo(beaconItem);
						
						beaconItem.appendTo($("#beaconList"));
					}
				}
				else {
					window.alert("등록된 비콘이 없습니다.");
				}
			},
			error: function(data) {
				
			}
		});
		
	});
	// 성별 누를 시 그래프
	$("#gender").click(function(){
		 var day = $("#duration option:selected").val();
		 $("#title").attr("data-id", "1");
		 $("#title").text("성별");
		 tileGenderAll(day);
	});
	$("#age").click(function(){
		var day = $("#duration option:selected").val();
		 $("#title").attr("data-id", "2");
		 $("#title").text("나이");
		tileAgeAll(day);
	});
	
	$("#duration").on("change", function(){
		var id = $("#title").attr("data-id");
		var day = $("#duration option:selected").val();
		
		if(id == "1"){
			 tileGenderAll(day);
		}else if(id == "2"){
			tileAgeAll(day);
		}
	});
	
});

function tileGenderAll(day){
	
	$.ajax({
		url : "tileGender",
		type : "GET",
		data : {
			day: day
		},
		dataType : "json",
		success : function(result){
			var options = {
				    chart: {
				        plotBackgroundColor: null,
				        plotBorderWidth: 0,
				        plotShadow: false
				    },
				    /*events:{
				    	load : setInterval(function(){
				    		var length = result.tileGender.length;
							
							console.log(result);
							
							var series = options.series;
							
							console.log(series);

							for(var i = 0; i < length; i++){
								series[0].data[i] = [];
								series[0].data[i][0] = result.tileGender[i].user_sexdstn;
								series[0].data[i][1] = result.tileGender[i].probability;
							}
							
							Highcharts.chart('tile_graph', options);
				    	}, 5000)
				    },*/
				    title: {
				        text: '전체 성별 방문율',
				        align: 'center',
				        verticalAlign: 'middle',
				        y: 40
				    },
				    tooltip: {
				        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				    },
				    plotOptions: {
				        pie: {
				            dataLabels: {
				                enabled: true,
				                distance: -50,
				                style: {
				                    fontWeight: 'bold',
				                    color: 'white'
				                }
				            },
				            startAngle: -90,
				            endAngle: 90,
				            center: ['50%', '75%']
				        }
				    },
				    series: [{
				        type: 'pie',
				        name: 'Browser share',
				        innerSize: '50%',
				        data: [
//				            ['Firefox',   10.38],
//				            ['IE',       56.33],
//				            ['Chrome', 24.03],
//				            ['Safari',    4.77],
//				            ['Opera',     0.91]
				            
				        ]
				    }]
				}
			
			
			var length = result.tileGender.length;
			
			console.log(result);

			for(var i = 0; i < length; i++){
				options.series[0].data[i] = [];
				options.series[0].data[i][0] = result.tileGender[i].user_sexdstn;
				options.series[0].data[i][1] = result.tileGender[i].probability;
			}
			
			console.log(result.tileGender);
			Highcharts.chart('tile_graph', options);

			
		}
	});
}



function tileAgeAll(day){
	$.ajax({
		url : "tileAge",
		type : "GET",
		data : {
			day: day
		},
		dataType : "json",
		success : function(result){
			var options = {
				    chart: {
				        type: 'pie',
				        options3d: {
				            enabled: true,
				            alpha: 45
				        }
				    },
				    title: {
				        text: '연령별 방문율'
				    },
				    plotOptions: {
				        pie: {
				            innerSize: 100,
				            depth: 45
				        }
				    },
				    series: [{
				        name: 'Delivered amount',
				        data: [
				            /*['Bananas', 8],
				            ['Kiwi', 3],
				            ['Mixed nuts', 1],
				            ['Oranges', 6],
				            ['Apples', 8],
				            ['Pears', 4],
				            ['Clementines', 4],
				            ['Reddish (bag)', 1],
				            ['Grapes (bunch)', 1]*/
				        ]
				    }]
			}
			var length = result.tileAge.length;
			
			console.log(result);

			for(var i = 0; i < length; i++){
				options.series[0].data[i] = [];
				options.series[0].data[i][0] = result.tileAge[i].agegroup + "대";
				options.series[0].data[i][1] = result.tileAge[i].probability;
			}
			
			Highcharts.chart('tile_graph', options);
		}
			
	});
}

