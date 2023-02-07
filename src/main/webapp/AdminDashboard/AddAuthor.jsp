
<%@ page import = "com.zed.bookstore.model.AdminBean" %>
<%@ page import = "com.zed.bookstore.control.Controller" %>
<%@ page import = "com.zed.bookstore.dao.BookStoreDAO" %>
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
<title>Add Author</title>
<link rel="stylesheet" type="text/css" href="AddAuthor.css">
</head>
<body>
    	    

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
                <li>
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
                <li class="hovered">
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
    	
    if(request.getAttribute("Author") == null){
    	List<String> listCountries = new Controller().getCountries();
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
            <div class="addBook">
                <form action="addNewAuthorByAdmin" method="post" class="addBook-form" enctype="multipart/form-data">
                    <h1>ADD AUTHOR</h1>
                    <div class="care">
                        <label for="" class="title">FIRST NAME</label>
                        <input type="text" name="fname" id="">
                    </div>
                    <div class="care">
                        <label for="" class="title">LAST NAME</label>
                        <input type="text" name="lname" id="">
                    </div>
                    <div class="care">
                        <label for="" class="title">COUNTRY</label>
                        <select name="country" style="width:70%; margin-right: 10px; margin-bottom: 10px;">
                        <%
                        
                       
                    	for(int i = 0; i < listCountries.size(); i++) {
                    		%>
                        <option value="<%=listCountries.get(i)%>"><%=listCountries.get(i)%></option>
                        <%} %>
                        </select>
                    </div>
                    <div class="care">
                        <label for="" class="title">BIRTH DATE</label>
                        <input type="date" name="Birthdate" id=""> 
                    </div>
                    <div class="care">
                        <label for="" class="title">BIOGRAPHY</label>
                        <textarea name="biography" id="" cols="30" rows="10"></textarea>
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

	AuthorBean Author = (AuthorBean) request.getAttribute("Author"); 

	
	String ADate = Author.getAuthor_birth_day().get(Calendar.YEAR)+"-"+String.format("%02d",Author.getAuthor_birth_day().get(Calendar.MONTH)+1)+"-"+String.format("%02d",Author.getAuthor_birth_day().get(Calendar.DATE));
	
	List<String> listCountries = new Controller().getCountries();
	
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
            <div class="addBook">
                <form action="UpdateAuthorByAdmin" method="post" class="addBook-form" enctype="multipart/form-data">
                    <h1>EDIT AUTHOR</h1>
                    <div class="care">
                        <label for="" class="title">FIRST NAME</label>
                        <input type="text" name="fname" id="" value="<%=Author.getAuthor_first_name()%>">
                    </div>
                    <div class="care">
                        <label for="" class="title">LAST NAME</label>
                        <input type="text" name="lname" id="" value="<%=Author.getAuthor_last_name()%>">
                    </div>
                    <div class="care">
                        <label for="" class="title">COUNTRY</label>
                        <select name="country" style="width:70%; margin-right: 10px; margin-bottom: 10px;">
                        <%
                        
                        
                    	for(int i = 0; i < listCountries.size(); i++) {
                    		String s = "";
                    		if(listCountries.get(i).equals(Author.getAuthor_country())) s = "selected";
                    		
                    		%>
                        <option value="<%=listCountries.get(i)%>" <%=s%>><%=listCountries.get(i)%></option>
                        <%} %>
                        </select>
                    </div>
                    <div class="care">
                        <label for="" class="title">BIRTH DATE</label>
                        <input type="date" name="Birthdate" id="" value= <%=ADate%>>
                    </div>
                    <div class="care">
                        <label for="" class="title">BIOGRAPHY</label>
                        <textarea name="biography" id="" cols="30" rows="10" ><%=Author.getAuthor_BIOGRAPHY()%></textarea>
                    </div>
                    <div class="care">
                        <label for="" class="title">UPLOAD PICTURE</label>
                        <input class="btnFile" type="file" name="picture" id="" value="">
                    </div>
                        <input type="hidden" value="<%=Aid%>" name="AdminID">
                        <input type="hidden" value="<%=Author.getAuthor_id()%>" name="AuthorId">
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