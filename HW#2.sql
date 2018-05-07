--2.1 SELECT
--Task – Select all records from the Employee table.
SELECT * 
FROM EMPLOYEE ;
--Task – Select all records from the Employee table where last name is King.
SELECT * 
FROM EMPLOYEE 
WHERE LASTNAME = 'King';
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * 
FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;


--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT *
FROM ALBUM
ORDER BY TITLE DESC;
--Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME
FROM CUSTOMER
ORDER BY CITY ASC;


--2.3 INSERT INTO
--Task – Insert two new records into Genre table 
INSERT ALL
INTO GENRE
VALUES (26, 'Smooth Jazz')
INTO GENRE
VALUES (27, 'Musical')
SELECT * FROM DUAL;
--Task – Insert two new records into Employee table
INSERT ALL
INTO EMPLOYEE
VALUES (9, 'Mathers', 'Marshall', 'CEO', NULL, TO_DATE('1972-08-17 00:00:00', 'yyyy-mm-dd hh24:mi:ss'), TO_DATE('1999-08-17 00:00:00', 'yyyy-mm-dd hh24:mi:ss'), '8 Mile Rd.', 'St. Joseph', 'MO', 'United States', '64501', '+1 (777) 123-4567', '+1 (777) 123-4567', 'eminem@losemyself.com')
INTO EMPLOYEE
VALUES (10, 'Jordan', 'Michael', 'CEO', NULL, TO_DATE('1963-02-17 00:00:00', 'yyyy-mm-dd hh24:mi:ss'), TO_DATE('1999-08-17 00:00:00', 'yyyy-mm-dd hh24:mi:ss'), '23 Hall of Fame Dr.', 'Wilmington', 'NC', 'United States', '28401', '+1 (888) 789-1654', '+1 (888) 789-1654', 'goat@nba.com')
SELECT * FROM DUAL;
--Task – Insert two new records into Customer table
INSERT ALL
INTO CUSTOMER
VALUES (60, 'Kevin', 'Magno', 'Revature', '2919 Network Place', 'Lutz', 'FL', 'United States', '33559', '+1 (718) 687-9478', '+1 (718) 687-9478', 'kmagno@albany.edu', '10')
INTO CUSTOMER
VALUES (61, 'Jesus', 'Magno', 'Sheet Metal Co.', '10 Willow Place', 'Albertson', 'NY', 'United States', '11507', '+1 (718) 687-9479', '+1 (718) 687-9479', 'busteresky@juno.edu', '10')
SELECT * FROM DUAL;


--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER
SET FIRSTNAME='Robert', LASTNAME='Walter'
WHERE FIRSTNAME='Aaron' AND LASTNAME='Mitchell';
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	
UPDATE ARTIST
SET NAME='CCR'
WHERE NAME='Creedence Clearwater Revival';


--2.5 LIKE
--Task – Select all invoices with a billing address like “T%” 
SELECT *
FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
SELECT *
FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;
--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT *
FROM EMPLOYEE
WHERE HIREDATE BETWEEN TO_DATE('2003-06-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss') AND TO_DATE('2004-03-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss');

--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
--Add ON DELETE CASCADE TO INVOICE CUSTOMER_ID
ALTER TABLE INVOICELINE
DROP CONSTRAINT FK_INVOICELINEINVOICEID;
/

ALTER TABLE INVOICELINE
ADD CONSTRAINT FK_INVOICELINEINVOICEID
FOREIGN KEY (INVOICEID) REFERENCES INVOICE(INVOICEID) ON DELETE CASCADE;
/

FK_BANK_ACCT_BANK_ACCT_TRANS

ALTER TABLE INVOICE
DROP CONSTRAINT FK_INVOICECUSTOMERID;
/

ALTER TABLE INVOICE
ADD CONSTRAINT FK_INVOICECUSTOMERID
FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID) ON DELETE CASCADE;
/

DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

--3.1 System Defined Functions
--Task – Create a function that returns the current time.
CREATE OR REPLACE FUNCTION RET_CURRENT_TIME
RETURN TIMESTAMP  
IS
T TIMESTAMP;
BEGIN
 SELECT CURRENT_TIMESTAMP(8)+0 INTO T FROM DUAL;
 RETURN T;
