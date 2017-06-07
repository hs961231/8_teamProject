<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa fa-bars"></i> 매장 등록
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="fa fa-bars"></i>Management</li>
		</ol>
	</div>
</div>

<!-- page start-->
<form id="pageForm">
</form>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>ProductList</strong>
				</h2>
				<div class="panel-actions">
					<a href="product_Register" class="btn-setting"><i
						class="fa fa-plus" aria-hidden="true"></i></a>
				</div>
			</div>
			<section class="panel" style="overflow: scroll; height: 430px;">>
					<table class="table table-striped table-advance table-hover">
						<tbody>
							<tr>
								<th style="text-align: center;"><i class="icon_profile"></i>
									Product_no</th>
								<th style="text-align: center;"><i class="icon_calendar"></i>
									Category_no</th>
								<th style="text-align: center;"><i class="icon_mail_alt"></i>
									Category_name</th>
								<th style="text-align: center;"><i class="icon_pin_alt"></i>
									Product_name</th>
								<th style="text-align: center;"><i class="icon_pin_alt"></i>
									Product_Amount</th>
								<th style="text-align: center;"><i class="icon_pin_alt"></i>
									Price(won)</th>
							</tr>

							<c:forEach items="${ GoodsList }" var="vo">
								<tr>
									<td style="text-align: center;">${ vo.goods_code }</td>
									<td style="text-align: center;">${ vo.detailctgry_code }</td>
									<td style="text-align: center;"><a href="${vo.goods_code}"
										class="code">이름출력 아직 안됨</a></td>
									<td style="text-align: center;">${ vo.goods_nm }</td>
									<td style="text-align: center;">상품수량 아직 안됨</td>
									<td style="text-align: center;">${ vo.goods_pc }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</section>
		</div>
	</div>
</div>

<script>
	var pageForm = $("#pageForm");

	$(".code").on("click", function(){
		event.preventDefault();	
		var product_id = $(this).attr("href");
		pageForm.attr("action", "product_Info");
		pageForm.attr("method", "get");
		$("<input type='hidden' name='product_id' value='"+product_id+"'>").appendTo(pageForm);
		pageForm.submit();
	}); 
	
</script>