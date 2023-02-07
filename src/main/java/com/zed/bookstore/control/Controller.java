package com.zed.bookstore.control;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.imageio.ImageIO;


import com.zed.bookstore.dao.BookStoreDAO;
import com.zed.bookstore.dao.ControllCSVFiles;
import com.zed.bookstore.model.AdminBean;
import com.zed.bookstore.model.AuthorBean;
import com.zed.bookstore.model.BookBean;
import com.zed.bookstore.model.CategoryBean;
import com.zed.bookstore.model.ClientBean;
import com.zed.bookstore.model.CommandBean;
import com.zed.bookstore.model.ManageAuthorBean;
import com.zed.bookstore.model.ManageBookBean;
import com.zed.bookstore.model.ManageClientBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;





/**
 * Servlet implementation class Controller
 */
@MultipartConfig
@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookStoreDAO bookStoreDAO;
	
    /**
     * Default constructor. 
     */
    public Controller() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String action = request.getServletPath();
		System.out.println(action);
		
			switch (action) {

			case "/Backups/UserSignUpPage/SignUpUser":
				insertNewClient(request, response);
				break;
			case "/Backups/UserLoginPage/AuthenticationUser":
				getUserAuthentication(request, response);
				break;
			case "/Backups/ProfilePage/uploadPicture":
				updatePictureForClient(request, response);
				break;
			case "/Backups/BooksPage/SerchBookByName":
				TheBook(request, response);
				break;
			case "/Backups/HOME/SearchBookByName":
				DiscoverTheBook(request, response);
				break;
			case "/Backups/BooksPage/NewBooks":
				newBooks(request, response);
				break;
			case "/Backups/BooksPage/MostLikedBooks":
				BestBooks(request, response);
				break;
			case "/Backups/BooksPage/BestSellingBooks":
				BestSelling(request, response);
				break;
			case "/Backups/BooksPage/TheMostExpensiveBooks":
				MostExpensiveBooks(request, response);
				break;
			case "/Backups/BooksPage/TheMostBooksInTheNumberOfPages":
				TheMostBooksInTheNumberOfPages(request, response);
				break;
			case "/Backups/BooksPage/ClientBuyBook":
				newSell(request, response);
				break;
			case "/Backups/AuthorsPage/SerchAuthorByName":
				TheAuthor(request, response);
				break;
			case "/Backups/AuthorsPage/InfoAboutThisAuthor":
				getAuthorInfosByID(request, response);
				break;
			case "/Backups/AuthorsPage/searchForAuthorsAndBooks":
				MultiSearch(request, response);
				break;
			case "/Backups/BooksPage/searchForAuthorsAndBooks":
				MultiSearch(request, response);
				break;
			case "/Backups/HOME/searchForAuthorsAndBooks":
				MultiSearch(request, response);
				break;
			case "/Backups/ProfilePage/searchForAuthorsAndBooks":
				MultiSearch(request, response);
				break;
			case "/Backups/AboutUsPage/searchForAuthorsAndBooks":
				MultiSearch(request, response);
				break;

			case "/Backups/BooksPage/BooksByCategory":
				BooksOfCategory(request, response);
				break;
			case "/Backups/BooksPage/ChangeLikeStatu":
				ChangeLikeStatu(request, response);
				break;
				
				
//			ADMIN DASHBOARD
			case "/AdminDashboard/LoginAdmin":
				getAdminAuthentication(request,response);
				break;
			case "/AdminDashboard/addNewBookByAdmin":
				insertNewBook(request, response);
				break;
			case "/AdminDashboard/addNewAuthorByAdmin":
				insertNewAuthor(request, response);
				break;
			case "/AdminDashboard/EditAuthor":
				updateAuthorDispatcher(request, response);
				break;
			case "/AdminDashboard/DeleteBook":
				deleteBook(request, response);
				break;
			case "/AdminDashboard/DeleteClient":
				deleteClient(request, response);
				break;
			case "/AdminDashboard/EditBook":
				updateBookDispatcher(request, response);
				break;
			case "/AdminDashboard/DeleteAuthor":
				deleteAuthor(request, response);
				break;
			case "/AdminDashboard/UpdateAuthorByAdmin":
				UpdateAuthorInfos(request, response);
				break;
			case "/AdminDashboard/UpdateBookByAdmin":
				UpdateBookInfos(request, response);
				break;

				
			default:
				break;
			}
		
	}

	public List<String> getCountries() {

		//The csv file doesn't work
		List<String> countries = new ArrayList<>(Arrays.asList("Afghanistan", "Albania"
				, "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina"
				, "Armenia", "Austria", "Azerbaijan", "Bahrain", "Bangladesh", "Barbados"
				, "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina"
				, "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde"
				, "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Channel Islands"
				, "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Côte d'Ivoire", "Croatia"
				, "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic"
				, "DR Congo", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea","Eritrea", "Estonia"
				, "Eswatini", "Ethiopia", "Faeroe Islands", "Finland", "France", "French Guiana", "Gabon"
				, "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Grenada", "Guatemala"
				, "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy See", "Honduras", "Hong Kong", "Hungary"
				, "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man","Italy"
				, "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kuwait", "Kyrgyzstan", "Laos", "Latvia"
				, "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao"
				, "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Mauritania", "Mauritius"
				, "Mayotte", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique"
				, "Myanmar", "Namibia", "Nepal", "Netherlands", "Nicaragua", "Niger", "Nigeria", "North Korea"
				, "North Macedonia", "Norway", "Oman", "Pakistan", "Panama", "Paraguay", "Peru", "Philippines"
				, "Poland", "Portugal", "Qatar", "Réunion", "Romania", "Russia", "Rwanda", "Saint Helena"
				, "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "San Marino"
				, "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone"
				, "Singapore", "Slovakia", "Slovenia", "Somalia", "South Africa", "South Korea", "South Sudan"
				, "Spain", "Sri Lanka", "State of Palestine", "Sudan", "Suriname", "Sweden", "Switzerland"
				, "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas", "Timor-Leste", "Togo"
				, "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Uganda", "Ukraine", "United Arab Emirates"
				, "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "Western Sahara"
				, "Yemen", "Zambia", "Zimbabwe"));

		return countries;
		
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	private void MultiSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		bookStoreDAO = new BookStoreDAO();
		if(isNumeric(request.getParameter("searchTerm"))) {
			
			List<BookBean> listBooks = new ArrayList<>();
			listBooks.add(bookStoreDAO.getBooksByID(Integer.parseInt(request.getParameter("searchTerm"))));
			HttpSession session = request.getSession();
			session.setAttribute("ListOfBooksByName", listBooks);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Backups/BooksPage/BooksPage.jsp");
		    dispatcher.forward(request, response);
		
		}else {
			List<BookBean> listBooks1 = bookStoreDAO.getBooksByName(request.getParameter("searchTerm"));
			if(!listBooks1.isEmpty()) {
				HttpSession session = request.getSession();
				session.setAttribute("ListOfBooksByName", listBooks1);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Backups/BooksPage/BooksPage.jsp");
			    dispatcher.forward(request, response);
			}else {
				List<AuthorBean> listAuthors = bookStoreDAO.getAuthorsByName(request.getParameter("searchTerm"));
				HttpSession session = request.getSession();
				session.setAttribute("ListOfAuthorsByName", listAuthors);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Backups/AuthorsPage/AuthorsPage.jsp");
			    dispatcher.forward(request, response);
			}
		}
	}

	public void TheMostBooksInTheNumberOfPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		bookStoreDAO = new BookStoreDAO();
		List<BookBean> listBooks = bookStoreDAO.getBooksWithTheMostNumberOfPages(5);
		//Here we print all the books in the console
