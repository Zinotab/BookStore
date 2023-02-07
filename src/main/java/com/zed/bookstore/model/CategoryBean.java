package com.zed.bookstore.model;

import java.io.Serializable;

public class CategoryBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Category_id;//	  CAT_ID INTEGER PRIMARY KEY,
	private String Category_name;//	  CATName VARCHAR2(30) NOT NULL,
	private String Category_desc;//	  CATdesc VARCHAR2(1200)
	
	public CategoryBean() {
		
	}
	public CategoryBean(String Category_name,String Category_desc) {
		this.Category_name = Category_name;
		this.Category_desc = Category_desc;
	}
	public CategoryBean(int Category_id,String Category_name,String Category_desc) {
		this.Category_id = Category_id;
		this.Category_name = Category_name;
		this.Category_desc = Category_desc;
	}
	public int getCategory_id() {
		return Category_id;
	}
	public void setCategory_id(int category_id) {
		Category_id = category_id;
	}
	public String getCategory_name() {
		return Category_name;
	}
	public void setCategory_name(String category_name) {
		Category_name = category_name;
	}
	public String getCategory_desc() {
		return Category_desc;
	}
	public void setCategory_desc(String category_desc) {
		Category_desc = category_desc;
	}
	
}
