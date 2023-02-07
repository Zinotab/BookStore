package com.zed.bookstore.model;

import java.io.Serializable;

public class ManageCommandBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Admin_id;//	  AD_id INTEGER NOT NULL,
	private int CMD_id;//	  CMD_id INTEGER NOT NULL,

	public ManageCommandBean() {
		
	}
	public ManageCommandBean(int Admin_id,int CMD_id) {
		this.Admin_id = Admin_id;
		this.CMD_id = CMD_id;
	}
	public int getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
	}
	public int getCMD_id() {
		return CMD_id;
	}
	public void setCMD_id(int cMD_id) {
		CMD_id = cMD_id;
	}
	
}