//		for(int i = 0; i < listBooks.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nBook Number " + (i+1));
//		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
//		    System.out.print("\nAuthor_id:"+listBooks.get(i).getAuthor_id());
//		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
//		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
//		    System.out.print("\nCategory Name:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_name());
//		    System.out.print("\nCategory Description:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_desc());
//		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
//		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
//		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getBook_picture()));
//		    System.out.print("\nLikes :"+listBooks.get(i).getLikes());
//		    
//		}
		//later will dispatcher or recreate of whatever we want to do below:
		if(request.getSession().getAttribute("ListOfBooksTheMostNumberOfPages") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("ListOfBooksTheMostNumberOfPages");
			session.setAttribute("ListOfBooksTheMostNumberOfPages", listBooks);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("ListOfBooksTheMostNumberOfPages", listBooks);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
	    dispatcher.forward(request, response);
	}

	public void MostExpensiveBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		bookStoreDAO = new BookStoreDAO();
		List<BookBean> listBooks = bookStoreDAO.getMostExpensiveBooks(5);
		//Here we print all the books in the console
//		for(int i = 0; i < listBooks.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nBook Number " + (i+1));
//		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
//		    System.out.print("\nAuthor_id:"+listBooks.get(i).getAuthor_id());
//		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
//		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
//		    System.out.print("\nCategory Name:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_name());
//		    System.out.print("\nCategory Description:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_desc());
//		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
//		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
//		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getBook_picture()));
//		    System.out.print("\nLikes :"+listBooks.get(i).getLikes());
//		    
//		}
		//later will dispatcher or recreate of whatever we want to do below:
		if(request.getSession().getAttribute("ListOfBooksMostExpensiveBooks") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("ListOfBooksMostExpensiveBooks");
			session.setAttribute("ListOfBooksMostExpensiveBooks", listBooks);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("ListOfBooksMostExpensiveBooks", listBooks);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
	    dispatcher.forward(request, response);
	}

	public String getCategoryNameByID(int id) {
		bookStoreDAO = new BookStoreDAO();
		CategoryBean cb  = new CategoryBean();
		cb = bookStoreDAO.getCategoriesById(id);
		return cb.getCategory_name();
	}
	public String getAuthorFullNameByID(int id) {
		bookStoreDAO = new BookStoreDAO();
		
		AuthorBean ab  = new AuthorBean();
		ab = bookStoreDAO.getAuthorById(id);
		return ab.getAuthor_first_name() +" "+ ab.getAuthor_last_name();
	}
	
	
	private void updatePictureForClient(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		bookStoreDAO = new BookStoreDAO();
		
		
		System.out.println("In do post method of Add Image Servlet");
		Part file = request.getPart("image");
		String imageFileName = file.getSubmittedFileName(); 
		System.out.println("Selected Image File Name: " + imageFileName);
		String uploadPath = "D:/Java Eclipse for web dev/BookStore/src/main/webapp/UserImages/"+imageFileName;
		System.out.println("Uploaded Path :"+uploadPath);
		
		try {
		
		FileOutputStream fos = new FileOutputStream(uploadPath);
		InputStream is = file.getInputStream();
		
		byte[] data = new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ClientBean clientBean = new ClientBean(Integer.parseInt(request.getParameter("ClientID")),"null","null",imageFileName);
		boolean result = bookStoreDAO.UpdatePictureForClient(clientBean);
		
		if(result) {
			ClientBean UpdateSession = bookStoreDAO.getUserByID(clientBean);
			HttpSession session = request.getSession();
			session.removeAttribute("UserInfo");
			session.setAttribute("UserInfo", UpdateSession);
			session.setAttribute("image", UpdateSession.getClient_picture());
			RequestDispatcher dispatcher = request.getRequestDispatcher("ProfiePage.jsp");
		    dispatcher.include(request, response);
		    
		}else {
			PrintWriter out = response.getWriter();
			out.print("<div style='width: 100vw; height : 30px ; background-color: black;' >");
			out.print("<center style='color:#0400ff; font-size:1rem; font-family: MV Boli;'>");
		    out.print("Something Goes Wrong");
		    out.print("</center>");
		    out.print("</div>");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("ProfiePage.jsp");
		    dispatcher.include(request, response);
		    
		}
	}
	protected void insertNewClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookStoreDAO = new BookStoreDAO();
		String[] arrOfStr = request.getParameter("birthdate").split("-");
	
		// create a Calendar object
		Calendar calendar = Calendar.getInstance();
		// set the calendar object to the date
		calendar.set(Calendar.YEAR,Integer.parseInt(arrOfStr[0]));
		calendar.set(Calendar.MONTH,(Integer.parseInt(arrOfStr[1])-1));
		calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(arrOfStr[2]));
		System.out.println(request.getParameter("birthdate"));
		ClientBean cb = new ClientBean(0, request.getParameter("First Name"), request.getParameter("Last Name"), calendar,request.getParameter("Tel"), request.getParameter("Email"), request.getParameter("Password"), null, null);
		boolean NewClient = bookStoreDAO.addNewClient(cb);
    	
		if(NewClient) {
			HttpSession session = request.getSession();
			session.setAttribute("have_an_Account", true);
			session.setAttribute("UserInfo", cb);
			session.setAttribute("UserID", cb.getClient_id());
			RequestDispatcher dispatcher = request.getRequestDispatcher("../UserLoginPage/UserLoginPage.jsp");
		    dispatcher.forward(request, response);
		}else {
			PrintWriter out = response.getWriter();
			out.print("<div style='width: 100vw; height : 30px ; background-color: black;' >");
			out.print("<center style='color:#0400ff; font-size:1rem; font-family: MV Boli;'>");
		    out.print("Something Goes Wrong");
		    out.print("</center>");
		    out.print("</div>");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("../AboutUsPage/AboutUsPage.jsp");
		    dispatcher.include(request, response);
		    
		}
	}
	protected void insertNewBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("\nRelease Date:"+request.getParameter("ReleaseDate")); 
		
		String[] arrOfStr = request.getParameter("ReleaseDate").split("-");
		
		// create a Calendar object
		Calendar calendar = Calendar.getInstance();
		// set the calendar object to the date
		calendar.set(Calendar.YEAR,Integer.parseInt(arrOfStr[0]));
		calendar.set(Calendar.MONTH,(Integer.parseInt(arrOfStr[1])-1));
		calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(arrOfStr[2]));
		
		   
		String title = request.getParameter("BookTitle");
		String Desc = request.getParameter("des");
		float price = Float.parseFloat(request.getParameter("price"));
		int authorID = Integer.parseInt(request.getParameter("authorID"));
		int pages = Integer.parseInt(request.getParameter("page"));
		int CatID = Integer.parseInt(request.getParameter("CategoryID"));
		 


		System.out.println("In do post method of Add Image Servlet");
		Part file = request.getPart("picture");
		String imageFileName = file.getSubmittedFileName(); 
		System.out.println("Selected Image File Name: " + imageFileName);
		String uploadPath = "D:/Java Eclipse for web dev/BookStore/src/main/webapp/BookImages/"+imageFileName;
		System.out.println("Uploaded Path :"+uploadPath);
		
		try {
		
		FileOutputStream fos = new FileOutputStream(uploadPath);
		InputStream is = file.getInputStream();
		
		byte[] data = new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		//then we gonna send a data as parameters in class bean
		BookBean bookBean = new BookBean(1,title,authorID,calendar,pages,price,Desc,imageFileName,CatID);
		bookStoreDAO = new BookStoreDAO();
		int adminID = Integer.parseInt(request.getParameter("AdminID"));
		System.out.println("\nadmin id:"+adminID);
		
		
		Calendar calendar2 = Calendar.getInstance();
    	// Get the current instant in UTC time 
    	Instant instant = Instant.now(); //=> this will get time like 2022-12-29T18:24:22.701255800Z
    	// this will get the time zone id from the system and will send it to the next object to get the time
    	ZoneId localZoneId = ZoneId.systemDefault(); //=> this will get Africa/Algiers
    	// Convert the instant to the local time zone 
    	ZonedDateTime localTime = instant.atZone(localZoneId);// this will get time like 2022-12-29T19:24:22.701255800+01:00[Africa/Algiers]
    	calendar2.setTimeZone(TimeZone.getTimeZone(localZoneId)); 
    	calendar2.setTimeInMillis(localTime.toInstant().toEpochMilli());// and this will hold the whole information

		
		
		
		
		System.out.println("Inserting New Book Status : "+bookStoreDAO.addNewBook(bookBean,new ManageBookBean(adminID,"ADD",calendar2)));
		//later will dispatcher or recreate of whatever we want to do below:
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
		
		
	}
	public void listBooks(HttpServletRequest request, HttpServletResponse response){


		
		List<BookBean> listBooks = new BookStoreDAO().getAllBooks();
		//Here we print all the books in the console
//		for(int i = 0; i < listBooks.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nBook Number " + (i+1));
//		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
//		    System.out.print("\nAuthor :"+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_first_name()+" "+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_last_name());
//		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
//		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
//		    System.out.print("\nCategory Name:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_name());
//		    System.out.print("\nCategory Description:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_desc());
//		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
//		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
//		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getBook_picture()));
//		    
//		}
		
		
		if(request.getSession().getAttribute("ListOfBooks") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("ListOfBooks");
			session.setAttribute("ListOfBooks", listBooks);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("ListOfBooks", listBooks);
		}
		//later will dispatcher or recreate of whatever we want to do below:
	
	}
	protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		bookStoreDAO = new BookStoreDAO();
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		System.out.println("the row with ISBN "+isbn+" has been deleted :"+bookStoreDAO.deleteBookByID(isbn));
    	AdminBean admin = new AdminBean();
    	HttpSession session = request.getSession();
    	admin = (AdminBean) session.getAttribute("AdminInfo");
    	Calendar calendar = Calendar.getInstance();
    	// Get the current instant in UTC time 
    	Instant instant = Instant.now(); //=> this will get time like 2022-12-29T18:24:22.701255800Z
    	// this will get the time zone id from the system and will send it to the next object to get the time
    	ZoneId localZoneId = ZoneId.systemDefault(); //=> this will get Africa/Algiers
    	// Convert the instant to the local time zone 
    	ZonedDateTime localTime = instant.atZone(localZoneId);// this will get time like 2022-12-29T19:24:22.701255800+01:00[Africa/Algiers]
    	calendar.setTimeZone(TimeZone.getTimeZone(localZoneId)); 
    	calendar.setTimeInMillis(localTime.toInstant().toEpochMilli());// and this will hold the whole information

		bookStoreDAO.insertManageBook(isbn, new ManageBookBean(admin.getAdmin_id(),"DELETE",calendar));
		
		//later will dispatcher or recreate of whatever we want to do below:
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
	}
	public void newBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		bookStoreDAO = new BookStoreDAO();
		List<BookBean> listBooks = bookStoreDAO.getLatestBooks(5);
		//Here we print all the books in the console
