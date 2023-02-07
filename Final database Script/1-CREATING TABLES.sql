CREATE TABLE CLIENT (
  CL_id INTEGER PRIMARY KEY,
  CLFname VARCHAR2(30) NOT NULL,
  CLLname VARCHAR2(30) NOT NULL,
  CLBirthD DATE,
  TEL VARCHAR2(20),
  CLEmail VARCHAR2(50) NOT NULL,
  CLPassword VARCHAR2(30) NOT NULL,
  CL_Review VARCHAR2(1200),
  CLPicture VARCHAR2(200)
);
CREATE TABLE CATEGORY (
  CAT_ID INTEGER PRIMARY KEY,
  CATName VARCHAR2(30) NOT NULL,
  CATdesc VARCHAR2(1200)
);
CREATE TABLE AUTHOR (
  AUTH_id INTEGER PRIMARY KEY,
  AUFname VARCHAR2(30) NOT NULL,
  AULname VARCHAR2(30) NOT NULL,
  AUBirthD DATE,
  AUCountry VARCHAR2(30),
  AUBiography VARCHAR2(1200),
  AUPicture VARCHAR2(200),
  AUFULLNAME VARCHAR2(100) NOT NULL,
  AUFULLNAMEREV VARCHAR2(100) NOT NULL
);

CREATE TABLE BOOK (
  ISBN INTEGER PRIMARY KEY,
  title VARCHAR2(100) NOT NULL,
  author_id INTEGER ,
  releaseD DATE NOT NULL,
  page INTEGER NOT NULL,
  price FLOAT NOT NULL,
  BOOK_DESC VARCHAR2(1200),
  picture VARCHAR2(200),
  Category_id INTEGER ,
  CONSTRAINT FK_AuthorBook FOREIGN KEY (author_id) REFERENCES AUTHOR(AUTH_id) ON DELETE SET NULL,
  CONSTRAINT FK_CategoryBook FOREIGN KEY (Category_id) REFERENCES CATEGORY(CAT_ID)ON DELETE SET NULL
);

CREATE TABLE PUBLISHING(
    Editor_name VARCHAR2(50) PRIMARY KEY,
    ISBN_B INTEGER,
    CONSTRAINT FK_BookPublishing FOREIGN KEY (ISBN_B) REFERENCES BOOK(ISBN) ON DELETE SET NULL
);

CREATE TABLE COMMAND (
  CMD_ID INTEGER PRIMARY KEY,
  CMD_DATE DATE NOT NULL,
  CL_id INTEGER,
  BOOK_ID INTEGER,
  CONSTRAINT FK_ClientCommand FOREIGN KEY (CL_id) REFERENCES CLIENT(CL_id) ON DELETE SET NULL,
  CONSTRAINT FK_BookCommand FOREIGN KEY (BOOK_ID) REFERENCES BOOK(ISBN) ON DELETE SET NULL
);

CREATE TABLE ADMIN (
  AD_id INTEGER PRIMARY KEY,
  AFname VARCHAR2(30) NOT NULL,
  ALname VARCHAR2(30) NOT NULL,
  AEmail VARCHAR2(50) NOT NULL,
  APassword VARCHAR2(30) NOT NULL
);

CREATE TABLE MANAGEBOOK (
  MB INT PRIMARY KEY,
  AD_id INTEGER ,
  BOOK_ID INTEGER ,
  STATUE VARCHAR2(20) NOT NULL,
  EDIT_DATE DATE,
  CONSTRAINT FK_AdminMBook FOREIGN KEY (AD_id) REFERENCES ADMIN(AD_id)ON DELETE SET NULL,
  CONSTRAINT FK_BookMBook FOREIGN KEY (BOOK_ID) REFERENCES BOOK(ISBN) ON DELETE SET NULL
);

CREATE TABLE MANAGEAUTHOR (
  MA INT PRIMARY KEY,
  AD_id INTEGER ,
  AUTH_id INTEGER ,
  STATUE VARCHAR2(20) NOT NULL,
  EDIT_DATE DATE,
  CONSTRAINT FK_AdminMAuthor FOREIGN KEY (AD_id) REFERENCES ADMIN(AD_id) ON DELETE SET NULL,
  CONSTRAINT FK_AuthorMAuthor FOREIGN KEY (AUTH_id) REFERENCES AUTHOR(AUTH_id) ON DELETE SET NULL
);

CREATE TABLE MANAGECLIENT (
    MCL INT PRIMARY KEY,
  AD_id INTEGER ,
  CL_id INTEGER ,
  STATUE VARCHAR2(20) NOT NULL,
  EDIT_DATE DATE,
  CONSTRAINT FK_AdminMClient FOREIGN KEY (AD_id) REFERENCES ADMIN(AD_id) ON DELETE SET NULL,
  CONSTRAINT FK_ClientMClient FOREIGN KEY (CL_id) REFERENCES CLIENT(CL_id) ON DELETE SET NULL
);
    
CREATE TABLE MANAGECOMMAND(
  MCMD INT PRIMARY KEY,
  AD_id INTEGER ,
  CMD_id INTEGER ,
  STATUE VARCHAR2(20) NOT NULL,
  EDIT_DATE DATE,
  CONSTRAINT FK_AdminMCommand FOREIGN KEY (AD_id) REFERENCES ADMIN(AD_id) ON DELETE SET NULL,
  CONSTRAINT FK_CommandMCommand FOREIGN KEY (CMD_id) REFERENCES COMMAND(CMD_ID) ON DELETE SET NULL
);
​
CREATE TABLE LIKEDBOOKS(
  LIKE_ID INTEGER PRIMARY KEY,
  CL_id INTEGER ,
  BOOK_id INTEGER ,
  CONSTRAINT FK_BookLikedBooks FOREIGN KEY (BOOK_id) REFERENCES BOOK(ISBN) ON DELETE SET NULL,
  CONSTRAINT FK_ClientLikedClient FOREIGN KEY (CL_id) REFERENCES CLIENT(CL_id) ON DELETE SET NULL
);