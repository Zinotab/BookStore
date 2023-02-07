<%@ page import = "com.zed.bookstore.model.ClientBean" %>
<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page import = "com.zed.bookstore.model.BookBean" %>
<%@ page import = "java.util.Arrays" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp"%>
	<%
    if(request.getParameterMap().containsKey("LO")){
    	new Controller().LogoutUser(request, response);
    	
    }

    %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="HOME.css">
<title>Home</title>
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

    <section id="main" class="carousel slide" data-bs-ride="carousel">
        <ol class="carousel-indicators">
            <li  data-bs-target="#main" data-bs-slide-to="0" class="active"  ></li>
            <li  data-bs-target="#main" data-bs-slide-to="1" ></li>
            <li  data-bs-target="#main" data-bs-slide-to="2" ></li>
        </ol>

        <div class="carousel-inner">
        
            <div class="carousel-item" style=" background-image:url(/BookStore/Assets/ManReadingBook1.jpg)">
    
                <div class="container">
                    <h1> Margaret Fuller</h1>
                    <p style="color: black; font-weight: bolder; font-size: 1.9rem; font-family: MV Boli;">Today a reader, tomorrow a leader</p>
                    <p><a class="btn btn-lg btn-primary" href="/BookStore/Backups/BooksPage/BooksPage.jsp">book</a></p>
                </div>
            </div>
        
            <div class="carousel-item" style=" background-image:url(/BookStore/Assets/ManReadingBook2.jpg)">
        
                <div class="container">
                    <h1>Carl Sagan</h1>
                    <p style="color: rgb(0, 0, 0); font-weight: bolder; font-size: 1.5rem; font-family: MV Boli;">One glance at a book and you hear the voice of another person, perhaps someone dead for 1,000 years. To read is to voyage through time.</p>
                    <p><a class="btn btn-lg btn-primary" href="/BookStore/Backups/UserSignUpPage/UserSignUp.jsp">Sign up today</a></p>
                </div>
            </div>

            <div class="carousel-item active" style=" background-image:url(/BookStore/Assets/WomanReadingBook.jpg)">
        
                <div class="container">
                    <h1>Charles Baudelaire</h1>
                    <p style="color: rgb(0, 0, 0); font-weight: bolder; font-size: 1.5rem; font-family: MV Boli;">A book is a garden, an orchard, a storehouse, a party, a company by the way, a counselor, a multitude of counselors.</p>
                    <p><a class="btn btn-lg btn-primary" href="/BookStore/Backups/UserSignUpPage/UserSignUp.jsp">Sign up today</a></p>
                </div>
            </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#main" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#main" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
         
        </div>
       
      
    </section>
    
    <section class="NewBooks">
        <h1 class="title">New Books</h1>
        <div class="cards">
        <% if(session.getAttribute("ListOfBooksNewBooks") == null){
        	 new Controller().newBooks(request, response);
    	}
    	List<BookBean> listBooks = (List) session.getAttribute("ListOfBooksNewBooks");
    	for(int i = 0; i < 4; i++) { 
    		request.setAttribute("title", listBooks.get(i).getBook_title());
    		request.setAttribute("ReleaseYear",listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE) );
    		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());
    		%>
        
            <div class="card-book">
            <% if(request.getAttribute("PicturePath") == null){%>
            <img class="img-New-Book" src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img class="img-New-Book" src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
                <h4 class="Name-New-Book"><%= request.getAttribute("title") %></h4>
                <h6 class="Date-New-Book"><%= request.getAttribute("ReleaseYear") %></h6>
                <form action="../BooksPage/BooksPage.jsp" method="get">
                	<input type="hidden" value="<%=request.getAttribute("title")%>" name="title">
                    <input class="btn-discover" type="submit" value="Discover">
                </form>
            </div>
        
        <%} %>
        </div>
    </section>
     <section class="BestBooks">
        <h1 class="title">Best Books</h1>
        <div class="cards">
        <% if(session.getAttribute("ListOfBooksMostLiked") == null){
        	 new Controller().BestBooks(request, response);
    	}
    	List<BookBean> listBookss = (List) session.getAttribute("ListOfBooksMostLiked");
    	for(int i = 0; i < 4; i++) { 
    		request.setAttribute("title", listBookss.get(i).getBook_title());
    		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());
    		%>
        
            <div class="card-book">
                <% if(request.getAttribute("PicturePath") == null){%>
	            <img class="img-New-Book" src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
	            <%}else{ %>
	            <img class="img-New-Book" src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
	            <%}%>
                <h4 class="Name-New-Book"><%= request.getAttribute("title") %></h4>
                <form action="../BooksPage/BooksPage.jsp" method="get">
                	<input type="hidden" value="<%=request.getAttribute("title")%>" name="title">
                    <input class="btn-discover" type="submit" value="Discover">
                </form>
            </div>
        
        <%} %>
        </div>
    </section>
    
    
    
    <%@ include file="../Supplement/Footer.html" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>