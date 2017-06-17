<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="icon_genius"></i> 쿠폰 정보 수정
		</h3>

		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="icon_genius"></i>Coupon</li>
		</ol>
	</div>
</div>

<div class="col-lg-12">
	<section class="panel">
		<header class="panel-heading"> 쿠폰 정보 수정 </header>
		<div class="panel-body">
			<div class="form">
				<form class="form-validate form-horizontal" id="modifyCoupon"
					method="get" action="modify">

					<div class="form-group">
						<label for="coupon_name" class="control-label col-lg-2">쿠폰
							이름 <span class="required">*</span>
						</label>
						<div class="col-lg-10">
							<input class="form-control" id="colname" name="coupon_nm"
								type="text" value="${coupon.coupon_nm}" required />
							<input class="form-control" id="colcode" name="coupon_code"
								type="hidden" value="${coupon.coupon_code}" required />
						</div>
					</div>

					<div class="form-group ">
						<label for="coupon_info" class="control-label col-lg-2">쿠폰
							내용 <span class="required">*</span>
						</label>
						<div class="col-lg-10">
							<input class="form-control" id="coupon_info" name="coupon_cntnts"
								type="text" value="${coupon.coupon_cntnts}" required />
						</div>
					</div>

					<div class="form-group ">
						<label for="coupon_sales" class="control-label col-lg-2">쿠폰
							할인율<span class="required">*</span>
						</label>
						<div class="col-lg-10">
							<input class="form-control" id="coupon_sales" name="coupon_dscnt"
								type="text" value="${coupon.coupon_dscnt}" required />
						</div>
					</div>

					<div class="form-group ">
						<label for="regDate" class="control-label col-lg-2">쿠폰 등록
							날짜 <span class="required">*</span>
						</label>
						<div class="col-lg-10">
							<input class="form-control" id="regDate" name="coupon_begin_de"
								type="date" value="${coupon.coupon_begin_de}" required />
						</div>
					</div>

					<div class="form-group ">
						<label for="finDate" class="control-label col-lg-2">쿠폰 종료
							날짜 <span class="required">*</span>
						</label>
						<div class="col-lg-10">
							<input class="form-control" id="finDate" name="coupon_end_de"
								type="date" value="${coupon.coupon_end_de}" required />
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-10">
							<button class="btn btn-primary" id="modify" type="submit">Modify</button>
							<button class="btn btn-default" id="couponCancel" type="button">Cancel</button>
						</div>
					</div>

					<div class="form-group" id="jsAdd"></div>
				</form>
			</div>
		</div>
	</section>
</div>