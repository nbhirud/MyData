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
                        <h1>Enter Bills Here</h1>
                        <p><% 
                        if(request.getParameter("status")!=null){
                        	out.print(request.getParameter("status"));
                        }
                        %></p>
						<form role="form" action="../BillGenerationServlet" method="post" enctype="multipart/form-data">
							<div class="form-group">
							  <label for="billAmount">Bill Amount:<sup>*</sup></label>
							  <input type="number" class="form-control" id="billAmount" required name="billAmount" placeholder="Enter Bill Amount">
							</div>
							<div class="form-group">
				                <label for="billDate" class="col-md-2 control-label">Bill Date<sup>*</sup></label>
				                <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="16" type="text" required name="billDate" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
								<input type="hidden" id="dtp_input2" value="" /><br/>
				            </div>
							<div class="form-group">
							  <label for="purpose">Purpose<sup>*</sup></label>
							  <input type="text" class="form-control" id="purpose" required name="purpose" placeholder="Enter Purspose">
							</div>
							<div class="form-group">
							  <label for="itenaryPDF">Bill Attachment<sup>*</sup></label>
							  <input type="file" class="form-control" id="itenary" name="itenary" >
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form><br /><br /><br />
						<% 
						ArrayList<BillBean> billBeans = (ArrayList<BillBean>)session.getAttribute("billDetails");
						if(billBeans!=null){
						%>
						<div class="table-responsive"> 
					        <table class="table table-bordered">
					            <thead>
					                <tr>
					                    <th>Bill Date</th>
					                    <th>Bill Amount</th>
					                    <th>Purpose</th>
					                    <th>Entered Date</th>
					                    <th>Url</th>
					                </tr>
					            </thead>
					            <tbody>
					            <%
					            for(BillBean billBean : billBeans){
					            %>
					                <tr>
					                <td><%=billBean.getBillDate() %></td>
					                <td><%=billBean.getBillAmount() %></td>
					                <td><%=billBean.getPurpose() %></td>
					                <td><%=billBean.getEnteredDate() %></td>
					                <td><a href="<%=billBean.getUrl()%>">Attachment</a>
					                </tr>
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
		$('.form_date').datetimepicker({
	        language:  'en',
	        startDate: '-45d',
	        format:'mm/dd/yyyy',
	        weekStart: 1,
	        todayBtn:  "linked",
			autoclose: true,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			todayHighlight: true
	    });
	
	</script>
</body>

</html>
