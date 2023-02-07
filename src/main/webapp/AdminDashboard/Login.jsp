<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    if(request.getParameterMap().containsKey("LO")){
    	new Controller().LogoutAdmin(request, response);
    	
    }

    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="Login.css">
</head>
<body style="background-color: #332f2f;">
    <form action="LoginAdmin" method="post" class="login">
        <h2 id="txt">Login</h2>
        <div class="inputbox">
            <input type="text" placeholder="E-mail" name="Email">
        </div>
        <div class="inputbox">
            <input type="password" placeholder="Password" name="Password">
        </div>
        <div class="inputbox">
            <input type="submit" value="Login" id="btn">
        </div>
    </form>
    <div class="colors">
        <span class="spanContainer active" data-color="#332f2f" style="--clr:#332f2f;" onclick="changeColor('#332f2f');"></span>
        <span class="spanContainer" data-color="#478581" style="--clr:#478581;" onclick="changeColor('#478581');"></span>
        <span class="spanContainer" data-color="#29af61" style="--clr:#29af61;" onclick="changeColor('#29af61');"></span>
        <span class="spanContainer" data-color="#a53b3b" style="--clr:#a53b3b;" onclick="changeColor('#a53b3b');"></span>
        <span class="spanContainer" data-color="#0a7fad" style="--clr:#0a7fad;" onclick="changeColor('#0a7fad');"></span>
    </div>
    <script>
    let button = document.getElementById('btn');
    let text = document.getElementById('txt');


    if(window.localStorage.getItem('color')){
        let storagecolor =localStorage.getItem('color');
        document.body.style.background = storagecolor;
        button.style.background =storagecolor;
        text.style.borderColor = storagecolor;

        document.querySelectorAll('span').forEach((i) =>{
            i.classList.remove('active');
        });
        document.querySelector(`[data-color="${window.localStorage.getItem('color')}"]`).classList.add('active');
    }

    function changeColor(color){
        
        document.body.style.background = color;
        button.style.background =color;
        text.style.borderColor = color;

        // mark as active selected color
        document.querySelectorAll('span').forEach((i) =>{
            i.classList.remove('active');
        });
        event.target.classList.add('active');
        localStorage.setItem('color',color);

    }
    </script>
</body>
</html>
