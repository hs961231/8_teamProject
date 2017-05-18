/* 모달 스크립트   */
$('.regist').click(function(){
		if(!$('.bgLayer').length){
			$('<div class="bgLayer"></div>').appendTo($('body'));
		}
		var object = $(".bgLayer");
		var object2 = $(".modal-layout");
		var w = $(document).width()+12;
		var h = $(document).height();

		// object.css({'width':w, 'height':h});  필요없는 부분
		object.fadeIn(500); // 나타나는 시간 설정

		object2.fadeIn(500);
	});


    /* 취소 버튼 클릭 */
	$('.close').click(function(){
		var object = $('.bgLayer');
		var object2 = $(".modal-layout");

		// if(object.length){}; 이프문? 안써도 되긴함.
			object.fadeOut(500, function(){
			});

			object2.fadeOut(500, function(){
			});	
	});

	
/* 모달 스크립트 끝 */
	
/* 기타 스크립트 */
	var formObj = $("#formObj");
	
	$(".modify").on("click",function(){   /* 수정 버튼 클릭 시 */
		formObj.attr("method", "post");
		formObj.attr("action", "coupon_Management");
		formObj.submit();
	});
	
	$(".delete").on("click", function(){  /* 삭제 버튼 클릭 시 */
		console.log(aaatest);
		formObj.attr("method", "post");
		formObj.attr("action", "remove");
		formObj.submit();
	});

	