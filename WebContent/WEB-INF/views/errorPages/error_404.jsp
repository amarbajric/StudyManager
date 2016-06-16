<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
  <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
  <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Error 404 - Resource not found!</title>

  <!-- Bootstrap core CSS -->

  <link href="css/bootstrap.min.css" rel="stylesheet">

  <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
  <link href="css/animate.min.css" rel="stylesheet">

  <!-- Custom styling plus plugins -->
  <link href="css/custom.css" rel="stylesheet">
  <link href="css/icheck/flat/green.css" rel="stylesheet">


  <script src="js/jquery.min.js"></script>

  <!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>


<body class="nav-md">

  <div class="container body">

    <div class="main_container">

      <!-- page content -->
      <div class="col-md-12">
        <div class="col-middle">
          <div class="text-center text-center">
            <h1 class="error-number">404</h1>
            <h1>Sorry but we couldn't find this page</h1>
            <h3>This page you are looking for does not exist</h3>           
          <br>
         <input action="action" class="btn btn-round btn-success" type="button" value="Go Back" onclick="history.go(-1);"/>
         <button type="button" id="opener" class="btn btn-round btn-danger">Show error</button>
          <div class="x_panel" style="display:none;height:600px" id="dialog">
          <div class="x_title">
          <h2>Error Message</h2>
          <div class="clearfix"></div></div>
          <br>
	Exception:    ${requestScope["javax.servlet.error.exception"]}<br>
	Message:      ${requestScope["javax.servlet.error.message"]}<br>
	Status Code:  ${requestScope["javax.servlet.error.status_code"]}<br>
	Request-URI:  ${requestScope["javax.servlet.error.request_uri"]}<br>
	Servlet Name: ${requestScope["javax.servlet.error.servlet_name"]}<br>
	<br>
	Exception:    ${pageContext.exception}<br>
	Message:      ${pageContext.exception.message}<br>
	<br>
	Exception:    ${pageContext.errorData.throwable}<br>
	Message:      ${pageContext.errorData.throwable.message}<br>
	Status Code:  ${pageContext.errorData.statusCode}<br>
	Request-URI:  ${pageContext.errorData.requestURI}<br>
	Servlet Name: ${pageContext.errorData.servletName}<br>
	<br>       
    	</div>
    	</div>
          </div>
        </div>
      </div>
      <!-- /page content -->

    </div>
    <!-- footer content -->

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
  <!-- pace -->
  <script src="js/pace/pace.min.js"></script>
  <!-- /footer content -->
</body>
<script type="text/javascript">
$('#opener').click(function() {
	if(dialog.style.display == 'none')
	{
	$('#dialog').slideDown();
	}
	else{
	$('#dialog').slideUp();
	}
})
</script>
</html>
