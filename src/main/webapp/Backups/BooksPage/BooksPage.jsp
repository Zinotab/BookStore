<%@ page import = "com.zed.bookstore.model.ClientBean" %>
<%@ page import = "com.zed.bookstore.model.BookBean" %>
<%@ page import = "com.zed.bookstore.model.CategoryBean" %>
<%@ page import = "com.zed.bookstore.model.AuthorBean" %>
<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page import = "com.zed.bookstore.dao.BookStoreDAO" %>
<%@ page import = "java.util.Arrays" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp" %>
    
    <%
    Controller cc = new Controller();
    if(request.getParameterMap().containsKey("title")){ 
    cc.DiscoverTheBook(request, response);
    }
    %>
    
<!DOCTYPE html>   
<html lang="en">
<head>
<link rel="icon" type="image/x-icon" href="/Assets/open-book.jpg">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/BookStore/Backups/BooksPage/BooksPage.css">
    <link rel="stylesheet" type="text/css" href="/Backups/BooksPage/style.css">
    
    <title>Book</title>

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
    
    <h1 class="title">Books</h1>
    
     <div class="wrap">
        <form action="SerchBookByName" method="get" class="search">
        	
           <input type="text" class="searchTerm" placeholder="Search for a book" name="title">
           <button type="submit" class="searchButton">
             <img src="/BookStore/Assets/search icon.png" alt="">
          	
          </button>
          </form>
        
     </div>
