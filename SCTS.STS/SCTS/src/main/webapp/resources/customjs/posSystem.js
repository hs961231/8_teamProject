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

	/*
	 * 쿠폰 포인트 모달 창에서 사용자의 아이디를 입력하였을 때
	 * 사용자가 현재 구매목록에서 사용할 수 있는 쿠폰들을 아작스로 디비에서 가져와서
	 * 모달창에 뿌려주는 역할을 함
	 */
	$('#getUserCoupon').on("click", function() {
		
		if($('#goodsList').find(".goodsItem").length == 0 ) {
			window.alert("구매할 상품을 먼저 등록 해주세요.");
			return;
		}
		
		var goods_code_Array = new Array(); 
		
		$('#goodsList').find(".active").each(function(i, e) {
			goods_code_Array.push( parseInt($(this).find(".goods_code").text()) );
		});
		
		console.log(goods_code_Array);
		var user_id = $('#user_id').val();
		console.log(user_id);

		$.ajax({

			url: "getUserCoupon",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify({
				user_id : user_id,
				goods_code_Array : goods_code_Array
			}),
			dataType: "json",
			
			success: function(data){
				if(data.length > 0) {
					$("#inputMode").css("display", "none");
					$("#couponMode").css("display", "block");
					
					console.log(data);
					
					var couponList = $("#couponList");
					
					for(var i=0; i<data.length; i++) {
						var couponItem = $("<tr></tr>").addClass("couponItem");
						$('<input type="hidden"/>').addClass("coupon_code").val(data[i].coupon_code).appendTo(couponItem);
						$('<input type="hidden"/>').addClass("couponGoods_code").val(data[i].goods_code).appendTo(couponItem);
						$('<td></td>').text(data[i].goods_nm).appendTo(couponItem);
						$('<td></td>').text(data[i].coupon_nm).appendTo(couponItem);
						$('<td></td>').text(data[i].coupon_dscnt).appendTo(couponItem);
						$('<td></td>').text(data[i].coupon_end_de).appendTo(couponItem);
						
						couponItem.appendTo(couponList);
					}
				}
				else {
					window.alert("사용할 수 있는 쿠폰이 없습니다.");
				}
			},
			error: function(data) {
				console.log("에러뜸");
			}
		});
	});
	
	$("#useCoupon").on("click", function() {
		var useCouponArray = new Array();
		var useGoodsArray = new Array();
		
		/* 쿠폰 리스트 아래에서 선택된 쿠폰들의 쿠폰코드와 적용될 물품코드를 저장한다. */
		$("#couponList").find(".active").each(function(i, e) {
			useGoodsArray.push($(this).find(".couponGoods_code").val());
			useCouponArray.push($(this).find(".coupon_code").val());
		});
		
		if(useGoodsArray.length == 0) {
			window.alert("선택된 쿠폰이 없습니다.");
			return;
		}
		$("#goodsList").find(".goodsItem").each(function(i, e) {
			for(var i=0; i<useGoodsArray.length; i++) {
				if($(this).find('.goods_code').text() == useGoodsArray[i]) {
					
				}
			}
		});
		
		
	});

	/* 쿠폰 리스트(모달창) 에서 쿠폰 클릭햇을때 사용한다는 의미로 강조 표시 해줄 것 */
	$("#couponList").on("click", ".couponItem", function() {
		$(this).addClass("active");
	
	});
	
	$("#goodsList").on("click", ".active", function() {
		$(this).removeClass("active");
	});
	
	/* 상품 리스트에서 상품 한개 클릭했을때 해당열에 강조 표시 */
	$("#goodsList").on("click", ".goodsItem", function() {
		$("#goodsList > .active").removeClass("active");
		
		$(this).addClass("active");
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
	
	$(window).click(function(e) {
		
	});
	/* 추후에 가격 입력시 ',' 를 찍어주는 것을 추가할 예정,
	 * 2개의 함수로 나누어서
	 * 1개는 숫자에 ','를 찍어서 반환 해주는 것과
	 * 한개는 ,를 제거해서 반환해주는 것으로 만들 예정
	 */
});