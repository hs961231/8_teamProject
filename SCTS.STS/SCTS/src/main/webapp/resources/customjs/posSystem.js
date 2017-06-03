/**
 * 
 */



$(document).ready(function() {

	
	$("#getGoods").on("click", function() {

		var goods_id = $("#goods_id");

		// 해당 부분 현재 테스트용으로 구현 해 놓은것.
		var goodsItem = $('<tr></tr>');
		$('<td></td>').text('1').appendTo(goodsItem);
		$('<td></td>').text('농심').appendTo(goodsItem);
		$('<td></td>').text('라면').appendTo(goodsItem);
		$('<td></td>').text('5000').appendTo(goodsItem);
		$('<td></td>').text('2').appendTo(goodsItem);
		$('<td></td>').text('10000').appendTo(goodsItem);
		$('<td></td>').text('1000').appendTo(goodsItem);
		
		var goodsList = $('#goodsList');
		goodsItem.appendTo(goodsList);
		/*
		$.ajax({
			url: "getGoods",
			type: "post",
			data: {goods_id : goods_id},
			dataType: "json",
			
			success: function(data){
				if(data.length >= 1) {
					var goodsItem = $('<tr></tr>');
					$('<td></td>').text('1').appendTo(goodsItem);
					$('<td></td>').text('농심').appendTo(goodsItem);
					$('<td></td>').text('라면').appendTo(goodsItem);
					$('<td></td>').text('5000').appendTo(goodsItem);
					$('<td></td>').text('2').appendTo(goodsItem);
					$('<td></td>').text('10000').appendTo(goodsItem);
					$('<td></td>').text('1000').appendTo(goodsItem);
					
					var goodsList = $('#goodsList');
					goodsItem.appendTo(goodsList);
				}
				else if(data.length == 0)
					window.alert("상품이 없습니다.")
			},
			error: function(data){
			}
		});
		*/
	});
	
	
});