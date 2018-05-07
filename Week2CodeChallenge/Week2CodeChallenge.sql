--TABLE CREATION

CREATE TABLE EMPLOYEE (
    EMPLOYEE_ID INTEGER PRIMARY KEY,
    EMP_FIRSTNAME VARCHAR2(100),
    EMP_LASTNAME VARCHAR2(100),
    SALARY NUMBER(8,2) DEFAULT 0.00,
    EMP_EMAIL VARCHAR2(100),
    DEPARTMENT_ID INTEGER NOT NULL
);
/

CREATE TABLE DEPARTMENT (
    DEPARTMENT_ID INTEGER PRIMARY KEY,
    DEPARTMENT_NAME VARCHAR2(100)
);
/

--FOREIGN KEY CONSTRAINTS 

ALTER TABLE EMPLOYEE
ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT
FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT(DEPARTMENT_ID) ON DELETE CASCADE;
/

--CREATE SEQUENCES AND TRIGGERS TO PROVIDE PRIMARY KEY VALUES

CREATE SEQUENCE SQ_EMPLOYEE_PK
START WITH 1 
INCREMENT BY 1;
/

CREATE SEQUENCE SQ_DEPARTMENT_PK
START WITH 1 
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER TR_INSERT_EMPLOYEE
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_EMPLOYEE_PK.NEXTVAL INTO :NEW.EMPLOYEE_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_DEPARTMENT
BEFORE INSERT ON DEPARTMENT
FOR EACH ROW
BEGIN
    SELECT SQ_DEPARTMENT_PK.NEXTVAL INTO :NEW.DEPARTMENT_ID FROM DUAL;
END;
/
/*
--INSERT AT LEAST 3 DEPARTMENTS
INSERT ALL 
INTO DEPARTMENT (DEPARTMENT_NAME)
VALUES ('Executives')
INTO DEPARTMENT (DEPARTMENT_NAME)
VALUES ('Cleaners')
INTO DEPARTMENT (DEPARTMENT_NAME)
VALUES ('Closers')
SELECT * FROM DUAL;
/

--INSERT AT LEAST 6 EMPLOYEES
INSERT ALL 
INTO EMPLOYEE (EMP_FIRSTNAME, EMP_LASTNAME, SALARY, EMP_EMAIL, DEPARTMENT_ID)
VALUES ('Arnold', 'Schwarzenegger', 300000, 'Thagovna@cali.st', 1)
INTO EMPLOYEE (EMP_FIRSTNAME, EMP_LASTNAME, SALARY, EMP_EMAIL, DEPARTMENT_ID)
VALUES ('Kevin', 'Magno', 500000, 'kmagno@albany.edu', 1)
INTO EMPLOYEE (EMP_FIRSTNAME, EMP_LASTNAME, SALARY, EMP_EMAIL, DEPARTMENT_ID)
VALUES ('Elon', 'Musk', 499999, 'CEO@tesla.com', 1)
INTO EMPLOYEE (EMP_FIRSTNAME, EMP_LASTNAME, SALARY, EMP_EMAIL, DEPARTMENT_ID)
VALUES ('Jim', 'Shwartz', 50000, 'jshwartz@gmail.com', 3)
INTO EMPLOYEE (EMP_FIRSTNAME, EMP_LASTNAME, SALARY, EMP_EMAIL, DEPARTMENT_ID)
VALUES ('James', 'White', 50000, 'jwhite@gmail.com', 4)
INTO EMPLOYEE (EMP_FIRSTNAME, EMP_LASTNAME, SALARY, EMP_EMAIL, DEPARTMENT_ID)
VALUES ('Jamie', 'Langston', 300000, 'jlangston@gmail.com', 4)
SELECT * FROM DUAL;
/
*/
--STORED PROCEDURE
CREATE OR REPLACE PROCEDURE SP_GIVE_RAISE(D_ID IN NUMBER, D_RAISE IN NUMBER)--, AVG_SAL OUT NUMBER, VALID_DEPT OUT NUMBER)
IS 
DEPT_EXISTS NUMBER;
BEGIN
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    --CHECK THAT THIS BEAR IS MATCHED WITH THIS BEEHIVE 
    SELECT COUNT(DEPARTMENT_ID) INTO DEPT_EXISTS FROM DEPARTMENT
        WHERE DEPARTMENT.DEPARTMENT_ID = D_ID;
    DBMS_OUTPUT.PUT_LINE(DEPT_EXISTS);
    IF DEPT_EXISTS > 0 THEN
        --UPDATE BOOLEAN
        --VALID_DEPT := 1;
        --INCREASE EACH EMPLOYEE SALARY IN DEPT BY %10
        UPDATE EMPLOYEE 
        SET SALARY = SALARY + (SALARY*.10)
        WHERE DEPARTMENT_ID = D_ID;
        --UPDATE AVG_SALARY FOR EACH EMPLOYEE
        --SELECT AVG(SALARY) INTO AVG_SAL FROM EMPLOYEE
        --WHERE DEPARTMENT_ID = D_ID;
        --DBMS_OUTPUT.PUT_LINE('EMPLOYEES AVERAGE SALARY FOR DEPT. '||D_ID||' IS ' ||AVG_SAL);
    ELSE 
    --AVG_SAL := 0;
    --VALID_DEPT := 0;
    DBMS_OUTPUT.PUT_LINE('DEPARTMENT DOES NOT EXIST!');
    END IF;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    --AVG_SAL := 0;
    --VALID_DEPT := 0;
    DBMS_OUTPUT.PUT_LINE('DEPARTMENT DOES NOT EXIST!');
    ROLLBACK;
END;

DECLARE
AVG_SAL NUMBER;
VALID_DEPT NUMBER;
BEGIN
SP_GIVE_RAISE(1, 10);
END;

--TEST FOR AVERAGE SALARY OF EMPLOYEE
SELECT AVG(SALARY) FROM EMPLOYEE WHERE DEPARTMENT_ID = 1;


