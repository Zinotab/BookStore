package com.zed.bookstore.control;


import java.util.List;

import com.zed.bookstore.dao.ControllCSVFiles;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> listCountries = ControllCSVFiles.readCountries();
		
		for(int i = 0; i < listCountries.size(); i++) {
    		
    		System.out.println(listCountries.get(i));
        } 
		
		
//		new Controller().UpdateCategorieInfoFromCSVFile();
	}
}
