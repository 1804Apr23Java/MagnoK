CREATE TABLE CITY (
    CITY_ID INTEGER PRIMARY KEY, 
    CITYNAME VARCHAR2(50), 
	CITYDESC VARCHAR2(200), 
	COMMENTER_ID INTEGER
)
CREATE TABLE COMMENTER (
    COMMENTER_ID INTEGER PRIMARY KEY,
    EMAIL VARCHAR2(50),
    FIRSTNAME VARCHAR2(50),
    LASTNAME VARCHAR2(50)
)
CREATE TABLE COMMENTS (
    COMMENT_ID INTEGER PRIMARY KEY,
    COMMENTER_ID INTEGER NOT NULL,
    COMMENTPOST VARCHAR2(200)
)
--CONSTRAINTS
ALTER TABLE CITY
ADD CONSTRAINT FK_CITY_COMMENTER_ID
FOREIGN KEY(COMMENTER_ID) REFERENCES COMMENTER(COMMENTER_ID);
/
ALTER TABLE COMMENTS
ADD CONSTRAINT FK_COMMENTS_COMMENTER_ID
FOREIGN KEY(COMMENTER_ID) REFERENCES COMMENTER(COMMENTER_ID);
/
--SEQUENCES
CREATE SEQUENCE SQ_CITY_PK
    INCREMENT BY 1
    START WITH 1;
    
CREATE SEQUENCE SQ_COMMENTS_PK
    INCREMENT BY 1
    START WITH 1;
    
CREATE SEQUENCE SQ_COMMENTER_PK
    INCREMENT BY 1
    START WITH 1;
--TRIGGERS
CREATE OR REPLACE TRIGGER TR_INSERT_COMMENTS
BEFORE INSERT ON COMMENTS
FOR EACH ROW
BEGIN
    SELECT SQ_COMMENTS_PK.NEXTVAL INTO :NEW.COMMENT_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_CITY
BEFORE INSERT ON CITY
FOR EACH ROW
BEGIN
    SELECT SQ_CITY_PK.NEXTVAL INTO :NEW.CITY_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_COMMENTER
BEFORE INSERT ON COMMENT
FOR EACH ROW
BEGIN
    SELECT SQ_COMMENTER_PK.NEXTVAL INTO :NEW.COMMENTER_ID FROM DUAL;
END;
--INSERT DATA
INSERT INTO COMMENTER(EMAIL, FIRSTNAME, LASTNAME)
VALUES('kmagno@gmail.com','Kevin','Magno');
INSERT INTO COMMENTER(EMAIL, FIRSTNAME, LASTNAME)
VALUES('awang@gmail.com','Angela','Wang');
INSERT INTO CITY(CITYNAME, CITYDESCR, COMMENTER_ID)
VALUES('Las Vegas!!','Poker and  more',1);
INSERT INTO CITY(CITYNAME, CITYDESCR, COMMENTER_ID)
VALUES('Seattle','Rainy every day',2);
INSERT INTO COMMENTS(COMMENTER_ID, COMMENTPOST)
VALUES('1','Casinos everywhere, MGM, gambling galore');
INSERT INTO COMMENTS(COMMENTER_ID, COMMENTPOST)
VALUES('2','Lots of coffee shops, scenic, mountains');