<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<script src="resources/customjs/posSystem.js"></script>

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
					<tr>
						<td>1</td>
						<td>Mark</td>
						<td>Otto</td>
						<td>@mdo</td>
						<td>Mark</td>
						<td>Otto</td>
						<td>@mdo</td>
					</tr>
					<tr>
						<td>2</td>
						<td>Jacob</td>
						<td>Thornton</td>
						<td>@fat</td>
						<td>Mark</td>
						<td>Otto</td>
						<td>@mdo</td>
					</tr>
					<tr>
						<td>3</td>
						<td colspan="2">Larry the Bird</td>
						<td>@twitter</td>
						<td>Mark</td>
						<td>Otto</td>
						<td>@mdo</td>
					</tr>
					<tr>
						<td>3</td>
						<td>Sumon</td>
						<td>Mosa</td>
						<td>@twitter</td>
						<td>Mark</td>
						<td>Otto</td>
						<td>@mdo</td>
					</tr>
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
							<td><input type="text" name="goods_id" id="goods_id" /></td>
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
							<td>60,000</td>
						</tr>
						<tr>
							<td>할인</td>
							<td>3,000</td>
						</tr>
						<tr>
							<td>합계</td>
							<td>57,000</td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>
	</div>

	<div class="col-lg-2">
		<section class="panel">
			<div class="panel-body">
				<button class="btn btn-default" type="button">1</button>
				<button class="btn btn-default" type="button">2</button>
				<button class="btn btn-default" type="button">3</button>
				<br>
				<button class="btn btn-default" type="button">4</button>
				<button class="btn btn-default" type="button">5</button>
				<button class="btn btn-default" type="button">6</button>
				<br>
				<button class="btn btn-default" type="button">7</button>
				<button class="btn btn-default" type="button">8</button>
				<button class="btn btn-default" type="button">9</button>
				<br>
				<button class="btn btn-default" type="button">D</button>
				<button class="btn btn-default" type="button">0</button>
				<button class="btn btn-default" type="button">C</button>
				<br>

			</div>
		</section>

	</div>

	<div class="col-lg-2">
		<section class="panel">
			<div class="panel-body">
				<button class="btn btn-default" type="button" id="getGoods">등록</button>
				<br>
				<button class="btn btn-default" type="button">취소</button>
				<br>
				<button class="btn btn-default" type="button">수량+</button>
				<br>
				<button class="btn btn-default" type="button">수량-</button>
				<br>
			</div>
		</section>
	</div>

	<div class="col-lg-2">
		<section class="panel">
			<div class="panel-body">
				<button class="btn btn-default" type="button">상품검섹</button>
				<br>
				<button class="btn btn-default" type="button">상품삭제</button>
				<br>
				<button class="btn btn-default" type="button">신용카드결제</button>
				<br>
				<button class="btn btn-default" type="button">현금결제</button>
				<br>
				<button class="btn btn-default" type="button">복합결제</button>
				<br>
			</div>
		</section>
	</div>
</div>