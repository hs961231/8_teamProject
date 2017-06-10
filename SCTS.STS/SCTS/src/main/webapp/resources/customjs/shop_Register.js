/**
 * 현재 이 파일은 dashBoard와
 * shop_Register 두개의 페이지에서 사용중이지만
 * 실제 사용하는 대부분은 shop_Register탭임
 */


$(document).ready(function() {
	if($("#countStory").val() > 0)
		imgLoad(0);
	/* ********************************************************************************************************* /
	 * 타일관련 선택시
	 */
	$("div.tile").on("mouseover", function() {
		$(this).addClass("mouseover");
	});
	
	$("div.tile").on("mouseout", function() {
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
				for(var i=0; i<data.length; i++) {
					$("<option></option>").val(data[i].bhf_code).text(data[i].bhf_nm).appendTo(branchList);
				}
				
				// 층 정보 셀렉트 리스트에 등록
				var floorList = $("#floorinfo_floor");
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
	
	
	/**
	 * 타일 클릭시 해당 타일 정보를 아작스로 서버에서 가져와서 도면 우측편에 표시하는 것
	 */
	$("div.tile").on("click", function() {
		$(".tileMap .active").removeClass("active");
		
		$(this).addClass("active");
		
		
		var totalNum = $("div.tile").index($(this))
		var RowNum = $("div.tileMap > div").length;

		var floor = parseInt($("#floor").val());
		var X_index = parseInt(totalNum / RowNum);
		var Y_index = totalNum % RowNum;
		
		$.ajax({
			url: "shopTileClick",
			type: "post",
			data: {
				floor : floor,
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
						$("<button id='getBeacon'></button>").text("비콘설정").appendTo(tile_info);
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
	
	
	/**
	 * 모달에 출력된 비콘리스트를 하나 클릭하면 서버에 해당 타일에 해당 비콘을 저장시키는 것
	 * 아직 제대로 코딩 안됨
	 */
	$("#beaconList").on("click", ".beacon", function() {
		console.log($(this));
		
		$.ajax({
			url: "setTileBeacon",
			type: "post",
			dataType: "text",
			success: function(data) {
				$('#listModal').css('display', 'none');
				alert("성공");
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
						$("<td></td>").text(data[i].beacon_mjr).appendTo(beaconItem);
						$("<td></td>").text(data[i].beacon_mnr).appendTo(beaconItem);
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
	
});

/**
 * 도면 이미지를 페이지에 뿌려줌
 */
var imgLoad = function(floor) {
	//var countStory = $("#countStory").val();
	//var floor = $("#floor").val();
	
	$.ajax({
		url: "getDrawingFileName",
		type: "post",
		data: {
			floor : floor
		},
		dataType: "text",
		success: function(data) {
			if(data != null) {
				$('#blueprint').empty();
				
				console.log(data);
				
				var drawingImg = $('<img src="displayDrawing?fileName=/' + data + '" style="width: 800px; height: 380px;">');
				drawingImg.appendTo($('#blueprint'));
				
				//$("#floor").val(floor+1);
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