END;
SELECT RET_CURRENT_TIME FROM DUAL;
--Task – create a function that returns the length of name in MEDIATYPE table
CREATE OR REPLACE FUNCTION MEDIATABLE_LENGTH(NAME_ IN VARCHAR)
RETURN NUMBER
IS
L NUMBER;
BEGIN
    L := LENGTH(NAME_);
    RETURN L;
END;

DECLARE
NAME_ VARCHAR(70);
BEGIN
    SELECT NAME INTO NAME_ FROM MEDIATYPE WHERE MEDIATYPEID = 2;
    DBMS_OUTPUT.PUT_LINE('Length of "' || NAME_ || '" is: ' || MEDIATABLE_LENGTH(NAME_) || ' characters');
END;

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 
CREATE OR REPLACE FUNCTION AVG_INVOICES
RETURN NUMBER
IS
AVG_ NUMBER;
BEGIN
    SELECT AVG(TOTAL) INTO AVG_ FROM INVOICE;
    RETURN AVG_;
END;

SELECT AVG_INVOICES FROM DUAL;
--Task – Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION MOST_EXP
RETURN NUMBER
IS
M_EX NUMBER;
BEGIN
    SELECT MAX(UNITPRICE) INTO M_EX FROM TRACK;
    RETURN M_EX;
END;

SELECT MOST_EXP FROM DUAL;

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION RET_AVG_PRICE
RETURN NUMBER
IS
AVG_ NUMBER;
BEGIN
    SELECT AVG(UNITPRICE) INTO AVG_ FROM INVOICELINE;
    RETURN AVG_;
END;
/

SELECT RET_AVG_PRICE FROM DUAL;

--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
CREATE TABLE EMPLOYEE (
    EMPLOYEE_F_NAME VARCHAR(25),
    EMPLOYEE_L_NAME VARCHAR(25),
    EMPLOYEE_BDAY DATE,
);
/

CREATE OR REPLACE FUNCTION EMPLOYEE_TABLE
RETURN TABLE
IS
BEGIN
    FOR I (SELECT LASTNAME, FIRSTNAME, BIRTHDATE) FROM EMPLOYEE WHERE BIRTHDATE > '01-JAN-68') LOOP
    INTO EMPLOYEE...
END;
/

