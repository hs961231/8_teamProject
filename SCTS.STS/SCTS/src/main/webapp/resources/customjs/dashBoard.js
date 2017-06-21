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
	
	
	/**
	 * 타일 클릭시 해당 타일 정보를 아작스로 서버에서 가져와서 도면 우측편에 표시하는 것
	 * 실제 매장등록부분과 가장 다른 타일 클릭시 데이터를 뿌려주는 아작스
	 * 
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
			url: "getTileData",
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
				
				if(data.status == "success") {
					$("<p></p>").text("일단 콘솔에 데이터 찍혀있음.").appendTo(tile_info);
					$("<p></p>").text("null 찍혀있으면 데이터 없는거.").appendTo(tile_info);
					
					// 일단 콘솔에 오는 데이터만 출력햇음
					console.log(data);
					console.log(data.jsonPro);
					console.log(data.jsonUser);
					
					// 데이터 넘어오는 형식
					/*
					data.jsonPro.tile_visit; // 해당 타일 방문자 수
					data.jsonPro.total_visit; // 매장 방문자 수
					for(var i=0; i< data.jsonUser.length; i++) {
						data.jsonUser[i].user_id; // 유저 아이디
						data.jsonUser[i].user_nm; // 유저 이름
						data.jsonUser[i].agegroup; // 유저 연령대
						data.jsonUser[i].user_sexdstn; // 유저 성별
						data.jsonUser[i].user_mrrg_at; // 유저 혼인여부
					};
					
					$.each(data.jsonUser, function(index) {
						this.user_id; // 유저 아이디
						this.user_nm; // 유저 이름
						this.agegroup; // 유저 연령대
						this.user_sexdstn; // 유저 성별
						this.user_mrrg_at; // 유저 혼인여부
					});*/
					
				}
				else {
					$("<p></p>").text("해당 타일은 등록되어 있지 않거나 데이터가 없습니다.").appendTo(tile_info);
				}
			},
			error: function(data) {
				console.log("데이터 전송 에러");
			}
		});
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