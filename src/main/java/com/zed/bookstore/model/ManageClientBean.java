package com.zed.bookstore.model;

import java.io.Serializable;
import java.util.Calendar;

public class ManageClientBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Admin_id;//	  AD_id INTEGER NOT NULL,
	private int Cl_id;//	  CL_id INTEGER NOT NULL,
	private String StatueC;
	private Calendar EditDate;
	public ManageClientBean() {
		
	}
	public ManageClientBean(int Admin_id,String StatueC,Calendar EditDate) {
		this.Admin_id = Admin_id;
		this.StatueC = StatueC;
		this.EditDate= EditDate;
	}
	public ManageClientBean(int Admin_id,int Cl_id) {
		this.Admin_id = Admin_id;
		this.Cl_id = Cl_id;
	}
	public int getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
	}
	public int getCl_id() {
		return Cl_id;
	}
	public void setCl_id(int cl_id) {
		Cl_id = cl_id;
	}
	public String getStatueC() {
		return StatueC;
	}
	public void setStatueC(String statueB) {
		StatueC = statueB;
	}
	public Calendar getEditDate() {
		return EditDate;
	}
	public void setEditDate(Calendar editDate) {
		EditDate = editDate;
	}
}
