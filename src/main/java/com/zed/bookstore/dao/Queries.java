package com.zed.bookstore.dao;

public enum Queries {

	INSERT_BOOK_SQL("INSERT INTO BOOKSTORE.BOOK (ISBN, TITLE, AUTHOR_ID,RELEASED,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"),
	SELECT_ALL_BOOKS("SELECT ISBN, TITLE, AUTHOR_ID,RELEASED,PAGE,PRICE,BOOK_DESC,PICTURE,CATEGORY_ID FROM BOOKSTORE.BOOK"),
	INSERT_AUTHOR_SQL("INSERT INTO BOOKSTORE.AUTHOR (AUTH_ID,AUFNAME,AULNAME,AUBIRTHD,AUCOUNTRY,AUBIOGRAPHY,AUFULLNAME,AUFULLNAMEREV,AUPICTURE) VALUES (?, ?, ?, ?, ?, ? ,? ,?,?)"),
	SELECT_ALL_AUTHORS("SELECT AUTH_ID,AUFNAME,AULNAME,AUBIRTHD,AUCOUNTRY,AUPICTURE,AUBIOGRAPHY FROM BOOKSTORE.AUTHOR"),
	INSERT_CLIENT_SQL("INSERT INTO BOOKSTORE.CLIENT (CL_ID,CLFNAME,CLLNAME,CLBIRTHD,TEL,CLEMAIL,CLPASSWORD,CLPICTURE,CL_REVIEW) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"),
	SELECT_ALL_CLIENTS("SELECT CL_ID,CLFNAME,CLLNAME,CLBIRTHD,TEL,CLEMAIL,CLPASSWORD,CLPICTURE,CL_REVIEW FROM BOOKSTORE.CLIENT"),
	INSERT_CATEGORY_SQL("INSERT INTO BOOKSTORE.CATEGORY (CAT_ID,CATNAME,CATDESC) VALUES (?, ?, ?)"),
	INSERT_COMMAND_SQL("INSERT INTO BOOKSTORE.COMMAND(CMD_ID,CL_ID,BOOK_ID,CMD_DATE) VALUES(?,?,?,?)"),
	SELECT_ALL_CATEGORIES("SELECT CAT_ID,CATNAME,CATDESC FROM BOOKSTORE.CATEGORY"),
	INSERT_MANAGEBOOK_SQL("INSERT INTO BOOKSTORE.MANAGEBOOK(MB,AD_ID,BOOK_ID,STATUE,EDIT_DATE) values(?,?,?,?,?)"),
	INSERT_MANAGECLIENT_SQL("INSERT INTO BOOKSTORE.MANAGECLIENT(MCL,AD_ID,CL_ID,STATUE,EDIT_DATE) values(?,?,?,?,?)"),
	INSERT_MANAGECOMMAND_SQL("INSERT INTO BOOKSTORE.MANAGECOMMAND(MCMD,AD_ID,CMD_ID,STATUE,EDIT_DATE) values(?,?,?,?,?)"),
	INSERT_MANAGEAUTHOR_SQL("INSERT INTO BOOKSTORE.MANAGEAUTHOR(MA,AD_ID,AUTH_ID,STATUE,EDIT_DATE) values(?,?,?,?,?)");
	
	public String Query;
	
	private Queries(String Query) {
		this.Query = Query;
	}
}