<%@ page import = "com.zed.bookstore.model.ClientBean" %>
<%@ page import = "com.zed.bookstore.model.BookBean" %>
<%@ page import = "com.zed.bookstore.model.CategoryBean" %>
<%@ page import = "com.zed.bookstore.model.AuthorBean" %>
<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page import = "java.util.Arrays" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp"%>
    <%
    Controller cc = new Controller();
    cc.getAuthorInfosByID(request, response);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="StyleAuthorInfos.css">
<title>The Author</title>
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

<%
        
    	AuthorBean ab = (AuthorBean) session.getAttribute("ThisAuthor");
    	
    		request.setAttribute("firstName", ab.getAuthor_first_name());
    		request.setAttribute("lastName", ab.getAuthor_last_name());
    		request.setAttribute("id", ab.getAuthor_id());
    		request.setAttribute("country", ab.getAuthor_country());
    		request.setAttribute("BDA",ab.getAuthor_birth_day().get(Calendar.YEAR)+"-"+ab.getAuthor_birth_day().get(Calendar.MONTH)+"-"+ab.getAuthor_birth_day().get(Calendar.DATE) );
    		request.setAttribute("Biography", ab.getAuthor_BIOGRAPHY());
    		request.setAttribute("PicturePath", ab.getAuthor_picture());
    		%> 

<section class="AuthorSection">
			<% if(request.getAttribute("PicturePath") == null){%>
            <img class="Author-img" src="/BookStore/Assets/AuthorPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img class="Author-img" src="/BookStore/AuthorImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>

 
  <div class="Author-info" style="display: flex; justify-content: center; width:80%; flex-direction: column; text-align: center;">
  	<h1 class="Author-Att" id="Author-fullName"><%= request.getAttribute("firstName") %> <%= request.getAttribute("lastName") %></h1>
  	<p style="font-family: 'Roboto'; padding-bottom:50px"><%= request.getAttribute("Biography") %></p>
  	<h5 class="Author-Att">Author ID : <span><%= request.getAttribute("id") %></span></h5>
  	<h4 class="Author-Att">BirthDate : <span><%= request.getAttribute("BDA") %></span></h4>
  	<h4 class="Author-Att">Country : <span><%= request.getAttribute("country") %></span></h4>
  </div>
</section>

<section
	style="display: flex;width:100vw;justify-content:center;align-items: center;height: 200px;"
>
	<h1>HIS BOOKS</h1>
</section>


<section class="BooksSection">
	  
	  <% 
    
    	List<BookBean> listBooks = (List) session.getAttribute("BooksOfThisAuthor");
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
            </div>
            </form>
        </div>
        
        
        <%  }
    	session.removeAttribute("BooksOfThisAuthor");	
    	session.removeAttribute("ThisAuthor");
		
    	%>
</section>








<%@ include file="../Supplement/Footer.html" %>
</body>
</html>