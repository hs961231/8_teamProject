<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="icon_genius"></i> 쿠폰 관리
		</h3>

<<<<<<< HEAD
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<input type="submit" class="regist btn btn-default" value="등록" />
			<li><i class="icon_genius"></i>Coupon</li>
		</ol>
=======
<form class='formObj'>
			<table class="table table-hover">
				<!-- table-bordered -->
				<thead>
					<tr>
						<th>쿠폰 코드</th>
						<th>쿠폰 이름</th>
						<th>쿠폰 내용</th>
						<th>쿠폰 할인율</th>
						<th>쿠폰 등록날짜</th>
						<th>쿠폰 기한날짜</th>
					</tr>
				</thead>
				<c:forEach items="${list}" var="selectCoupon">
				
					<tbody>
						<tr class="active">
						
							<td>${selectCoupon.coupon_code}</td>
							<td>${selectCoupon.coupon_nm}</td>
							<td>${selectCoupon.coupon_cntnts }</td>
							<td>${selectCoupon.coupon_dscnt }</td>
 							<td>${selectCoupon.coupon_begin_de }</td>
							<td>${selectCoupon.coupon_end_de }</td>
							<td>
								<input type="submit" class="modify btn btn-primary" value="수정">
								<input type="submit" class="delBtn btn btn-danger" value="삭제">
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			
			<div class="modal-layout">
				<h4>
					쿠폰 이름 : <input type="text" name="coupon_nm">
				</h4>
>>>>>>> origin/master

		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>CouponList</strong>
				</h2>
			</div>
			<section class="panel" style="overflow: scroll; height: 600px;">
				<form class='formObj'>
					<table class="table table-hover">
						<!-- table-bordered -->
						<thead>
							<tr>
								<th>Coupon_code</th>
								<th>Coupon_name</th>
								<th>Coupon_content</th>
								<th>Coupon_discount</th>
								<th>Coupon_start</th>
								<th>Coupon_end</th>
							</tr>
						</thead>
						<c:forEach items="${list}" var="selectCoupon">

							<tbody>
								<tr class="active">
									<td>${selectCoupon.coupon_code}</td>
									<td>${selectCoupon.coupon_nm}</td>
									<td>${selectCoupon.coupon_cntnts }</td>
									<td>${selectCoupon.coupon_dscnt }</td>
									<td>${selectCoupon.coupon_begin_de }</td>
									<td>${selectCoupon.coupon_end_de }</td>
									<td><input type="submit" class="modiBtn btn btn-primary" value="수정">
										<input type="submit" class="delBtn btn btn-danger" value="삭제" />
									</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>

					<div class="modal-layout">
						<h4>
							쿠폰 이름 : <input type="text" name="coupon_nm">
						</h4>

						<h4>
							쿠폰 내용 : <input type="text" name="coupon_cntnts">
						</h4>

						<h4>
							쿠폰 할인율 : <select name="coupon_dscnt">
								<option value="10%">10%</option>
								<option value="20%">20%</option>
								<option value="30%">30%</option>
							</select>
						</h4>

						<h4>
							쿠폰 등록날짜 : <input type="date" name="coupon_begin_de">
						</h4>

						<h4>
							쿠폰 마감날짜 : <input type="date" name="coupon_end_de">
						</h4>



						<input type="button" class="close canBtn" value="취소" /> <input
							type="submit" class="close regiBtn" value="등록" />
					</div>


					<!-- 	<div class="modal fade" id="myModal" role="dialog">
		 <div class="modal-dialog">
    
      Modal content
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          <p>Some text in the modal.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
</div> -->

				</form>
			</section>
		</div>
	</div>
</div>
<script src="resources/customjs/couponManagement.js"></script>