--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE REC_EMP( S OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN S FOR 
    SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;

DECLARE 
S SYS_REFCURSOR ;
FIRST_N EMPLOYEE.FIRSTNAME%TYPE;
LAST_N EMPLOYEE.LASTNAME%TYPE;
BEGIN
    REC_EMP(S);
    LOOP
        FETCH S INTO FIRST_N, LAST_N;
        EXIT WHEN S%NOTFOUND; --BREAK OUT OF LOOP WHEN NO MORE ROWS ARE AVAILABLE 
        DBMS_OUTPUT.PUT_LINE(FIRST_N||' '||LAST_N);
    END LOOP;
    CLOSE S;
END;

--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UP_EMP(EMP_ID NUMBER, EMP_LNAME VARCHAR2(20), EMP_FNAME VARCHAR2(20), EMP_TITLE VARCHAR(20), EMP_REP NUMBER, EMP_BDAY DATE, EMP_HDAY DATE, EMP_ADR VARCHAR2(70), EMP_CIT VARCHAR2(70), EMP_ST VARCHAR2(40), EMP_COUN VARCHAR2(40), EMP_ZIP VARCHAR2(10), EMP_PHO VARCHAR2(24), EMP_FAX VARCHAR2(24), EMP_EMA VARCHAR2(60));
IS
BEGIN
    UPDATE EMPLOYEE
    SET EMPLOYEEID = EMP_ID, LASTNAME = EMP_LNAME, FIRSTNAME = EMP_FNAME, TITLE = EMP_TITLE, REPORTSOTO = EMP_REP, BIRTHDATE = EMP_BDAY, HIREDATE = EMP_HDAY, ADDRESS = EMP_ADR, CITY = EMP_CIT, STATE = EMP_ST, COUNTRY = EMP_COUN, POSTALCODE = EMP_ZIP, PHONE = EMP_PHO, FAX = EMP_FAX, EMAIL = EMP_EMA
    WHERE EMPLOYEEID = EMP_ID;
    COMMIT;
END;

BEGIN
    UP_EMP(11, 'Jordan', 'Michael', 'CEO', NULL, TO_DATE('1963-02-17 00:00:00', 'yyyy-mm-dd hh24:mi:ss'), TO_DATE('1999-08-17 00:00:00', 'yyyy-mm-dd hh24:mi:ss'), '23 Hall of Fame Dr.', 'Wilmington', 'NC', 'United States', '28401', '+1 (888) 789-1654', '+1 (888) 789-1654', 'goat@nba.com');
END;
--Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE RET_MANAGERS( S OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN S FOR 
    SELECT FIRSTNAME, LASTNAME, TITLE FROM EMPLOYEE WHERE TITLE LIKE '%Manager%';
END;

DECLARE 
S SYS_REFCURSOR ;
FIRST_N EMPLOYEE.FIRSTNAME%TYPE;
LAST_N EMPLOYEE.LASTNAME%TYPE;
TITLE EMPLOYEE.TITLE%TYPE;
BEGIN
    RET_MANAGERS(S);
    LOOP
        FETCH S INTO FIRST_N, LAST_N, TITLE;
        EXIT WHEN S%NOTFOUND; --BREAK OUT OF LOOP WHEN NO MORE ROWS ARE AVAILABLE 
        DBMS_OUTPUT.PUT_LINE(FIRST_N||' '||LAST_N||' '||TITLE);
    END LOOP;
    CLOSE S;
END;

--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE CUST_INFO( S OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN S FOR 
    SELECT FIRSTNAME, LASTNAME, COMPANY FROM CUSTOMER;
END;

DECLARE 
S SYS_REFCURSOR ;
FIRST_N CUSTOMER.FIRSTNAME%TYPE;
LAST_N CUSTOMER.LASTNAME%TYPE;
COMPANY CUSTOMER.COMPANY%TYPE;
BEGIN
    CUST_INFO(S);
    LOOP
        FETCH S INTO FIRST_N, LAST_N, COMPANY;
        EXIT WHEN S%NOTFOUND; --BREAK OUT OF LOOP WHEN NO MORE ROWS ARE AVAILABLE 
        DBMS_OUTPUT.PUT_LINE(FIRST_N||' '||LAST_N||'        '||COMPANY);
    END LOOP;
    CLOSE S;
END;

--5.0 Transactions
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE DEL_INV (INV_ID IN INVOICE.INVOICEID%TYPE)
IS
INV_ID_EXISTS INTEGER;
BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    --CHECK IF INV_ID EXISTS
    SELECT COUNT(I.INVOICEID) INTO INV_ID_EXISTS FROM INVOICE I
        WHERE I.INVOICEID = INV_ID;
    
    -- CONSTRAINT INTEGRITY
    IF INV_ID_EXISTS > 0 THEN
        DELETE FROM INVOICELINE WHERE INVOICEID = INV_ID;
        DELETE FROM INVOICE WHERE INVOICEID = INV_ID;
        DBMS_OUTPUT.PUT_LINE('ID: '||INV_ID||' HAS BEEN DELETED');
    ELSE
    DBMS_OUTPUT.PUT_LINE('ID: '||INV_ID||' DOES NOT EXIST!');
    
    END IF;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('ID: '||INV_ID||' DOES NOT EXIST!');
    ROLLBACK;
END;

BEGIN
    DEL_INV(109);
END;

--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.

















--7.1 INNER 
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME ,CUSTOMER.CUSTOMERID, INVOICE.INVOICEID
FROM CUSTOMER
INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME , INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER
FULL OUTER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT ARTIST.NAME, ALBUM.TITLE
FROM ALBUM
RIGHT OUTER JOIN ARTIST ON ARTIST.ARTISTID = ALBUM.ARTISTID;

--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT *
FROM ALBUM
CROSS JOIN ARTIST
ORDER BY ARTIST.NAME ASC;

--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT A.EMPLOYEEID, A.FIRSTNAME, A.LASTNAME, B.REPORTSTO
FROM EMPLOYEE A, EMPLOYEE B
WHERE A.REPORTSTO = B.REPORTSTO
ORDER BY B.REPORTSTO;











