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


			<table class="table table-hover">
				<!-- table-bordered -->
				<thead>
					<tr>
						<th>쿠폰 번호</th>
						<!-- <th>쿠폰 코드</th> -->
						<th>쿠폰 명</th>
						<th>쿠폰 할인율</th>
						<th>쿠폰 시작날짜</th>
						<th>쿠폰 종료날짜</th>
						<!--<th>유효 기간</th>
										<th>내용</th> -->
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${Couponlist}" var="vo">
					<tbody>
						<tr class="active">
							<td>${ vo.coupon_code }</td>
							<td>${ vo.coupon_nm }</td>
							<td>${ vo.coupon_dscnt }</td>
							<td>${ vo.coupon_begin_de }</td>x
							<td>${ vo.coupon_end_de }</td>

							<td><input type="submit" class="modify btn btn-primary"
								value="수정"> <input type="submit"
								class="delete btn btn-danger" value="삭제"></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<form id="formObj">
				<input type="hidden" id="aaatest" name="coupon_id" />
			</form>

			<div class="modal-layout">
				<h4>
					쿠폰 이름 : <input type="text">
				</h4>

				<h4>
					쿠폰 내용 : <input type="text">
				</h4>
				
				<h4>
					쿠폰 시작일 : <input type="date-locale">
				</h4>
				
				<h4>
					쿠폰 종료일 : <input type="date-locale">
				</h4>
				
				

				<h4>유효 기간 :</h4>
				<input type="date">
				<!-- 								<h4>
									사용 조건 : 총 구매 금액이<input type="number">(만)원 이상일 경우
								</h4>
								<h4>
									할인율 : <input type="number">% 사용 대상 <select>
										<option value="child">아동</option>
										<option value="youth">청소년</option>
										<option value="adult">성인</option>
									</select>
								</h4> -->
				<input type="button" class="close" value="취소" /> <input
					type="button" class="close" value="등록" />
			</div>
		</section>

	</div>

</div>
<script src="resources/customjs/modal.js"></script>