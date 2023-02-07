package com.zed.bookstore.model;

import java.io.Serializable;

public class LikedBookBean  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int CL_id;//	  CL_id INTEGER NOT NULL,
	private int book_id;//	  BOOK_id INTEGER NOT NULL,

	public LikedBookBean() {
		
	}
	public LikedBookBean(int CL_id,int book_id) {
		this.CL_id = CL_id;
		this.book_id = book_id;
	}
	public int getCL_id() {
		return CL_id;
	}
	public void setCL_id(int cL_id) {
		CL_id = cL_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	
}
