package com.zed.bookstore.model;

import java.io.Serializable;
import java.util.Calendar;

public class AuthorBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Author_id;//	  AUTH_id INTEGER PRIMARY KEY,
	private String Author_first_name;//	  AUFname VARCHAR2(30) NOT NULL,
	private String Author_last_name;//	  AULname VARCHAR2(30) NOT NULL,
	private Calendar Author_birth_day;//	  AUBirthD DATE,
	private String Author_country;//	  AUCountry VARCHAR2(30),
	private String Author_picture;//	  AUPicture BLOB
	private int Author_Books;
	private String Author_BIOGRAPHY;
	
	public AuthorBean() {
		
	}
	public AuthorBean(String Author_first_name,String Author_last_name,Calendar Author_birth_day,String Author_country,String Author_picture) {
		this.Author_first_name = Author_first_name;
		this.Author_last_name = Author_last_name;
		this.Author_birth_day = Author_birth_day;
		this.Author_country = Author_country;
		this.Author_picture = Author_picture;
	}
	public AuthorBean(int Author_id,String Author_first_name,String Author_last_name,Calendar Author_birth_day,String Author_country,String Author_picture) {
		this.Author_id = Author_id;
		this.Author_first_name = Author_first_name;
		this.Author_last_name = Author_last_name;
		this.Author_birth_day = Author_birth_day;
		this.Author_country = Author_country;
		this.Author_picture = Author_picture;
	}
	public AuthorBean(int Author_id,String Author_first_name,String Author_last_name,String Author_country,String Author_BIOGRAPHY) {
		this.Author_id = Author_id;
		this.Author_first_name = Author_first_name;
		this.Author_last_name = Author_last_name;
		this.Author_country = Author_country;
		this.Author_BIOGRAPHY = Author_BIOGRAPHY;
	}
	public AuthorBean(int Author_id,String Author_first_name,String Author_last_name,Calendar Author_birth_day,String Author_country,String Author_picture,int Author_Books) {
		this.Author_id = Author_id;
		this.Author_first_name = Author_first_name;
		this.Author_last_name = Author_last_name;
		this.Author_birth_day = Author_birth_day;
		this.Author_country = Author_country;
		this.Author_picture = Author_picture;
		this.Author_Books = Author_Books;
	}
	public AuthorBean(int Author_id,String Author_first_name,String Author_last_name,Calendar Author_birth_day,String Author_country,String Author_picture,String Author_BIOGRAPHY) {
		this.Author_id = Author_id;
		this.Author_first_name = Author_first_name;
		this.Author_last_name = Author_last_name;
		this.Author_birth_day = Author_birth_day;
		this.Author_country = Author_country;
		this.Author_picture = Author_picture;
		
		this.Author_BIOGRAPHY = Author_BIOGRAPHY;
	}
	public int getAuthor_id() {
		return Author_id;
	}
	public void setAuthor_id(int author_id) {
		Author_id = author_id;
	}
	public String getAuthor_first_name() {
		return Author_first_name;
	}
	public void setAuthor_first_name(String author_first_name) {
		Author_first_name = author_first_name;
	}
	public String getAuthor_last_name() {
		return Author_last_name;
	}
	public void setAuthor_last_name(String author_last_name) {
		Author_last_name = author_last_name;
	}
	public Calendar getAuthor_birth_day() {
		return Author_birth_day;
	}
	public void setAuthor_birth_day(Calendar author_birth_day) {
		Author_birth_day = author_birth_day;
	}
	public String getAuthor_country() {
		return Author_country;
	}
	public void setAuthor_country(String author_country) {
		Author_country = author_country;
	}
	public String getAuthor_picture() {
		return Author_picture;
	}
	public void setAuthor_picture(String author_picture) {
		Author_picture = author_picture;
	}
	public int getAuthor_Books() {
		return Author_Books;
	}
	public void setAuthor_Books(int author_Books) {
		Author_Books = author_Books;
	}
	public String getAuthor_BIOGRAPHY() {
		return Author_BIOGRAPHY;
	}
	public void setAuthor_BIOGRAPHY(String author_BIOGRAPHY) {
		Author_BIOGRAPHY = author_BIOGRAPHY;
	}
	
}
