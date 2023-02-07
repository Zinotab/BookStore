package com.zed.bookstore.model;

import java.io.Serializable;
import java.util.Calendar;

public class CommandBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Command_id;//	CMD_ID INTEGER PRIMARY KEY,
	private int CL_id;//	  CL_id INTEGER NOT NULL,
	private int book_id;//	  BOOK_ID INTEGER NOT NULL,
	private Calendar Command_Date;//	  CMD_DATE DATE NOT NULL,
	
	public CommandBean() {
		
	}
	public CommandBean(int CL_id,int book_id,Calendar Command_Date) {
		this.Command_id = Command_id;
		this.CL_id = CL_id;
		this.book_id = book_id;
		this.Command_Date = Command_Date;
	}
	public CommandBean(int Command_id,int CL_id,int book_id,Calendar Command_Date) {
		this.Command_id = Command_id;
		this.CL_id = CL_id;
		this.book_id = book_id;
		this.Command_Date = Command_Date;
	}
	public int getCommand_id() {
		return Command_id;
	}
	public void setCommand_id(int command_id) {
		Command_id = command_id;
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
	public Calendar getCommand_Date() {
		return Command_Date;
	}
	public void setCommand_Date(Calendar command_Date) {
		Command_Date = command_Date;
	}
	
}
