package com.zed.bookstore.dao;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.zed.bookstore.model.AdminBean;
import com.zed.bookstore.model.AuthorBean;
import com.zed.bookstore.model.BookBean;
import com.zed.bookstore.model.CategoryBean;
import com.zed.bookstore.model.ClientBean;
import com.zed.bookstore.model.CommandBean;
import com.zed.bookstore.model.LikedBookBean;
import com.zed.bookstore.model.ManageAuthorBean;
import com.zed.bookstore.model.ManageBookBean;
import com.zed.bookstore.model.ManageClientBean;


public class BookStoreDAO {
	
	static int Authors;
	static int Books;
	static int Clients;
	static int MB;
	static int MA;
	static int MCL;
	static int MCMD;
	static CategoryBean category;
	static AuthorBean Author;
	static ClientBean clientbean;
	static AdminBean adminBean;
	static BookBean bookBean;
	public BookStoreDAO() {
		super();
	}
	
	
	
	public BookBean getBooksByID(int isbn){




		bookBean =null ;
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement;
						preparedStatement = connection.prepareStatement("SELECT b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.PICTURE,b.CATEGORY_ID FROM BOOKSTORE.BOOK b WHERE b.ISBN = ? ");
						System.out.println(preparedStatement);
						preparedStatement.setInt(1,isbn);
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						// Step 4: Process the ResultSet object.
						while (rs.next()) {
							int ISBN = rs.getInt(1);
							String TITLE = rs.getString(2);
							int AUTHOR_ID = rs.getInt(3);
							Date RELEASED = rs.getDate(4);
							System.out.println(RELEASED);
							int PAGE = rs.getInt(5);
							float PRICE = rs.getFloat(6);
							String BOOK_DESC = rs.getString(7);
							String PICTURE = rs.getString(8);
							int CATEGORY_ID = rs.getInt(9);
							
							
							// create a Calendar object
							Calendar calendar = Calendar.getInstance();
							// set the calendar object to the date
							calendar.setTime(RELEASED);
							// create instance of BookBean to store data and add it to the list
							bookBean = new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return bookBean;
	}
	public boolean addNewBook(BookBean bookBean,ManageBookBean manager) {

           boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_BOOK_SQL.Query);
			int isbn = (getMaxISBNNumberOfBooks()+1);
			preparedStatement.setInt(1,isbn );
			preparedStatement.setString(2, bookBean.getBook_title());
			preparedStatement.setInt(3, bookBean.getAuthor_id());
			preparedStatement.setDate(4, new java.sql.Date(bookBean.getReleaseDate().getTimeInMillis()));
			preparedStatement.setInt(5, bookBean.getBook_pages());
			preparedStatement.setFloat(6, bookBean.getBook_price());
			preparedStatement.setString(7, bookBean.getBook_description());
			preparedStatement.setString(8, bookBean.getBook_picture());
			preparedStatement.setInt(9, bookBean.getCat_id());
			
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
			
			insertManageBook(isbn,manager);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
	}
	public boolean insertManageBook(int isbn,ManageBookBean manager) {

        boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_MANAGEBOOK_SQL.Query);
			
			preparedStatement.setInt(1,(getMaxISBNNumberOfManageBook()+1));
			preparedStatement.setInt(2,manager.getAdmin_id() );
			preparedStatement.setInt(3,isbn);
			preparedStatement.setString(4,manager.getStatueB());
			
	    	
			
