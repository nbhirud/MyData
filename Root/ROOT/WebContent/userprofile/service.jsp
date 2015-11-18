<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>TRIVENI - UNC Charlotte Indian Students Organization</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="sidebar.jsp"></jsp:include>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Change Password</h1>
                        <form role="form" method="post" id="changePasswordForm">
							<div class="form-group" >
							  <label for="firstname" >Triveni Id<sup>*</sup></label>
							  <input type="text" class="form-control" id="triveniId" readonly value="<%=session.getAttribute("triveniId") %>" name="triveniId" placeholder="Enter Triveni Id">
							</div>
							<div class="form-group">
							  <label for="current_password" >Current Password<sup>*</sup></label>
							  <input type="password" class="form-control" id="currentPassword" required name="currentPassword" placeholder="Enter Current Password">
							</div>
							<div class="form-group" >
							  <label for="new_password" >New Password<sup>*</sup></label>
							  <input type="password" class="form-control" id="newPassword" name="newPassword"  required placeholder="Enter new password">
							</div>
							<div class="form-group" >
							  <label for="confirm_password" >Confirm Password<sup>*</sup></label>
							  <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required placeholder="confirm password">
							</div>
							<input type="button" value="update" class="btn btn-default" onclick="changePassword()">
						</form>
							
                       <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Menu</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>
	<script>
	function changePassword(){
		var pwd = $("#newPassword").val();
		var confirmPassword = $("#confirmPassword").val();
		var errorMsg = "";
		var currentPassword = $("#currentPassword").val();
		if(pwd.trim().length != 0 && confirmPassword.trim().length !=0 && pwd == confirmPassword && currentPassword.trim().length !=0){
			var data = $("#changePasswordForm").serializeArray();
			$.post("../ChangePasswordServlet",data,function(info){
				alert(info);
				location.reload();
			});
		}
		if(currentPassword.trim().length==0){
			errorMsg += "Current password cannot be Empty, ";
		}
		if(pwd.trim().length == 0){
			errorMsg += "New password cannot be Empty, ";
		}
		if(confirmPassword.trim().length == 0){
			errorMsg += "confirmPassword cannot be Empty, ";
		}
		if(pwd != confirmPassword){
			errorMsg += "New password and confirm password donot match.";
		}
		if(errorMsg.trim().length!=0){
			alert(errorMsg);
		}
	}
	</script>
</body>

</html>
