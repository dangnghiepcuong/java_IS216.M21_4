--20521252 - Lê Hoàng Duyên
/*          TABLE: ACCOUNT          */
--Create a table struct without constraint
--<Attribute name> <Data type>

--Store data of accounts from actors
CREATE TABLE ACCOUNT
(
    --Username of an account
    Username varchar2(254) not null,
    --Password of an account
    Password varchar2(128),
    --Identity of a person foreign key references PERSON(ID)
    PersonalID varchar2(12),
    --Role of an account, value of {0, 1, 2} | 0: Ministry of Health, 1: vaccination agency, 2: citizens
    Role number(1)
);


/*	CONSTRAINT	*/
--Primary Key
CONSTRAINT s_customer_id_pk PRIMARY KEY (id)


--Foreign Key



--Check



/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/