//		for(int i = 0; i < listBooks.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nBook Number " + (i+1));
//		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
//		    System.out.print("\nAuthor :"+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_first_name()+" "+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_last_name());
//		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
//		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
//		    System.out.print("\nCat_id:"+listBooks.get(i).getCat_id());
//		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
//		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
//		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getBook_picture()));
//		    
//		}
		//later will dispatcher or recreate of whatever we want to do below:
		if(request.getSession().getAttribute("ListOfBooksNewBooks") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("ListOfBooksNewBooks");
			session.setAttribute("ListOfBooksNewBooks", listBooks);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("ListOfBooksNewBooks", listBooks);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
	    dispatcher.forward(request, response);
	}
	public void BestBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		bookStoreDAO = new BookStoreDAO();
		List<BookBean> listBooks = bookStoreDAO.getMostLikedBooks(5);
		//Here we print all the books in the console
//		for(int i = 0; i < listBooks.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nBook Number " + (i+1));
//		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
//		    System.out.print("\nAuthor_id:"+listBooks.get(i).getAuthor_id());
//		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
//		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
//		    System.out.print("\nCategory Name:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_name());
//		    System.out.print("\nCategory Description:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_desc());
//		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
//		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
//		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getBook_picture()));
//		    System.out.print("\nLikes :"+listBooks.get(i).getLikes());
//		    
//		}
		//later will dispatcher or recreate of whatever we want to do below:
		if(request.getSession().getAttribute("ListOfBooksMostLiked") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("ListOfBooksMostLiked");
			session.setAttribute("ListOfBooksMostLiked", listBooks);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("ListOfBooksMostLiked", listBooks);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
	    dispatcher.forward(request, response);
	}
	protected void TheBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		bookStoreDAO = new BookStoreDAO();
		List<BookBean> listBooks = bookStoreDAO.getBooksByName(request.getParameter("title"));
		//Here we print all the books in the console
