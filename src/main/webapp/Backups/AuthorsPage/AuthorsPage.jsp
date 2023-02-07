<%@ page import = "com.zed.bookstore.model.ClientBean" %>
<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page import = "com.zed.bookstore.model.AuthorBean" %>
<%@ page import = "java.util.Arrays" %>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="AuthorsPage.css">
    <link rel="stylesheet" type="text/css" href="/BookStore/Backups/AuthorsPage/AuthorsPage.css">
    <title>Authors</title>
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



    <h1 class="title">Authors</h1>

    <div class="wrap">
        <form action="SerchAuthorByName" method="get" class="search">
           <input type="text" class="searchTerm" placeholder="Search for an author" name="AuthorName">
           <button type="submit" class="searchButton">
             <img src="/BookStore/Assets/search icon.png" alt="">
          </button>
        </form>
     </div>

<div id="authors">
 <%if(session.getAttribute("ListOfAuthorsByName") != null){
        
    	List<AuthorBean> listAuthors = (List) session.getAttribute("ListOfAuthorsByName");
    	for(int i = 0; i < listAuthors.size(); i++) { 
    		request.setAttribute("firstName", listAuthors.get(i).getAuthor_first_name());
    		request.setAttribute("lastName", listAuthors.get(i).getAuthor_last_name());
    		request.setAttribute("id", listAuthors.get(i).getAuthor_id());
    		request.setAttribute("PicturePath", listAuthors.get(i).getAuthor_picture());
    		%>  
    <div class="container">
    		<% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/AuthorPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/AuthorImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
    
        
        
        <div class="overly"></div>
            <a href="http://" target="_blank" ><span>
            	<form action="InfoAboutThisAuthor">
            	<input type="hidden" name="AuthorID" value="<%= request.getAttribute("id") %>">
                <input style=" width:175px;
 height:40px;
 background-color: rgba(29, 29, 29, 0.89); ;
 color: #eee;
 border: solid black 2px;
 font-weight: 500;
 font-size: 18px;
 border-radius: 15px;" id="btn-author" type="submit" value="About This Author">
                </form>
            </span></a>
            <p id="authorFullname"><%= request.getAttribute("firstName") %> <%= request.getAttribute("lastName") %></p>
    </div>



      <%}
    	session.removeAttribute("ListOfAuthorsByName");   	
 }else{ if(session.getAttribute("ListOfAuthors") == null){
        	 new Controller().listAuthors(request, response);
    	}
    	List<AuthorBean> listAuthors = (List) session.getAttribute("ListOfAuthors");
    	for(int i = 0; i < listAuthors.size(); i++) { 
    		request.setAttribute("firstName", listAuthors.get(i).getAuthor_first_name());
    		request.setAttribute("lastName", listAuthors.get(i).getAuthor_last_name());
    		request.setAttribute("id", listAuthors.get(i).getAuthor_id());
    		request.setAttribute("PicturePath", listAuthors.get(i).getAuthor_picture());
    		%>  
    <div class="container">
        <% if(request.getAttribute("PicturePath") == null){%>
            <img src="/BookStore/Assets/AuthorPicture.jpg" alt="" srcset="" width="150">
            <%}else{ %>
            <img src="/BookStore/AuthorImages/<%=request.getAttribute("PicturePath")%>" alt="" srcset="" width="150">
            <%}%>
        <div class="overly"></div>
            <a href="http://" target="_blank" ><span>
            	<form action="/BookStore/Backups/AuthorInfoPage/Author.jsp">
            	<input type="hidden" name="AuthorID" value="<%= request.getAttribute("id") %>">
                <input style=" width:175px;
 height:40px;
 background-color: rgba(29, 29, 29, 0.89); ;
 color: #eee;
 border: solid black 2px;
 font-weight: 500;
 font-size: 18px;
 border-radius: 15px;" id="btn-author" type="submit" value="About This Author">
                </form>
            </span></a>
            <p id="authorFullname"><%= request.getAttribute("firstName") %> <%= request.getAttribute("lastName") %></p>
    </div>
	<%}} %>
</div>

<%@ include file="../Supplement/Footer.html" %>
</body>
</html>