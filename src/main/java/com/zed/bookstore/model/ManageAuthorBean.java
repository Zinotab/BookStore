package com.zed.bookstore.model;

import java.io.Serializable;
import java.util.Calendar;

public class ManageAuthorBean  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Admin_id;//	  AD_id INTEGER NOT NULL,
	private int Author_id;//	  AUTH_id INTEGER NOT NULL,
	private String StatueA;
	private Calendar EditDate;
	public ManageAuthorBean() {
		
	}
	public ManageAuthorBean(int Admin_id,String StatueA,Calendar EditDate) {
		this.Admin_id = Admin_id;
		this.StatueA = StatueA;
		this.EditDate= EditDate;
	}
	public ManageAuthorBean(int Admin_id,int Author_id) {
		this.Admin_id = Admin_id;
		this.Author_id = Author_id;
	}
	public int getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
	}
	public int getAuthor_id() {
		return Author_id;
	}
	public void setAuthor_id(int author_id) {
		Author_id = author_id;
	}
	public String getStatueA() {
		return StatueA;
	}
	public void setStatueA(String statueA) {
		StatueA = statueA;
	}
	public Calendar getEditDate() {
		return EditDate;
	}
	public void setEditDate(Calendar editDate) {
		EditDate = editDate;
	}
	
}