//		for(int i = 0; i < listBooks.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nBook Number " + (i+1));
//		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
//		    System.out.print("\nAuthor :"+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_first_name()+" "+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_last_name());
//		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
//		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
//		    System.out.print("\nCategory Name:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_name());
//		    System.out.print("\nCategory Description:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_desc());
//		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
//		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
//		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getBook_picture()));
//		}
		//later will dispatcher or recreate of whatever we want to do below:
		HttpSession session = request.getSession();
		session.setAttribute("ListOfBooksByName", listBooks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
	    dispatcher.forward(request, response);
		
	}
	public void DiscoverTheBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		bookStoreDAO = new BookStoreDAO();
		List<BookBean> listBooks = bookStoreDAO.getBooksByName(request.getParameter("title"));
		//Here we print all the books in the console
//		for(int i = 0; i < listBooks.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nBook Number " + (i+1));
//		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
//		    System.out.print("\nAuthor :"+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_first_name()+" "+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_last_name());
//		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
//		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
//		    System.out.print("\nCategory Name:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_name());
//		    System.out.print("\nCategory Description:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_desc());
//		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
//		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
//		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getBook_picture()));
//		}
		//later will dispatcher or recreate of whatever we want to do below:
		HttpSession session = request.getSession();
		session.setAttribute("ListOfBooksByName", listBooks);
		
		
	}
	public void BestSelling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		bookStoreDAO = new BookStoreDAO();
		List<BookBean> listBooks = bookStoreDAO.getBestSellingBooks(5);
		//Here we print all the books in the console
//		for(int i = 0; i < listBooks.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nBook Number " + (i+1));
//		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
//		    System.out.print("\nAuthor :"+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_first_name()+" "+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_last_name());
//		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
//		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
//		    System.out.print("\nCategory Name:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_name());
//		    System.out.print("\nCategory Description:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_desc());
//		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
//		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
//		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getBook_picture()));
//		    System.out.print("\nCommands :"+listBooks.get(i).getCommands());
//		    
//		}
		//later will dispatcher or recreate of whatever we want to do below:
		if(request.getSession().getAttribute("ListOfBooksBestSelling") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("ListOfBooksBestSelling");
			session.setAttribute("ListOfBooksBestSelling", listBooks);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("ListOfBooksBestSelling", listBooks);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
	    dispatcher.forward(request, response);
	}
    protected void BooksOfCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    	bookStoreDAO = new BookStoreDAO();
		List<BookBean> listBooks = bookStoreDAO.getBooksByCategoryName(request.getParameter("CategoryName"));
		//Here we print all the books in the console
//		for(int i = 0; i < listBooks.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nBook Number " + (i+1));
//		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
//		    System.out.print("\nAuthor :"+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_first_name()+" "+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_last_name());
//		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
//		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
//		    System.out.print("\nCategory Name:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_name());
//		    System.out.print("\nCategory Description:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_desc());
//		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
//		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
//		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getBook_picture()));
//		    
//		}
		//later will dispatcher or recreate of whatever we want to do below:
		if(request.getSession().getAttribute("ListOfBooksByCategory") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("ListOfBooksByCategory");
			session.setAttribute("ListOfBooksByCategory", listBooks);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("ListOfBooksByCategory", listBooks);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
	    dispatcher.forward(request, response);
    }
    protected void newSell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
    	int client_id =(int) request.getSession().getAttribute("UserID");
    	
