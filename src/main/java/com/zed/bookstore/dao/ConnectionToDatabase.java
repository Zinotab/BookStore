package com.zed.bookstore.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;




public class ConnectionToDatabase extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String DRIVER ="oracle.jdbc.driver.OracleDriver";
	private String URL ="jdbc:oracle:thin:@localhost:1521/XE";
	private String USERNAME ="system";
	private String PASSWORD ="12345";

    public ConnectionToDatabase(){
    	
    }


	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		
		DRIVER = context.getInitParameter("Driver");
		URL = context.getInitParameter("Url");
		USERNAME = context.getInitParameter("Username");
		PASSWORD = context.getInitParameter("Password");
		
		System.out.println("init(): \\nDriver: "+DRIVER+"\nUrl: "+URL+"\nUsername: "+USERNAME+"\nPassword: "+PASSWORD);

	}

	public Connection getDatabaseConnection(){	
		Connection connection = null;
//		System.out.println("\ngetDatabaseConnection(): \nDriver: "+DRIVER+"\nUrl: "+URL+"\nUsername: "+USERNAME+"\nPassword: "+PASSWORD+"\n");
		
		try {
			
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	

}
