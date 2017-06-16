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

				var goodsList = $('#goodsList');
				
				var equalsGoods = false;
				goodsList.find(".goodsItem").each(function(i, e) {
					console.log(  );
					if($(this).find(".goods_code").text() == data.goods_code) {
						equalsGoods = true;
						
						var purchsgoods_qy = parseInt($(this).find(".purchsgoods_qy").text());
						$(this).find(".purchsgoods_qy").text(purchsgoods_qy+1);
						
						reloadPrice($(this));
					}
				});
				
				if(!equalsGoods) {
					var goodsItem = $('<tr></tr>').addClass('goodsItem');
					$('<td></td>').addClass("goods_code").text(data.goods_code).appendTo(goodsItem);
					$('<td></td>').text('농심').appendTo(goodsItem);
					$('<td></td>').text(data.goods_nm).appendTo(goodsItem);
					$('<td></td>').addClass("goodsPrice").text(data.goods_pc).appendTo(goodsItem);	// 상품 한개 가격
					$('<td></td>').addClass("purchsgoods_qy").text(1).appendTo(goodsItem);		// 수량 (처음엔 1개로 들어감)
					$('<td></td>').addClass("price").text(data.goods_pc).appendTo(goodsItem);	// 수량 * 가격 , 처음엔 1개 기준으로 들어감
					$('<td></td>').addClass("discount").text(0).appendTo(goodsItem);
					
					goodsItem.appendTo(goodsList);
					reloadTotalPrice();
				}
				$("#goods_code").val("");
			},
			error: function(data) {
				//console.log("에러뜸");
				window.alert("해당 상품이 존재하지 않습니다.");
				$("#goods_code").val("");
			}
		});
	});
	
	$('#getUserCoupon').on("click", function() {
		var user_id = $('#user_id').text();
		
		$.ajax({

			url: "getUserCoupon",
			type: "post",
			contentType: "application/json",
			data: {user_id : user_id},
			dataType: "json",
			
			success: function(data){
				
			},
			error: function(data) {
				//console.log("에러뜸");
				window.alert("해당 상품이 존재하지 않습니다.");
				$("#goods_code").val("");
			}
		});
	});
	
	
	/* 상품 리스트에서 상품 한개 클릭했을때 해당열에 강조 표시 */
	$("#goodsList").on("click", ".goodsItem", function() {
		$("#goodsList > .active").removeClass("active");
		
		$(this).addClass("active");
		
		console.log($(this));
	});
	
	$("#goodsList").on("click", ".active", function() {
		$(this).removeClass("active");
	});

	/* 취소 버튼 눌럿을 시 선택된 열 삭제 */
	$("#cancleGoods").on("click", function() {
		$("#goodsList > .active").remove();
		reloadTotalPrice();
	});

	/* 수량 + 버튼 클릭 시 */
	$("#additionGoods").on("click", function() {
		var num = parseInt($("#goodsList > .active > .purchsgoods_qy").text());
		num++;
		
		console.log("현재 물품 수량 = " + num);
		$("#goodsList > .active > .purchsgoods_qy").text(num);
		reloadPrice($("#goodsList > .active"));
	});
	/* 수량 - 버튼 클릭 시 */
	$("#subtractGoods").on("click", function() {
		var num = parseInt($("#goodsList > .active > .purchsgoods_qy").text());
		if(num <= 1) {
			$("#goodsList > .active").remove();
		}
		else {
			num--;
			
			console.log("현재 물품 수량 = " + num);
			$("#goodsList > .active > .purchsgoods_qy").text(num);
		}
		reloadPrice($("#goodsList > .active"));
	});
	
	/* 추후에 수량 증가에 따른 가격 갱신을 위해 필요함 */
	var reloadPrice = function(goodsItem) {
		
		var purchsgoods_qy = parseInt(goodsItem.find(".purchsgoods_qy").text());
		goodsItem.find(".price").text( parseInt(goodsItem.find(".goodsPrice").text()) * (purchsgoods_qy) );
		
		reloadTotalPrice();
	};
	/* 전체 가격 변동이 일어날 경우 갱신하는 함수 */
	var reloadTotalPrice = function() {
		var totalPrice = 0;
		
		$("#goodsList").find(".goodsItem").each(function(i, e) {
			totalPrice += parseInt( $(this).find(".price").text() );
		});
		$("#totalPrice").text(totalPrice);
		console.log("임시로 일단 추가 안하고 여기서 표시 전체 가격 = " + totalPrice);
	}
	
	$(".numberPad").on("click", function() {
		var num = $(this).text();
		
		if(num == "D") {
			// 바코드에 입력된 숫자 한자리 지우는 것
			var length = $("#goods_code").val().length;
			var str = $("#goods_code").val().substr(0,length-1);
			
			$("#goods_code").val(str);
		}
		else if(num == "C") {
			// 바코드에 입력된 숫자 전체 지우는 것 들어가야함
			$("#goods_code").val("");
		}
		else {
			num = parseInt(num);
			// 바코드에 입력되어 있는 숫자에 현재 누른 숫자를 추가해줘야함
			
			var str = $("#goods_code").val();
			
			$("#goods_code").val( str + num );
		}
	});

	/* 추후에 가격 입력시 ',' 를 찍어주는 것을 추가할 예정,
	 * 2개의 함수로 나누어서
	 * 1개는 숫자에 ','를 찍어서 반환 해주는 것과
	 * 한개는 ,를 제거해서 반환해주는 것으로 만들 예정
	 */
});