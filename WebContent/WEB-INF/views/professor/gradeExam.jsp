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
								<li><a href="/StudyManager/addExam"><i class="fa fa-desktop"></i>Exams</a></li>
								<li><a href="/StudyManager/gradeExams"><i class="fa fa-edit"></i>Grade Exams</a></li>	
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
            <div class="clearfix"></div>
            
              <div class="row">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Grade all applicants for exam date: <b>${course}</b> - ${type} - ${dateNumber} - ${date}</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    <form method="post" action="gradeExam?${_csrf.parameterName}=${_csrf.token}" method="post">
                    <table class="table table-striped">
                    <thead>
                      <tr class="headings">
                        <th class="column-title">Student ID</th>
                        <th class="column-title">Firstname</th>
                        <th class="column-title">Lastname</th>
                        <th class="column-title">Attempt</th>
                        <th style="max-width: 50px;" class="column-title"><b>Grade</b></th>
                      </tr>
                    </thead>
					

                    <tbody>
                      <c:forEach items="${applicantsList}" var="applicant">
                   
                        <tr class="even pointer">
                        <th class=" " >${applicant.student.id}</th>
                        <td class=" ">${applicant.student.firstname}</td>
                        <td class=" ">${applicant.student.lastname}</td>
                        <td class=" ">${applicant.attempt}</td>
                        <td style="max-width: 50px;"><select class="form-control">
							  <option value="1" <c:if test="${applicant.grade eq 1}">selected</c:if>>1</option>
							  <option value="2" <c:if test="${applicant.grade eq 2}">selected</c:if>>2</option>
							  <option value="3" <c:if test="${applicant.grade eq 3}">selected</c:if>>3</option>
							  <option value="4" <c:if test="${applicant.grade eq 4}">selected</c:if>>4</option>
							  <option value="5" <c:if test="${applicant.grade eq 5}">selected</c:if>>5</option>
							  <option value="null" <c:if test="${applicant.grade eq null}">selected</c:if>>not graded</option>
							</select>
						</td>
                      </tr>
                      </c:forEach>
                    
                    </tbody>

                  </table>
                  <input type="submit" value="Save grades" />
                  </form>
                    
                    
                    <!--  <form class="form-horizontal form-label-left" action="addExamModel?course=${courseSelected}&type=${typeSelected}">

             
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Select</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <select class="form-control" id = "courseSelected" name="courseSelected">
                           <c:forEach items="${professorData.courses}" var="course">
                           		<option>${course.acronym}</option>
                           </c:forEach>
                          </select>
                        </div>
                      </div>
                      
                       <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Type</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <select class="form-control" id="typeSelected" name="typeSelected">
                           		<option>final</option>
                           		<option>midterm</option>
                          </select>
                        </div>
                      </div>

                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">               
                          	<button id="submitExam" type="submit" class="btn btn-success">Submit</button>
                        </div>
                      </div>

                    </form>-->
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
					
			<!--Check if exam already exist or not -->

	
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
  <!-- examNotify -->
  <script src="js/notify/pnotify.button.js"></script>
  <script src="js/notify/pnotify.core.js"></script>
  <script src="js/notify/examNotify.js"></script>
  <script src="js/custom.js"></script>
  
  <!--Check if exam already exist or not -->
	<c:choose>
	<c:when test="${alreadyExists eq true}">
	<script type="text/javascript">newExamFailed()</script>
	</c:when>
	<c:when test="${alreadyExists eq false}">
	<script type="text/javascript">newExamSuccess()</script>
	</c:when>
	</c:choose>
  <!--Check if exam already exist or not -->
  
</body>
</html>
