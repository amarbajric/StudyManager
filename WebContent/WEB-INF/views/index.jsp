<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="includes/bootstrapMeta.inc"%>
<title>Study Manager Test</title>
<%@include file="includes/bootstrapCss.css"%>
<%@include file="includes/treeView.css"%>



</head>
<body>

	<sec:authorize access="hasRole('STUDENT')">
    	<c:redirect url="/student/" />
	</sec:authorize>
	<sec:authorize access="hasRole('PROFESSOR')">
    	<c:redirect url="/professor/" />
	</sec:authorize>

	<div class="container">
		<center>
			<h1>${type}</h1>
			<!--  paging ----------------------------------------------------------- -->
			<form action="getPage" method="post">
				<input type="submit" value="Do it">
			</form>
			<hr>
		</center>



		<!--  list all employees ----------------------------------------------------------- -->
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<h1>Students</h1>
				<table data-toggle="table" class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Mail</th>
							<th>ECTS</th>
							<th>Year</th>
							<th>Action <a href="fill"><button type="button"
										class="btn btn-success">Fill List</button></a></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${students}" var="student">
							<tr>
								<td>${student.id}</td>
								<td>${student.firstname}</td>
								<td>${student.lastname}</td>
								<td>${student.mail}</td>
								<td>${student.ects}</td>
								<td>${student.year.year}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl }" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input class="btn btn-xs btn-danger"
				type="submit" value="Logout" />
		</form>
		<!--  list all employees ----------------------------------------------------------- -->
	</div>
	<!--  end of container -->
	<%@include file="includes/bootstrapJs.js"%>
</body>
</html>
