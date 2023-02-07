<%@ page import = "com.zed.bookstore.model.ClientBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About Us</title>
<link rel="stylesheet" type="text/css" href="/BookStore/Backups/AboutUsPage/style.css">
</head>
<body>

<%	if(session.getAttribute("have_an_Account") != null){
	ClientBean cb = new ClientBean();
	cb = (ClientBean) session.getAttribute("UserInfo");
	String result = cb.getClient_first_name();
%>
<%@ include file="../Supplement/HeaderForClient.html" %>
<% }else{ %>
<%@ include file="../Supplement/HeaderForUser.html" %>
<%}%>

<div class="container">
      <h1>About Us</h1>
      <div class="image-container">
        <img src="../../Assets/pic.jpg" alt="Company logo">
      </div>
      <p>bookstore is a website that allows customers to purchase books and other printed materials over the internet, and typically offer a wide selection of books, including bestsellers, new releases, and classics, as well as other printed materials such as magazines and newspapers. Our online Bookstore also offers to download and read ebooks on electronic devices such as tablets, smartphones, and e-readers.</p><br>
      
    <h1>Our Developers</h1>
    </div>
    <div class="cards">
      <div class="card card-1">
        <img src="/BookStore/Assets/developer.png" alt="Developer" class="card__icon">
        <h2 class="card__title">Tabbech Zine Eddine</h2>
        <p class="card__apply">
          <a class="card__link" href="#">Contact </a>
        </p>
      </div>
      <div class="card card-2">
        <img src="/BookStore/Assets/developer.png" alt="Developer" class="card__icon">
        <h2 class="card__title">Herhouch Lotfi</h2>
        <p class="card__apply">
          <a class="card__link" href="#">Contact </a>
        </p>
      </div>
      <div class="card card-3">
        <img src="/BookStore/Assets/developer.png" alt="Developer" class="card__icon">
        <h2 class="card__title">Hattna Daoud</h2>
        <p class="card__apply">
          <a class="card__link" href="#">Contact </a>
        </p>
      </div>
      <div class="card card-4">
        <img src="/BookStore/Assets/developer.png" alt="Developer" class="card__icon">
        <h2 class="card__title">Houache Daoud</h2>
        <p class="card__apply">
          <a class="card__link" href="#">Contact</a>
        </p>
      </div>

    </div>
    <%@ include file="../Supplement/Footer.html" %>
</body>
</html>