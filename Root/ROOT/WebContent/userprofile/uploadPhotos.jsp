<%@page import="com.triveni.beans.PhotoBean"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.triveni.beans.BillBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.triveni.beans.ItenaryBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.triveni.beans.RegistrationBean"%>
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
	<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    function validate(){
    	var photoName = $("#photoName").val();
    	var description = $("#description").val();
    	var flag=true;
    	$("#photoNameErrorMessage").html("");
    	$("#descriptionErrorMessage").html("");
    	if(photoName.length>=50){
    		$("#photoNameErrorMessage").html("Photo Name should be less than 50 characters");
    		$("#photoNameErrorMessage").css("color","red");
    		flag = false;
    	}
    	if(description.length>=100){
    		$("#descriptionErrorMessage").html("Description should be less than 100 characters");
    		$("#descriptionErrorMessage").css("color","red");
    		flag = false;
    	}
    	return flag;
    }
    </script>

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
                        <h1>Enter Photo Details Here</h1>
                        <p><% 
                        if(request.getParameter("status")!=null){
                        	out.print(request.getParameter("status"));
                        }
                        %></p>
						<form role="form" action="../UploadPhotoServlet" method="post"  onsubmit="return validate();" enctype="multipart/form-data">
							<div class="form-group">
							  <label for="photoName">Photo Name:<sup>*</sup><span id="photoNameErrorMessage"></span></label>
							  <input type="text" class="form-control" id="photoName" required name="photoName" placeholder="Enter Photo Name">
							</div>
							<div class="form-group">
							  <label for="description">Photo Description<sup>*</sup><span id="descriptionErrorMessage"></span></label>
							  <input type="text" class="form-control" id="description" required name="description" placeholder="Enter Description">
							</div>
							<div class="form-group">
							  <label for="photoAttachment">Photo Attachment<sup>*</sup></label>
							  <input type="file" class="form-control" id="photoAttachment" name="photoAttachment" >
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form><br /><br /><br />
						<%
						ArrayList<PhotoBean> photoBeans = (ArrayList<PhotoBean>) session.getAttribute("photoDetails");
						if(photoBeans != null){
						%>
						<div class="table-responsive"> 
					        <table class="table table-bordered">
					            <thead>
					                <tr>
					                    <th>Photo Id</th>
					                    <th>Photo Name</th>
					                    <th>Description</th>
					                    <th>URL</th>
					                    <th>Edit/Delete</th>
					                </tr>
					            </thead>
					            <tbody>
					            <%
					            int i=0;
					            for(PhotoBean photoBean : photoBeans){
					            %>
					            	<tr>
						            	<td><%=++i %></td>
						            	<td><%=photoBean.getPhotoName() %></td>
						            	<td><%=photoBean.getDescription() %></td>
						            	<td><a href="<%=photoBean.getUrl()%>">View Attachment</a></td>
						            	<td><a href="updatePhoto.jsp?id=<%=photoBean.getPhotoId()%>">Update</a>|
						            	<button class="btn btn-default" onclick="deleteEvent(<%=photoBean.getPhotoId()%>)">Delete</button></td>
					            	<tr>
					            <% } %>
					    		</tbody>
					    	</table>
					    </div>
					    <% } %>
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
	<script type="text/javascript" src="jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<!--  <script type="text/javascript" src="js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script> -->
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>
    <script type="text/javascript">
    function deleteEvent(id){
    	$.post("../DeletePhotoServler",{"id":id},function(info){
    		alert(info);
    		location.reload();
    	});
    }
    </script>
</body>

</html>