//    	System.out.println("\nisbn:"+Integer.parseInt(request.getParameter("isbn")));
    	
    	System.out.println("\nisbn:"+request.getParameter("isbn"));
    	int book_id =Integer.parseInt(request.getParameter("isbn"));
    	Calendar calendar = Calendar.getInstance();
    	// Get the current instant in UTC time 
    	Instant instant = Instant.now(); //=> this will get time like 2022-12-29T18:24:22.701255800Z
    	// this will get the time zone id from the system and will send it to the next object to get the time
    	ZoneId localZoneId = ZoneId.systemDefault(); //=> this will get Africa/Algiers
    	// Convert the instant to the local time zone 
    	ZonedDateTime localTime = instant.atZone(localZoneId);// this will get time like 2022-12-29T19:24:22.701255800+01:00[Africa/Algiers]
    	calendar.setTimeZone(TimeZone.getTimeZone(localZoneId)); 
    	calendar.setTimeInMillis(localTime.toInstant().toEpochMilli());// and this will hold the whole information
    	CommandBean command = new CommandBean(client_id,book_id,calendar);
    	bookStoreDAO = new BookStoreDAO();
		System.out.println("Inserting New Command Status : "+bookStoreDAO.addNewCommand(command));
		RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
	    dispatcher.forward(request, response);
    }
    protected void BooksOfAuthor(String Name) {// it will not work if send first name and last name combined
    	bookStoreDAO = new BookStoreDAO();
		List<BookBean> listBooks = bookStoreDAO.getBooksByAuthorName(Name);
		//Here we print all the books in the console
		for(int i = 0; i < listBooks.size(); i++) {   
			System.out.print("\n===================================");
			System.out.print("\nBook Number " + (i+1));
		    System.out.print("\nBook_title:"+listBooks.get(i).getBook_title());
		    System.out.print("\nAuthor :"+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_first_name()+" "+bookStoreDAO.getAuthorById(listBooks.get(i).getAuthor_id()).getAuthor_last_name());
		    System.out.print("\ndescription:"+listBooks.get(i).getBook_description());
		    System.out.print("\nprice:"+listBooks.get(i).getBook_price());
		    System.out.print("\nCategory Name:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_name());
		    System.out.print("\nCategory Description:"+bookStoreDAO.getCategoriesById(listBooks.get(i).getCat_id()).getCategory_desc());
		    System.out.print("\nISBN:"+listBooks.get(i).getISBN());
		    System.out.print("\npages:"+listBooks.get(i).getBook_pages());
		    System.out.print("\nReleased:"+listBooks.get(i).getReleaseDate().get(Calendar.YEAR)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.MONTH)+"-"+listBooks.get(i).getReleaseDate().get(Calendar.DATE));
		    System.out.print("\npictureData:"+listBooks.get(i).getBook_picture());
		    
		}
		//later will dispatcher or recreate of whatever we want to do below:
	
	}

    protected void TheAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    	bookStoreDAO = new BookStoreDAO();
		List<AuthorBean> listAuthors = bookStoreDAO.getAuthorsByName(request.getParameter("AuthorName"));
		
		//Here we print all the books in the console
