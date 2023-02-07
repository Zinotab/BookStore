package com.zed.bookstore.dao;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zed.bookstore.model.CategoryBean;

public class ControllCSVFiles {

	
	public static void readCategories() {


		
		
		String filePath = "src/main/java/com/zed/bookstore/model/Categories.csv";
		BufferedReader reader = null;
		String line = "";
	
		List<CategoryBean> categories = new ArrayList<>();
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			while((line = reader.readLine()) != null) {
				String[] row = line.split("/");
				
				int cat_id = Integer.parseInt(row[0]);
				String category = row[1];
				String cat_desc = row[2];
				
				categories.add(new CategoryBean(cat_id,category,cat_desc));
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new BookStoreDAO().UpdateCategoryTable(categories);
	}
	public static List<String> readCountries() {


		
		
		
		String filePath = "Countries.csv";
		
		
		BufferedReader reader = null;
		String line = "";
	
		List<String> Countries = new ArrayList<>();
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			while((line = reader.readLine()) != null) {
				Countries.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Countries;
		
	}
}
