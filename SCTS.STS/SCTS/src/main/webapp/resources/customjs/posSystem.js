/**
 * 
 */



$(document).ready(function() {

	/* 상품번호(바코드) 입력해서 상품 한줄 추가하는 부분, 등록 버튼을 누를시 실행됨 */
	$("#getGoods").on("click", function() {
		var goods_code = $("#goods_code").val();
		
		$.ajax({
			url: "getGoodsAjax",
			type: "post",
			data: {goods_code : goods_code},
			dataType: "json",
			
			success: function(data){
				console.log(data);
				
				var goodsItem = $('<tr></tr>').addClass('goodsItem');
				$('<td></td>').text(data.goods_code).appendTo(goodsItem);
				$('<td></td>').text('농심').appendTo(goodsItem);
				$('<td></td>').text(data.goods_nm).appendTo(goodsItem);
				$('<td></td>').text(data.goods_pc).appendTo(goodsItem);
				$('<td></td>').addClass("purchsgoods_qy").text('1').appendTo(goodsItem);
				$('<td></td>').text('5000').appendTo(goodsItem);
				$('<td></td>').text('500').appendTo(goodsItem);
				
				var goodsList = $('#goodsList');
				goodsItem.appendTo(goodsList);
			},
			error: function(data) {
				console.log("에러뜸");
			}
		});
	});
	
	/* 상품 리스트에서 상품 한개 클릭했을때 해당열에 강조 표시 */
	$("#goodsList").on("click", ".goodsItem", function() {
		$("#goodsList > .active").removeClass("active");
		
		$(this).addClass("active");
		
		console.log($(this));
	});
	
	/* 취소 버튼 눌럿을 시 선택된 열 삭제 */
	$("#cancleGoods").on("click", function() {
		$("#goodsList > .active").remove();
	});
	
	/* 수량 + 버튼 클릭 시 */
	$("#additionGoods").on("click", function() {
		var num = parseInt($("#goodsList > .active > .purchsgoods_qy").text());
		num++;
		
		console.log("현재 물품 수량 = " + num);
		$("#goodsList > .active > .purchsgoods_qy").text(num);
	});
	/* 수량 - 버튼 클릭 시 */
	$("#subtractGoods").on("click", function() {
		var num = parseInt($("#goodsList > .active > .purchsgoods_qy").text());
		num++;
		
		console.log("현재 물품 수량 = " + num);
		$("#goodsList > .active > .purchsgoods_qy").text(num);
	});
	
	/* 추후에 수량 증가에 따른 가격 갱신을 위해 필요함 */
	/*
	var resetTotalPC = function(goodsItem) {
		
	};
	*/
	
	$(".numberPad").on("click", function() {
		var num = $(this).text();
		
		if(num == "D") {
			// 바코드에 입력된 숫자 한자리 지우는 것
		}
		else if(num == "C") {
			// 바코드에 입력된 숫자 전체 지우는 것 들어가야함
		}
		else {
			num = parseInt(num);
			// 바코드에 입력되어 있는 숫자에 현재 누른 숫자를 추가해줘야함
		}
		
		console.log(num);
	});
	
	$('#searchGoods').click(function(){
		if(!$('.bgLayer').length){
			$('<div class="bgLayer"></div>').appendTo($('body'));
		}
		var object = $(".bgLayer");
		var object2 = $(".modal-layout");
		var w = $(document).width()+12;
		var h = $(document).height();

		object.css({'width':w, 'height':h});
		object.fadeIn(500); // 나타나는 시간 설정
		object2.fadeIn(500);
	});
	
});