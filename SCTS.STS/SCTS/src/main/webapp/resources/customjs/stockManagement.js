$('#mySearch').click(function(){
	var goodsName = $('.form-control.searchForm').val();
	/*var startAmount = $('#startAmount').val();
	var endAmount = $('#endAmount').val();
	var stockCategory = $('#stockCategory').val();*/
	
	$('.navbar-form').attr("action", "searchStock");
	$('.navbar-form').attr("method", "post");
	$('.navbar-form').submit();
});