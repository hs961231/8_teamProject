/**
 * 
 */


$(document).ready(function() {
	imgLoad();
	
	$("div.tile").on("mouseover", function() {
		$(this).addClass("mouseover");
	});
	
	$("div.tile").on("mouseout", function() {
		$(this).removeClass("mouseover");
	});
	
	$("div.tile").on("click", function() {
		$(".tileMap .active").removeClass("active");
		
		$(this).addClass("active");
		
		
		var totalNum = $("div.tile").index($(this))
		var RowNum = $("div.tileMap > div").length;
		
		var X_index = parseInt(totalNum / RowNum);
		var Y_index = totalNum % RowNum;
		
		console.log("X = " + X_index + " Y = " + Y_index);
		
		$.ajax({
			url: "shopTileClick",
			type: "post",
			data: {
				X_index : X_index,
				Y_index : Y_index
			},
			dataType: "json",
			success: function(data) {
				if(data != null) {
					var tile_info = $("#tile_info");
					tile_info.empty();
					
					$("<p></p>").text("tile_code = " + data.tile_code).appendTo(tile_info);
					$("<p></p>").text("tile_nm = " + data.tile_nm).appendTo(tile_info);
					if(data.beacon_code != null) {
						$("<p></p>").text("beacon_code = " + data.beacon_code).appendTo(tile_info);
						$("<p></p>").text("beacon_mjr = " + data.beacon_mjr).appendTo(tile_info);
						$("<p></p>").text("beacon_mnr = " + data.beacon_mnr).appendTo(tile_info);
					}
					else {
						$("<button id='setBeacon'></button>").text("비콘설정").appendTo(tile_info);
					}
					
				}
				else {
					window.alert("현재 해당 타일은 등록되어있지 않습니다.");
				}
			},
			error: function(data) {
				
			}
		});
		
	});
	
	$("#tile_info").on("click", "#setBeacon", function() {
		window.alert("비콘설정 클릭");
	});
	
});

var imgLoad = function() {
	var countStory = $("#countStory").val();
	var floor = $("#floor").val();
	
	$.ajax({
		url: "getDrawingFileName",
		type: "post",
		data: {
			floor : floor
		},
		dataType: "text",
		success: function(data) {
			if(data != null) {
				$('#blueprint > img').remove();
				
				console.log(data);
				
				var drawingImg = $('<img src="displayDrawing?fileName=/' + data + '" style="width: 800px; height: 380px;">');
				drawingImg.appendTo($('#blueprint'));
				
				$("#floor").val(floor+1);
			}
			else {
				window.alert("도면이 없습니다. 등록해주세요");
			}
		},
		error: function(data) {
			console.log("아작스 에러남");
		}
	});
};