<%@ page import = "com.zed.bookstore.model.AdminBean" %>
<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page import = "com.zed.bookstore.dao.BookStoreDAO" %>
<%@ page import = "com.zed.bookstore.model.AuthorBean" %>
<%@ page import = "java.util.Arrays" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp"%>
    
    <%
    if(session.getAttribute("have_an_AccountA") == null){
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
	    dispatcher.forward(request, response);
    }else{
    	AdminBean admin = new AdminBean();
    	admin = (AdminBean) session.getAttribute("AdminInfo");
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Author List</title>
<link rel="stylesheet" type="text/css" href="AuthorList.css">
</head>
<body>
    <div class="container">
      <div class="navigation">
        <ul>
          <li>
            <form>
              <span class="icon"
                ><ion-icon name="book-outline"></ion-icon
              ></span>
              <input class="title" type="submit" value="Book Store" />
            </form>
          </li>
          <li>
            <form action="index.jsp">
              <span class="icon"
                ><ion-icon name="home-outline"></ion-icon
              ></span>
              <input class="title" type="submit" value="Dashboard" />
            </form>
          </li> 
          <li >
            <form action="AddBook.jsp">
              <span class="icon"
                ><ion-icon name="add-circle-outline"></ion-icon
              ></span>
              <input class="title" type="submit" value="Add Book" />
            </form>
          </li>
          <li>
            <form action="BookList.jsp">
              <span class="icon"
                ><ion-icon name="list-outline"></ion-icon
              ></span>
              <input class="title" type="submit" value="Book List" />
            </form>
          </li>
          <li>
            <form action="AddAuthor.jsp">
              <span class="icon"
                ><ion-icon name="add-circle-outline"></ion-icon
              ></span>
              <input class="title" type="submit" value="Add Author" />
            </form>
          </li>
          <li class="hovered">
            <form action="AuthorList.jsp">
              <span class="icon"
                ><ion-icon name="list-outline"></ion-icon
              ></span>
              <input class="title" type="submit" value="Author List" />
            </form>
          </li>
          <li>
            <form action="ClientList.jsp">
              <span class="icon"
                ><ion-icon name="list-outline"></ion-icon
              ></span>
              <input class="title" type="submit" value="Client List" />
            </form>
          </li>
          <li>
            <form action="CategoryList.jsp">
              <span class="icon"
                ><ion-icon name="list-outline"></ion-icon
              ></span>
              <input class="title" type="submit" value="Category List" />
            </form>
          </li>
        </ul>
      </div>
      <!-- main -->
      <div class="main">
        <div class="topbar">
          <div class="toggle">
            <ion-icon name="grid-outline"></ion-icon>
          </div>
          <div class="user">
            <h6><%=admin.getAdmin_first_name()%> <%=admin.getAdmin_last_name()%></h6>
            <ion-icon name="person-outline"></ion-icon>
            <a href="Login.jsp?LO=true" style="text-decoration: none;"><button id="LogOut-btn"><ion-icon name="log-out-outline"></ion-icon><h6>Logout</h6></button></a>
          </div>
        </div>

       <!-- BOOK LIST -->
        <div class="list-container">
          <h1>AUTHOR LIST</h1>
          <table class="ele-list">
            <thead>
            <tr class="head-of-table">
              <td>ID</td>
              <td>FIRST NAME</td>
              <td>LAST NAME</td>
              <td>ACTION</td>
            </tr>
          </thead>
          <tbody>
          
          <%
    
          BookStoreDAO Bdao = new BookStoreDAO();
      	List<AuthorBean> listAuthors = Bdao.getAllAuthors();
	for(int i = 0; i < listAuthors.size(); i++) {
		request.setAttribute("Authorid", listAuthors.get(i).getAuthor_id());
		request.setAttribute("AfirstN", listAuthors.get(i).getAuthor_first_name());
		request.setAttribute("AlastN", listAuthors.get(i).getAuthor_last_name());
	%>
          
            <tr>
              <td><%= request.getAttribute("Authorid") %></td>
              <td><%= request.getAttribute("AfirstN") %></td>
              <td><%= request.getAttribute("AlastN") %></td>
              <td><a href="EditAuthor?id=<%=request.getAttribute("Authorid")%>">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="DeleteAuthor?id=<%=request.getAttribute("Authorid")%>">Delete</a></td>
            </tr>
            
           <% }%>    
            
            
          </tbody>
          </table>
        </div>
      </div>
    </div>

    <script>
      let toggle = document.querySelector('.toggle');
      let navigation = document.querySelector('.navigation');
      let main = document.querySelector('.main');

      toggle.onclick = function(){
          navigation.classList.toggle('active');
          main.classList.toggle('active');
      }

      let list = document.querySelectorAll('.navigation li');
      function activeLink(){
          list.forEach(
              (item)=>item.classList.remove('hovered'));
              this.classList.add('hovered')
        };
          list.forEach((item)=>
              item.addEventListener('mouseclick',activeLink)
            );
      
    </script>

    <script
      type="module"
      src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
    ></script>
    <script
      nomodule
      src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
    ></script>
</body>
</html>

<%} %>