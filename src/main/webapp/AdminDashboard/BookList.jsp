<%@ page import = "com.zed.bookstore.model.AdminBean" %>
<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page import = "com.zed.bookstore.dao.BookStoreDAO" %>
<%@ page import = "com.zed.bookstore.model.BookBean" %>
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
<title>Book List</title>
<link rel="stylesheet" type="text/css" href="BookList.css">
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
          <li class="hovered">
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
          <li>
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
          <h1>BOOK LIST</h1>
          <table class="ele-list">
            <thead>
            <tr class="head-of-table">
              <td>ISBN</td>
              <td>TITLE</td>
              <td>ACTION</td>
            </tr>
          </thead>
          <tbody>
          
          
          
          <%
    
          BookStoreDAO Bdao = new BookStoreDAO();
      	List<BookBean> listbooks = Bdao.getAllBooks();
	for(int i = 0; i < listbooks.size(); i++) {
		request.setAttribute("ISBN", listbooks.get(i).getISBN());
		request.setAttribute("TITLE", listbooks.get(i).getBook_title());
	%>
          
            <tr>
              <td><%= request.getAttribute("ISBN") %></td>
              <td><%= request.getAttribute("TITLE") %></td>
              <td><a href="EditBook?isbn=<%=request.getAttribute("ISBN")%>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="DeleteBook?isbn=<%=request.getAttribute("ISBN")%>">Delete</a></td>
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