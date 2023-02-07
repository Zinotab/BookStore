package com.zed.bookstore.model;

import java.io.Serializable;

public class AdminBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Admin_id;//	  AD_id INTEGER PRIMARY KEY,
	private String Admin_first_name;//	  AFname VARCHAR2(30) NOT NULL,
	private String Admin_last_name;//	  ALname VARCHAR2(30) NOT NULL,
	private String Admin_Email;//	  AEmail VARCHAR2(50) NOT NULL,
	private String Admin_Password;//	  APassword VARCHAR2(30) NOT NULL
	
	public AdminBean() {
		
	}
	
	public AdminBean(String admin_Email, String admin_Password) {
		
		Admin_Email = admin_Email;
		Admin_Password = admin_Password;
	}

	public AdminBean(int Admin_id,String Admin_first_name,String Admin_last_name,String Admin_Email,String Admin_Password) {
		this.Admin_id = Admin_id;
		this.Admin_first_name = Admin_first_name;
		this.Admin_last_name = Admin_last_name;
		this.Admin_Email = Admin_Email;
		this.Admin_Password = Admin_Password;
	}
	public int getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
	}
	public String getAdmin_first_name() {
		return Admin_first_name;
	}
	public void setAdmin_first_name(String admin_first_name) {
		Admin_first_name = admin_first_name;
	}
	public String getAdmin_last_name() {
		return Admin_last_name;
	}
	public void setAdmin_last_name(String admin_last_name) {
		Admin_last_name = admin_last_name;
	}
	public String getAdmin_Email() {
		return Admin_Email;
	}
	public void setAdmin_Email(String admin_Email) {
		Admin_Email = admin_Email;
	}
	public String getAdmin_Password() {
		return Admin_Password;
	}
	public void setAdmin_Password(String admin_Password) {
		Admin_Password = admin_Password;
	}
	
}
