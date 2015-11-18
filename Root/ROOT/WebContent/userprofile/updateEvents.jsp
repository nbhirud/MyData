<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
                        String eventId = request.getParameter("id");
                        if(eventId != null){
                        	ArrayList<EventBean> eventBeans = (ArrayList<EventBean>)session.getAttribute("eventDetails");
                        	if(eventBeans!=null){
                        		for(EventBean eventBean : eventBeans){
                        			if(eventBean.getEventId() == Integer.parseInt(eventId)){
                        				
                        			%>
<%@page import="com.triveni.beans.EventBean"%>
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
                        <h1>Update Events</h1>
                        <p>
                        <%
                        if(request.getParameter("status")!=null){
                        	out.print(request.getParameter("status"));
                        }
                        %>
                        </p>
                       	<form role="form" action="../UpdateEventServlet" method="post">
							<div class="form-group" >
							  <label for=" " >Event Name<sup>*</sup></label>
							  <input type="text" class="form-control" id="eventName" name="eventName" value = "<%=eventBean.getEventName() %>" required placeholder="Enter Event Name">
							  <input type="hidden" name="eventId" value="<%=eventBean.getEventId()%>">
							</div>
							<%
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
							Date myDate = new Date();
							myDate = sdf.parse(eventBean.getEventDate());
							%>
							<div class="form-group">
				                <label for="eventDate" class="col-md-2 control-label">Event Date<sup>*</sup></label>
				                <div class="input-group date form_date col-md-5" data-date="" data-date-format="MM dd yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="16" type="text" required value = "<%=new SimpleDateFormat("mm/dd/yyy").format(myDate)%>"name="eventDate" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
								<input type="hidden" id="dtp_input2" value="" /><br/>
				            </div>
							<div class="form-group">
							  <label for="eventTime">Event Time:<sup>*</sup></label>
							  <select name="eventTime" id="eventTime" required class="form-control">
								<option value="<%=eventBean.getEventTime() %>"><%=eventBean.getEventTime() %></option>
								<option value="0">00</option>
								<option value="1">01</option>
								<option value="2">02</option>
								<option value="3">03</option>
								<option value="4">04</option>
								<option value="5">05</option>
								<option value="6">06</option>
								<option value="7">07</option>
								<option value="8">08</option>
								<option value="9">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
							  </select>
							</div>
							<div class="form-group">
							  <label for="venue">Venue</label>
							  <input type="text" class="form-control" id="venue" value="<%=eventBean.getVenue() %>" required name="venue" placeholder="Enter Venue">
							</div>
							<div class="form-group">
							  <label for="description">Description</label>
							  <input type="text" class="form-control" id="description" value="<%=eventBean.getEventDescription() %>" required name="description" placeholder="Enter Venue">
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
    	$.post("../DeleteEventServlet",{"id":id},function(info){
    		alert(info);
    		location.reload();
    	});
    }
    </script>
	<script type="text/javascript">
		$('.form_date').datetimepicker({
	        language:  'en',
	        startDate: '+0d',
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
<%
                        			}
                        		}
                        	}
                        }
                      	%>