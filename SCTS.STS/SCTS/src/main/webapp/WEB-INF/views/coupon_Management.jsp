<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-lg-12">
		<section class="panel">
			<h3 class="page-header">
				<i class="icon_genius"></i> 쿠폰 관리
			</h3>
		
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index">Home</a></li>
				<input type="submit" class="regist btn btn-default" value="등록" />
				<li><i class="icon_genius"></i>쿠폰 관리</li>
			</ol>

<form class='formObj'>
			<table class="table table-hover">
				<!-- table-bordered -->
				<thead>
					<tr>
						<th>쿠폰 코드</th>
						<th>쿠폰 번호</th>
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

				<h4>
					쿠폰 내용 : <input type="text" name="coupon_cntnts">
				</h4>
				
				<h4>
					쿠폰 할인율 : <select name="coupon_dscnt">
								<option value="10">10</option>
  								<option value="20">20</option>
  								<option value="30">30</option>
							 </select>
				</h4>
				
 				<h4>
					쿠폰 등록날짜 : <input type="date" name="coupon_begin_de">
				</h4>

				<h4>
					쿠폰 마감날짜 : <input type="date" name="coupon_end_de"> 
				</h4>
				 
				

				<input type="button" class="close" value="취소" />
				<input type="submit" class="close regiBtn" value="등록" />
			</div>

	<!-- 		<div class="modal">
				<h4>
					쿠폰 이름 : <input type="text" name="coupon_nm">
				</h4>

				<h4>
					쿠폰 내용 : <input type="text" name="coupon_cntnts">
				</h4>
				
				<h4>
					쿠폰 할인율 : <select name="coupon_dscnt">
								<option value="10">10</option>
  								<option value="20">20</option>
  								<option value="30">30</option>
							 </select>
				</h4>
				
 				<h4>
					쿠폰 등록날짜 : <input type="date" name="coupon_begin_de">
				</h4>

				<h4>
					쿠폰 마감날짜 : <input type="date" name="coupon_end_de"> 
				</h4>
				 
				

				<input type="button" class="close" value="취소" />
				<input type="submit" class="close regiBtn" value="수정" />
			</div> -->

			</form>
		</section>
	</div>
</div>
<script src="resources/customjs/couponManagement.js"></script>