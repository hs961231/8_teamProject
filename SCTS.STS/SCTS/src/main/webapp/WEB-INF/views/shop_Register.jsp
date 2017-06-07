<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
/* The Modal (background) */
.modal,.tileModal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 220px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-row {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding-top: 16px;
	width: 53%;
	height: 66%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop;
	animation-duration: 0.4s
}

.tileModal-row {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding-top: 16px;
	width: 43%;
	height: 40%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop;
	animation-duration: 0.4s
}

/* Add Animation */
@
-webkit-keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}
@
keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}
.panel-heading {
	border: 1px solid #D5D5D5;
}
</style>

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
<div class="row">
	<div class="col-lg-9 col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>BluePrint</strong>
				</h2>
				<div class="panel-actions">
					<a href="#" class="btn-setting" id="MyBtn"><i id="MyBtn"
						class="fa fa-plus" aria-hidden="true"></i></a>
				</div>
			</div>
			<div class="panel-body-map">
				<div id="blueprint" style="height: 380px; text-align: center;">
					<br> <br> <br> <br> <br> <br> <br>
					<br>
					<p>설계도면 파일을 등록해주세요.</p>
				</div>
			</div>

		</div>
	</div>

	<div class="col-md-3"
		style="background-color: white; width: 280px; height: 417px; position: absolute; top: 230px; right: 20px; border: 1px solid #D5D5D5; text-align: center;">

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br>
		<p>선택된 타일 정보가 없습니다.</p>

	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>타일 리스트</strong>
				</h2>
				<div class="panel-actions">
					<a href="#" class="btn-setting" id="tileBtn"><i id="tileBtn"
						class="fa fa-plus" aria-hidden="true"></i></a>
				</div>
			</div>
			<section class="panel" style="overflow: scroll; height: 430px;">

				<table class="table table-striped table-advance table-hover">
					<tbody>
						<tr>
							<th style="text-align: center;"><i class="icon_profile"></i>
								타일번호</th>
							<th style="text-align: center;"><i class="icon_calendar"></i>
								메이저</th>
							<th style="text-align: center;"><i class="icon_mail_alt"></i>
								마이너</th>
							<th style="text-align: center;"><i class="icon_pin_alt"></i>
								평균머문시간</th>
							<th style="text-align: center;"><i class="icon_pin_alt"></i>
								방문횟수</th>
						</tr>


						<c:forEach items="${ tileList }" var="vo">

							<tr>
								<td style="text-align: center;">${ vo.get("tile_nm") }</td>
								<td style="text-align: center;">${ vo.get("beacon_mjr") }</td>
								<td style="text-align: center;">${ vo.get("beacon_mnr") }</td>
								<td style="text-align: center;">${ vo.get("avg") }</td>
								<td style="text-align: center;">${ vo.get("cnt") }</td>
							</tr>

						</c:forEach>

					</tbody>
				</table>
			</section>
		</div>
	</div>
</div>

<div id="Mymodal" class="modal">
	<div class="modal-row">
		<div class="col-lg-12">
			<section class="panel">
				<header class="panel-heading"> 설계도면 등록 </header>
				<div class="panel-body">
					<div class="form">
						<form class="form-validate form-horizontal" id="feedback_form"
							method="post" action="" enctype="multipart/form-data">

							<div class="form-group ">
								<label for="cname" class="control-label col-lg-2">지점코드 <span
									class="required">*</span>
								</label>
								<div class="col-lg-10">
									<input class="form-control" id="name" name="bhf_code"
										type="text" required />
								</div>

							</div>

							<div class="form-group ">
								<label for="cname" class="control-label col-lg-2">층정보 <span
									class="required">*</span>
								</label>
								<div class="col-lg-10">
									<input class="form-control" id="level_info"
										name="floorinfo_floor" type="text" required />
								</div>
							</div>

							<div class="form-group ">
								<label for="cname" class="control-label col-lg-2">첨부파일 <span
									class="required">*</span>
								</label>
								<div class="col-lg-10">
									<input type="file" name="file" required>
								</div>
							</div>

							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<button class="btn btn-primary" id="save" type="submit">Save</button>
									<button class="btn btn-default" id="shopCancel"type="button">Cancel</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</section>
		</div>
	</div>
</div>

<div id="tileModal" class="modal">
	<div class="tileModal-row">
		<div class="col-lg-12">
		<section class="panel">
			<header class="panel-heading"> 타일 정보 등록 </header>
			<div class="panel-body">
				<div class="form">
					<form class="form-validate form-horizontal" id="feedback_form"
						method="post" action="">

						<div class="form-group ">
							<label for="name" class="control-label col-lg-2">타일 명 <span
								class="required">*</span>
							</label>
							<div class="col-lg-10">
								<input class="form-control" id="tile_nm" name="tile_nm"
									type="text" required />
							</div>
						</div>
<!-- 
						<div class="form-group ">
							<label for="major" class="control-label col-lg-2">비콘 시리얼
								번호 <span class="required">*</span> <br>(Major)
							</label>
							<div class="col-lg-10">
								<input class="form-control" id="major" name="fullname"
									type="text" required />
							</div>
						</div>

						<div class="form-group ">
							<label for="minor" class="control-label col-lg-2">비콘 시리얼
								번호 <span class="required">*</span> <br>(Minor)
							</label>
							<div class="col-lg-10">
								<input class="form-control" id="minor" name="fullname"
									type="text" required />
							</div>
						</div>

						<div class="form-group ">
							<label for="ccomment" class="control-label col-lg-2">비콘
								부가 정보</label>
							<div class="col-lg-10">
								<textarea class="form-control " id="ccomment" name="comment"
									required></textarea>
							</div>
						</div>
-->

						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<button class="btn btn-primary" id="tileSave" type="submit">Save</button>
								<button class="btn btn-default" id="tileCancel" type="button">Cancel</button>
							</div>
						</div>
					</form>
				</div>

			</div>
		</section>
	</div>
	</div>
</div>

<script src="resources/customjs/shopRegister.js"></script>