package com.zed.bookstore.model;

import java.io.Serializable;

public class PublishingBean  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Editor_name;//    Editor_name VARCHAR2(50) PRIMARY KEY,
	private int ISBN_book;//    ISBN_B INTEGER,
	
	public PublishingBean() {
		
	}
	public PublishingBean(String Editor_name,int ISBN_book) {
		this.Editor_name = Editor_name;
		this.ISBN_book = ISBN_book;
	}
	public String getEditor_name() {
		return Editor_name;
	}
	public void setEditor_name(String editor_name) {
		Editor_name = editor_name;
	}
	public int getISBN_book() {
		return ISBN_book;
	}
	public void setISBN_book(int iSBN_book) {
		ISBN_book = iSBN_book;
	}
	
}
