 $('#mySearch').click(function(){
		var start = $('#startAmount').val();
		var end = $('#endAmount').val();
		var keyword = $('.searchForm').val();
		var searchType = $('#searchType').val();
		window.alert(start + " " + end + " " + keyword +" " + searchType);
		

	// 카테고리 선택 안할 시 0으로 변경
/*	if(searchType == "미분류"){
		lclasctgry_code = 0;
	}else{
		lclasctgry_code = lclasctgry_code.substr(0,1);
	}
	
	if(lclasctgry_code == 0 && goods_nm == "" && amount == ""){  // 전부 누락, 전체 리스트 띄움
		check = 7;
	}
	
	if(goods_nm != ""){ // 상품명만 검색 (o)
		check = 0;
		if(amount != "" && lclasctgry_code != 0){ // 상품명, 카테고리, 재고량 검색 (o)
			check = 1;
		}else if(amount == "" && lclasctgry_code != 0){ // 상품명, 카테고리만 검색 (o)
			check = 2;
		}else if(amount != "" && lclasctgry_code == 0){ // 상품명, 재고량만 검색  (o)
			check = 3;
		}
	}else if(amount != ""){ // 재고량만 검색 (o)
		check = 4;
		if(goods_nm == "" && lclasctgry_code != 0){ // 카테고리, 재고량 검색 (o)
			check = 5;
		}
	}else if(lclasctgry_code != 0){   // 카테고리만 검색 (o)
		check = 6;
	}*/
	
	
	// start / endAmount는 값이 누락될 경우 공백으로 넘어가므로 오류가 나니까 동적으로 추가하는게 나을거같다.
	$('.navbar-inner .navbar-form').attr("action", "searchStock");
	$('.navbar-inner .navbar-form').attr("method", "get");
	$('.navbar-inner .navbar-form').submit();
	
});


$('.delBtn').click(function(){
	var SiNum = $(this).parent().siblings().eq(1).html();
	var SiNum2 = $(this).parent().siblings().eq(4).html();
	window.alert(SiNum + SiNum2);
	$('.form').append("<input type='text' name='user_id' value='"+ SiNum + "'/> ");
	$('.form').append("<input type='number' name='goods_code' value='"+ SiNum2 + "'/> ");
	$('.form').attr("action", "deleteStock");
	$('.form').attr("method", "post");
	$('.form').submit();
});
