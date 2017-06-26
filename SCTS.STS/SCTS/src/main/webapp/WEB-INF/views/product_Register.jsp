<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa fa-bars"></i>물품 정보 등록
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="fa fa-bars"></i>Management</li>
			<li><i class="fa fa-bars"></i>Edit</li>
		</ol>
	</div>
</div>

<!-- Form validations -->
<div class="row">
	<div class="col-lg-12">
		<section class="panel">
			<header class="panel-heading"> 물품 정보 등록 </header>
			<div class="panel-body">
				<div class="form">
					<form class="form-validate form-horizontal" id="feedback_form"
						method="post" action="product_Register">
						<div class="form-group ">
							<label for="name" class="control-label col-lg-2">상품 명 <span
								class="required">*</span>
							</label>
							<div class="col-lg-10">
								<input class="form-control" id="name" name="goods_nm"
									type="text" required />
							</div>
						</div>

						<div class="form-group "><!-- 
							<label for="amount" class="control-label col-lg-2">물품 수량
								<span class="required">*</span>
							</label>
							<div class="col-lg-4">
								<input class="form-control" id="amount" name="fullname"
									type="text" required />
							</div> -->

							<label for="price" class="control-label col-lg-2">판매 가 <span
								class="required">*</span>
							</label>
							<div class="col-lg-4">
								<input class="form-control" id="price" name="goods_pc"
									type="number" required />
							</div>
						</div>

						<div class="form-group ">

							<label for="amount" class="control-label col-lg-2">카테고리
								정보 <span class="required">*</span>
							</label>
							<div class="col-lg-4">
								<select class="form-control m-bot15" name="detailctgry_code">
									<option selected>카테고리 정보를 선택해주세요.</option>
									<option value="1">신선 식품(01)</option>
									<option value="2">차량 용품(02)</option>
									<option value="3">가전 제품(03)</option>
									<option value="4">문구 용품(04)</option>
									<option value="5">의류 제품(05)</option>
								</select>
							</div>

							<label for="price" class="control-label col-lg-2">상품 이미지
								파일<span class="required">*</span>
							</label>
							<div class="col-lg-4">
								<input type="file" name="file">
							</div>
						</div>


						<div class="form-group ">
							<label for="ccomment" class="control-label col-lg-2">상품
								설명</label>
							<div class="col-lg-10">
								<textarea class="form-control " id="ccomment" name="goods_dc"
									required></textarea>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<button class="btn btn-primary" type="submit">Save</button>
								<button class="btn btn-default" type="button">Cancel</button>
							</div>
						</div>
					</form>
				</div>

			</div>
		</section>
	</div>
</div>

<script>
	$('.btn-default').on("click",function(){
		alert('hi');
		var row = document.getElementById('row');
		row.style.display("none");
	});
</script>