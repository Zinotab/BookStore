package com.zed.bookstore.model;

import java.io.Serializable;

import java.util.Calendar;


public class BookBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ISBN;//	ISBN INTEGER PRIMARY KEY,
	private String Book_title;//	title VARCHAR2(100) NOT NULL,
	private int Author_id;//	author_id INTEGER NOT NULL,
	private Calendar ReleaseDate;//	releaseD DATE NOT NULL,
	private int Book_pages;//	page INTEGER NOT NULL,
	private float Book_price;//	price FLOAT NOT NULL,
	private String Book_description;//	BOOK_DESC VARCHAR2(1200),
	private String Book_image_data;//	picture BLOB,
	private int Cat_id;//	Category_id INTEGER NOT NULL,
	private int likes;
	private int Commands;
	
	public BookBean() {
		
	}
	public BookBean(int ISBN,String Book_title,int Author_id,int Book_pages,int Cat_id) {

		this.ISBN = ISBN;
		this.Book_title = Book_title;
		this.Author_id = Author_id;
		this.Book_pages = Book_pages;
		this.Cat_id = Cat_id;

	}
	public BookBean(int ISBN,String Book_title,int Author_id,Calendar ReleaseDate,int Book_pages,float Book_price,String Book_description,String Book_image_data,int Cat_id) {
		this.ISBN = ISBN;
		this.Book_title = Book_title;
		this.Author_id = Author_id;
		this.ReleaseDate = ReleaseDate;
		this.Book_pages = Book_pages;
		this.Book_price = Book_price;
		this.Book_description = Book_description;
		this.Book_image_data = Book_image_data;
		this.Cat_id = Cat_id;
	}
	public BookBean(int ISBN,String Book_title,int Author_id,Calendar ReleaseDate,int Book_pages,float Book_price,String Book_description,String Book_image_data,int Cat_id,int likes) {
		this.ISBN = ISBN;
		this.Book_title = Book_title;
		this.Author_id = Author_id;
		this.ReleaseDate = ReleaseDate;
		this.Book_pages = Book_pages;
		this.Book_price = Book_price;
		this.Book_description = Book_description;
		this.Book_image_data = Book_image_data;
		this.Cat_id = Cat_id;
		this.likes = likes;
	}
	public BookBean(int ISBN,String Book_title,int Author_id,Calendar ReleaseDate,int Book_pages,float Book_price,String Book_description,String Book_image_data,int Cat_id,int Commands,int likes) {
		this.ISBN = ISBN;
		this.Book_title = Book_title;
		this.Author_id = Author_id;
		this.ReleaseDate = ReleaseDate;
		this.Book_pages = Book_pages;
		this.Book_price = Book_price;
		this.Book_description = Book_description;
		this.Book_image_data = Book_image_data;
		this.Cat_id = Cat_id;
		this.Commands = Commands;
		this.likes = likes;
	}
	
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getBook_title() {
		return Book_title;
	}
	public void setBook_title(String book_title) {
		Book_title = book_title;
	}
	public int getAuthor_id() {
		return Author_id;
	}
	public void setAuthor_id(int author_id) {
		Author_id = author_id;
	}
	public Calendar getReleaseDate() {
		return ReleaseDate;
	}
	public void setReleaseDate(Calendar releaseDate) {
		ReleaseDate = releaseDate;
	}
	public int getBook_pages() {
		return Book_pages;
	}
	public void setBook_pages(int book_pages) {
		Book_pages = book_pages;
	}
	public float getBook_price() {
		return Book_price;
	}
	public void setBook_price(float book_price) {
		Book_price = book_price;
	}
	public String getBook_description() {
		return Book_description;
	}
	public void setBook_description(String book_description) {
		Book_description = book_description;
	}
	public String getBook_picture() {
		return Book_image_data;
	}
	public void setBook_picture(String book_image_data) {
		Book_image_data = book_image_data;
	}
	public int getCat_id() {
		return Cat_id;
	}
	public void setCat_id(int cat_id) {
		Cat_id = cat_id;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getCommands() {
		return Commands;
	}
	public void setCommands(int commands) {
		Commands = commands;
	}
	
}
