<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" isErrorPage="true"%>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Error</title>
    <style type="text/css">
      @import url("https://fonts.googleapis.com/css?family=Abril+Fatface|Lato");

      body {
        background: #758ea7;
      }

      .top {
        margin-top: 30px;
      }

      .container {
        margin: 0 auto;
        position: relative;
        width: 250px;
        height: 250px;
        margin-top: -40px;
      }

      .ghost {
        width: 50%;
        height: 53%;
        left: 25%;
        top: 10%;
        position: absolute;
        border-radius: 50% 50% 0 0;
        background: #ededed;
        border: 1px solid #bfc0c0;
        border-bottom: none;
        animation: float 2s ease-out infinite;
      }

      .ghost-copy {
        width: 50%;
        height: 53%;
        left: 25%;
        top: 10%;
        position: absolute;
        border-radius: 50% 50% 0 0;
        background: #ededed;
        border: 1px solid #bfc0c0;
        border-bottom: none;
        animation: float 2s ease-out infinite;
        z-index: 0;
      }

      .face {
        position: absolute;
        width: 100%;
        height: 60%;
        top: 20%;
      }
      .eye,
      .eye-right {
        position: absolute;
        background: #585959;
        width: 13px;
        height: 13px;
        border-radius: 50%;
        top: 40%;
      }

      .eye {
        left: 25%;
      }
      .eye-right {
        right: 25%;
      }

      .mouth {
        position: absolute;
        top: 50%;
        left: 45%;
        width: 10px;
        height: 10px;
        border: 3px solid;
        border-radius: 50%;
        border-color: transparent #585959 #585959 transparent;
        transform: rotate(45deg);
      }

      .one,
      .two,
      .three,
      .four {
        position: absolute;
        background: #ededed;
        top: 85%;
        width: 25%;
        height: 23%;
        border: 1px solid #bfc0c0;
        z-index: 0;
      }

      .one {
        border-radius: 0 0 100% 30%;
        left: -1px;
      }

      .two {
        left: 23%;
        border-radius: 0 0 50% 50%;
      }

      .three {
        left: 50%;
        border-radius: 0 0 50% 50%;
      }

      .four {
        left: 74.5%;
        border-radius: 0 0 30% 100%;
      }

      .shadow {
        position: absolute;
        width: 30%;
        height: 7%;
        background: #bfc0c0;
        left: 35%;
        top: 80%;
        border-radius: 50%;
        animation: scale 2s infinite;
      }

      @keyframes scale {
        0% {
          transform: scale(1);
        }
        50% {
          transform: scale(1.1);
        }
        100% {
          transform: scale(1);
        }
      }

      @keyframes float {
        50% {
          transform: translateY(15px);
        }
      }

      .bottom {
        margin-top: 50px;
      }

      /*text styling*/
      h1 {
        font-family: "Abril Fatface", serif;
        color: #ededed;
        text-align: center;
        font-size: 9em;
        margin: 0;
        text-shadow: -1px 0 #bfc0c0, 0 1px #bfc0c0, 1px 0 #bfc0c0,
          0 -1px #bfc0c0;
      }
      h3 {
        font-family: "Lato", sans-serif;
        font-size: 2em;
        text-transform: uppercase;
        text-align: center;
        color: #bfc0c0;
        margin-top: -20px;
        font-weight: 900;
      }
      p {
        text-align: center;
        font-family: "Abril Fatface", serif;
        color: #585959;
        font-size: 1.2em;
        margin-top: -20px;
        text-transform: uppercase;
      }

      .buttons {
        display: flex;
        align-items: center;
        justify-content: center;
        margin-top: 20px;

      }

      .btn {
        background: #ededed;
        padding: 15px 20px;
        margin: 5px;
        color: #585959;
        font-family: "MV Boli", serif;
        font-weight: 900;
        border: #585959 2px solid;
        text-transform: uppercase;
        font-size: 0.8em;
        letter-spacing: 1px;
        
      }
      .btn:hover {
        background: #bfc0c0;
        transition: all 0.4s ease-out;
      }


    </style>
  </head>
  <body>
    <div id="background"></div>
<% if(response.getStatus() == 404){ %>
    <div class="top">
      <h1>404</h1>
      <h3>page not found</h3>
    </div>
    <div class="container">
      <div class="ghost-copy">
        <div class="one"></div>
        <div class="two"></div>
        <div class="three"></div>
        <div class="four"></div>
      </div>
      <div class="ghost">
        <div class="face">
          <div class="eye"></div>
          <div class="eye-right"></div>
          <div class="mouth"></div>
        </div>
      </div>
      <div class="shadow"></div>
    </div>
    <div class="bottom">
      <p>looks like we did not find this page!</p>
      <div class="buttons">
        <button class="btn" onclick="goBack()">Back</button>
        <button class="btn" onclick="location.href='/BookStore/Backups/HOME/HOME.jsp';">Home</button>
      </div>
    </div>

<%}else{%>

    
<div class="top">
      <h1>500</h1>
      <h3>Server Error</h3>
    </div>
    <div class="container">
      <div class="ghost-copy">
        <div class="one"></div>
        <div class="two"></div>
        <div class="three"></div>
        <div class="four"></div>
      </div>
      <div class="ghost">
        <div class="face">
          <div class="eye"></div>
          <div class="eye-right"></div>
          <div class="mouth"></div>
        </div>
      </div>
      <div class="shadow"></div>
    </div>
    <div class="bottom">
      <p><%=exception.getMessage() %></p>
      <div class="buttons">
        <button class="btn" onclick="goBack()">Back</button>
        <button class="btn" onclick="location.href='/BookStore/Backups/HOME/HOME.jsp';">Home</button>
      </div>
    </div>
    
<%} %>
    <script type="text/javascript">
    function goBack() 
    {
        window.history.back();
    }
    </script>
  </body>
</html>
    