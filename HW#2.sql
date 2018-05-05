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
SELECT TOCHAR(SYSDATE CURRENT_TIMESTAMP ;
--Task – create a function that returns the length of name in MEDIATYPE table
--****************************************

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION RET_AVG_PRICE
RETURNS FLOAT
AS
BEGIN
    DECLARE AVG_ FLOAT
    SELECT AVG_ = AVG(UNITPRICE)
    FROM INVOICELINE;
    RETURN AVG_;
END;
/
DECLARE 
AVG_ NUMBER;
BEGIN
    AVG_ := RET_AVG_PRICE(FIRST_NUM, SECOND_NUM);
    DBMS_OUTPUT.PUT_LINE('MAX: '||AVG_);
END;



















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











