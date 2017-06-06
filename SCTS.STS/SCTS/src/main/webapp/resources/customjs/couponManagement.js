/* 모달 스크립트   */
	$('.regist').click(function(){
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

	$('.modiBtn').click(function(){
		window.alert("외부 수정 버튼");
		if(!$('.bgLayer').length){
			$('<div class="bgLayer"></div>').appendTo($('body'));
		}
		var object = $('.bgLayer');
		var object2 = $(".myModal");
		var w = $(document).width()+12;
		var h = $(document).height();
		
		object.css({'width':w, 'height':h});
		object.fadeIn(500); // 나타나는 시간 설정
		object2.fadeIn(500);
	});
	
    /* 취소 버튼 클릭 */
	$('.canBtn').click(function(){
		var object = $('.bgLayer');
		var object2 = $(".modal-layout");
		var object3 = $(".myModals");
		
		// if(object.length){}; 이프문? 안써도 되긴함.
			object.fadeOut(500, function(){});
			object2.fadeOut(500, function(){});
			object3.fadeOut(500, function(){});
	});

/* 모달 스크립트 끝 */

	
/* 기타 스크립트 */
	$(".regiBtn").on("click", function(){
		$(".formObj").attr("action", "insertCoupon");
		$(".formObj").attr("method", "post");
		$(".formObj").submit();
	});

	$(".modifyBtn").on("click", function(){
		window.alert("내부 수정 버튼");
	});

	$(".delBtn").on("click", function(){
		var CouNum = $(this).parent().siblings().eq(0).html();
		window.alert(CouNum);
		$('.formObj').append("<input type='number' name='coupon_code' value='"+ CouNum +"'/>");
		$(".formObj").attr("action", "deleteCoupon");
		$(".formObj").attr("method", "post");
		$(".formObj").submit();
	});

	
