<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/customcss/couponManagement.css" rel="stylesheet" />

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="icon-envelope-l"></i> 쿠폰 관리
		</h3>

		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="icon-envelope-l"></i>Coupon</li>
		</ol>
		<!-- page start -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h2>
							<i class="fa fa-map-marker red"></i><strong>CouponList</strong>
						</h2>

						<div class="panel-actions">
							<a href="#" class="btn-setting" id="couponBtn"><i
								id="couponBtn" class="fa fa-plus" aria-hidden="true"></i></a>
						</div>
					</div>

					<form class="formObj">
						<section class="panel col-lg-12"
							style="overflow: scroll; height: 600px;">
							<table class="table table-striped table-advance table-hover">
								<thead>
									<tr>
										<th style="text-align: center;"><i class="icon_profile"></i>
											Coupon_code</th>
										<th style="text-align: center;"><i class="icon_calendar"></i>
											Coupon_name</th>
										<th style="text-align: center;"><i class="icon_mail_alt"></i>
											Coupon_Info</th>
										<th style="text-align: center;"><i class="icon_pin_alt"></i>
											Discount_percent</th>
										<th style="text-align: center;"><i class="icon_pin_alt"></i>
											Coupon_startDate</th>
										<th style="text-align: center;"><i class="icon_pin_alt"></i>
											Coupon_endDate</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${ list }" var="selectCoupon">
										<tr>
											<td style="text-align: center;">${selectCoupon.coupon_code}</td>
											<td style="text-align: center;">${selectCoupon.coupon_nm}</td>
											<td style="text-align: center;">${selectCoupon.coupon_cntnts }</td>
											<td style="text-align: center;">${selectCoupon.coupon_dscnt }</td>
											<td style="text-align: center;">${selectCoupon.coupon_begin_de }</td>
											<td style="text-align: center;">${selectCoupon.coupon_end_de }</td>
											<td><button id="modifyBtn"
													class="modifyBtn btn btn-danger">수정</button>
												<button id="delBtn" class="delBtn btn btn-default">삭제</button></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</section>
					</form>
				</div>
			</div>
		</div>

		<div id="couponModal" class="modal" style="z-index: 3;">
			<div class="modal-row">
				<div class="col-lg-12" style="width:100%;">
					<section class="panel">
						<header class="panel-heading"> 쿠폰 등록</header>
						<div class="panel-body">
							<div class="form">
								<form class="form-validate form-horizontal" id="couponForm"
									action="insertCoupon" method="post">
									<div class="form-group">
										<label for="coupon_name" class="control-label col-lg-2">쿠폰
											이름 <span class="required">*</span>
										</label>
										<div class="col-lg-5" style="width:50%">
											<input class="form-control" id="coupon_name" name="coupon_nm"
												type="text" required />
										</div>
										
										<div class="form-group">
											<label for="coupon_insert" class="control-label">적용
												분류 <span class="required">*</span>
											</label>
											<div class="col-lg-5" style="width:50%">
												<select class="btn btn-default dropdown-toggle">
													<option value="category">상품카테고리별</option>
													<option value="product">물품별</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group ">
										<label for="coupon_info" class="control-label col-lg-2">쿠폰
											내용 <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="coupon_info"
												name="coupon_cntnts" type="text" required />
										</div>
									</div>

									<div class="form-group ">
										<label for="coupon_sales" class="control-label col-lg-2">쿠폰
											할인율<span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="coupon_sales"
												name="coupon_dscnt" type="text" required />
										</div>
									</div>

									<div class="form-group ">
										<label for="regDate" class="control-label col-lg-2">쿠폰
											등록 날짜 <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="regDate"
												name="coupon_begin_de" type="date" required />
										</div>
									</div>

									<div class="form-group ">
										<label for="finDate" class="control-label col-lg-2">쿠폰
											종료 날짜 <span class="required">*</span>
										</label>
										<div class="col-lg-10">
											<input class="form-control" id="finDate" name="coupon_end_de"
												type="date" required />
										</div>
									</div>

									<div class="form-group">
										<div class="col-lg-offset-2 col-lg-10">
											<button class="btn btn-primary" id="couponSave" type="submit">Save</button>
											<button class="cancel btn btn-default" id="couponCancel"
												type="button">Cancel</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="resources/customjs/couponManagement.js"></script>