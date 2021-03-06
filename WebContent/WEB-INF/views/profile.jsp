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

<title>Student | Profile</title>

<!-- Bootstrap core CSS -->

  <link href="css/bootstrap.min.css" rel="stylesheet">

  <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
  <link href="css/animate.min.css" rel="stylesheet">

  <!-- Custom styling plus plugins -->
  <link href="css/custom.css" rel="stylesheet">
  <link href="css/icheck/flat/green.css" rel="stylesheet">


  <script src="js/jquery.min.js"></script>
  
  <!-- Check for images -->
	<c:choose>
		<c:when test="${isStudent eq true}">
			<c:set var="logoVal" value="icon_student.png"></c:set>
		</c:when>
		<c:when test="${isStudent eq false}">
			<c:set var="logoVal" value="icon_prof.png"></c:set>
		</c:when>
	</c:choose>
  <!-- /Check for images -->

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
							<img src="images/${logoVal}" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>${profileData.firstname}</h2>
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
								<c:choose>
								<c:when test="${isStudent eq false}">
								<li><a href="/StudyManager/manageExams"><i class="fa fa-desktop"></i>Manage Exams</a></li>
								<li><a href="/StudyManager/writtenExams"><i class="fa fa-edit"></i>Grade Exams</a></li>					
								</c:when>
								<c:otherwise>
								<li><a href="/StudyManager/exams"><i class="fa fa-edit"></i>Exams</a></li>
								<li><a href="/StudyManager/grades"><i class="fa fa-desktop"></i>Grades</a></li>
								</c:otherwise>
								</c:choose>								
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
							aria-expanded="false"> <img src="images/${logoVal}" alt="">${profileData.firstname}
								${profileData.lastname} <span class=" fa fa-angle-down"></span>
						</a>
							<ul
								class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
								<li><a href="/StudyManager/profile">Profile</a></li>
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
			
			<!-- page content -->
      	<div class="right_col" role="main">

          <div class="clearfix"></div>

          <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-12">
              <div class="x_panel">
                <div class="x_title">
                  <h1>User Profile <small>General overview of your profile</small></h1>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="col-md-6 col-sm-6 col-xs-12 profile_left">
                    <div class="profile_img">
						<div class="avatar-view" title="Change the avatar">						  
                          <img src="images/fh_logo.png" alt="Avatar">
                        </div>
                      </div>
                    <h3>${profileData.firstname} ${profileData.lastname} </h3>

                    <ul class="list-unstyled user_data">
                      <li><i class="fa fa-desktop user-profile-icon"></i> ${profileData.mail}</li>
                     <c:if test="${isStudent eq true}">
                      <li><i class="fa fa-briefcase user-profile-icon"></i> ${profileData.year.year}</li>					  
                      <li><i class="fa fa-briefcase user-profile-icon"></i> ${profileData.year.degreeProgram.description}</li>
                     </c:if>
                    </ul>                  
                  </div>
                  <!-- Profile left -->
                  </div>               
                </div>
                </div>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                <div class="x_panel">
                <div class="x_title">
                  <h1>Your courses <small>Overview of your courses</small></h1>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <table class="table">
                    <thead>
                      <tr>
                        <th>Acronym</th>
                        <th>Description</th>
                        <th>Type</th>
                        <th>ECTS Value</th>
                        <c:if test="${isStudent eq false}">
                        <th>Degree Program</th>
                        </c:if>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${profileData.courses}" var="course">
                      <tr>
                        <td>${course.acronym}</td>
                        <td>${course.description}</td>
                        <td>${course.type}</td>
                        <td>${course.ectsValue}</td>
                        <c:if test="${isStudent eq false}">
                        <td>${course.degreeProgram.description}</td>
                        </c:if>
                      </tr>
 					</c:forEach>
                    </tbody>
                  </table>

                </div>
              </div>
              </div>                  
                  </div>
                  </div>
                 
      <!-- /page content -->
	
	
	
	
	
	
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

</body>

</html>
