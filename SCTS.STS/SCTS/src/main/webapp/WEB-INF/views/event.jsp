<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 달력 -->
<div class="row">
	<div class="col-md-6 portlets">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<strong>Calendar</strong>
				</h2>
				<div class="panel-actions">
					<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
					<a href="#" class="wclose"><i class="fa fa-times"></i></a>
				</div>

			</div>
			<br> <br> <br>
			<div class="panel-body">
				<!-- Widget content -->

				<!-- Below line produces calendar. I am using FullCalendar plugin. -->
				<div id="calendar"></div>

			</div>
		</div>

	</div>

	<div class="col-md-6 portlets">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="pull-left">Quick Post</div>
				<div class="widget-icons pull-right">
					<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
					<a href="#" class="wclose"><i class="fa fa-times"></i></a>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="panel-body">
				<div class="padd">

					<div class="form quick-post">
						<!-- Edit profile form (not working)-->
						<form class="form-horizontal">
							<!-- Title -->
							<div class="form-group">
								<label class="control-label col-lg-2" for="title">Title</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="title">
								</div>
							</div>
							<!-- Content -->
							<div class="form-group">
								<label class="control-label col-lg-2" for="content">Content</label>
								<div class="col-lg-10">
									<textarea class="form-control" id="content"></textarea>
								</div>
							</div>
							<!-- Cateogry -->
							<div class="form-group">
								<label class="control-label col-lg-2">Category</label>
								<div class="col-lg-10">
									<select class="form-control">
										<option value="">- Choose Cateogry -</option>
										<option value="1">General</option>
										<option value="2">News</option>
										<option value="3">Media</option>
										<option value="4">Funny</option>
									</select>
								</div>
							</div>
							<!-- Tags -->
							<div class="form-group">
								<label class="control-label col-lg-2" for="tags">Tags</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="tags">
								</div>
							</div>

							<!-- Buttons -->
							<div class="form-group">
								<!-- Buttons -->
								<div class="col-lg-offset-2 col-lg-9">
									<button type="submit" class="btn btn-primary">Publish</button>
									<button type="submit" class="btn btn-danger">Save
										Draft</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</div>
							</div>
						</form>
					</div>


				</div>
				<div class="widget-foot">
					<!-- Footer goes here -->
				</div>
			</div>
		</div>

	</div>

</div>
<!-- project team & activity end -->

<div class="text-right">
	<div class="credits">
		<!-- 
                    All the links in the footer should remain intact. 
                    You can delete the links only if you purchased the pro version.
                    Licensing information: https://bootstrapmade.com/license/
                    Purchase the pro version form: https://bootstrapmade.com/buy/?theme=NiceAdmin
                -->
		<a
			href="https://bootstrapmade.com/free-business-bootstrap-themes-website-templates/">Business
			Bootstrap Themes</a> by <a href="https://bootstrapmade.com/">BootstrapMade</a>
	</div>
</div>
