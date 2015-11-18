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
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet"
	media="screen">
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
						<%
							RegistrationBean registrationBean = (RegistrationBean) session
									.getAttribute("userDetails");
							ItenaryBean itenaryBean = (ItenaryBean) session
									.getAttribute("itenaryDetails");
							boolean pickupFormDisable = true;
							if (itenaryBean.getTriveniId() == null ) {
								if(pickupFormDisable == false){
						%>
						<h1>Pickup Form</h1>
						<marquee style="font-family: Book Antiqua; color: #FFFFFF"
							bgcolor="black" behavior="alternate" scrolldelay="100">
							Please submit the details carefully. You cannot edit the details
							once you submit the form.</marquee>
						<form role="form" action="../UploadItenaryServlet" method="post"
							enctype="multipart/form-data" onsubmit="return checkTerms();">
							<div class="form-group">
								<label for="triveniId">Triveni Id<sup>*</sup></label> <input
									type="text" class="form-control" id="triveniId"
									value="<%=session.getAttribute("triveniId")%>"
									name="triveniId" readonly placeholder="Enter Triveni Id">
							</div>
							<div class="form-group">
								<label for="arrival_date" class="col-md-2 control-label">Arrival
									Date<sup>*</sup>
								</label>
								<div class="input-group date form_date col-md-5" data-date=""
									data-date-format="dd MM yyyy" data-link-field="dtp_input2"
									data-link-format="yyyy-mm-dd">
									<input id="arrivalDate" class="form-control" size="16"
										type="text" required name="arrivalDate" readonly> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="dtp_input2" value="" /><br />
							</div>
							<label for="arrival_time">Arrival Time:<sup>*</sup></label>
							 <select name="arrivalTime" id="arrivalTime" required >
								<option value="">Hours..</option>
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
							</select>
							<select name="minutes" id="minutes" required>
								<option value="">Minutes</option>
								<option value="0">0</option>
								<option value="5">5</option>
								<option value="10">10</option>
								<option value="15">15</option>
								<option value="20">20</option>
								<option value="25">25</option>
								<option value="30">30</option>
								<option value="35">35</option>
								<option value="40">40</option>
								<option value="45">45</option>
								<option value="50">50</option>
								<option value="55">55</option>
							</select>
							<select name="AMPM" id="AMPM" required>
								<option value="">AM/PM</option>
								<option value="AM">AM</option>
								<option value="PM">PM</option>
							</select><br />
							<div class="form-group">
								<label for="extension">Extension:<sup>*</sup>&nbsp;&nbsp;&nbsp;
									(Don't enter + symbol. Ex: 1 )
								</label> <input type="number" class="form-control" id="extension"
									required name="extension" placeholder="Enter extension">
							</div>
							<div class="form-group">
								<label for="phone">Contact Phone Number<sup>*</sup></label> <input
									type="number" class="form-control" id="phone" required
									name="phone" placeholder="Enter phone">
							</div>
							<div class="form-group">
								<label for="itenaryPDF">Upload Itenary PDF:<sup>*</sup></label>
								<input type="file" class="form-control" id="itenary" required
									name="itenary">
							</div>
							<div class="form-group">
								<label for="extraInfo">Extra Info</label> <input type="text"
									class="form-control" id="extraInfo" name="extraInfo"
									placeholder="Extra Information">
							</div>
							<div class="form-group">
								<label for="arrival_time">Temporary accommodation
									required?:<sup>*</sup>
								</label> <select name="accommodationRequired" id="accommodationRequired"
									required class="form-control">
									<option value="">Accommodation Required</option>
									<option value="YES">YES</option>
									<option value="NO">NO</option>
								</select>
							</div>
							<input type="checkbox" id="terms" name="terms"
								value="terms accepted" /> <span id="text">I agree to pay
								$40.00 towards membership fee of Triveni Indian Students
								Association and I understand this money is collected towards
								operational expenses of organization but not for airport
								pickups.<br />
							</span>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
						<%
								}else{
									out.println("Last date to submit pickup forms has passed."+
											"For people who have missed/couldn't upload their itinerary please send an email to: isa-triveni@uncc.edu."+
											"Please go through the spread sheet which is shared in Official Triveni WhatsApp groups.");
								}
							} else {
						%>
						<h1>Pickup Form</h1>
						<br />
						<label for="triveniId">Triveni Id</label><br /><%=itenaryBean.getTriveniId()%>
						<br />
						<label for="arrivalDate">Arrival Date(mm/dd/yyy)</label><br /><%=itenaryBean.getArrivalDate()%>
						<br />
						<label for="arrivalTime">Arrival Time</label><br /><%=itenaryBean.getArrivalTime()%>
						<br />
						<label for="phoneNumber">Phone Number</label><br /><%=itenaryBean.getPhonenumber()%>
						<br />
						<label for="extraInfo">Extra Information Provided</label><br /><%=itenaryBean.getExtraInfo()%>
						<br />
						<label for="accommodationRequired">Temporary Accommodation
							Required</label><br /><%=itenaryBean.getAccommodationRequired()%>
						<br />
						<label for="FileUploaded">Itenary Attachment Uploaded</label><br />
						<a href="<%=itenaryBean.getUrl()%>">Itenary</a>
						<%
							}
						%>
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
	<script type="text/javascript" src="jquery/jquery-1.8.3.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
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
	<script>
		function checkTerms() {
			var arrivalDate = document.getElementById("arrivalDate").value;
			if ($("#terms").is(":checked") && arrivalDate != "") {
				return true;
			} else {
				$("#arrivalDate").css("border-color", "red");
				$("#text").css("color", "red");
				return false;
			}
		}
	</script>
	<script type="text/javascript">
		$('.form_date').datetimepicker({
			language : 'en',
			startDate : '+0d',
			format : 'mm/dd/yyyy',
			weekStart : 1,
			todayBtn : "linked",
			autoclose : true,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			todayHighlight : true
		});
	</script>
</body>

</html>