//				for(int i = 0; i < listBooks.size(); i++) {   
//					System.out.print("\n===================================");
//					System.out.print("\nAuthor Number " + (i+1));
//				    System.out.print("\nAuthor first name:"+listBooks.get(i).getAuthor_first_name());
//				    System.out.print("\nAuthor last name:"+listBooks.get(i).getAuthor_last_name());
//				    System.out.print("\nCountry:"+listBooks.get(i).getAuthor_country());
//				    System.out.print("\nReleased:"+listBooks.get(i).getAuthor_birth_day().get(Calendar.YEAR)+"-"+listBooks.get(i).getAuthor_birth_day().get(Calendar.MONTH)+"-"+listBooks.get(i).getAuthor_birth_day().get(Calendar.DATE));
//				    System.out.print("\npictureData:"+Arrays.toString(listBooks.get(i).getAuthor_picture()));
//				}
				//later will dispatcher or recreate of whatever we want to do below:
		HttpSession session = request.getSession();
		session.setAttribute("ListOfAuthorsByName", listAuthors);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("AuthorsPage.jsp");
	    dispatcher.forward(request, response);
    }
    protected void BestAuthors(int rows) {

    	bookStoreDAO = new BookStoreDAO();
		List<AuthorBean> listBooks = bookStoreDAO.getAuthorWithMostBook(rows);
    	//Here we print all the books in the console
		for(int i = 0; i < listBooks.size(); i++) {   
			System.out.print("\n===================================");
			System.out.print("\nAuthor Number " + (i+1));
		    System.out.print("\nAuthor first name:"+listBooks.get(i).getAuthor_first_name());
		    System.out.print("\nAuthor last name:"+listBooks.get(i).getAuthor_last_name());
		    System.out.print("\nCountry:"+listBooks.get(i).getAuthor_country());
		    System.out.print("\nReleased:"+listBooks.get(i).getAuthor_birth_day().get(Calendar.YEAR)+"-"+listBooks.get(i).getAuthor_birth_day().get(Calendar.MONTH)+"-"+listBooks.get(i).getAuthor_birth_day().get(Calendar.DATE));
		    System.out.print("\npictureData:"+listBooks.get(i).getAuthor_picture());
		    System.out.print("\nBooks:"+listBooks.get(i).getAuthor_Books());
		}
		//later will dispatcher or recreate of whatever we want to do below:

    }
	protected void insertNewAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String[] arrOfStr = request.getParameter("Birthdate").split("-");
		
		// create a Calendar object
		Calendar calendar = Calendar.getInstance();
		// set the calendar object to the date
		calendar.set(Calendar.YEAR,Integer.parseInt(arrOfStr[0]));
		calendar.set(Calendar.MONTH,(Integer.parseInt(arrOfStr[1])-1));
		calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(arrOfStr[2]));
		
		   
		String FirstName = request.getParameter("fname");
		String LastName = request.getParameter("lname");
		String country = request.getParameter("country");
		String Biography = request.getParameter("biography");
		
		System.out.println("In do post method of Add Image Servlet");
		Part file = request.getPart("picture");
		String imageFileName = file.getSubmittedFileName(); 
		System.out.println("Selected Image File Name: " + imageFileName);
		String uploadPath = "D:/Java Eclipse for web dev/BookStore/src/main/webapp/AuthorImages/"+imageFileName;
		System.out.println("Uploaded Path :"+uploadPath);
		
		try {
		
		FileOutputStream fos = new FileOutputStream(uploadPath);
		InputStream is = file.getInputStream();
		
		byte[] data = new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		//then we gonna send a data as parameters in class bean
		AuthorBean authorBean = new AuthorBean(1,FirstName,LastName,calendar,country,imageFileName,Biography);
		bookStoreDAO = new BookStoreDAO();
		
		int adminID = Integer.parseInt(request.getParameter("AdminID"));
		System.out.println("\nadmin id:"+adminID);
		
		
		Calendar calendar2 = Calendar.getInstance();
    	// Get the current instant in UTC time 
    	Instant instant = Instant.now(); //=> this will get time like 2022-12-29T18:24:22.701255800Z
    	// this will get the time zone id from the system and will send it to the next object to get the time
    	ZoneId localZoneId = ZoneId.systemDefault(); //=> this will get Africa/Algiers
    	// Convert the instant to the local time zone 
    	ZonedDateTime localTime = instant.atZone(localZoneId);// this will get time like 2022-12-29T19:24:22.701255800+01:00[Africa/Algiers]
    	calendar2.setTimeZone(TimeZone.getTimeZone(localZoneId)); 
    	calendar2.setTimeInMillis(localTime.toInstant().toEpochMilli());// and this will hold the whole information

		System.out.println("Inserting New Author Status : "+bookStoreDAO.addNewAuthor(authorBean,new ManageAuthorBean(adminID,"ADD",calendar2)));
		//later will dispatcher or recreate of whatever we want to do below:
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
	}
	public void listAuthors(HttpServletRequest request, HttpServletResponse response) {


    	bookStoreDAO = new BookStoreDAO();
		List<AuthorBean> listAuthors = bookStoreDAO.getAllAuthors();
    	//Here we print all the books in the console
//		for(int i = 0; i < listAuthors.size(); i++) {   
//			System.out.print("\n===================================");
//			System.out.print("\nAuthor Number " + (i+1));
//		    System.out.print("\nAuthor first name:"+listAuthors.get(i).getAuthor_first_name());
//		    System.out.print("\nAuthor last name:"+listAuthors.get(i).getAuthor_last_name());
//		    System.out.print("\nCountry:"+listAuthors.get(i).getAuthor_country());
//		    System.out.print("\nReleased:"+listAuthors.get(i).getAuthor_birth_day().get(Calendar.YEAR)+"-"+listAuthors.get(i).getAuthor_birth_day().get(Calendar.MONTH)+"-"+listAuthors.get(i).getAuthor_birth_day().get(Calendar.DATE));
//		    System.out.print("\npictureData:"+Arrays.toString(listAuthors.get(i).getAuthor_picture()));
//		}
		//later will dispatcher or recreate of whatever we want to do below:
		if(request.getSession().getAttribute("ListOfAuthors") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("ListOfAuthors");
			session.setAttribute("ListOfAuthors", listAuthors);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("ListOfAuthors", listAuthors);
		}
    }
	protected void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		bookStoreDAO = new BookStoreDAO();
		int authorID = Integer.parseInt(request.getParameter("id"));
		System.out.println("the row with author id "+authorID+" has been deleted :"+bookStoreDAO.deleteAuthorByID(authorID));
		
		AdminBean admin = new AdminBean();
    	HttpSession session = request.getSession();
    	admin = (AdminBean) session.getAttribute("AdminInfo");
    	Calendar calendar = Calendar.getInstance();
    	// Get the current instant in UTC time 
    	Instant instant = Instant.now(); //=> this will get time like 2022-12-29T18:24:22.701255800Z
    	// this will get the time zone id from the system and will send it to the next object to get the time
    	ZoneId localZoneId = ZoneId.systemDefault(); //=> this will get Africa/Algiers
    	// Convert the instant to the local time zone 
    	ZonedDateTime localTime = instant.atZone(localZoneId);// this will get time like 2022-12-29T19:24:22.701255800+01:00[Africa/Algiers]
    	calendar.setTimeZone(TimeZone.getTimeZone(localZoneId)); 
    	calendar.setTimeInMillis(localTime.toInstant().toEpochMilli());// and this will hold the whole information

		bookStoreDAO.insertManageBook(authorID, new ManageBookBean(admin.getAdmin_id(),"DELETE",calendar));
		
		//later will dispatcher or recreate of whatever we want to do below:
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
	}
	protected void addReviewOnTheSite(){

		
	   //then we gonna send a data as parameters in class bean

		ClientBean clientBean = new ClientBean(19,"THIS IS MY REVIEW");
		bookStoreDAO = new BookStoreDAO();
		System.out.println("Adding Review Status : "+bookStoreDAO.setClientReviewOnTheWebSite(clientBean));
		//later will dispatcher or recreate of whatever we want to do below:
		
	}
	protected void listClients() {



    	bookStoreDAO = new BookStoreDAO();
		List<ClientBean> listClients = bookStoreDAO.getAllClients();
    	//Here we print all the books in the console
		

		for(int i = 0; i < listClients.size(); i++) {   
			System.out.print("\n===================================");
			System.out.print("\nClient Number " + (i+1));
			System.out.print("\nClient id:"+listClients.get(i).getClient_id());
		    System.out.print("\nClient first name:"+listClients.get(i).getClient_first_name());
		    System.out.print("\nClient last name:"+listClients.get(i).getClient_last_name());
		    System.out.print("\nBirth Day:"+listClients.get(i).getClient_birth_day().get(Calendar.YEAR)+"-"+listClients.get(i).getClient_birth_day().get(Calendar.MONTH)+"-"+listClients.get(i).getClient_birth_day().get(Calendar.DATE));
		    System.out.print("\nTelephone:"+listClients.get(i).getClient_telephone());
		    System.out.print("\nEmail:"+listClients.get(i).getClient_Email());
		    System.out.print("\nPassword:"+listClients.get(i).getClient_Password());
		    //System.out.print("\npictureData:"+Arrays.toString(listClients.get(i).getClient_picture()));
		    System.out.print("\nReview:"+listClients.get(i).getClient_review());
		    
		}
		//later will dispatcher or recreate of whatever we want to do below:

    }
	protected void getUserAuthentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		bookStoreDAO = new BookStoreDAO();
		
		ClientBean Client = bookStoreDAO.getUserByPE(new ClientBean(request.getParameter("Email"), request.getParameter("Password"),null));
    	
		System.out.println(request.getParameter("Email")+"   "+ request.getParameter("Password"));
		if(Client != null) {
		
			HttpSession session = request.getSession();
			session.setAttribute("have_an_Account", "true");
			session.setAttribute("UserID", (int)Client.getClient_id());
			session.setAttribute("UserInfo", Client);
			
		//Here we print all the books in the console
			System.out.print("\n===================================");
			System.out.print("\nClient id:"+Client.getClient_id());
		    System.out.print("\nClient first name:"+Client.getClient_first_name());
		    System.out.print("\nClient last name:"+Client.getClient_last_name());
		    System.out.print("\nBirth Day:"+Client.getClient_birth_day().get(Calendar.YEAR)+"-"+Client.getClient_birth_day().get(Calendar.MONTH)+"-"+Client.getClient_birth_day().get(Calendar.DATE));
		    System.out.print("\nTelephone:"+Client.getClient_telephone());
		    System.out.print("\nEmail:"+Client.getClient_Email());
		    System.out.print("\nPassword:"+Client.getClient_Password());
		    //System.out.print("\npictureData:"+Arrays.toString(listClientsgetClient_picture()));
		    System.out.print("\nReview:"+Client.getClient_review());
		    
		
		//later will dispatcher or recreate of whatever we want to do below:
		    															
		    response.sendRedirect("../HOME/HOME.jsp");
		   
		    
		}else {
			PrintWriter out = response.getWriter();
			out.print("<div style='width: 100vw; height : 30px ; background-color: black;' >");
			out.print("<center style='color:#0400ff; font-size:1rem; font-family: MV Boli;'>");
		    out.print("Password or Email Wrong");
		    out.print("</center>");
		    out.print("</div>");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("UserLoginPage.jsp");
		    dispatcher.include(request, response);
		}
	}
	protected void getAdminAuthentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		bookStoreDAO = new BookStoreDAO();
		AdminBean adminBean = bookStoreDAO.getAdminByPE(new AdminBean(request.getParameter("Email"), request.getParameter("Password")));
    	//Here we print all the books in the console
		

		System.out.println(request.getParameter("Email")+"   "+ request.getParameter("Password"));
		if(adminBean != null) {
		
			HttpSession session = request.getSession();
			session.setAttribute("have_an_AccountA", true);
			session.setAttribute("AdminInfo", adminBean);
			
			System.out.print("\n===================================");
			System.out.print("\nAdmin id:"+adminBean.getAdmin_id());
		    System.out.print("\nAdmin first name:"+adminBean.getAdmin_first_name());
		    System.out.print("\nAdmin last name:"+adminBean.getAdmin_last_name());
		    System.out.print("\nEmail:"+adminBean.getAdmin_Email());
		    System.out.print("\nPassword:"+adminBean.getAdmin_Password());
		    
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		}else {
			PrintWriter out = response.getWriter();
			out.print("<header style='width: 100vw; height : 30px ; display: absolute; top:0; margin-bottom: 50px;'>");
			out.print("<center style='color:red; font-size:2rem; font-family: MV Boli;'>");
		    out.print("Password or Email Wrong");
		    out.print("</center>");
		    out.print("</header>");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
		    dispatcher.include(request, response);
		}
		
	}
	protected void UpdateCategorieInfoFromCSVFile() {
		ControllCSVFiles.readCategories();
	}
	public void getAuthorInfosByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		bookStoreDAO = new BookStoreDAO();
		AuthorBean Author = bookStoreDAO.getAuthorById(Integer.parseInt(request.getParameter("AuthorID")));
		List<BookBean> listBooks = bookStoreDAO.getBooksByAuthorID(Integer.parseInt(request.getParameter("AuthorID")));
		
		HttpSession session = request.getSession();
		session.setAttribute("ThisAuthor", Author);
		session.setAttribute("BooksOfThisAuthor", listBooks);
		System.out.println("Done");

	}
	public void updateAuthorDispatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		bookStoreDAO = new BookStoreDAO();
		AuthorBean ab = bookStoreDAO.getAuthorById(id);
		request.setAttribute("Author", ab);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AddAuthor.jsp");
		
		dispatcher.forward(request, response);
	}

	public void UpdateAuthorInfos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String[] arrOfStr = request.getParameter("Birthdate").split("-");
		
		// create a Calendar object
		Calendar calendar = Calendar.getInstance();
		// set the calendar object to the date
		calendar.set(Calendar.YEAR,Integer.parseInt(arrOfStr[0]));
		calendar.set(Calendar.MONTH,(Integer.parseInt(arrOfStr[1])-1));
		calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(arrOfStr[2]));
		
		int AuthorID = Integer.parseInt(request.getParameter("AuthorId"));
		String FirstName = request.getParameter("fname");
		String LastName = request.getParameter("lname");
		String country = request.getParameter("country");
		String Biography = request.getParameter("biography");
		
		
		System.out.println("In do post method of Add Image Servlet");
		Part file = request.getPart("picture");
		String imageFileName = file.getSubmittedFileName(); 
		System.out.println("Selected Image File Name: " + imageFileName);
		String uploadPath = "D:/Java Eclipse for web dev/BookStore/src/main/webapp/AuthorImages/"+imageFileName;
		System.out.println("Uploaded Path :"+uploadPath);
		
		try {
		
		FileOutputStream fos = new FileOutputStream(uploadPath);
		InputStream is = file.getInputStream();
		
		byte[] data = new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		//then we gonna send a data as parameters in class bean
		AuthorBean authorBean = new AuthorBean(AuthorID,FirstName,LastName,calendar,country,imageFileName,Biography);
		bookStoreDAO = new BookStoreDAO();
		
		int adminID = Integer.parseInt(request.getParameter("AdminID"));
		System.out.println("\nadmin id:"+adminID);
		
		
		Calendar calendar2 = Calendar.getInstance();
    	// Get the current instant in UTC time 
    	Instant instant = Instant.now(); //=> this will get time like 2022-12-29T18:24:22.701255800Z
    	// this will get the time zone id from the system and will send it to the next object to get the time
    	ZoneId localZoneId = ZoneId.systemDefault(); //=> this will get Africa/Algiers
    	// Convert the instant to the local time zone 
    	ZonedDateTime localTime = instant.atZone(localZoneId);// this will get time like 2022-12-29T19:24:22.701255800+01:00[Africa/Algiers]
    	calendar2.setTimeZone(TimeZone.getTimeZone(localZoneId)); 
    	calendar2.setTimeInMillis(localTime.toInstant().toEpochMilli());// and this will hold the whole information

		System.out.println("Updating Author Status : "+bookStoreDAO.UpdateAuthorByid(authorBean,new ManageAuthorBean(adminID,"UPDATE",calendar2)));
		//later will dispatcher or recreate of whatever we want to do below:
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);

		

	}

	
	public void updateBookDispatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("isbn"));
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		System.out.println(isbn);
		bookStoreDAO = new BookStoreDAO();
		BookBean bb = bookStoreDAO.getBooksByID(isbn);
		System.out.println("\n"+bb.getBook_title());
		request.setAttribute("Book", bb);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AddBook.jsp");
		
		dispatcher.forward(request, response);
	}
	public void UpdateBookInfos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		System.out.println("\n"+request.getParameter("BookTitle"));
		System.out.println("\n"+request.getParameter("des"));
		System.out.println("\n"+request.getParameter("price"));
		System.out.println("\n"+request.getParameter("authorID"));
		System.out.println("\n"+request.getParameter("page"));
		System.out.println("\n"+request.getParameter("CategoryID"));
		
		
		String[] arrOfStr = request.getParameter("ReleaseDate").split("-");
		
		// create a Calendar object
		Calendar calendaryy = Calendar.getInstance();
		// set the calendar object to the date
		calendaryy.set(Calendar.YEAR,Integer.parseInt(arrOfStr[0]));
		calendaryy.set(Calendar.MONTH,(Integer.parseInt(arrOfStr[1])-1));
		calendaryy.set(Calendar.DAY_OF_MONTH,Integer.parseInt(arrOfStr[2]));



		
		
		String title = request.getParameter("BookTitle");
		String Desc = request.getParameter("des");
		float price = Float.parseFloat(request.getParameter("price"));
		int authorID = Integer.parseInt(request.getParameter("authorID"));
		int pages = Integer.parseInt(request.getParameter("page"));
		int CatID = Integer.parseInt(request.getParameter("CategoryID"));
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		
		
		System.out.println("In do post method of Add Image Servlet");
		Part file = request.getPart("picture");
		String imageFileName = file.getSubmittedFileName(); 
		System.out.println("Selected Image File Name: " + imageFileName);
		String uploadPath = "D:/Java Eclipse for web dev/BookStore/src/main/webapp/BookImages/"+imageFileName;
		System.out.println("Uploaded Path :"+uploadPath);
		
		try {
		
		FileOutputStream fos = new FileOutputStream(uploadPath);
		InputStream is = file.getInputStream();
		
		byte[] data = new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//then we gonna send a data as parameters in class bean
		BookBean bookBean = new BookBean(isbn,title,authorID,calendaryy,pages,price,Desc,imageFileName,CatID);
		bookStoreDAO = new BookStoreDAO();
		int adminID = Integer.parseInt(request.getParameter("AdminID"));
		System.out.println("\nadmin id:"+adminID);
		
		
		Calendar calendar2 = Calendar.getInstance();
    	// Get the current instant in UTC time 
    	Instant instant = Instant.now(); //=> this will get time like 2022-12-29T18:24:22.701255800Z
    	// this will get the time zone id from the system and will send it to the next object to get the time
    	ZoneId localZoneId = ZoneId.systemDefault(); //=> this will get Africa/Algiers
    	// Convert the instant to the local time zone 
    	ZonedDateTime localTime = instant.atZone(localZoneId);// this will get time like 2022-12-29T19:24:22.701255800+01:00[Africa/Algiers]
    	calendar2.setTimeZone(TimeZone.getTimeZone(localZoneId)); 
    	calendar2.setTimeInMillis(localTime.toInstant().toEpochMilli());// and this will hold the whole information

		
		
		
		
		System.out.println("Updating Book Status : "+bookStoreDAO.UpdateBookByISBN(bookBean,new ManageBookBean(adminID,"UPDATE",calendar2)));
		//later will dispatcher or recreate of whatever we want to do below:
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
		

	}
	protected void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		bookStoreDAO = new BookStoreDAO();
		int Clientid = Integer.parseInt(request.getParameter("id"));
		System.out.println("the row with id "+Clientid+" has been deleted :"+bookStoreDAO.deleteClient(Clientid));
    	AdminBean admin = new AdminBean();
    	HttpSession session = request.getSession();
    	admin = (AdminBean) session.getAttribute("AdminInfo");
    	Calendar calendar = Calendar.getInstance();
    	// Get the current instant in UTC time 
    	Instant instant = Instant.now(); //=> this will get time like 2022-12-29T18:24:22.701255800Z
    	// this will get the time zone id from the system and will send it to the next object to get the time
    	ZoneId localZoneId = ZoneId.systemDefault(); //=> this will get Africa/Algiers
    	// Convert the instant to the local time zone 
    	ZonedDateTime localTime = instant.atZone(localZoneId);// this will get time like 2022-12-29T19:24:22.701255800+01:00[Africa/Algiers]
    	calendar.setTimeZone(TimeZone.getTimeZone(localZoneId)); 
    	calendar.setTimeInMillis(localTime.toInstant().toEpochMilli());// and this will hold the whole information

		bookStoreDAO.insertManageClient(Clientid, new ManageClientBean(admin.getAdmin_id(),"DELETE",calendar));
		
		//later will dispatcher or recreate of whatever we want to do below:
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
	}
	public void LogoutAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("have_an_AccountA");
		session.removeAttribute("AdminInfo");
	}
	public void LogoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("have_an_Account");
		session.removeAttribute("UserID");
		session.removeAttribute("UserInfo");
	}
	
	public boolean CheckLike(int isbn,int idC){
		return new BookStoreDAO().CheckLikeByisbn_CID(isbn, idC);
	}
	public void ChangeLikeStatu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int isbn = Integer.parseInt(request.getParameter("isbn"));
		int idC = Integer.parseInt(request.getParameter("idC"));
		new BookStoreDAO().ChangeLike(isbn,idC);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BooksPage.jsp");
	    dispatcher.forward(request, response);
	}
}