			preparedStatement.setDate(5, new java.sql.Date(manager.getEditDate().getTimeInMillis()));
			
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
	}
	
	
	public int getMaxISBNNumberOfManageBook(){


		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();

		MB = 0;
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT MAX(MB) FROM BOOKSTORE.MANAGEBOOK");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				MB = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return MB;
	}
	public int getMaxISBNNumberOfManageAuthor(){


		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();

		MA = 0;
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT MAX(MA) FROM BOOKSTORE.MANAGEAUTHOR");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				MA = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return MA;
	}
	public int getMaxISBNNumberOfManageCommand(){


		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();

		MCMD = 0;
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT MAX(MCMD) FROM BOOKSTORE.MANAGECOMMAND");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				MCMD = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return MCMD;
	}
	public int getMaxISBNNumberOfManageClient(){


		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();

		MCL = 0;
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT MAX(MCL) FROM BOOKSTORE.MANAGECLIENT");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				MCL = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return MCL;
	}
	
	
	
	
	
	
	
	public int getMaxISBNNumberOfBooks(){


		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();

		Books = 0;
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT MAX(ISBN) FROM BOOKSTORE.BOOK");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
			Books = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return Books;
	}
	public int getMaxISBNNumberOfAuthors(){


		Authors = 0;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT MAX(AUTH_ID) FROM BOOKSTORE.AUTHOR");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Authors = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return Authors;
	}
	public List<BookBean> getAllBooks() {


		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_BOOKS.Query);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int ISBN = rs.getInt(1);
					String TITLE = rs.getString(2);
					int AUTHOR_ID = rs.getInt(3);
					Date RELEASED = rs.getDate(4);
					int PAGE = rs.getInt(5);
					float PRICE = rs.getFloat(6);
					String BOOK_DESC = rs.getString(7);
					String PICTURE = rs.getString(8);
					int CATEGORY_ID = rs.getInt(9);
					
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(RELEASED);
					// create instance of BookBean to store data and add it to the list
					books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return books;
	}
	public boolean deleteBookByID(int ISBN) {
		boolean rowDeleted = false;
		
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("DELETE FROM BOOKSTORE.BOOK WHERE ISBN = ?");
			statement.setInt(1, ISBN);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rowDeleted;
	}
	public List<BookBean> getLatestBooks(int numberOfTheBooks){
		
		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT ISBN, TITLE, AUTHOR_ID,RELEASED,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID FROM (SELECT ISBN, TITLE, AUTHOR_ID,RELEASED,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID FROM BOOKSTORE.BOOK ORDER BY RELEASED DESC) WHERE ROWNUM <= ?");
				System.out.println(preparedStatement);
				preparedStatement.setInt(1, numberOfTheBooks);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int ISBN = rs.getInt(1);
					String TITLE = rs.getString(2);
					int AUTHOR_ID = rs.getInt(3);
					Date RELEASED = rs.getDate(4);
					System.out.println(RELEASED);
					int PAGE = rs.getInt(5);
					float PRICE = rs.getFloat(6);
					String BOOK_DESC = rs.getString(7);
					String PICTURE = rs.getString(8);
					int CATEGORY_ID = rs.getInt(9);
					
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(RELEASED);
					// create instance of BookBean to store data and add it to the list
					books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return books;
	}
	public int getNumberOfBooks(){



		Books = 0;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT COUNT(ISBN) FROM BOOKSTORE.BOOK");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
			Books = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return Books;
	}
	public List<BookBean> getMostLikedBooks(int numberOfRows){



		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT T.ISBN, T.TITLE ,T.AUTHOR_ID,T.RELEASED,T.PAGE,T.PRICE,T.BOOK_DESC, BBB.PICTURE,T.CATEGORY_ID , T.LIKES FROM (SELECT b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.CATEGORY_ID , count(lb.CL_ID) AS LIKES FROM BOOKSTORE.BOOK b ,BOOKSTORE.LIKEDBOOKS lb WHERE b.ISBN = lb.BOOK_ID GROUP BY b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.CATEGORY_ID ORDER BY LIKES DESC) T , BOOKSTORE.BOOK BBB WHERE ROWNUM <= ? AND BBB.ISBN = T.ISBN");
				System.out.println(preparedStatement);
				preparedStatement.setInt(1, numberOfRows);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int ISBN = rs.getInt(1);
					String TITLE = rs.getString(2);
					int AUTHOR_ID = rs.getInt(3);
					Date RELEASED = rs.getDate(4);
					System.out.println(RELEASED);
					int PAGE = rs.getInt(5);
					float PRICE = rs.getFloat(6);
					String BOOK_DESC = rs.getString(7);
					String PICTURE = rs.getString(8);
					int CATEGORY_ID = rs.getInt(9);
					int likes = rs.getInt(10);
					
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(RELEASED);
					// create instance of BookBean to store data and add it to the list
					books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID,likes));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return books;
	}
	public List<BookBean> getBooksByName(String Title){


		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement;
						preparedStatement = connection.prepareStatement("SELECT b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.PICTURE,b.CATEGORY_ID FROM BOOKSTORE.BOOK b WHERE b.title LIKE  ? ");
						System.out.println(preparedStatement);
						preparedStatement.setString(1,"%"+Title+"%");
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						// Step 4: Process the ResultSet object.
						while (rs.next()) {
							int ISBN = rs.getInt(1);
							String TITLE = rs.getString(2);
							int AUTHOR_ID = rs.getInt(3);
							Date RELEASED = rs.getDate(4);
							System.out.println(RELEASED);
							int PAGE = rs.getInt(5);
							float PRICE = rs.getFloat(6);
							String BOOK_DESC = rs.getString(7);
							String PICTURE = rs.getString(8);
							int CATEGORY_ID = rs.getInt(9);
							
							
							// create a Calendar object
							Calendar calendar = Calendar.getInstance();
							// set the calendar object to the date
							calendar.setTime(RELEASED);
							// create instance of BookBean to store data and add it to the list
							books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return books;
	}
	public List<LikedBookBean> getBookLikes(){



		
		List<LikedBookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
						// Step 2:Create a statement using connection object
						Statement statement;
						statement = connection.createStatement();
						System.out.println(statement);
						// Step 3: Execute the query or update query
						ResultSet rs = statement.executeQuery("SELECT CL_ID,BOOK_ID FROM BOOKSTORE.LIKEDBOOKS");
						// Step 4: Process the ResultSet object.
						while (rs.next()) {
							int cl_id = rs.getInt(1);
							int book_id = rs.getInt(2);
							
							books.add(new LikedBookBean(cl_id, book_id));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return books;
	}
	public List<BookBean> getBestSellingBooks(int numberOfRows){



		
		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT T.ISBN, T.TITLE ,T.AUTHOR_ID,T.RELEASED,T.PAGE,T.PRICE,T.BOOK_DESC, BBB.PICTURE,T.CATEGORY_ID , T.COMMANDS FROM (SELECT b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.CATEGORY_ID , count(CMD.CMD_ID) AS COMMANDS FROM BOOKSTORE.BOOK b ,BOOKSTORE.COMMAND CMD WHERE b.ISBN = CMD.BOOK_ID GROUP BY b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.CATEGORY_ID ORDER BY COMMANDS DESC) T, BOOKSTORE.BOOK BBB WHERE ROWNUM <= ? AND BBB.ISBN = T.ISBN");
				System.out.println(preparedStatement);
				preparedStatement.setInt(1, numberOfRows);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int ISBN = rs.getInt(1);
					String TITLE = rs.getString(2);
					int AUTHOR_ID = rs.getInt(3);
					Date RELEASED = rs.getDate(4);
					System.out.println(RELEASED);
					int PAGE = rs.getInt(5);
					float PRICE = rs.getFloat(6);
					String BOOK_DESC = rs.getString(7);
					String PICTURE = rs.getString(8);
					int CATEGORY_ID = rs.getInt(9);
					int COMANDS = rs.getInt(10);
					
					
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(RELEASED);
					// create instance of BookBean to store data and add it to the list
					books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID,COMANDS,0));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return books;
	}
	public CategoryBean getCategoriesById(int cat_id){
		category = null;
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement;
					preparedStatement = connection.prepareStatement("SELECT CATNAME ,CATDESC FROM BOOKSTORE.CATEGORY WHERE CAT_ID = ?");
//					System.out.println(preparedStatement);
					preparedStatement.setInt(1, cat_id);
					// Step 3: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();
					// Step 4: Process the ResultSet object.
					while (rs.next()) {
							String CATEGORY_NAME = rs.getString(1);
							String DESCRIPTION = rs.getString(2);
							
							category = new CategoryBean(cat_id,CATEGORY_NAME,DESCRIPTION);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return category;
	}
	public List<BookBean> getBooksByCategoryName(String CategoryName){


		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement;
						preparedStatement = connection.prepareStatement("SELECT b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.PICTURE,b.CATEGORY_ID FROM BOOKSTORE.BOOK b , BOOKSTORE.CATEGORY C WHERE C.CATNAME LIKE  ?  AND C.CAT_ID = b.CATEGORY_ID");
						System.out.println(preparedStatement);
						preparedStatement.setString(1,"%"+CategoryName+"%");
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						// Step 4: Process the ResultSet object.
						while (rs.next()) {
							int ISBN = rs.getInt(1);
							String TITLE = rs.getString(2);
							int AUTHOR_ID = rs.getInt(3);
							Date RELEASED = rs.getDate(4);
							System.out.println(RELEASED);
							int PAGE = rs.getInt(5);
							float PRICE = rs.getFloat(6);
							String BOOK_DESC = rs.getString(7);
							String PICTURE = rs.getString(8);
							int CATEGORY_ID = rs.getInt(9);
							
							
							// create a Calendar object
							Calendar calendar = Calendar.getInstance();
							// set the calendar object to the date
							calendar.setTime(RELEASED);
							// create instance of BookBean to store data and add it to the list
							books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return books;
	}
	
	public List<CategoryBean> getAllCategories(){
		
		List<CategoryBean> categories = new ArrayList<>();
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement;
						preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_CATEGORIES.Query);
						System.out.println(preparedStatement);
						
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						// Step 4: Process the ResultSet object.
						while (rs.next()) {
							
							int CATEGORY_ID = rs.getInt(1);
							String CATEGORY_Name = rs.getString(2);
							String CATEGORY_desc = rs.getString(3);
							categories.add(new CategoryBean(CATEGORY_ID,CATEGORY_Name,CATEGORY_desc));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return categories;
	}
	
	public List<BookBean> getMostExpensiveBooks(int numberOfRows){


		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.PICTURE,b.CATEGORY_ID FROM BOOKSTORE.BOOK b WHERE ROWNUM <= ? ORDER BY b.PRICE DESC");
				System.out.println(preparedStatement);
				preparedStatement.setInt(1, numberOfRows);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int ISBN = rs.getInt(1);
					String TITLE = rs.getString(2);
					int AUTHOR_ID = rs.getInt(3);
					Date RELEASED = rs.getDate(4);
					System.out.println(RELEASED);
					int PAGE = rs.getInt(5);
					float PRICE = rs.getFloat(6);
					String BOOK_DESC = rs.getString(7);
					String PICTURE = rs.getString(8);
					int CATEGORY_ID = rs.getInt(9);
					
					
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(RELEASED);
					// create instance of BookBean to store data and add it to the list
					books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return books;
	}
	public List<BookBean> getBooksWithTheMostNumberOfPages(int numberOfRows){



		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.PICTURE,b.CATEGORY_ID FROM BOOKSTORE.BOOK b WHERE ROWNUM <= ? ORDER BY b.PAGE DESC");
				System.out.println(preparedStatement);
				preparedStatement.setInt(1, numberOfRows);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int ISBN = rs.getInt(1);
					String TITLE = rs.getString(2);
					int AUTHOR_ID = rs.getInt(3);
					Date RELEASED = rs.getDate(4);
					System.out.println(RELEASED);
					int PAGE = rs.getInt(5);
					float PRICE = rs.getFloat(6);
					String BOOK_DESC = rs.getString(7);
					String PICTURE = rs.getString(8);
					int CATEGORY_ID = rs.getInt(9);
					
					
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(RELEASED);
					// create instance of BookBean to store data and add it to the list
					books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return books;
	}
	
	
	public boolean UpdateBookByISBN(BookBean bookBean,ManageBookBean MBB) {



		boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE BOOKSTORE.BOOK SET TITLE = ? , AUTHOR_ID = ?,RELEASED = ?,PAGE = ?,PRICE = ?,BOOK_DESC = ?,CATEGORY_ID = ?,PICTURE =? where ISBN = ?");
			
			preparedStatement.setInt(9, bookBean.getISBN());
			
			preparedStatement.setString(1, bookBean.getBook_title());
			preparedStatement.setInt(2, bookBean.getAuthor_id());
			preparedStatement.setDate(3, new java.sql.Date(bookBean.getReleaseDate().getTimeInMillis()));
			preparedStatement.setInt(4, bookBean.getBook_pages());
			preparedStatement.setFloat(5, bookBean.getBook_price());
			preparedStatement.setString(6, bookBean.getBook_description());
			preparedStatement.setInt(7, bookBean.getCat_id());
			preparedStatement.setString(8, bookBean.getBook_picture());
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
			insertManageBook(bookBean.getISBN(),MBB);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
		
	}
	public boolean UpdateAuthorByid(AuthorBean authorBean,ManageAuthorBean MAB) {


		boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE BOOKSTORE.AUTHOR SET AUFNAME = ? , AULNAME = ?,AUBIRTHD = ?,AUCOUNTRY = ?,AUBIOGRAPHY = ?,AUFULLNAME = ?,AUFULLNAMEREV = ?,AUPICTURE =? where AUTH_ID = ?");
			
			preparedStatement.setInt(9, authorBean.getAuthor_id());
			
			preparedStatement.setString(1, authorBean.getAuthor_first_name());
			preparedStatement.setString(2, authorBean.getAuthor_last_name());
			preparedStatement.setDate(3, new java.sql.Date(authorBean.getAuthor_birth_day().getTimeInMillis()));
			preparedStatement.setString(4, authorBean.getAuthor_country());
			preparedStatement.setString(5, authorBean.getAuthor_BIOGRAPHY());
			preparedStatement.setString(6, authorBean.getAuthor_first_name()+" "+authorBean.getAuthor_last_name());
			preparedStatement.setString(7, authorBean.getAuthor_last_name()+" "+authorBean.getAuthor_first_name());
			preparedStatement.setString(8, authorBean.getAuthor_picture());
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
			
			insertManageAuthor(authorBean.getAuthor_id(),MAB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
		
	}
	
	
	public AuthorBean getAuthorById(int author_id){

		Author = null;
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement;
					preparedStatement = connection.prepareStatement("SELECT AUFNAME ,AULNAME,AUBIRTHD,AUCOUNTRY,AUBIOGRAPHY,AUPICTURE FROM BOOKSTORE.AUTHOR WHERE AUTH_ID = ?");
//					System.out.println(preparedStatement);
					preparedStatement.setInt(1, author_id);
					// Step 3: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();
					// Step 4: Process the ResultSet object.
					while (rs.next()) {
							String AuthorFirstName = rs.getString(1);
							String AuthorLastName = rs.getString(2);
							Date BirthDate = rs.getDate(3);
							String Country = rs.getString(4);
							
							
							// create a Calendar object
							Calendar calendar = Calendar.getInstance();
							// set the calendar object to the date
							calendar.setTime(BirthDate);
							String BIOGRAPHY = rs.getString(5);
							String picture = rs.getString(6);
							Author = new AuthorBean(author_id,AuthorFirstName,AuthorLastName,calendar,Country,picture,BIOGRAPHY);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return Author;
	}
	public List<BookBean> getBooksByAuthorName(String AuthorName){



		
		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement;
						preparedStatement = connection.prepareStatement("SELECT b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.PICTURE,b.CATEGORY_ID FROM BOOKSTORE.BOOK b , BOOKSTORE.AUTHOR A WHERE A.AUTH_ID = b.AUTHOR_ID AND A.AUFNAME LIKE  ?  OR A.AULNAME LIKE  ? ");
						System.out.println(preparedStatement);
						preparedStatement.setString(1,"%"+AuthorName+"%");
						preparedStatement.setString(2,"%"+AuthorName+"%");
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						// Step 4: Process the ResultSet object.
						while (rs.next()) {
							int ISBN = rs.getInt(1);
							String TITLE = rs.getString(2);
							int AUTHOR_ID = rs.getInt(3);
							Date RELEASED = rs.getDate(4);
							System.out.println(RELEASED);
							int PAGE = rs.getInt(5);
							float PRICE = rs.getFloat(6);
							String BOOK_DESC = rs.getString(7);
							String PICTURE = rs.getString(8);
							int CATEGORY_ID = rs.getInt(9);
							
							
							// create a Calendar object
							Calendar calendar = Calendar.getInstance();
							// set the calendar object to the date
							calendar.setTime(RELEASED);
							// create instance of BookBean to store data and add it to the list
							books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return books;
	}
	public List<BookBean> getBooksByAuthorID(int id){



		
		List<BookBean> books = new ArrayList<>();
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement;
						preparedStatement = connection.prepareStatement("SELECT b.ISBN, b.TITLE ,b.AUTHOR_ID,b.RELEASED,b.PAGE,b.PRICE,b.BOOK_DESC,b.PICTURE,b.CATEGORY_ID FROM BOOKSTORE.BOOK b WHERE b.AUTHOR_ID =?");
						System.out.println(preparedStatement);
						preparedStatement.setInt(1,id);
						
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						// Step 4: Process the ResultSet object.
						while (rs.next()) {
							int ISBN = rs.getInt(1);
							String TITLE = rs.getString(2);
							int AUTHOR_ID = rs.getInt(3);
							Date RELEASED = rs.getDate(4);
							System.out.println(RELEASED);
							int PAGE = rs.getInt(5);
							float PRICE = rs.getFloat(6);
							String BOOK_DESC = rs.getString(7);
							String PICTURE = rs.getString(8);
							int CATEGORY_ID = rs.getInt(9);
							
							
							// create a Calendar object
							Calendar calendar = Calendar.getInstance();
							// set the calendar object to the date
							calendar.setTime(RELEASED);
							// create instance of BookBean to store data and add it to the list
							books.add(new BookBean(ISBN,TITLE,AUTHOR_ID,calendar,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return books;
	}
	public List<AuthorBean> getAuthorsByName(String Name){



		List<AuthorBean> authorslist = new ArrayList<>();
		// Step 1: Establishing a Connection
				ConnectionToDatabase con = new ConnectionToDatabase();
				Connection connection = con.getDatabaseConnection();
				
				try {
						// Step 2:Create a statement using connection object
						PreparedStatement preparedStatement;
						preparedStatement = connection.prepareStatement("SELECT AUTH_ID,AUFNAME ,AULNAME,AUBIRTHD,AUCOUNTRY,AUBIOGRAPHY,AUPICTURE FROM BOOKSTORE.AUTHOR WHERE AUFNAME LIKE  ? OR AULNAME LIKE ? OR AUFULLNAME LIKE ? OR AUFULLNAMEREV LIKE ?");
						System.out.println(preparedStatement);
						preparedStatement.setString(1,"%"+Name+"%");
						preparedStatement.setString(2,"%"+Name+"%");
						preparedStatement.setString(3,"%"+Name+"%");
						preparedStatement.setString(4,"%"+Name+"%");
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						// Step 4: Process the ResultSet object.
						while (rs.next()) {
							int Author_id = rs.getInt(1);
							String AuthorFirstName = rs.getString(2);
							String AuthorLastName = rs.getString(3);
							Date BirthDate = rs.getDate(4);
							String Country = rs.getString(5);
							String BIOGRAPHY = rs.getString(6);
							String picture = rs.getString(7);
							// create a Calendar object
							Calendar calendar = Calendar.getInstance();
							// set the calendar object to the date
							calendar.setTime(BirthDate);
							
							
							authorslist.add(new AuthorBean(Author_id,AuthorFirstName,AuthorLastName,calendar,Country,picture,BIOGRAPHY));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
		return authorslist;
	}
	public List<AuthorBean> getAuthorWithMostBook(int numberOfRows){


		List<AuthorBean> Authors = new ArrayList<>();
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT T.AUTH_ID,T.AUFNAME,T.AULNAME,T.AUBIRTHD,T.AUCOUNTRY,BBB.AUPICTURE,T.BOOKSN FROM( SELECT A.AUTH_ID,A.AUFNAME,A.AULNAME,A.AUBIRTHD,A.AUCOUNTRY, COUNT(B.AUTHOR_ID) AS BOOKSN FROM BOOKSTORE.AUTHOR A, BOOKSTORE.BOOK B WHERE B.AUTHOR_ID = A.AUTH_ID GROUP BY A.AUTH_ID,A.AUFNAME,A.AULNAME,A.AUBIRTHD,A.AUCOUNTRY ORDER BY BOOKSN DESC) T , BOOKSTORE.AUTHOR BBB WHERE ROWNUM <= ? AND BBB.AUTH_ID = T.AUTH_ID");
				System.out.println(preparedStatement);
				preparedStatement.setInt(1, numberOfRows);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int Author_id = rs.getInt(1);
					String AuthorFirstName = rs.getString(2);
					String AuthorLastName = rs.getString(3);
					Date BirthDate = rs.getDate(4);
					String Country = rs.getString(5);
					String PICTURE = rs.getString(6);
					int NumberOfBooks = rs.getInt(7);
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(BirthDate);
					
					
					Authors.add(new AuthorBean(Author_id,AuthorFirstName,AuthorLastName,calendar,Country,PICTURE,NumberOfBooks));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return Authors;
	}
	public boolean addNewAuthor(AuthorBean authorBean,ManageAuthorBean MAB) {


		boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_AUTHOR_SQL.Query);
			
			int id = (getMaxISBNNumberOfAuthors()+1);
			
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, authorBean.getAuthor_first_name());
			preparedStatement.setString(3, authorBean.getAuthor_last_name());
			preparedStatement.setDate(4, new java.sql.Date(authorBean.getAuthor_birth_day().getTimeInMillis()));
			preparedStatement.setString(5, authorBean.getAuthor_country());
			preparedStatement.setString(6, authorBean.getAuthor_BIOGRAPHY());
			preparedStatement.setString(7, authorBean.getAuthor_first_name()+" "+authorBean.getAuthor_last_name());
			preparedStatement.setString(8, authorBean.getAuthor_last_name()+" "+authorBean.getAuthor_first_name());
			preparedStatement.setString(9, authorBean.getAuthor_picture());
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
			insertManageAuthor(id,MAB);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
	}
	
	public boolean insertManageAuthor(int id,ManageAuthorBean MAB) {


        boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_MANAGEAUTHOR_SQL.Query);
			
			preparedStatement.setInt(1,(getMaxISBNNumberOfManageAuthor()+1));
			preparedStatement.setInt(2,MAB.getAdmin_id() );
			preparedStatement.setInt(3,id);
			preparedStatement.setString(4,MAB.getStatueA());
			
	    	
			
			preparedStatement.setDate(5, new java.sql.Date(MAB.getEditDate().getTimeInMillis()));
			
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
	}
	
	
	
	public List<AuthorBean> getAllAuthors() {


		List<AuthorBean> authors = new ArrayList<>();
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_AUTHORS.Query);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int Author_id = rs.getInt(1);
					String AuthorFirstName = rs.getString(2);
					String AuthorLastName = rs.getString(3);
					Date BirthDate = rs.getDate(4);
					String Country = rs.getString(5);
					String PICTURE = rs.getString(6);
					
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(BirthDate);
					String AUBIOGRAPHY = rs.getString(7);

					authors.add(new AuthorBean(Author_id,AuthorFirstName,AuthorLastName,calendar,Country,PICTURE,AUBIOGRAPHY));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return authors;
	}
	public boolean deleteAuthorByID(int Author_id) {

		boolean rowDeleted = false;
		
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("DELETE FROM BOOKSTORE.AUTHOR WHERE AUTH_ID = ?");
			statement.setInt(1, Author_id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rowDeleted;
	}
	public int getMaxNumberOfClients(){



		Clients = 0;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT MAX(CL_ID) FROM BOOKSTORE.CLIENT");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Clients = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return Clients;
	}
	
	public boolean addNewClient(ClientBean clientBean) {


        boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_CLIENT_SQL.Query);

			preparedStatement.setInt(1, (getMaxNumberOfClients()+1));
			preparedStatement.setString(2, clientBean.getClient_first_name());
			preparedStatement.setString(3, clientBean.getClient_last_name());
			preparedStatement.setDate(4, new java.sql.Date(clientBean.getClient_birth_day().getTimeInMillis()));
			preparedStatement.setString(5, clientBean.getClient_telephone());
			preparedStatement.setString(6, clientBean.getClient_Email());
			preparedStatement.setString(7, clientBean.getClient_Password());
			preparedStatement.setString(8, clientBean.getClient_picture());
			preparedStatement.setString(9, clientBean.getClient_review());
			
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
	}
	public int getNumberOfClients(){



		Clients = 0;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT COUNT(CL_ID) FROM BOOKSTORE.CLIENT");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Clients = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return Clients;
	}
	public int getNumberOfAuthors(){


		Authors = 0;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("SELECT COUNT(AUTH_ID) FROM BOOKSTORE.AUTHOR");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Authors = rs.getInt(1);
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return Authors;
	}
	
	public boolean setClientReviewOnTheWebSite(ClientBean clientBean) {


		boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE BOOKSTORE.CLIENT SET CL_REVIEW = ? where CL_ID = ?");

			preparedStatement.setString(1, clientBean.getClient_review());
			preparedStatement.setInt(2, clientBean.getClient_id());
			
			
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
		
	}
	public List<ClientBean> getAllClients() {

		List<ClientBean> clients = new ArrayList<>();
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_CLIENTS.Query);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					
					int CL_ID = rs.getInt(1);
					String CLFNAME = rs.getString(2);
					String CLLNAME = rs.getString(3);
					Date BirthDate = rs.getDate(4);
					String TEL = rs.getString(5);
					String EMAIL = rs.getString(6);
					String PASSWORD = rs.getString(7);
					String PICTURE = rs.getString(8);
					String REVIEW = rs.getString(9);
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(BirthDate);
					
					
					clients.add(new ClientBean(CL_ID,CLFNAME,CLLNAME,calendar,TEL,EMAIL,PASSWORD,PICTURE,REVIEW));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return clients;
	}
	public ClientBean getUserByPE(ClientBean clientBean) {


		clientbean = null;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT CL_ID,CLFNAME,CLLNAME,CLBIRTHD,TEL,CLEMAIL,CLPASSWORD,CLPICTURE,CL_REVIEW FROM BOOKSTORE.CLIENT WHERE CLEMAIL = ? AND CLPASSWORD = ?");
				System.out.println(preparedStatement);
				preparedStatement.setString(1, clientBean.getClient_Email());
				preparedStatement.setString(2, clientBean.getClient_Password());
				
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					
					int CL_ID = rs.getInt(1);
					String CLFNAME = rs.getString(2);
					String CLLNAME = rs.getString(3);
					Date BirthDate = rs.getDate(4);
					String TEL = rs.getString(5);
					String EMAIL = rs.getString(6);
					String PASSWORD = rs.getString(7);
					String PICTURE = rs.getString(8);
					String REVIEW = rs.getString(9);
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(BirthDate);
					
					
					clientbean = new ClientBean(CL_ID,CLFNAME,CLLNAME,calendar,TEL,EMAIL,PASSWORD,PICTURE,REVIEW);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		
		return clientbean;
		
	}
	public boolean UpdatePictureForClient(ClientBean clientBean) {


		boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE BOOKSTORE.CLIENT SET CLPICTURE = ? where CL_ID = ?");

			preparedStatement.setString(1, clientBean.getClient_picture());
			preparedStatement.setInt(2, clientBean.getClient_id());
			
			
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
	}
	public ClientBean getUserByID(ClientBean clientBean) {


		clientbean = null;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT CL_ID,CLFNAME,CLLNAME,CLBIRTHD,TEL,CLEMAIL,CLPASSWORD,CLPICTURE,CL_REVIEW FROM BOOKSTORE.CLIENT WHERE CL_ID = ?");
				System.out.println(preparedStatement);
				preparedStatement.setInt(1, clientBean.getClient_id());
				
				
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					
					int CL_ID = rs.getInt(1);
					String CLFNAME = rs.getString(2);
					String CLLNAME = rs.getString(3);
					Date BirthDate = rs.getDate(4);
					String TEL = rs.getString(5);
					String EMAIL = rs.getString(6);
					String PASSWORD = rs.getString(7);
					String PICTURE = rs.getString(8);
					String REVIEW = rs.getString(9);
					// create a Calendar object
					Calendar calendar = Calendar.getInstance();
					// set the calendar object to the date
					calendar.setTime(BirthDate);
					
					
					clientbean = new ClientBean(CL_ID,CLFNAME,CLLNAME,calendar,TEL,EMAIL,PASSWORD,PICTURE,REVIEW);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		
		return clientbean;
		
	}
	
	public AdminBean getAdminByPE(AdminBean adminbean) {
		adminBean = null;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("SELECT AD_ID,AFNAME,ALNAME,AEMAIL,APASSWORD FROM BOOKSTORE.ADMIN WHERE AEMAIL = ? AND APASSWORD = ?");
				System.out.println(preparedStatement);
				preparedStatement.setString(1, adminbean.getAdmin_Email());
				preparedStatement.setString(2, adminbean.getAdmin_Password());
				
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					
					int AD_ID = rs.getInt(1);
					String ADFNAME = rs.getString(2);
					String ADLNAME = rs.getString(3);
					String EMAIL = rs.getString(4);
					String PASSWORD = rs.getString(5);
					
					adminBean = new AdminBean(AD_ID,ADFNAME,ADLNAME,EMAIL,PASSWORD);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		
		return adminBean;
		
	}
	
	public int getNubmerOfCommands() {
		int rows = 0;
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				Statement statement;
				statement = connection.createStatement();
				System.out.println(statement);
				// Step 3: Execute the query or update query
				ResultSet rs = statement.executeQuery("SELECT count(CMD_ID) FROM BOOKSTORE.COMMAND");
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					rows = rs.getInt(1);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return rows;
	}
	public boolean addNewCommand(CommandBean command) {

		boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_COMMAND_SQL.Query);
			
			preparedStatement.setInt(1, (getNubmerOfCommands()+1));
			preparedStatement.setInt(2, command.getCL_id());
			preparedStatement.setInt(3, command.getBook_id());
			preparedStatement.setDate(4, new java.sql.Date(command.getCommand_Date().getTimeInMillis()));

			
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
	}
	public int UpdateCategoryTable(List<CategoryBean> categories) {



		int rowAffected = 0;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			Statement statement = connection.createStatement();
			boolean Statue = statement.execute("DELETE FROM BOOKSTORE.CATEGORY");
			System.out.println("\nTruncate Statement : "+Statue);
			
			for(int i = 0; i < categories.size(); i++) {   
				PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_CATEGORY_SQL.Query);
				
				preparedStatement.setInt(1,categories.get(i).getCategory_id());
				preparedStatement.setString(2,categories.get(i).getCategory_name() );
				preparedStatement.setString(3,categories.get(i).getCategory_desc() );

				
				System.out.println("\nPrepared Statement : "+preparedStatement);
				int update = preparedStatement.executeUpdate();
				boolean statue = update>0;
				System.out.println("Statue :"+statue);
				rowAffected += update;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
	}



	public boolean deleteClient(int clientid) {
		boolean rowDeleted = false;
		
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("DELETE FROM BOOKSTORE.CLIENT WHERE CL_ID = ?");
			statement.setInt(1, clientid);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rowDeleted;
	}



	public boolean insertManageClient(int clientid, ManageClientBean manageClientBean) {

        boolean rowAffected = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_MANAGECLIENT_SQL.Query);
			
			preparedStatement.setInt(1,(getMaxISBNNumberOfManageAuthor()+1));
			preparedStatement.setInt(2,manageClientBean.getAdmin_id() );
			preparedStatement.setInt(3,clientid);
			preparedStatement.setString(4,manageClientBean.getStatueC());
			
	    	
			
			preparedStatement.setDate(5, new java.sql.Date(manageClientBean.getEditDate().getTimeInMillis()));
			
			System.out.println("\nPrepared Statement : "+preparedStatement);
			
			rowAffected = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowAffected;
		
	}
	
	public boolean CheckLikeByisbn_CID(int isbn, int Cid) {

        boolean RESULT = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM BOOKSTORE.LIKEDBOOKS WHERE BOOK_ID = ? AND CL_ID = ?");
			
			preparedStatement.setInt(1,isbn);
			preparedStatement.setInt(2,Cid );
			
			
			
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				RESULT = rs.getInt(1) > 0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return RESULT;
		
	}
	public boolean ChangeLike(int isbn,int idC){


        boolean RESULT = false;
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
			
			if(!CheckLikeByisbn_CID(isbn, idC)) {
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO BOOKSTORE.LIKEDBOOKS(LIKE_ID,CL_ID,BOOK_ID) VALUES(?,?,?)");
				preparedStatement.setInt(1,(getMaxNubmerOfLIKEID()+1));
				preparedStatement.setInt(2,isbn);
				preparedStatement.setInt(3,idC );
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return RESULT;
		
	}
	public int getMaxNubmerOfLIKEID() {
		int rows = 0;
		// Step 1: Establishing a Connection
		ConnectionToDatabase con = new ConnectionToDatabase();
		Connection connection = con.getDatabaseConnection();
		
		try {
				// Step 2:Create a statement using connection object
				Statement statement;
				statement = connection.createStatement();
				System.out.println(statement);
				// Step 3: Execute the query or update query
				ResultSet rs = statement.executeQuery("SELECT MAX(LIKE_ID) FROM BOOKSTORE.LIKEDBOOKS");
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					rows = rs.getInt(1);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return rows;
	}
}
