<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
  <style type="text/css">
      <%@ include file="style.css" %>
  </style>

  </head>
  <body>
    <img src="/BookStore/Assets/LibraryBG2.jpg" class="bggg" />
        <div class="login-box">
      <h2>Login</h2>
      <form action="AuthenticationUser" method="post">
        <div class="user-box">
          <input type="text" name="Email" required="" />
          <label>Email</label>
        </div>
        <div class="user-box">
          <input type="password" name="Password" required="" />
          <label>Password</label>
        </div>
        <div class="btn-Link">
        <button name="button" type="submit">Login</button><br />
        <a href="/BookStore/Backups/UserSignUpPage/UserSignUp.jsp">SIGN UP</a>
      </div>
      </form>
      
    </div>
  </body>
</html>