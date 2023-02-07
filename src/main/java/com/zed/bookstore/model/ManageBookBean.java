package com.zed.bookstore.model;

import java.io.Serializable;
import java.util.Calendar;

public class ManageBookBean  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Admin_id;//	  AD_id INTEGER NOT NULL,
	private int book_id;//	  BOOK_ID INTEGER NOT NULL,
	private String StatueB;
	private Calendar EditDate;
	public ManageBookBean() {
		
	}
	public ManageBookBean(int Admin_id,String StatueB,Calendar EditDate) {
		this.Admin_id = Admin_id;
		this.StatueB = StatueB;
		this.EditDate= EditDate;
	}
	public ManageBookBean(int Admin_id,int book_id,String StatueB) {
		this.Admin_id = Admin_id;
		this.book_id = book_id;
		this.StatueB = StatueB;
	}
	public int getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getStatueB() {
		return StatueB;
	}
	public void setStatueB(String statueB) {
		StatueB = statueB;
	}
	public Calendar getEditDate() {
		return EditDate;
	}
	public void setEditDate(Calendar editDate) {
		EditDate = editDate;
	}
	
	
}
