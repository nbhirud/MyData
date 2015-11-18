<%@page import="com.triveni.beans.RegistrationBean"%>
<%@page import="com.triveni.DAO.RegistrationDAO"%>
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
<jsp:include page="header.jsp"></jsp:include>
    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="sidebar.jsp"></jsp:include>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                    	<label for="FileUploaded">Off Campus Housing Details</label><br />
                    	<a href="/static/triveni-uploads/Off_Campus_Housing_Details.pdf" >Off Campus Housing</a><br />
						<label for="FileUploaded">Final Packing List Fall 2015</label><br />
						<a href="/static/triveni-uploads/final_FALL15_PackingList_Triveni.pdf" >Final Packing List Fall 2015</a><br />
						<label for="FileUploaded">Fall 2015 modes of Payment</label><br />
						<a href="/static/triveni-uploads/FALL15_ModesOfPayment_Triveni.docx" >Fall 2015 modes of Payment</a><br />
						<label for="FileUploaded">Insurance</label><br />
						<a href="/static/triveni-uploads/Insurance.pdf" >Insurance</a><br />
						<label for="FileUploaded">Travel</label><br />
						<a href="/static/triveni-uploads/Travel.pdf" >Travel</a><br />
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
