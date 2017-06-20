$('#mySearch').click(function(){
	var amount = $('#start').val() + $('#end').val();
	var keyword = $('.searchForm').val();
	var searchType = $('#searchType').val();
	window.alert(amount + " " + keyword +" " + searchType);
	
	if(searchType == 'n' && keyword == "" && amount == ""){  // 전부 누락, 전체 리스트 띄움
			check = 7;
		}
		
		if(keyword != ""){ // 상품명만 검색 (o)
			check = 0;
			if(amount != "" && searchType != 'n'){ // 상품명, 카테고리, 재고량 검색 (o)
				check = 1;
			}else if(amount == "" && searchType != 'n'){ // 상품명, 카테고리만 검색 (o)
				check = 2;
			}else if(amount != "" && searchType == 'n'){ // 상품명, 재고량만 검색  (o)
				check = 3;
			}
		}else if(amount != ""){ // 재고량만 검색 (o)
			check = 4;
			if(keyword == "" && searchType != 'n'){ // 카테고리, 재고량 검색 (o)
				check = 5;
			}
		}else if(searchType != 'n'){   // 카테고리만 검색 (o)
			check = 6;
		}
		window.alert("check는 "+check);
		
	$('.navbar-inner .navbar-form').append("<input type='text' name='check' value='"+ check + "'/> ");	
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
	$('.form').attr("method", "get");
	$('.form').submit();
});
