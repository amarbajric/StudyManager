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

<title>Professor | Exams</title>

<!-- Bootstrap core CSS -->

  <link href="css/bootstrap.min.css" rel="stylesheet">

  <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
  <link href="css/animate.min.css" rel="stylesheet">

  <!-- Custom styling plus plugins -->
  <link href="css/custom.css" rel="stylesheet">
  <link href="css/icheck/flat/green.css" rel="stylesheet">
  

  <script src="js/jquery.min.js"></script>

</head>
<body>
<body class="nav-md">

	<div class="container body">


		<div class="main_container">

			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">

					<div class="navbar nav_title" style="border: 0;">
						<a href="/StudyManager/"
							class="site_title"><span>StudyManager</span></a>
					</div>
					<div class="clearfix"></div>

					<!-- menu prile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src="images/icon_prof.png" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>${professorData.firstname}</h2>
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
								<li><a href="/StudyManager/"><i class="fa fa-home"></i> Overview</a></li>
								<li><a href="/StudyManager/manageExams"><i class="fa fa-desktop"></i>Manage Exams</a></li>
								<li><a href="/StudyManager/writtenExams"><i class="fa fa-edit"></i>Grade Exams</a></li>							
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
							aria-expanded="false"> <img src="images/icon_prof.png" alt="">${professorData.firstname}
								${professorData.lastname} <span class=" fa fa-angle-down"></span>
						</a>
							<ul
								class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
								<li><a href="profile">Profile</a></li>
								<li><a
									href="javascript:document.getElementById('logout').submit();"><i
										class="fa fa-sign-out pull-right"></i>Log Out</a></li>
							</ul></li>
						<!-- top navigation -->


					</ul>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->
			
			
			<!-- /page content -->
			<div class="right_col" role="main" style="min-height: 950px;">
			
			
			
			<!-- TOP TITLES (4) -->
				<div class="row tile_count">
					<div
						class="animated flipInY col-md-3 col-sm-3 col-xs-12 tile_stats_count">
						<div class="left"></div>
						<div class="right">
							<span class="count_top"><i class="fa fa-sitemap"></i>
								Total Number of Professors</span>
							<div class="count">${numberOfAllProfessors}</div>
						</div>
					</div>
					<div
						class="animated flipInY col-md-3 col-sm-3 col-xs-12 tile_stats_count">
						<div class="left"></div>
						<div class="right">
							<span class="count_top"><i class="fa fa-user"></i> Total
								Number of Students</span>
							<div class="count">${numberOfAllStudents}</div>
							<span class="count_bottom"><b><i class="green">${numberOfMyStudents}</i></b>
								of them are or were in your courses</span>
						</div>
					</div>
					<div
						class="animated flipInY col-md-3 col-sm-3 col-xs-12 tile_stats_count">
						<div class="left"></div>
						<div class="right">
							<span class="count_top"><i class="fa fa-pencil"></i> Total
								Exams held</span>
							<div class="count">${numberOfExamsHeld}</div>
							<c:set var="percentagePositive" value=""></c:set>
							<c:choose>
								<c:when	test="${professorAverageExamGrade>4}">
									<c:set var="classCol">red</c:set>
								</c:when>
								<c:when	test="${professorAverageExamGrade<4 && professorAverageExamGrade>=2}">
									<c:set var="classCol">orange</c:set>
								</c:when>
								<c:when	test="${professorAverageExamGrade<2}">
									<c:set var="classCol">green</c:set>
								</c:when>
							</c:choose>
							<span class="count_bottom"><b><i class="${classCol}"><fmt:formatNumber value="${professorAverageExamGrade}" maxFractionDigits="2"/></i></b> is the average grade on your exams</span>
						</div>
					</div>
					<div
						class="animated flipInY col-md-3 col-sm-3 col-xs-12 tile_stats_count">
						<div class="left"></div>
						<div class="right">
							<span class="count_top"><i class="fa fa-book"></i> Amount
								of courses</span>
							<div class="count">${professorData.courses.size()}</div>
						</div>
					</div>

				</div>
				<!-- /TOP TITLES (4) -->
			
			
			
			
			
			
            <div class="clearfix"></div>

              <div class="col-md-8 col-sm-8 col-xs-12">
                
              
              <div class="x_panel">
                <div class="x_title">
                  <h1>Upcoming Exams</h1>
                  <div class="clearfix"></div>
                </div>

                <div class="x_content">

                  <p>All your future exams</p>

                  <table class="table table-striped">
                    <thead>
                      <tr class="headings">
                        <th class="column-title">Course</th>
                        <th class="column-title">Type</th>
                        <th class="column-title">Date Number</th>
                        <th class="column-title">Date</th>
                        <th class="column-title">Room</th>
                        <th class="column-title">Applicants</th>
                      </tr>
                    </thead>

                    <tbody>
                      <c:forEach items="${professorExams}" var="exam">
                        <tr class="even pointer">
                        <th class=" ">${exam.getDescription()}</th>
                        <td class=" ">${exam.getType()}</td>
                        <td class=" ">${exam.getDateNumber()}</td>
                        <td class=" "><fmt:formatDate value="${exam.getDate()}" pattern="dd.MM.yyyy hh:mm" /></td>
                        <td class=" ">${exam.getRoom()}</td>
                        <td class=" ">${exam.getApplicants()}</td>
                      </tr>
                      </c:forEach>
                    
                    </tbody>

                  </table>
                </div>
              </div>


        </div>
        
				<div class="col-md-4 col-sm-4 col-xs-12">
					<div class="x_panel tile fixed_height_320 overflow_hidden">
						<div class="x_title">
							<h2>Grades overview</h2>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">

							<table class="" style="width: 100%">
								<tbody>
									<tr>
										<th style="width: 37%;">
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
										<td><iframe class="chartjs-hidden-iframe"
												style="width: 100%; display: block; border: 0px; height: 0px; margin: 0px; position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px;"></iframe>
											<canvas id="canvasDataCircle" height="150" width="150"
												style="margin: 15px 10px 10px 0"></canvas></td>
										<td>
											<table class="tile_info">
												<tbody>
													<tr>
														<td>
															<p>
																<i class="fa fa-square blue"></i>Excellent
															</p>
														</td>
														<td id="Excellent">${professorGrades[0]}</td>
													</tr>
													<tr>
														<td>
															<p>
																<i class="fa fa-square green"></i>Good
															</p>
														</td>
														<td id="Good">${professorGrades[1]}</td>
													</tr>
													<tr>
														<td>
															<p>
																<i class="fa fa-square purple"></i>Satisfactory
															</p>
														</td>
														<td id="Satisfactory">${professorGrades[2]}</td>
													</tr>
													<tr>
														<td>
															<p>
																<i class="fa fa-square aero"></i>Sufficient
															</p>
														</td>
														<td id="Sufficient">${professorGrades[3]}</td>
													</tr>
													<tr>
														<td>
															<p>
																<i class="fa fa-square red"></i>Not Sufficient
															</p>
														</td>
														<td id="NotSufficient">${professorGrades[4]}</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
        
        </div>
              
            </div>
          </div>
        </div>

			<!-- LOGOUT FORM => USED AT THE LOGOUT HREF in the code above -->
			<c:url value="/logout" var="logoutUrl" />
			<form action="${logoutUrl}" id=logout method="post">
				<input hidden=true name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<!-- LOGOUT FORM  -->
			

  <script src="js/bootstrap.min.js"></script>

  <!-- bootstrap progress js -->
  <script src="js/progressbar/bootstrap-progressbar.min.js"></script>
  <script src="js/nicescroll/jquery.nicescroll.min.js"></script>
  <!-- icheck -->
  <script src="js/icheck/icheck.min.js"></script>
  <script src="js/custom.js"></script>
  <!-- pace -->
  <script src="js/pace/pace.min.js"></script>
  <!-- chart js -->
	<script src="js/chartjs/chart.min.js"></script>
	<script type="text/javascript" src="js/moment/moment.min.js"></script>
	<script type="text/javascript" src="js/chartjs/dataCircle.js"></script>
	<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
  
</body>
</html>
