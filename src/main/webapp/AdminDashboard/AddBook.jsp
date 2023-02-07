<%@ page import = "com.zed.bookstore.model.AdminBean" %>
<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page import = "com.zed.bookstore.dao.BookStoreDAO" %>
<%@ page import = "com.zed.bookstore.model.BookBean" %>
<%@ page import = "com.zed.bookstore.model.CategoryBean" %>
<%@ page import = "com.zed.bookstore.model.AuthorBean" %>
<%@ page import = "java.util.Calendar" %>
<%@ page import = "java.util.Arrays" %>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="../../errorPage.jsp"%>
    
     <%
    if(session.getAttribute("have_an_AccountA") == null){
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
	    dispatcher.forward(request, response);
    }else{
    	AdminBean admin = new AdminBean();
    	admin = (AdminBean) session.getAttribute("AdminInfo");
    	int Aid = admin.getAdmin_id();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
<link rel="stylesheet" type="text/css" href="AddBook.css">
</head>
<body style="overflow: scroll;">
<div class="container">
        <div class="navigation">
            <ul>
                <li>
                    <form>
                        <span class="icon"><ion-icon name="book-outline"></ion-icon></span>
                        <input class="title" type="submit" value="Book Store">
                    </form>
                </li>
                <li>
                    <form action="index.jsp">
                        <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
                        <input class="title" type="submit" value="Dashboard">
                    </form>
                </li>
                <li class="hovered">
                    <form action="AddBook.jsp">
                        <span class="icon"><ion-icon name="add-circle-outline"></ion-icon></span>
                        <input class="title" type="submit" value="Add Book">
                    </form>
                </li>
                <li>
                    <form action="BookList.jsp">
                        <span class="icon"><ion-icon name="list-outline"></ion-icon></span>
                        <input class="title" type="submit" value="Book List">
                    </form>
                </li>
                <li>
                    <form action="AddAuthor.jsp">
                        <span class="icon"><ion-icon name="add-circle-outline"></ion-icon></span>
                        <input class="title" type="submit" value="Add Author">
                    </form>
                </li>
                <li>
                    <form action="AuthorList.jsp">
                        <span class="icon"><ion-icon name="list-outline"></ion-icon></span>
                        <input class="title" type="submit" value="Author List">
                    </form>
                </li>
                <li>
                    <form action="ClientList.jsp">
                        <span class="icon"><ion-icon name="list-outline"></ion-icon></span>
                        <input class="title" type="submit" value="Client List">
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
        
        
        <%
    	
    if(request.getAttribute("Book") == null){
    %>
        
        
        
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
            <div class="addBook" style="margin-top: 150px;">
                <form action="addNewBookByAdmin" method="post" class="addBook-form"  enctype="multipart/form-data">
                    <h1>ADD BOOK</h1>
                    <div class="care">
                        <label for="" class="title">TITLE</label>
                        <input type="text" name="BookTitle" id="">
                    </div>
                    <div class="care">
                        <label for="" class="title">PRICE</label>
                        <input type="number" step="0.01" name="price" id="">
                    </div>
                    <div class="care">
                        <label for="" class="title">PAGE</label>
                        <input type="number" name="page" id="">
                    </div>
                    <div class="care">
                        <label for="" class="title">RELEASE DATE</label>
                        <input type="date" name="ReleaseDate" id="">
                    </div>
                    <div class="care">
                        <label for="" class="title">DESCRIPTION</label>
                        <textarea name="des" id="" cols="30" rows="10"></textarea>
                    </div>
                    <div class="care">
                        <label for="" class="title">AUTHOR ID</label>
                        <select name="authorID" style="width:70%; margin-right: 10px; margin-bottom: 10px;">
                        <%
                        BookStoreDAO Bdao = new BookStoreDAO();
                        List<AuthorBean> listAuthors = Bdao.getAllAuthors();
                    	for(int i = 0; i < listAuthors.size(); i++) {
                    		%>
                        <option value="<%=listAuthors.get(i).getAuthor_id()%>"><%=listAuthors.get(i).getAuthor_first_name()%> <%=listAuthors.get(i).getAuthor_last_name()%></option>
                        <%} %>
                        </select>
                    </div>
                    <div class="care">
                        <label for="" class="title">CATEGORY ID</label>
                        <select name="CategoryID" style="width:70%; margin-right: 10px; margin-bottom: 10px;">
                        <%
                        
                        List<CategoryBean> listCategories = Bdao.getAllCategories();
                    	for(int i = 0; i < listCategories.size(); i++) {
                    		%>
                        <option value="<%=listCategories.get(i).getCategory_id()%>"><%=listCategories.get(i).getCategory_name()%></option>
                        <%} %>
                        </select>
                    </div>
                    <div class="care">
                        <label for="" class="title">UPLOAD PICTURE</label>
                        <input class="btnFile" type="file" name="picture" id="">
                    </div>
                        <input type="hidden" value="<%=Aid%>" name="AdminID">
                        <input class="btnSub" type="submit" value="ADD">
                </form>
            </div>


        </div>
    </div>

    <%}else{

    	BookBean book = (BookBean) request.getAttribute("Book"); 



String BDate =book.getReleaseDate().get(Calendar.YEAR)+"-"+String.format("%02d",book.getReleaseDate().get(Calendar.MONTH)+1)+"-"+String.format("%02d",book.getReleaseDate().get(Calendar.DATE));


%>

        
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
            <div class="addBook" style="margin-top: 150px;">
                <form action="UpdateBookByAdmin" method="post" class="addBook-form"  enctype="multipart/form-data">
                    <h1>EDIT BOOK</h1>
                    <div class="care">
                        <label for="" class="title">TITLE</label>
                        <input type="text" name="BookTitle" id="" value="<%=book.getBook_title()%>">
                    </div>
                    <div class="care">
                        <label for="" class="title">PRICE</label>
                        <input type="number" step="0.01" name="price" id="" value="<%=book.getBook_price()%>">
                    </div>
                    <div class="care">
                        <label for="" class="title">PAGE</label>
                        <input type="number" name="page" id="" value="<%=book.getBook_pages()%>">
                    </div>
                    <div class="care">
                        <label for="" class="title">RELEASE DATE</label>
                        <input type="date" name="ReleaseDate" id="" value="<%=BDate%>">
                    </div>
                    <div class="care">
                        <label for="" class="title">DESCRIPTION</label>
                        <textarea name="des" id="" cols="30" rows="10"><%=book.getBook_description()%></textarea>
                    </div>
                    <div class="care">
                        <label for="" class="title">AUTHOR ID</label>
                        <select name="authorID" style="width:70%; margin-right: 10px; margin-bottom: 10px;">
                        <%
                        BookStoreDAO Bdao = new BookStoreDAO();
                        List<AuthorBean> listAuthors = Bdao.getAllAuthors();
                    	for(int i = 0; i < listAuthors.size(); i++) {
                    		String s = "";
                    		if(listAuthors.get(i).getAuthor_id()==book.getAuthor_id()) s = "selected";
                    		%>
                        <option value="<%=listAuthors.get(i).getAuthor_id()%>" <%=s%>><%=listAuthors.get(i).getAuthor_first_name()%> <%=listAuthors.get(i).getAuthor_last_name()%></option>
                        <%} %>
                        </select>
                    </div>
                    <div class="care">
                        <label for="" class="title">CATEGORY ID</label>
                        <select name="CategoryID" style="width:70%; margin-right: 10px; margin-bottom: 10px;">
                        <%
                        
                        List<CategoryBean> listCategories = Bdao.getAllCategories();
                    	for(int i = 0; i < listCategories.size(); i++) {
                    		
                    		String s = "";
                    		if(listCategories.get(i).getCategory_id()==book.getCat_id()) s = "selected";%>
                        <option value="<%=listCategories.get(i).getCategory_id()%>" <%=s%>><%=listCategories.get(i).getCategory_name()%></option>
                        <%} %>
                        </select>
                    </div>
                    <div class="care">
                        <label for="" class="title">UPLOAD PICTURE</label>
                        <input class="btnFile" type="file" name="picture" id="">
                    </div>
                        <input type="hidden" value="<%=Aid%>" name="AdminID">
                        <input type="hidden" value="<%=book.getISBN()%>" name="isbn">
                        <input class="btnSub" type="submit" value="SAVE">
                </form>
            </div>


        </div>
    </div>

<%}%>

    
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


    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>

<%} %>