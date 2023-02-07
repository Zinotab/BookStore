package com.zed.bookstore.model;

import java.io.Serializable;
import java.util.Calendar;

public class ClientBean  implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int client_id;//CL_id INTEGER PRIMARY KEY,
	  private String client_first_name;//CLFname VARCHAR2(30) NOT NULL,
	  private String client_last_name;//CLLname VARCHAR2(30) NOT NULL,
	  private Calendar client_birth_day;//CLBirthD DATE,
	  private String client_telephone;//TEL VARCHAR2(20),
	  private String client_Email;//CLEmail VARCHAR2(50) NOT NULL,
	  private String client_Password;//CLPassword VARCHAR2(30) NOT NULL,
	  private String client_picture;//CLPicture BLOB
	  private String client_review;
	
	  	public ClientBean() {
	
	  }
	  	
	  	public ClientBean(int client_id,String client_first_name,String client_last_name,String client_picture) {
			this.client_id = client_id;
		  	this.client_picture = client_picture;
		  	this.client_first_name = client_first_name;
			this.client_last_name = client_last_name;
	  }
		public ClientBean(int client_id,String client_review) {
			this.client_id = client_id;
		  	this.client_review = client_review;
	  }
		public ClientBean(String client_Email,String client_Password,String client_telephone) {
		    this.client_Password = client_Password;
		  	this.client_Email = client_Email;
		  	this.client_telephone = client_telephone;
	  }
	  
	  	public ClientBean(String client_first_name,String client_last_name,Calendar client_birth_day,String client_telephone,String client_Email,String client_Password,String client_picture) {
			this.client_first_name = client_first_name;
			this.client_last_name = client_last_name;
			this.client_Email = client_Email;
			this.client_Password = client_Password;
			this.client_birth_day = client_birth_day;
			this.client_telephone = client_telephone;
			this.client_picture = client_picture;
	  }
	  	public ClientBean(int client_id,String client_first_name,String client_last_name,Calendar client_birth_day,String client_telephone,String client_Email,String client_Password,String client_picture) {
		    this.client_id = client_id;
		  	this.client_first_name = client_first_name;
			this.client_last_name = client_last_name;
			this.client_Email = client_Email;
			this.client_Password = client_Password;
			this.client_birth_day = client_birth_day;
			this.client_telephone = client_telephone;
			this.client_picture = client_picture;
	  }
	  	public ClientBean(int client_id,String client_first_name,String client_last_name,Calendar client_birth_day,String client_telephone,String client_Email,String client_Password,String client_picture,String client_review) {
		    this.client_id = client_id;
		  	this.client_first_name = client_first_name;
			this.client_last_name = client_last_name;
			this.client_Email = client_Email;
			this.client_Password = client_Password;
			this.client_birth_day = client_birth_day;
			this.client_telephone = client_telephone;
			this.client_picture = client_picture;
			this.client_review = client_review;
	  }
	  
	  	public int getClient_id() {
			return client_id;
		}
		public void setClient_id(int client_id) {
			this.client_id = client_id;
		}
		public String getClient_first_name() {
			return client_first_name;
		}
		public void setClient_first_name(String client_first_name) {
			this.client_first_name = client_first_name;
		}
		public String getClient_last_name() {
			return client_last_name;
		}
		public void setClient_last_name(String client_last_name) {
			this.client_last_name = client_last_name;
		}
		public Calendar getClient_birth_day() {
			return client_birth_day;
		}
		public void setClient_birth_day(Calendar client_birth_day) {
			this.client_birth_day = client_birth_day;
		}
		public String getClient_telephone() {
			return client_telephone;
		}
		public void setClient_telephone(String client_telephone) {
			this.client_telephone = client_telephone;
		}
		public String getClient_Email() {
			return client_Email;
		}
		public void setClient_Email(String client_Email) {
			this.client_Email = client_Email;
		}
		public String getClient_Password() {
			return client_Password;
		}
		public void setClient_Password(String client_Password) {
			this.client_Password = client_Password;
		}
		public String getClient_picture() {
			return client_picture;
		}
		public void setClient_picture(String client_picture) {
			this.client_picture = client_picture;
		}
		public String getClient_review() {
			return client_review;
		}
		public void setClient_review(String client_review) {
			this.client_review = client_review;
		}

}
