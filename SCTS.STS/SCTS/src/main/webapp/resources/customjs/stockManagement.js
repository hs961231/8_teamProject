$('#mySearch').click(function(){
	var goodsName = $('.form-control.searchForm').val();
	/*var startAmount = $('#startAmount').val();
	var endAmount = $('#endAmount').val();
	var stockCategory = $('#stockCategory').val();*/
	
	$('.navbar-form').attr("action", "searchStock");
	$('.navbar-form').attr("method", "post");
	$('.navbar-form').submit();
});


$('.delBtn').click(function(){
	var SiNum = $(this).parent().siblings().eq(0).html();
	window.alert(SiNum);
	$('.form').append("<input type='number' name='goods_code' value='"+ SiNum + "'/> ");
	$('.form').attr("action", "deleteStock");
	$('.form').attr("method", "post");
	$('.form').submit();
});