<h4 style="margin-top:15px;padding: 15px 0;width:100vw; display:flex; justify-content: center; text-align: center; font-family: 'Tahoma'; background-color: rgb(216, 212, 207)">Sort Books By</h4> 
<%@ include file="index.html" %>
<h4 style="width:100vw; display:flex; justify-content: center; text-align: center; font-family: 'Tahoma'; background-color: rgb(216, 212, 207)">Search By Category</h4> 
    <div class="containerSearchByCategory" style="
    width: 100vw;
    background-color: rgb(216, 212, 207);
    display: flex;
    justify-content: center;
    padding: 0 20px 20px 20px;">
    
    
    <%
    
    List<CategoryBean> listcategories = new BookStoreDAO().getAllCategories();
	for(int i = 0; i < listcategories.size(); i++) {
		request.setAttribute("CatName", listcategories.get(i).getCategory_name());
	%>
    
    
        <form class="btns" action="BooksByCategory" method="get">
        	<input type="hidden" value="<%= request.getAttribute("CatName") %>" name="CategoryName">
            <input type="submit" value="<%= request.getAttribute("CatName") %>">
        </form>
    
    <% }%>
    
    
        
    </div>








    <div class="container">
    <%
    if(request.getParameterMap().containsKey("checkBuy")){ 
    System.out.print("\n checkBuy="+request.getParameter("checkBuy")+"\n");
    %>
    
    <div class="popup" id="popup">
    	<img src="/BookStore/Assets/404-tick.png">
    	<h2>Thank You!</h2>
    	<p>Your purchase has been completed successfully</p>
    	<p>Press done to make this command</p>
    	<form action="ClientBuyBook">
    		<input type="hidden" value="<%=request.getParameter("isbn")%>" name="isbn">
    		<input class="btnS2" type="submit" value="DONE" onclick="closepopup()">
    	</form>
    </div>
    <script type="text/javascript">
    let popup = document.getElementById("popup");
    popup.classList.add("open-popup");
    </script>
    
    <%}else if(request.getParameterMap().containsKey("verifyCount")){ 
    System.out.print("\n checkBuy="+request.getParameter("checkBuy")+"\n");
    %>
    
    <div class="popup" id="popup">
    	<img src="/BookStore/Assets/Delete-PNG-High-Quality-Image.png">
    	<h2>You are not Login</h2>
    	<p>Please login if you have account</p>
    	<p>Press Login</p>
    	<form action="../UserLoginPage/UserLoginPage.jsp">
    		<input class="btnS2" type="submit" value="Login" onclick="closepopup()" style="background-color: gray;">
    	</form>
    </div>
    <script type="text/javascript">
    let popup = document.getElementById("popup");
    popup.classList.add("open-popup");
    </script>
    
    <%} %>
    
    
    
    
    
    
    <% 
    if(session.getAttribute("ListOfBooksByName") != null){
    	
    	List<BookBean> listBooks = (List) session.getAttribute("ListOfBooksByName");
    	for(int i = 0; i < listBooks.size(); i++) { 
    		request.setAttribute("title", listBooks.get(i).getBook_title());
    		request.setAttribute("desc", listBooks.get(i).getBook_description());
    		request.setAttribute("isbn", listBooks.get(i).getISBN());
    		request.setAttribute("author",cc.getAuthorFullNameByID(listBooks.get(i).getAuthor_id()));
    		request.setAttribute("price", listBooks.get(i).getBook_price());
    		request.setAttribute("category", cc.getCategoryNameByID(listBooks.get(i).getCat_id()));
    		request.setAttribute("page", listBooks.get(i).getBook_pages());
    		request.setAttribute("ReleaseYear",listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE) );
    		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());
    		%>
    		
    		<div class="Book">
    		<div class="book-form">
            <form onsubmit="return false" action="" class="book-form">
            <% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
            <div class="infos">
                <h1 class="title1"><%= request.getAttribute("title") %></h1>
                <p class="desc"><h3><span class="att">Description  </span></h3><%= request.getAttribute("desc") %></p>
                <h3 class="isbn"><span class="att">ISBN : </span><%= request.getAttribute("isbn") %></h3>
                <h3 class="author"><span class="att">Author : </span><%= request.getAttribute("author") %></h3>
                <h3 class="price"><span class="att">Price : </span><%= request.getAttribute("price") %>$</h3>
                <h3 class="category"><span class="att">Category : </span><%= request.getAttribute("category") %></h3>
                <h3 class="page"><span class="att">pages : </span><%= request.getAttribute("page") %></h3>
                <h3 class="ReleaseYear"><span class="att">Release date : </span><%= request.getAttribute("ReleaseYear") %></h3>
                <input value="<%= request.getAttribute("isbn") %>" type="hidden" name="isbn">
                <%if(session.getAttribute("have_an_Account")!= null){ %>
                <input value="true" type="hidden" name="checkBuy">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%}else{ %>
				<input value="true" type="hidden" name="verifyCount">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%} %>
            </div>
            </form>
            <%if(session.getAttribute("have_an_Account")!= null){ 
            		if(new Controller().CheckLike(Integer.parseInt(request.getAttribute("isbn").toString()),Integer.parseInt(session.getAttribute("UserID").toString()))){
            %>
            <input type="image" src="../../Assets/heart (2).png" class="heart">
            <%    		
	            }else{
            %>
            <input type="image" src="../../Assets/heart.png" class="heart">
            <%}}%>
            </div>
        </div>
    		
    		<%  }
    	session.removeAttribute("ListOfBooksByName");	
    	
    }  else if(session.getAttribute("ListOfBooksByCategory") != null){
    	
    	List<BookBean> listBooks = (List) session.getAttribute("ListOfBooksByCategory");
    	for(int i = 0; i < listBooks.size(); i++) { 
    		request.setAttribute("title", listBooks.get(i).getBook_title());
    		request.setAttribute("desc", listBooks.get(i).getBook_description());
    		request.setAttribute("isbn", listBooks.get(i).getISBN());
    		request.setAttribute("author",cc.getAuthorFullNameByID(listBooks.get(i).getAuthor_id()));
    		request.setAttribute("price", listBooks.get(i).getBook_price());
    		request.setAttribute("category", cc.getCategoryNameByID(listBooks.get(i).getCat_id()));
    		request.setAttribute("page", listBooks.get(i).getBook_pages());
    		request.setAttribute("ReleaseYear",listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE) );
    		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());
    		%>
    		
    		<div class="Book">
    		<div class="book-form">
            <form onsubmit="return false" action="" class="book-form">
            <% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
            <div class="infos">
                <h1 class="title1"><%= request.getAttribute("title") %></h1>
                <p class="desc"><h3><span class="att">Description  </span></h3><%= request.getAttribute("desc") %></p>
                <h3 class="isbn"><span class="att">ISBN : </span><%= request.getAttribute("isbn") %></h3>
                <h3 class="author"><span class="att">Author : </span><%= request.getAttribute("author") %></h3>
                <h3 class="price"><span class="att">Price : </span><%= request.getAttribute("price") %>$</h3>
                <h3 class="category"><span class="att">Category : </span><%= request.getAttribute("category") %></h3>
                <h3 class="page"><span class="att">pages : </span><%= request.getAttribute("page") %></h3>
                <h3 class="ReleaseYear"><span class="att">Release date : </span><%= request.getAttribute("ReleaseYear") %></h3>
                <input value="<%= request.getAttribute("isbn") %>" type="hidden" name="isbn">
                <%if(session.getAttribute("have_an_Account")!= null){ %>
                <input value="true" type="hidden" name="checkBuy">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%}else{ %>
				<input value="true" type="hidden" name="verifyCount">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%} %>
            </div>
            </form>
            <%if(session.getAttribute("have_an_Account")!= null){ 
            		if(new Controller().CheckLike(Integer.parseInt(request.getAttribute("isbn").toString()),Integer.parseInt(session.getAttribute("UserID").toString()))){
            %>
            <input type="image" src="../../Assets/heart (2).png" class="heart">
            <%    		
	            }else{
            %>
            <input type="image" src="../../Assets/heart.png" class="heart">
            <%}}%>
            </div>
        </div>
    		
    		<%  }
    	session.removeAttribute("ListOfBooksByCategory");	
    	
    }   else if(session.getAttribute("ListOfBooksTheMostNumberOfPages") != null){
    	
    	List<BookBean> listBooks = (List) session.getAttribute("ListOfBooksTheMostNumberOfPages");
    	for(int i = 0; i < listBooks.size(); i++) { 
    		request.setAttribute("title", listBooks.get(i).getBook_title());
    		request.setAttribute("desc", listBooks.get(i).getBook_description());
    		request.setAttribute("isbn", listBooks.get(i).getISBN());
    		request.setAttribute("author",cc.getAuthorFullNameByID(listBooks.get(i).getAuthor_id()));
    		request.setAttribute("price", listBooks.get(i).getBook_price());
    		request.setAttribute("category", cc.getCategoryNameByID(listBooks.get(i).getCat_id()));
    		request.setAttribute("page", listBooks.get(i).getBook_pages());
    		request.setAttribute("ReleaseYear",listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE) );
    		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());
    		%>
    		
    		<div class="Book">
    		<div class="book-form">
            <form onsubmit="return false" action="" class="book-form">
            <% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
            <div class="infos">
                <h1 class="title1"><%= request.getAttribute("title") %></h1>
                <p class="desc"><h3><span class="att">Description  </span></h3><%= request.getAttribute("desc") %></p>
                <h3 class="isbn"><span class="att">ISBN : </span><%= request.getAttribute("isbn") %></h3>
                <h3 class="author"><span class="att">Author : </span><%= request.getAttribute("author") %></h3>
                <h3 class="price"><span class="att">Price : </span><%= request.getAttribute("price") %>$</h3>
                <h3 class="category"><span class="att">Category : </span><%= request.getAttribute("category") %></h3>
                <h3 class="page"><span class="att">pages : </span><%= request.getAttribute("page") %></h3>
                <h3 class="ReleaseYear"><span class="att">Release date : </span><%= request.getAttribute("ReleaseYear") %></h3>
                <input value="<%= request.getAttribute("isbn") %>" type="hidden" name="isbn">
                <%if(session.getAttribute("have_an_Account")!= null){ %>
                <input value="true" type="hidden" name="checkBuy">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%}else{ %>
				<input value="true" type="hidden" name="verifyCount">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%} %>
            </div>
            </form>
            <%if(session.getAttribute("have_an_Account")!= null){ 
            		if(new Controller().CheckLike(Integer.parseInt(request.getAttribute("isbn").toString()),Integer.parseInt(session.getAttribute("UserID").toString()))){
            %>
            <input type="image" src="../../Assets/heart (2).png" class="heart">
            <%    		
	            }else{
            %>
            <input type="image" src="../../Assets/heart.png" class="heart">
            <%}}%>
            </div>
        </div>
    		
    		<%  }
    	session.removeAttribute("ListOfBooksTheMostNumberOfPages");	
    	
    }   else if(session.getAttribute("ListOfBooksMostExpensiveBooks") != null){
    	
    	List<BookBean> listBooks = (List) session.getAttribute("ListOfBooksMostExpensiveBooks");
    	for(int i = 0; i < listBooks.size(); i++) { 
    		request.setAttribute("title", listBooks.get(i).getBook_title());
    		request.setAttribute("desc", listBooks.get(i).getBook_description());
    		request.setAttribute("isbn", listBooks.get(i).getISBN());
    		request.setAttribute("author",cc.getAuthorFullNameByID(listBooks.get(i).getAuthor_id()));
    		request.setAttribute("price", listBooks.get(i).getBook_price());
    		request.setAttribute("category", cc.getCategoryNameByID(listBooks.get(i).getCat_id()));
    		request.setAttribute("page", listBooks.get(i).getBook_pages());
    		request.setAttribute("ReleaseYear",listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE) );
    		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());
    		%>
    		
    		<div class="Book">
    		<div class="book-form">
            <form onsubmit="return false" action="" class="book-form">
            <% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
            <div class="infos">
                <h1 class="title1"><%= request.getAttribute("title") %></h1>
                <p class="desc"><h3><span class="att">Description  </span></h3><%= request.getAttribute("desc") %></p>
                <h3 class="isbn"><span class="att">ISBN : </span><%= request.getAttribute("isbn") %></h3>
                <h3 class="author"><span class="att">Author : </span><%= request.getAttribute("author") %></h3>
                <h3 class="price"><span class="att">Price : </span><%= request.getAttribute("price") %>$</h3>
                <h3 class="category"><span class="att">Category : </span><%= request.getAttribute("category") %></h3>
                <h3 class="page"><span class="att">pages : </span><%= request.getAttribute("page") %></h3>
                <h3 class="ReleaseYear"><span class="att">Release date : </span><%= request.getAttribute("ReleaseYear") %></h3>
                <input value="<%= request.getAttribute("isbn") %>" type="hidden" name="isbn">
                <%if(session.getAttribute("have_an_Account")!= null){ %>
                <input value="true" type="hidden" name="checkBuy">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%}else{ %>
				<input value="true" type="hidden" name="verifyCount">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%} %>
            </div>
            </form>
            <%if(session.getAttribute("have_an_Account")!= null){ 
            		if(new Controller().CheckLike(Integer.parseInt(request.getAttribute("isbn").toString()),Integer.parseInt(session.getAttribute("UserID").toString()))){
            %>
            <input type="image" src="../../Assets/heart (2).png" class="heart">
            <%    		
	            }else{
            %>
            <input type="image" src="../../Assets/heart.png" class="heart">
            <%}}%>
            </div>
        </div>
    		
    		<%  }
    	session.removeAttribute("ListOfBooksMostExpensiveBooks");	
    	
    }else if(session.getAttribute("ListOfBooksNewBooks") != null){
    	
    	List<BookBean> listBooks = (List) session.getAttribute("ListOfBooksNewBooks");
    	for(int i = 0; i < listBooks.size(); i++) { 
    		request.setAttribute("title", listBooks.get(i).getBook_title());
    		request.setAttribute("desc", listBooks.get(i).getBook_description());
    		request.setAttribute("isbn", listBooks.get(i).getISBN());
    		request.setAttribute("author",cc.getAuthorFullNameByID(listBooks.get(i).getAuthor_id()));
    		request.setAttribute("price", listBooks.get(i).getBook_price());
    		request.setAttribute("category", cc.getCategoryNameByID(listBooks.get(i).getCat_id()));
    		request.setAttribute("page", listBooks.get(i).getBook_pages());
    		request.setAttribute("ReleaseYear",listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE) );
    		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());
    		%>
    		
    		<div class="Book">
    		<div class="book-form">
            <form onsubmit="return false" action="" class="book-form">
            <% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
            <div class="infos">
                <h1 class="title1"><%= request.getAttribute("title") %></h1>
                <p class="desc"><h3><span class="att">Description  </span></h3><%= request.getAttribute("desc") %></p>
                <h3 class="isbn"><span class="att">ISBN : </span><%= request.getAttribute("isbn") %></h3>
                <h3 class="author"><span class="att">Author : </span><%= request.getAttribute("author") %></h3>
                <h3 class="price"><span class="att">Price : </span><%= request.getAttribute("price") %>$</h3>
                <h3 class="category"><span class="att">Category : </span><%= request.getAttribute("category") %></h3>
                <h3 class="page"><span class="att">pages : </span><%= request.getAttribute("page") %></h3>
                <h3 class="ReleaseYear"><span class="att">Release date : </span><%= request.getAttribute("ReleaseYear") %></h3>
                <input value="<%= request.getAttribute("isbn") %>" type="hidden" name="isbn">
                <%if(session.getAttribute("have_an_Account")!= null){ %>
                <input value="true" type="hidden" name="checkBuy">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%}else{ %>
				<input value="true" type="hidden" name="verifyCount">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%} %>
            </div>
            </form>
            <%if(session.getAttribute("have_an_Account")!= null){ 
            		if(new Controller().CheckLike(Integer.parseInt(request.getAttribute("isbn").toString()),Integer.parseInt(session.getAttribute("UserID").toString()))){
            %>
            <input type="image" src="../../Assets/heart (2).png" class="heart">
            <%    		
	            }else{
            %>
            <input type="image" src="../../Assets/heart.png" class="heart">
            <%}}%>
            </div>
        </div>
    		
    		<%  }
    	session.removeAttribute("ListOfBooksNewBooks");	
    	
    }else if(session.getAttribute("ListOfBooksMostLiked") != null){
    	
    	List<BookBean> listBooks = (List) session.getAttribute("ListOfBooksMostLiked");
    	for(int i = 0; i < listBooks.size(); i++) { 
    		request.setAttribute("title", listBooks.get(i).getBook_title());
    		request.setAttribute("desc", listBooks.get(i).getBook_description());
    		request.setAttribute("isbn", listBooks.get(i).getISBN());
    		request.setAttribute("author",cc.getAuthorFullNameByID(listBooks.get(i).getAuthor_id()));
    		request.setAttribute("price", listBooks.get(i).getBook_price());
    		request.setAttribute("category", cc.getCategoryNameByID(listBooks.get(i).getCat_id()));
    		request.setAttribute("page", listBooks.get(i).getBook_pages());
    		request.setAttribute("ReleaseYear",listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE) );
    		request.setAttribute("Likes", listBooks.get(i).getLikes());
    		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());
    		%>
    		
    		<div class="Book">
    		<div class="book-form">
            <form onsubmit="return false" action="" class="book-form">
            <% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
            <div class="infos">
                <h1 class="title1"><%= request.getAttribute("title") %></h1>
                <p class="desc"><h3><span class="att">Description  </span></h3><%= request.getAttribute("desc") %></p>
                <h3 class="isbn"><span class="att">ISBN : </span><%= request.getAttribute("isbn") %></h3>
                <h3 class="author"><span class="att">Author : </span><%= request.getAttribute("author") %></h3>
                <h3 class="price"><span class="att">Price : </span><%= request.getAttribute("price") %>$</h3>
                <h3 class="category"><span class="att">Category : </span><%= request.getAttribute("category") %></h3>
                <h3 class="page"><span class="att">pages : </span><%= request.getAttribute("page") %></h3>
                <h3 class="ReleaseYear"><span class="att">Release date : </span><%= request.getAttribute("ReleaseYear") %></h3>
                <h3 class="Likes"><span class="att">Likes : </span><%= request.getAttribute("Likes") %></h3>
                <input value="<%= request.getAttribute("isbn") %>" type="hidden" name="isbn">
                <%if(session.getAttribute("have_an_Account")!= null){ %>
                <input value="true" type="hidden" name="checkBuy">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%}else{ %>
				<input value="true" type="hidden" name="verifyCount">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%} %>
            </div>
            </form>
            <%if(session.getAttribute("have_an_Account")!= null){ 
            		if(new Controller().CheckLike(Integer.parseInt(request.getAttribute("isbn").toString()),Integer.parseInt(session.getAttribute("UserID").toString()))){
            %>
            <input type="image" src="../../Assets/heart (2).png" class="heart">
            <%    		
	            }else{
            %>
            <input type="image" src="../../Assets/heart.png" class="heart">
            <%}}%>
            </div>
        </div>
    		
    		<%  }
    	session.removeAttribute("ListOfBooksMostLiked");	
    	
    }else if(session.getAttribute("ListOfBooksBestSelling") != null){
    	
    	List<BookBean> listBooks = (List) session.getAttribute("ListOfBooksBestSelling");
    	for(int i = 0; i < listBooks.size(); i++) { 
    		request.setAttribute("title", listBooks.get(i).getBook_title());
    		request.setAttribute("desc", listBooks.get(i).getBook_description());
    		request.setAttribute("isbn", listBooks.get(i).getISBN());
    		request.setAttribute("author",cc.getAuthorFullNameByID(listBooks.get(i).getAuthor_id()));
    		request.setAttribute("price", listBooks.get(i).getBook_price());
    		request.setAttribute("category", cc.getCategoryNameByID(listBooks.get(i).getCat_id()));
    		request.setAttribute("page", listBooks.get(i).getBook_pages());
    		request.setAttribute("ReleaseYear",listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE) );
    		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());request.setAttribute("Sales", listBooks.get(i).getCommands());
    		
    		%>
    		
    		<div class="Book">
    		<div class="book-form">
            <form onsubmit="return false" action="" class="book-form">
            <% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
            <div class="infos">
                <h1 class="title1"><%= request.getAttribute("title") %></h1>
                <p class="desc"><h3><span class="att">Description  </span></h3><%= request.getAttribute("desc") %></p>
                <h3 class="isbn"><span class="att">ISBN : </span><%= request.getAttribute("isbn") %></h3>
                <h3 class="author"><span class="att">Author : </span><%= request.getAttribute("author") %></h3>
                <h3 class="price"><span class="att">Price : </span><%= request.getAttribute("price") %>$</h3>
                <h3 class="category"><span class="att">Category : </span><%= request.getAttribute("category") %></h3>
                <h3 class="page"><span class="att">pages : </span><%= request.getAttribute("page") %></h3>
                <h3 class="ReleaseYear"><span class="att">Release date : </span><%= request.getAttribute("ReleaseYear") %></h3>
                <h3 class="Selles"><span class="att">The Selles : </span><%= request.getAttribute("Sales") %></h3>
                <input value="<%= request.getAttribute("isbn") %>" type="hidden" name="isbn">
                
                
                <%if(session.getAttribute("have_an_Account")!= null){ %>
                <input value="true" type="hidden" name="checkBuy">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%}else{ %>
				<input value="true" type="hidden" name="verifyCount">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%} %>
            </div>
            </form>
            <%if(session.getAttribute("have_an_Account")!= null){ 
            		if(new Controller().CheckLike(Integer.parseInt(request.getAttribute("isbn").toString()),Integer.parseInt(session.getAttribute("UserID").toString()))){
            %>
            <input type="image" src="../../Assets/heart (2).png" class="heart">
            <%    		
	            }else{
            %>
            <input type="image" src="../../Assets/heart.png" class="heart">
            <%}}%>
            
            </div>
        </div>
    		
    		<%  }
    	session.removeAttribute("ListOfBooksBestSelling");	
    	
    }else{
    	if(session.getAttribute("ListOfBooks") == null){
    new Controller().listBooks(request,response);
    	}
    //BookBean bb = new BookBean();
    
	List<BookBean> listBooks = (List) session.getAttribute("ListOfBooks");
	for(int i = 0; i < 10; i++) { 
		request.setAttribute("title", listBooks.get(i).getBook_title());
		request.setAttribute("desc", listBooks.get(i).getBook_description());
		request.setAttribute("isbn", listBooks.get(i).getISBN());
		request.setAttribute("author", cc.getAuthorFullNameByID(listBooks.get(i).getAuthor_id()));
		request.setAttribute("price", listBooks.get(i).getBook_price());
		request.setAttribute("category", cc.getCategoryNameByID(listBooks.get(i).getCat_id()));
		request.setAttribute("page", listBooks.get(i).getBook_pages());
		request.setAttribute("ReleaseYear",listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE) );
		request.setAttribute("PicturePath", listBooks.get(i).getBook_picture());
		%>
    
    
        <div class="Book">
        <div class="book-form">
            <form method="get" action="../BooksPage/BooksPage.jsp" class="book-form">
            <% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/BookPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/BookImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
            <div class="infos">
                <h1 class="title1"><%= request.getAttribute("title") %></h1>
                <p class="desc"><h3><span class="att">Description  </span></h3><%= request.getAttribute("desc") %></p>
                <h3 class="isbn"><span class="att">ISBN : </span><%= request.getAttribute("isbn") %></h3>
                <h3 class="author"><span class="att">Author : </span><%= request.getAttribute("author") %></h3>
                <h3 class="price"><span class="att">Price : </span><%= request.getAttribute("price") %>$</h3>
                <h3 class="category"><span class="att">Category : </span><%= request.getAttribute("category") %></h3>
                <h3 class="page"><span class="att">pages : </span><%= request.getAttribute("page") %></h3>
                <h3 class="ReleaseYear"><span class="att">Release date : </span><%= request.getAttribute("ReleaseYear") %></h3>
                <input value="<%= request.getAttribute("isbn") %>" type="hidden" name="isbn">
                <%if(session.getAttribute("have_an_Account")!= null){ %>
                <input value="true" type="hidden" name="checkBuy">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%}else{ %>
				<input value="true" type="hidden" name="verifyCount">
				<input class="btnS" type="submit" value="Buy" onclick="openpopup()">
				<%} %>
            </div>
            </form>
            <form action="ChangeLikeStatu">
            <%if(session.getAttribute("have_an_Account")!= null){ 
            		if(new Controller().CheckLike(Integer.parseInt(request.getAttribute("isbn").toString()),Integer.parseInt(session.getAttribute("UserID").toString()))){
            %>
            <input type="image" src="../../Assets/heart (2).png" class="heart">
            <input value="<%=request.getAttribute("isbn")%>" type="hidden" name="isbn">
            <input value="<%=session.getAttribute("UserID")%>" type="hidden" name="idC">
           	<%    		
	            }else{
            %>
            <input type="image" src="../../Assets/heart.png" class="heart">
            <input value="<%=request.getAttribute("isbn")%>" type="hidden" name="isbn">
            <input value="<%=session.getAttribute("UserID")%>" type="hidden" name="idC">
            <%}}%>
            </form>
            </div>
        </div>
        
        <% } 	}%>
        
    </div>







<%@ include file="../Supplement/Footer.html" %>


    <script type="text/javascript" src="main.js">

</script>
</body>
</html>