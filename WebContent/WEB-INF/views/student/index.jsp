<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Student | Overview</title>

<!-- Bootstrap core CSS -->

<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="fonts/css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">

<!-- Custom styling plus plugins -->
<link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="css/maps/jquery-jvectormap-2.0.3.css" />
<link href="css/icheck/flat/green.css" rel="stylesheet" />
<link href="css/floatexamples.css" rel="stylesheet" type="text/css" />

<script src="js/jquery.min.js"></script>
<script src="js/nprogress.js"></script>

<!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>
<body>
<body class="nav-md">

	<div class="container body">


		<div class="main_container">

			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">

					<div class="navbar nav_title" style="border: 0;">
						<a href="${requestScope['javax.servlet.forward.request_uri']}"
							class="site_title"><span>StudyManager</span></a>
					</div>
					<div class="clearfix"></div>

					<!-- menu prile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src="images/fh_logo.png" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>${studentData.firstname}</h2>
						</div>
					</div>
					<!-- /menu prile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">

						<div class="menu_section">
							<h3>General</h3>
							<ul class="nav side-menu">
								<li><a
									href="${requestScope['javax.servlet.forward.request_uri']}"><i
										class="fa fa-home"></i> Overview</a></li>
								<li><a href="PLACEHOLDER"><i class="fa fa-edit"></i>Exams</a></li>
								<li><a><i class="fa fa-desktop"></i>Grades<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="PLACEHOLDER">View Grades</a></li>
									</ul>
									<ul class="nav child_menu" style="display: none">
										<li><a href="PLACEHOLDER">Export Grades</a></li>
									</ul>
									</li>
								<li><a href="PLACEHOLDER"><i class="fa fa-bar-chart-o"></i>Statistics</a></li>
							</ul>
						</div>
					</div>
					<!-- sidebar menu -->

				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">

				<div class="nav_menu">
					<nav class="" role="navigation">
					<div class="nav toggle">
						<a id="menu_toggle"><i class="fa fa-bars"></i></a>
					</div>

					<ul class="nav navbar-nav navbar-right">
						<li class=""><a href="javascript:;"
							class="user-profile dropdown-toggle" data-toggle="dropdown"
							aria-expanded="false"> <img src="images/fh_logo.png" alt="">${studentData.firstname}
								${studentData.lastname} <span class=" fa fa-angle-down"></span>
						</a>
							<ul
								class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
								<li><a href="javascript:;">Profile</a></li>
								<li><a
									href="javascript:document.getElementById('logout').submit();"><i
										class="fa fa-sign-out pull-right"></i>Log Out</a></li>
							</ul></li>
						<!-- top navigation -->


						<!-- ALERTS -->
						<li role="presentation" class="dropdown"><a
							href="javascript:;" class="dropdown-toggle info-number"
							data-toggle="dropdown" aria-expanded="false"> <i
								class="fa fa-envelope-o"></i> <span class="badge bg-green">1</span>
						</a>
							<ul id="menu1"
								class="dropdown-menu list-unstyled msg_list animated fadeInDown"
								role="menu">
								<li><a> <span class="image"> <img
											src="images/fh_logo.png" alt="Profile Image" />
									</span> <span> <span>John Smith</span> <span class="time">3
												mins ago</span>
									</span> <span class="message"> Film festivals used to be
											do-or-die moments for movie makers. They were where... </span>
								</a></li>
								<li>
									<div class="text-center">
										<a href="inbox.html"> <strong>See All Alerts</strong> <i
											class="fa fa-angle-right"></i>
										</a>
									</div>
								</li>
							</ul></li>
						<!-- ALERTS -->

					</ul>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->


			<!-- page content: OVERVIEW -->
			<div class="right_col" role="main">
			
			<!-- TOP TITLES (4) -->
			<div class="row tile_count">
			<div class="animated flipInY col-md-3 col-sm-3 col-xs-12 tile_stats_count">
            <div class="left"></div>
            <div class="right">
              <span class="count_top"><i class="fa fa-sitemap"></i> Current Rank (based on average grades)</span>
              <div class="count">PLACEHOLDER</div>
              <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
            </div>
          </div>
          <div class="animated flipInY col-md-3 col-sm-3 col-xs-12 tile_stats_count">
            <div class="left"></div>
            <div class="right">
              <span class="count_top"><i class="fa fa-user"></i> Total Students</span>
              <div class="count">${allStudents.size()}</div>
              <span class="count_bottom"><i class="green">${studentColleagues.size()}</i> are studying with you</span>
            </div>
          </div>
          <div class="animated flipInY col-md-3 col-sm-3 col-xs-12 tile_stats_count">
            <div class="left"></div>
            <div class="right">
              <span class="count_top"><i class="fa fa-pencil"></i> Total Exams written</span>
              <div class="count">${studentData.applications.size()}</div>
			<c:choose>
				<c:when test="${((studentData.applications.size())/(examsOfDegreeProgram.size()))*100 > 50}">
					<c:set var="classCol">green</c:set>
				</c:when>
				<c:when test="${((studentData.applications.size())/(examsOfDegreeProgram.size()))*100 < 50}">
					<c:set var="classCol">red</c:set>
				</c:when>
			</c:choose>
			<span class="count_bottom"><i class="${classCol}">${((studentData.applications.size())/(examsOfDegreeProgram.size()))*100}%</i> accomplished</span>
            </div>
          </div>
          <div class="animated flipInY col-md-3 col-sm-3 col-xs-12 tile_stats_count">
            <div class="left"></div>
            <div class="right">
              <span class="count_top"><i class="fa fa-book"></i> Amount of courses participating</span>
              <div class="count">${studentData.getCourses().size()}</div>
            </div>
          </div>

        </div>
        <!-- /TOP TITLES (4) -->		
			
				<!-- START FIRST ROW /w 2 Tables -->
				<div class="row">
				<!-- TABLE 1 List of Grades -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>
									Last Grades<small>Quick overview of your last graded exams</small>
								</h2>

								<div class="clearfix"></div>
							</div>
							<div class="x_content">

								<table class="table table-striped">
									<thead>
										<tr>
											<th>Course</th>
											<th>Exam Type</th>
											<th>Attempt</th>
											<th>Date</th>
											<th>Grade</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${examApplications}"
											var="application">
											<tr>
												<th scope="row">${application.examDate.exam.description}</th>
												<td>${application.examDate.exam.type}</td>
												<td>${application.attempt}</td>
												<td><fmt:formatDate
														value="${application.examDate.date}" pattern="dd.MM.yyyy" />
												<!-- Check grade for showing icon next to it -->
												<c:choose>
														<c:when test="${application.grade == 5}">
															<c:set var="className">label label-danger pull-right</c:set>
															<c:set var="text">Fail</c:set>
														</c:when>
														<c:when test="${application.grade >= 3}">
														<c:set var="className">label label-warning pull-right</c:set>
														<c:set var="text">Sufficient</c:set>
														</c:when>
														<c:when test="${application.grade < 3}">
														<c:set var="className">label label-success pull-right</c:set>
														<c:set var="text">Good Job</c:set>
														</c:when>
													</c:choose><td>${application.grade} <span class="${className}">${text}</span></td>
													<!-- /Check grade for showing icon next to it -->
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</div>
						</div>
					</div>
					<!-- /TABLE 1 List of Grades -->				
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>
									Upcoming Exams<small>Quick overview of the next upcoming exams</small>
								</h2>

								<div class="clearfix"></div>
							</div>
							<div class="x_content">

								<table class="table table-striped">
									<thead>
										<tr>
											<th>Course</th>
											<th>Exam Type</th>
											<th>Attempt</th>
											<th>Date</th>
											<th>Grade</th>
										</tr>
									</thead>
									<tbody>

										<!-- HERE FOREACH! -->
											<tr>
												<th scope="row">PLACEHOLDER</th>
												<td>PLACEHOLDER</td>
												<td>PLACEHOLDER</td>
												<td><fmt:formatDate
														value="${application.examDate.date}" pattern="dd.MM.yyyy" />
												<td>PLACEHOLDER</td>
											</tr>
										<!-- END HERE FOREACH -->
									</tbody>
								</table>

							</div>
						</div>
					</div>
					</div>
					<!-- END FIRST ROW /w 2 Tables -->
					
				<!-- ECTS COUNTER -->
				<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="x_panel tile fixed_height_320 overflow_hidden">
					<div class="dashboard-widget-content sidebar-widget">
								<div class="x_title">
									<h2>Degree Completion</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
								<canvas width="150" height="80" id="analog_ects" class=""
									style="width: 160px; height: 100px;"></canvas>
								<div class="goal-wrapper">
							<span class="gauge-value pull-left"><u><i>ECTS</i></u>:</span> <span
								id="current_ects" class="gauge-value">${studentData.ects}</span> <span
								id="goal_ects" class="goal-value">/180</span>
						</div>
						</div>
					</div>
					</div>
				</div>
				<!-- /ECTS COUNTER -->
				
				<!-- statistics -->
				<div class="col-md-3 col-sm-3 col-xs-6">
				<div class="x_panel tile fixed_height_320 overflow_hidden">
              <div class="x_title">
                <h2>Grades overview</h2>
                <div class="clearfix"></div>
              </div>
              <div class="x_content">

                <table class="" style="width:100%">
                  <tbody><tr>
                    <th style="width:37%;">
                      <p></p>
                    </th>
                    <th>
                      <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                        <p class="">Grade</p>
                      </div>
	                      <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                        <p class="">Number</p>
                      </div>
                    </th>
                  </tr>
                  <tr>
                    <td><iframe class="chartjs-hidden-iframe" style="width: 100%; display: block; border: 0px; height: 0px; margin: 0px; position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px;"></iframe>
                      <canvas id="canvasDataCircle" height="150" width="150" style="margin: 15px 10px 10px 0"></canvas>
                    </td>
                    <td>
                      <table class="tile_info">
                        <tbody><tr>
                          <td>
                            <p><i class="fa fa-square blue"></i>Excellent </p>
                          </td>
                          <td id="Excellent">${grades[0]}</td>
                        </tr>
                        <tr>
                          <td>
                            <p><i class="fa fa-square green"></i>Good </p>
                          </td>
                          <td id="Good">${grades[1]}</td>
                        </tr>
                        <tr>
                          <td>
                            <p><i class="fa fa-square purple"></i>Satisfactory </p>
                          </td>
                          <td id="Satisfactory">${grades[2]}</td>
                        </tr>
                        <tr>
                          <td>
                            <p><i class="fa fa-square aero"></i>Sufficient </p>
                          </td>
                          <td id="Sufficient">${grades[3]}</td>
                        </tr>
                        <tr>
                          <td>
                            <p><i class="fa fa-square red"></i>Not Sufficient </p>
                          </td>
                          <td id="NotSufficient">${grades[4]}</td>
                        </tr>
                      </tbody></table>
                    </td>
                  </tr>
                </tbody></table>
              </div>
              </div>
            </div>
            <!-- /statistics -->
				</div>
				 
				<!-- /top tiles -->			
			
			</div>
			<!-- /page content -->

			<!-- LOGOUT FORM => USED AT THE LOGOUT HREF in the code above -->
			<c:url value="/logout" var="logoutUrl" />
			<form action="${logoutUrl}" id=logout method="post">
				<input hidden=true name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<!-- LOGOUT FORM  -->

		</div>

	</div>

	<div id="custom_notifications" class="custom-notifications dsp_none">
		<ul class="list-unstyled notifications clearfix"
			data-tabbed_notifications="notif-group">
		</ul>
		<div class="clearfix"></div>
		<div id="notif-group" class="tabbed_notifications"></div>
	</div>

	<script src="js/bootstrap.min.js"></script>

	<!-- gauge js -->
	<script type="text/javascript" src="js/gauge/gauge.min.js"></script>
	<script type="text/javascript" src="js/gauge/gauge_ects.js"></script>
	<!-- bootstrap progress js -->
	<script src="js/progressbar/bootstrap-progressbar.min.js"></script>
	<script src="js/nicescroll/jquery.nicescroll.min.js"></script>
	<!-- icheck -->
	<script src="js/icheck/icheck.min.js"></script>
	<!-- chart js -->
	<script src="js/chartjs/chart.min.js"></script>
	<script type="text/javascript" src="js/chartjs/dataCircle.js"></script>
	<script type="text/javascript" src="js/moment/moment.min.js"></script>
  	<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
	

	<script src="js/custom.js"></script>

	<!-- flot js -->
	<!--[if lte IE 8]><script type="text/javascript" src="js/excanvas.min.js"></script><![endif]-->
	<script type="text/javascript" src="js/flot/jquery.flot.js"></script>
	<script type="text/javascript" src="js/flot/jquery.flot.pie.js"></script>
	<script type="text/javascript" src="js/flot/jquery.flot.orderBars.js"></script>
	<script type="text/javascript" src="js/flot/jquery.flot.time.min.js"></script>
	<script type="text/javascript" src="js/flot/date.js"></script>
	<script type="text/javascript" src="js/flot/jquery.flot.spline.js"></script>
	<script type="text/javascript" src="js/flot/jquery.flot.stack.js"></script>
	<script type="text/javascript" src="js/flot/curvedLines.js"></script>
	<script type="text/javascript" src="js/flot/jquery.flot.resize.js"></script>

	<script>
    NProgress.done();
  </script>
	<!-- /datepicker -->
	<!-- /footer content -->

</body>
</html>