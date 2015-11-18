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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    function validate(){
		var email = document.getElementById("email").value;
		var flag = true;
		var regex = /^[a-z0-9]+$/i;
		var errorMsg = "";
		/* if(!email.match(regex)){
			flag=false;
			errorMsg += " Invalid email,";
		} */
		var firstname =  $("#firstname").val();
		$("#firstname").css("border-color","gray");
		var lastname = $("#lastname").val();
		$("#lastname").css("border-color","gray");
		var city = $("#city").val();
		$("#city").css("border-color","gray");
		var zip = $("#zip").val();
		$("#zip").css("border-color","gray");
		var state = $("#state").val();
		$("#state").css("border-color","gray");
		var address1 = $("#address1").val();
		$("#address1").css("border-color","gray");
		$("#firstNameErrorMessage").html("");
		$("#addressErrorMessage").html("");
		$("#lastNameErrorMessage").html("");
		$("#cityErrorMessage").html("");
		$("#zipErrorMessage").html("");
		$("#stateErrorMessage").html("");
		if(firstname.trim().length==0){
			flag = false;
			$("#firstname").css("border-color","red");
			$("#firstNameErrorMessage").html(" Firstname can not be Empty");
			$("#firstNameErrorMessage").css("color","red");
			errorMsg += " Firstname cannot be Empty,";
		}
		if(lastname.trim().length==0){
			flag = false;
			$("#lastname").css("border-color","red");
			$("#lastNameErrorMessage").html(" Lastname can not be Empty");
			$("#lastNameErrorMessage").css("color","red");
			errorMsg += " lastname cannot be Empty,";
		}
		if(city.trim().length==0){
			flag = false;
			$("#city").css("border-color","red");
			$("#cityErrorMessage").html(" City can not be Empty");
			$("#cityErrorMessage").css("color","red");
			errorMsg += " city cannot be Empty,";
		}
		if(zip.trim().length==0){
			flag = false;
			$("#zip").css("border-color","red");
			$("#zipErrorMessage").html(" ZIP can not be Empty");
			$("#zipErrorMessage").css("color","red");
			errorMsg += " zip cannot be Empty,";
		}
		if(state.trim().length==0){
			flag = false;
			$("#state").css("border-color","red");
			$("#stateErrorMessage").html(" State can not be Empty");
			$("#stateErrorMessage").css("color","red");
			errorMsg += " State cannot be Empty,";
		}
		if(address1.trim().length==0){
			$("#address1").css("border-color","red");
			$("#addressErrorMessage").html(" Address can not be Empty");
			$("#addressErrorMessage").css("color","red");
		}
		if(errorMsg.trim().length!=0){
			/* alert(errorMsg); */
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
                    <%
                    	RegistrationBean registrationBean = (RegistrationBean)session.getAttribute("userDetails");
                    %>
                        <h1>Profile</h1>
						<form role="form" action="../UpdateProfileServlet" method="post" onsubmit="return validate()">
							<div class="form-group" >
							  <label for="firstname" >Triveni Id<sup>*</sup></label>
							  <input type="text" class="form-control" id="triveniId" disabled="disabled" value="<%=session.getAttribute("triveniId") %>" name="triveniId" placeholder="Enter Triveni Id">
							</div>
							<div class="form-group" >
							  <label for="firstname" >First Name:<sup>*</sup><span id="firstNameErrorMessage"></span></label>
							  <input type="text" class="form-control" id="firstname" readonly value="<%=registrationBean.getFirstname() %>" name="firstname" placeholder="Enter First Name">
							</div>
							<div class="form-group">
							  <label for="lastname">Last Name:<sup>*</sup><span id="lastNameErrorMessage"></span></label>
							  <input type="text" class="form-control" id="lastname" readonly value="<%=registrationBean.getLastname() %>" name="lastname" placeholder="Enter Last Name">
							</div>
							<div class="form-group">
							  <label for="email">Email:<sup>*</sup><span id="lastNameErrorMessage"></span></label>
							  <input type="text" class="form-control" id="email" readonly value="<%=registrationBean.getEmail() %>" name="email" placeholder="Enter Email">
							</div>
							<div class="form-group">
							  <label for="gender">Gender:<sup>*</sup></label>
							  <select name="gender" id="gender" required class="form-control">
								<option value="<%=registrationBean.getGender()%>" selected><%=registrationBean.getGender() %></option>
								<option value="Male">Male</option>
								<option value="Female">Female</option>
							  </select>
							</div>
							<div class="form-group">
							  <label for="address1">Address Line-1:<sup>*</sup><span id="addressErrorMessage"></span></label>
							  <input type="text" class="form-control" id="address1" required name="address1" value="<%=registrationBean.getAddress1() %>" placeholder="Enter Address Line-1">
							</div>
							<div class="form-group">
							  <label for="address2">Address Line-2:<sup>*</sup></label>
							  <input type="text" class="form-control" id="address2" name="address2"  value="<%=registrationBean.getAddress2() %>" placeholder="Enter Address Line-2">
							</div>
							<div class="form-group">
							  <label for="phone">Phone:<sup>*</sup></label>
							  <input type="text" class="form-control" id="phone" name="phone" required value="<%=registrationBean.getPhnNumber() %>" placeholder="Enter phone">
							</div>
							<div class="form-group">
							  <label for="permanentContact">Phone:<sup>*</sup></label>
							  <input type="text" class="form-control" id="permanentContact" required name="permanentContact" value="<%=registrationBean.getPermanentContact() %>" placeholder="Enter permanent contact">
							</div>
							<div class="form-group">
							  <label for="city">City:<sup>*</sup><span id="cityErrorMessage"></span></label>
							  <input type="text" class="form-control" id="city" name="city" required value="<%=registrationBean.getCity() %>" placeholder="Enter City">
							</div>
							<div class="form-group">
							  <label for="zip">ZIP:<sup>*</sup><span id="zipErrorMessage"></span></label>
							  <input type="text" class="form-control" id="zip" name="zip" required value="<%=registrationBean.getZip() %>" placeholder="Enter zip">
							</div>
							<div class="form-group">
							  <label for="state">State:<sup>*</sup><span id="stateErrorMessage"></span></label>
							  <input type="text" class="form-control" id="state" name="state" required value="<%=registrationBean.getState() %>" placeholder="Enter State">
							</div>
							<div class="form-group">
							  <label for="country">Country:<sup>*</sup></label>
							  <select name="country" id="country" required class="form-control">
									<option value="<%=registrationBean.getCountry()%>"><%=registrationBean.getCountry() %></option>
									<option value="Afganistan">Afghanistan</option>
									<option value="Albania">Albania</option>
									<option value="Algeria">Algeria</option>
									<option value="American Samoa">American Samoa</option>
									<option value="Andorra">Andorra</option>
									<option value="Angola">Angola</option>
									<option value="Anguilla">Anguilla</option>
									<option value="Antigua &amp; Barbuda">Antigua &amp; Barbuda</option>
									<option value="Argentina">Argentina</option>
									<option value="Armenia">Armenia</option>
									<option value="Aruba">Aruba</option>
									<option value="Australia">Australia</option>
									<option value="Austria">Austria</option>
									<option value="Azerbaijan">Azerbaijan</option>
									<option value="Bahamas">Bahamas</option>
									<option value="Bahrain">Bahrain</option>
									<option value="Bangladesh">Bangladesh</option>
									<option value="Barbados">Barbados</option>
									<option value="Belarus">Belarus</option>
									<option value="Belgium">Belgium</option>
									<option value="Belize">Belize</option>
									<option value="Benin">Benin</option>
									<option value="Bermuda">Bermuda</option>
									<option value="Bhutan">Bhutan</option>
									<option value="Bolivia">Bolivia</option>
									<option value="Bonaire">Bonaire</option>
									<option value="Bosnia &amp; Herzegovina">Bosnia &amp; Herzegovina</option>
									<option value="Botswana">Botswana</option>
									<option value="Brazil">Brazil</option>
									<option value="British Indian Ocean Ter">British Indian Ocean Ter</option>
									<option value="Brunei">Brunei</option>
									<option value="Bulgaria">Bulgaria</option>
									<option value="Burkina Faso">Burkina Faso</option>
									<option value="Burundi">Burundi</option>
									<option value="Cambodia">Cambodia</option>
									<option value="Cameroon">Cameroon</option>
									<option value="Canada">Canada</option>
									<option value="Canary Islands">Canary Islands</option>
									<option value="Cape Verde">Cape Verde</option>
									<option value="Cayman Islands">Cayman Islands</option>
									<option value="Central African Republic">Central African Republic</option>
									<option value="Chad">Chad</option>
									<option value="Channel Islands">Channel Islands</option>
									<option value="Chile">Chile</option>
									<option value="China">China</option>
									<option value="Christmas Island">Christmas Island</option>
									<option value="Cocos Island">Cocos Island</option>
									<option value="Colombia">Colombia</option>
									<option value="Comoros">Comoros</option>
									<option value="Congo">Congo</option>
									<option value="Cook Islands">Cook Islands</option>
									<option value="Costa Rica">Costa Rica</option>
									<option value="Cote DIvoire">Cote D'Ivoire</option>
									<option value="Croatia">Croatia</option>
									<option value="Cuba">Cuba</option>
									<option value="Curaco">Curacao</option>
									<option value="Cyprus">Cyprus</option>
									<option value="Czech Republic">Czech Republic</option>
									<option value="Denmark">Denmark</option>
									<option value="Djibouti">Djibouti</option>
									<option value="Dominica">Dominica</option>
									<option value="Dominican Republic">Dominican Republic</option>
									<option value="East Timor">East Timor</option>
									<option value="Ecuador">Ecuador</option>
									<option value="Egypt">Egypt</option>
									<option value="El Salvador">El Salvador</option>
									<option value="Equatorial Guinea">Equatorial Guinea</option>
									<option value="Eritrea">Eritrea</option>
									<option value="Estonia">Estonia</option>
									<option value="Ethiopia">Ethiopia</option>
									<option value="Falkland Islands">Falkland Islands</option>
									<option value="Faroe Islands">Faroe Islands</option>
									<option value="Fiji">Fiji</option>
									<option value="Finland">Finland</option>
									<option value="France">France</option>
									<option value="French Guiana">French Guiana</option>
									<option value="French Polynesia">French Polynesia</option>
									<option value="French Southern Ter">French Southern Ter</option>
									<option value="Gabon">Gabon</option>
									<option value="Gambia">Gambia</option>
									<option value="Georgia">Georgia</option>
									<option value="Germany">Germany</option>
									<option value="Ghana">Ghana</option>
									<option value="Gibraltar">Gibraltar</option>
									<option value="Great Britain">Great Britain</option>
									<option value="Greece">Greece</option>
									<option value="Greenland">Greenland</option>
									<option value="Grenada">Grenada</option>
									<option value="Guadeloupe">Guadeloupe</option>
									<option value="Guam">Guam</option>
									<option value="Guatemala">Guatemala</option>
									<option value="Guinea">Guinea</option>
									<option value="Guyana">Guyana</option>
									<option value="Haiti">Haiti</option>
									<option value="Hawaii">Hawaii</option>
									<option value="Honduras">Honduras</option>
									<option value="Hong Kong">Hong Kong</option>
									<option value="Hungary">Hungary</option>
									<option value="Iceland">Iceland</option>
									<option value="India">India</option>
									<option value="Indonesia">Indonesia</option>
									<option value="Iran">Iran</option>
									<option value="Iraq">Iraq</option>
									<option value="Ireland">Ireland</option>
									<option value="Isle of Man">Isle of Man</option>
									<option value="Israel">Israel</option>
									<option value="Italy">Italy</option>
									<option value="Jamaica">Jamaica</option>
									<option value="Japan">Japan</option>
									<option value="Jordan">Jordan</option>
									<option value="Kazakhstan">Kazakhstan</option>
									<option value="Kenya">Kenya</option>
									<option value="Kiribati">Kiribati</option>
									<option value="Korea North">Korea North</option>
									<option value="Korea Sout">Korea South</option>
									<option value="Kuwait">Kuwait</option>
									<option value="Kyrgyzstan">Kyrgyzstan</option>
									<option value="Laos">Laos</option>
									<option value="Latvia">Latvia</option>
									<option value="Lebanon">Lebanon</option>
									<option value="Lesotho">Lesotho</option>
									<option value="Liberia">Liberia</option>
									<option value="Libya">Libya</option>
									<option value="Liechtenstein">Liechtenstein</option>
									<option value="Lithuania">Lithuania</option>
									<option value="Luxembourg">Luxembourg</option>
									<option value="Macau">Macau</option>
									<option value="Macedonia">Macedonia</option>
									<option value="Madagascar">Madagascar</option>
									<option value="Malaysia">Malaysia</option>
									<option value="Malawi">Malawi</option>
									<option value="Maldives">Maldives</option>
									<option value="Mali">Mali</option>
									<option value="Malta">Malta</option>
									<option value="Marshall Islands">Marshall Islands</option>
									<option value="Martinique">Martinique</option>
									<option value="Mauritania">Mauritania</option>
									<option value="Mauritius">Mauritius</option>
									<option value="Mayotte">Mayotte</option>
									<option value="Mexico">Mexico</option>
									<option value="Midway Islands">Midway Islands</option>
									<option value="Moldova">Moldova</option>
									<option value="Monaco">Monaco</option>
									<option value="Mongolia">Mongolia</option>
									<option value="Montserrat">Montserrat</option>
									<option value="Morocco">Morocco</option>
									<option value="Mozambique">Mozambique</option>
									<option value="Myanmar">Myanmar</option>
									<option value="Nambia">Nambia</option>
									<option value="Nauru">Nauru</option>
									<option value="Nepal">Nepal</option>
									<option value="Netherland Antilles">Netherland Antilles</option>
									<option value="Netherlands">Netherlands (Holland, Europe)</option>
									<option value="Nevis">Nevis</option>
									<option value="New Caledonia">New Caledonia</option>
									<option value="New Zealand">New Zealand</option>
									<option value="Nicaragua">Nicaragua</option>
									<option value="Niger">Niger</option>
									<option value="Nigeria">Nigeria</option>
									<option value="Niue">Niue</option>
									<option value="Norfolk Island">Norfolk Island</option>
									<option value="Norway">Norway</option>
									<option value="Oman">Oman</option>
									<option value="Pakistan">Pakistan</option>
									<option value="Palau Island">Palau Island</option>
									<option value="Palestine">Palestine</option>
									<option value="Panama">Panama</option>
									<option value="Papua New Guinea">Papua New Guinea</option>
									<option value="Paraguay">Paraguay</option>
									<option value="Peru">Peru</option>
									<option value="Phillipines">Philippines</option>
									<option value="Pitcairn Island">Pitcairn Island</option>
									<option value="Poland">Poland</option>
									<option value="Portugal">Portugal</option>
									<option value="Puerto Rico">Puerto Rico</option>
									<option value="Qatar">Qatar</option>
									<option value="Republic of Montenegro">Republic of Montenegro</option>
									<option value="Republic of Serbia">Republic of Serbia</option>
									<option value="Reunion">Reunion</option>
									<option value="Romania">Romania</option>
									<option value="Russia">Russia</option>
									<option value="Rwanda">Rwanda</option>
									<option value="St Barthelemy">St Barthelemy</option>
									<option value="St Eustatius">St Eustatius</option>
									<option value="St Helena">St Helena</option>
									<option value="St Kitts-Nevis">St Kitts-Nevis</option>
									<option value="St Lucia">St Lucia</option>
									<option value="St Maarten">St Maarten</option>
									<option value="St Pierre &amp; Miquelon">St Pierre &amp; Miquelon</option>
									<option value="St Vincent &amp; Grenadines">St Vincent &amp; Grenadines</option>
									<option value="Saipan">Saipan</option>
									<option value="Samoa">Samoa</option>
									<option value="Samoa American">Samoa American</option>
									<option value="San Marino">San Marino</option>
									<option value="Sao Tome &amp; Principe">Sao Tome &amp; Principe</option>
									<option value="Saudi Arabia">Saudi Arabia</option>
									<option value="Senegal">Senegal</option>
									<option value="Serbia">Serbia</option>
									<option value="Seychelles">Seychelles</option>
									<option value="Sierra Leone">Sierra Leone</option>
									<option value="Singapore">Singapore</option>
									<option value="Slovakia">Slovakia</option>
									<option value="Slovenia">Slovenia</option>
									<option value="Solomon Islands">Solomon Islands</option>
									<option value="Somalia">Somalia</option>
									<option value="South Africa">South Africa</option>
									<option value="Spain">Spain</option>
									<option value="Sri Lanka">Sri Lanka</option>
									<option value="Sudan">Sudan</option>
									<option value="Suriname">Suriname</option>
									<option value="Swaziland">Swaziland</option>
									<option value="Sweden">Sweden</option>
									<option value="Switzerland">Switzerland</option>
									<option value="Syria">Syria</option>
									<option value="Tahiti">Tahiti</option>
									<option value="Taiwan">Taiwan</option>
									<option value="Tajikistan">Tajikistan</option>
									<option value="Tanzania">Tanzania</option>
									<option value="Thailand">Thailand</option>
									<option value="Togo">Togo</option>
									<option value="Tokelau">Tokelau</option>
									<option value="Tonga">Tonga</option>
									<option value="Trinidad &amp; Tobago">Trinidad &amp; Tobago</option>
									<option value="Tunisia">Tunisia</option>
									<option value="Turkey">Turkey</option>
									<option value="Turkmenistan">Turkmenistan</option>
									<option value="Turks &amp; Caicos Is">Turks &amp; Caicos Is</option>
									<option value="Tuvalu">Tuvalu</option>
									<option value="Uganda">Uganda</option>
									<option value="Ukraine">Ukraine</option>
									<option value="United Arab Erimates">United Arab Emirates</option>
									<option value="United Kingdom">United Kingdom</option>
									<option value="United States of America" selected>United States of America</option>
									<option value="Uraguay">Uruguay</option>
									<option value="Uzbekistan">Uzbekistan</option>
									<option value="Vanuatu">Vanuatu</option>
									<option value="Vatican City State">Vatican City State</option>
									<option value="Venezuela">Venezuela</option>
									<option value="Vietnam">Vietnam</option>
									<option value="Virgin Islands (Brit)">Virgin Islands (Brit)</option>
									<option value="Virgin Islands (USA)">Virgin Islands (USA)</option>
									<option value="Wake Island">Wake Island</option>
									<option value="Wallis &amp; Futana Is">Wallis &amp; Futana Is</option>
									<option value="Yemen">Yemen</option>
									<option value="Zaire">Zaire</option>
									<option value="Zambia">Zambia</option>
									<option value="Zimbabwe">Zimbabwe</option>
							  </select>
							</div>
							<button type="submit" class="btn btn-default">Update</button>
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
