CREATE TABLE CATEGORY (
    ID INT(11) NOT NULL AUTO_INCREMENT
  , NAME VARCHAR(100) NOT NULL
  , PRIMARY KEY (ID)
);

CREATE TABLE BOOK (
    ID INT(11) NOT NULL AUTO_INCREMENT
  , CATEGORY_ID INT(11) NOT NULL
  , ISBN VARCHAR(10) NOT NULL
  , TITLE VARCHAR(500) NOT NULL
  , PRIMARY KEY (ID)
  , CONSTRAINT FK_BOOK
    FOREIGN KEY (CATEGORY_ID)
    REFERENCES CATEGORY (ID)
    ON DELETE CASCADE
);