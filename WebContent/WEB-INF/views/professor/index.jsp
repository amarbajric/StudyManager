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
								<li><a href="/StudyManager/addExam"><i class="fa fa-edit"></i>Exams</a></li>
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
			<div class="right_col" role="main" style="min-height: 3104px;">
            <div class="clearfix"></div>

              <div class="col-md-12 col-sm-12 col-xs-12">
                
              
              <div class="x_panel">
                <div class="x_title">
                  <h1>Exams</h1>
                  <div class="clearfix"></div>
                </div>

                <div class="x_content">

                  <p>All your exams</p>

                  <table class="table table-striped">
                    <thead>
                      <tr class="headings">
                        <th class="column-title">Course</th>
                        <th class="column-title">Type</th>
                        <th class="column-title">Date Number</th>
                        <th class="column-title">Date</th>
                        <th class="column-title">Room</th>
                        <th class="column-title">Applicants</th>
                        <th class="column-title">Actions</th>
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
                        <td class=" "><button id="enroll" type="button" class="btn btn-success">Success</button></td>
                      </tr>
                      </c:forEach>
                    
                    </tbody>

                  </table>
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

  <!-- bootstrap progress js -->
  <script src="js/progressbar/bootstrap-progressbar.min.js"></script>
  <script src="js/nicescroll/jquery.nicescroll.min.js"></script>
  <!-- icheck -->
  <script src="js/icheck/icheck.min.js"></script>

  <!-- pace -->
  <script src="js/pace/pace.min.js"></script>
</body>
</html>
