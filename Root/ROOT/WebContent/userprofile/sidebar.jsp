   <%@page import="com.triveni.beans.RegistrationBean"%>
<div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="index.jsp">
                        Triveni
                    </a>
                </li>
                <li>
                    <a href="about.jsp">About</a>
                </li>
                <li>
                    <a href="profile.jsp">Profile</a>
                </li>
                <%
                RegistrationBean bean = (RegistrationBean)session.getAttribute("userDetails");
                if(bean.getUserRole().equalsIgnoreCase("treasurer") == true){
                %>
                 <li>
                    <a href="bills.jsp">Bills</a>
                </li>
                <li>
                    <a href="triveniPayments.jsp">Triveni Payments</a>
                </li>
                <%	
                }else if(bean.getUserRole().equalsIgnoreCase("administrator")){
                %>
                <li>
                    <a href="defineUsers.jsp">Define Users</a>
                </li>
                <li>
                    <a href="reports.jsp">Reports</a>
                </li>
                <%
                }else if(bean.getUserRole().equalsIgnoreCase("event organizer")){
                %>
                <li>
                    <a href="uploadPhotos.jsp">Photos</a>
                </li>
                 <li>
                    <a href="createEvents.jsp">Create Events</a>
                </li>
                <%
                }
                %>
                <li>
                    <a href="pickupform.jsp">Pickup form</a>
                </li>
                <li>
                    <a href="payment.jsp">Payments</a>
                </li>
                <li>
                    <a href="event.jsp">Events</a>
                </li>
                <li>
                    <a href="service.jsp">Change Password</a>
                </li>
                <li>
                    <a href="triveniforms.jsp">Triveni Documents</a>
                </li>
               <!--  <li>
                    <a href="contact.jsp">Contact</a>
                </li> -->
                <li>
                    <a href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>