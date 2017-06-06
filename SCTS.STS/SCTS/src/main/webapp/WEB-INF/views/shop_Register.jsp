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
<div class="row">
	<div class="col-lg-9 col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>BluePrint</strong>
				</h2>
				<div class="panel-actions">
					<a href="shop_RegisterForm" class="btn-setting"><i
						class="fa fa-plus" aria-hidden="true"></i></a>
				</div>
			</div>
			<div class="panel-body-map">
				<div id="blueprint" style="height: 380px; text-align: center;">
				<!-- 
					<br> <br> <br> <br> <br> <br> <br>
					<br>
					<p>설계도면 파일을 등록해주세요.</p>
				 -->
				 	<img src="resources\\drawing\\${ drawingList.get(0).drw_flpth }">
				 	<!-- <img src="resources/drawing/asdqwe_94bc590b-322a-4553-8be7-4e0aa61b1132.jpg"> -->
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
		<section class="panel" style="overflow: scroll;">

			<table class="table table-striped table-advance table-hover">
				<tbody>
					<tr>
						<th style="text-align: center;"><i class="icon_calendar"></i>
							Tile_name</th>
						<th style="text-align: center;"><i class="icon_mail_alt"></i>
							Beacon_Major</th>
						<th style="text-align: center;"><i class="icon_pin_alt"></i>
							Beacon_Minor</th>
						<th style="padding-left: 60px;"><a href="tile_RegisterForm"><i
								class="fa fa-plus" aria-hidden="true"></i></a></th>
					</tr>
				<tbody>

					<c:forEach items="${ tileList }" var="vo">

						<tr class="active">
							<td style="text-align: center;">${ vo.get("tile_nm") }</td>
							
							<td style="text-align: center;">${ vo.get("beacon_mjr") }</td>
							<td style="text-align: center;">${ vo.get("beacon_mnr") }</td>
							
							<td>
								<div class="btn-group">
									<a class="btn btn-primary" href="product_Info"><i
										class="icon_plus_alt2"></i></a> <a class="btn btn-success"
										href="#"><i class="icon_check_alt2"></i></a> <a
										class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
								</div>
							</td>
						</tr>

					</c:forEach>
				</tbody>

			</table>
		</section>
	</div>
</div>