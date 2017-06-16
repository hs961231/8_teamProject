<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script src="resources/customjs/posSystem.js"></script>

<style>
.white_content {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background: rgba(0, 0, 0, 0.8);
    opacity:0;
    -webkit-transition: opacity 400ms ease-in;
    -moz-transition: opacity 400ms ease-in;
    transition: opacity 400ms ease-in;
    pointer-events: none;
}
.white_content:target {
    opacity:1;
    pointer-events: auto;
}
.white_content > div {
	position: absolute;
	top: 25%;
	left: 25%;
	width: 50%;
	height: 50%;
	padding: 16px;
	border: 16px solid orange;
	background-color: white;
	overflow: auto;	
}
</style>

<%-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 상품리스트 부분 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ --%>
<div class="row">
	<div class="col-lg-12">
		<section class="panel">
			<header class="panel-heading"> Hover Table (상품 리스트) </header>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>상품번호</th>
						<th>제조사</th>
						<th>상품명</th>
						<th>단가</th>
						<th>수량</th>
						<th>금액</th>
						<th>할인</th>
					</tr>
				</thead>
				<tbody id="goodsList">
				</tbody>
			</table>
		</section>
	</div>
</div>


<%-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 아래 버튼 부분 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ --%>
<div class="row">
	<div class="col-lg-4">
		<section class="panel">
			<div class="table-responsive">
				<table class="table">
					<tbody>
						<tr>
							<td>바코드</td>
							<td><input type="text" name="goods_code" id="goods_code" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>

		<section class="panel">
			<div class="table-responsive">
				<table class="table">
					<tbody>
						<tr>
							<td>소계</td>
							<td id="totalPrice">0</td>
						</tr>
						<tr>
							<td>할인</td>
							<td id="totalDiscount">3,000</td>
						</tr>
						<tr>
							<td>합계</td>
							<td id="totalAmount">57,000</td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>
	</div>

	<div class="col-lg-2">
		<section class="panel">
			<div class="panel-body">
				<button class="btn btn-default numberPad" type="button">1</button>
				<button class="btn btn-default numberPad" type="button">2</button>
				<button class="btn btn-default numberPad" type="button">3</button>
				<br>
				<button class="btn btn-default numberPad" type="button">4</button>
				<button class="btn btn-default numberPad" type="button">5</button>
				<button class="btn btn-default numberPad" type="button">6</button>
				<br>
				<button class="btn btn-default numberPad" type="button">7</button>
				<button class="btn btn-default numberPad" type="button">8</button>
				<button class="btn btn-default numberPad" type="button">9</button>
				<br>
				<button class="btn btn-default numberPad" type="button">D</button>
				<button class="btn btn-default numberPad" type="button">0</button>
				<button class="btn btn-default numberPad" type="button">C</button>
				<br>

			</div>
		</section>

	</div>

	<div class="col-lg-2">
		<section class="panel">
			<div class="panel-body">
				<button class="btn btn-default" type="button" id="getGoods">등록</button>
				<br>
				<button class="btn btn-default" type="button" id="cancleGoods">취소</button>
				<br>
				<button class="btn btn-default" type="button" id="additionGoods">수량+</button>
				<br>
				<button class="btn btn-default" type="button" id="subtractGoods">수량-</button>
				<br>
			</div>
		</section>
	</div>

	<div class="col-lg-2">
		<section class="panel">
			<div class="panel-body">
				<a href="#searchGoodsOpen"><button class="btn btn-default" type="button" id="searchGoods">상품검색</button></a>
				<br>
				<a href="#couponPointOpen"><button class="btn btn-default" type="button" id="couponPoint">쿠폰 포인트</button></a>
				<br>
				<a href="#cardOpen"><button class="btn btn-default" type="button" id="card">신용카드 결제</button></a>
				<br>
				<a href="#moneyOpen"><button class="btn btn-default" type="button" id="money">현금 결제</button></a>
				<br>
				<a href="#mixOpen"><button class="btn btn-default" type="button" id="mix">복합 결제</button></a>
				<br>
			</div>
		</section>
	</div>
</div>

<div class="white_content" id="searchGoodsOpen">
	<div>
		<p>상품검색 모달 </p>
		<a href="#">닫기</a>
	</div>
</div>
<div class="white_content" id="couponPointOpen">
	<div>
		<p>쿠폰 포인트 </p>
		고객아이디 <input type="text" name="user_id" id="user_id" />
		<button class="btn btn-default" type="button" id="getUserCoupon">등록</button> <br>
		<a href="#">닫기</a>
	</div>
</div>
<div class="white_content" id="cardOpen">
	<div>
		<p>신용카드 결제 </p>
		<a href="#">닫기</a>
	</div>
</div>
<div class="white_content" id="moneyOpen">
	<div>
		<p>현금 결제</p>
		<a href="#">닫기</a>
	</div>
</div>
<div class="white_content" id="mixOpen">
	<div>
		<p>복합 결제 </p>
		<a href="#">닫기</a>
	</div>
</div>