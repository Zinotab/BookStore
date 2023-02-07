<%@ page import = "com.zed.bookstore.model.AdminBean" %>
<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page import = "com.zed.bookstore.dao.BookStoreDAO" %>
<%@ page import = "com.zed.bookstore.model.CategoryBean" %>
<%@ page import = "java.util.Arrays" %>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
<title>Category List</title>
<link rel="stylesheet" type="text/css" href="CategoryList.css">
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
          <li class="hovered">
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
          <h1>CATEGORY LIST</h1>
          <table class="ele-list">
            <thead>
            <tr class="head-of-table">
              <td>CATEGORY ID</td>
              <td>CATEGORY</td>
              <td>DESCRIPTION</td>
            </tr>
          </thead>
          <tbody>
          
          <%
    
          BookStoreDAO Bdao = new BookStoreDAO();
      	List<CategoryBean> listCategories = Bdao.getAllCategories();
	for(int i = 0; i < listCategories.size(); i++) {
		request.setAttribute("CatID", listCategories.get(i).getCategory_id());
		request.setAttribute("Category", listCategories.get(i).getCategory_name());
		request.setAttribute("DESC", listCategories.get(i).getCategory_desc());
	%>
          
            <tr>
              <td><%= request.getAttribute("CatID") %></td>
              <td><%= request.getAttribute("Category") %></td>
              <td><%= request.getAttribute("DESC") %></td>
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