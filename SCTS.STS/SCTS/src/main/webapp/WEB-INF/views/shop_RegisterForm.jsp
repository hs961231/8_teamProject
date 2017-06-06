<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="resources/customjs/fileUpload.js"></script>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa fa-bars"></i> 설계도면 등록
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
			<header class="panel-heading"> 설계도면 등록 </header>
			<div class="panel-body">
				<div class="form">
					<form class="form-validate form-horizontal" id="feedback_form"
						method="post" action="" enctype="multipart/form-data">

						<div class="form-group ">
							<label for="cname" class="control-label col-lg-2">지점코드
								<span class="required">*</span>
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
								<input class="form-control" id="level_info" name="floorinfo_floor"
									type="text" required />
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