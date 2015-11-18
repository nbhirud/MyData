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
     	
        <!-- /#sidebar-wrapper -->
		<jsp:include page="sidebar.jsp"></jsp:include>
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Change User Role</h1>
                         <p>
				       <% if(request.getParameter("status") != null){
				    	   out.print(request.getParameter("status"));
				       }%>
				       </p>
						<form role="form" action="../ChangeUserRoleServlet" method="post">
							<div class="form-group" >
							  <label for="triveniId" >Triveni Id<sup>*</sup></label> 
							  <input type="text" class="form-control" id="triveniId"  name="triveniId" required placeholder="Enter Triveni Id">
							</div>
				            <div class="form-group">
							  <label for="userRole">User Role<sup>*</sup></label>
							  <select name="userRole" id="userRole" required class="form-control">
								<option>Select Role</option>
								<option value="Event organizer">Event Co-ordinator</option>
								<option value="Treasurer">Treasurer</option>
								<option value="Student">Student</option>
				            	</select>
				            </div>
				            <button type="submit" class="btn btn-default">Submit</button>
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

</body>

</html>
