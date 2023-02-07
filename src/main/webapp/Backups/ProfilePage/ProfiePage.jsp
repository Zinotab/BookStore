<%@ page import = "com.zed.bookstore.model.ClientBean" %>
<%@ page import = "java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp"%>
   
   <%
    if(session.getAttribute("have_an_Account") == null){
    	RequestDispatcher dispatcher = request.getRequestDispatcher("../UserLoginPage/UserLoginPage.jsp");
	    dispatcher.forward(request, response);
    }else{
    	%>
   
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
    <link rel="stylesheet" type="text/css" href="/BookStore/Backups/ProfilePage/profile.css">

    
</head>
<body>
   <%	if(session.getAttribute("have_an_Account") != null){
	ClientBean cb = new ClientBean();
	cb = (ClientBean) session.getAttribute("UserInfo");
	String result = cb.getClient_first_name();
	String fullName =(String) result +" "+ cb.getClient_last_name();
	request.setAttribute("FullName", fullName);
	request.setAttribute("FirstName", cb.getClient_first_name());
	request.setAttribute("LastName", cb.getClient_last_name());
	request.setAttribute("ClientID", cb.getClient_id());
	request.setAttribute("BirthDay", cb.getClient_birth_day().get(Calendar.YEAR)+"-"+cb.getClient_birth_day().get(Calendar.MONTH)+"-"+cb.getClient_birth_day().get(Calendar.DATE));
	request.setAttribute("Telephone", cb.getClient_telephone());
	request.setAttribute("Email", cb.getClient_Email());
	request.setAttribute("PicturePath", cb.getClient_picture());
%>
<%@ include file="../Supplement/HeaderForClient.html" %>
<% }else{ %>
<%@ include file="../Supplement/HeaderForUser.html" %>
<%}%>
        <div class="profile">
        <% if(request.getAttribute("PicturePath") == null){ %>
            <img src="/BookStore/Assets/user.png" alt="User Picture" width="150" height="150" id="profilePicture">
            <%}else{ %>
            <img src="/BookStore/UserImages/<%=request.getAttribute("PicturePath")%>" alt="My Profile" width="150" height="150" id="profilePicture">
            <%}%>
		<form class="ForPicture" style="border: none;" action="uploadPicture" method="post" enctype="multipart/form-data">
  			<input class="getPathP" type="file" name="image">
  			<input type="hidden" name="ClientID" value="<%=request.getAttribute("ClientID")%>">
  			<button class="sbtn" type="submit">Upload Image</button>
		</form>
		
            <div class="name" id="fullname">
                <%= request.getAttribute("FullName") %>
            </div>
            <div class="id" id="id">
                <%= request.getAttribute("ClientID") %>
            </div>
        </div>


    <div class="main">
        <h2>IDENTITY</h2>
        <div class="card">
            <div class="card-body">
                <table>

                        <tr>
                            <td>ID</td>
                            <td>:</td>
                            <td id="id"><%= request.getAttribute("ClientID") %></td>
                        </tr>

                        <tr>
                            <td>Firstname</td>
                            <td>:</td>
                            <td id="firstname"><%= request.getAttribute("FirstName") %></td>
                        </tr>
                        <tr>
                            <td>Lastname</td>
                            <td>:</td>
                            <td id="lastname"><%= request.getAttribute("LastName") %></td>
                        </tr>
                         <tr>
                            <td>Birthday</td>
                            <td>:</td>
                            <td id="birthday"><%= request.getAttribute("BirthDay") %></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>:</td>
                            <td id="email"><%= request.getAttribute("Email") %></td>
                        </tr>
                        <tr>
                            <td>Phone Number</td>
                            <td>:</td>
                            <td id="phoneNumber"><%= request.getAttribute("Telephone") %></td>
                        </tr>
                </table>
            </div>
        </div>
    </div>
    <%@ include file="../Supplement/Footer.html" %>
</body>
</html>

<%} %>