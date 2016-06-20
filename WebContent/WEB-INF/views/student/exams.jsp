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

<title>Student | Exams</title>

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
								<li><a href="/StudyManager/"><i class="fa fa-home"></i> Overview</a></li>
								<li><a href="/StudyManager/exams"><i class="fa fa-edit"></i>Exams</a></li>
								<li><a href="/StudyManager/grades"><i class="fa fa-desktop"></i>Grades</a></li>
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
					</ul>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->
			
			
			<!-- /page content -->
			<div class="right_col" role="main">

				<div class="x_panel">
                <div class="x_title">
                 <h1>Exams <small>Sign on off</small></h1>
                  <div class="clearfix"></div>
                </div>

                <div class="x_content">

                  <p>All exams to sign on or off</p>


                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th class="column-title">Course</th>
                        <th class="column-title">Type</th>
                        <th class="column-title">Description </th>
                        <th class="column-title">Date</th>
                        <th class="column-title">ECTS </th>
                        <th class="column-title">Room </th>
                        <th class="column-title">Enroll/Sign Out</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${futureStudentExams}" var="exam">                    
                      <tr>
                        <td class=" ">${exam.getCourse()}</td>
                        <td class=" ">${exam.getType()}</td>
                        <td class=" ">${exam.getDescription()}</td>
                        <td class=" "><fmt:formatDate value="${exam.getDate()}" pattern="dd.MM.yyyy" /></td>
                        <td class=" ">${exam.getEcts()}</td>
                        <td class=" ">${exam.getRoom()}</td>
                        <td class=" ">
                        <c:choose>
                        <c:when test="${exam.getEnrolled() eq 0}">
                        <a href="manageExam?id=${exam.getId()}&action=enroll"><button id="enroll" type="submit" class="btn btn-success" onclick="enroll()">Enroll</button></a> 
                        </c:when>
                        <c:otherwise>
                        <a href="manageExam?id=${exam.getId()}&action=signOut"><button id="signOut" type="submit" class="btn btn-info" onclick="signOut()">Sign Out</button></a>
                        </c:otherwise>
                        </c:choose>
                        </td>
                        
                     
                      </tr>
                      
                    
                      </c:forEach>
                  	</tbody>
                  </table>
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

  <div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
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

  <script src="js/custom.js"></script>
  <!-- NOTIFICATIONS -->
  <script src="js/notify/pnotify.button.js"></script>
  <script src="js/notify/pnotify.core.js"></script>
  <script src="js/notify/examNotify.js"></script>
   <!-- /NOTIFICATIONS -->
   
  
  <!-- pace -->
  <script src="js/pace/pace.min.js"></script>
</body>

</html>
