--20521252 - Lê Hoàng Duyên
/*          TABLE: ACCOUNT          */
--Create a table struct without constraint
--<Attribute name> <Data type>

--Store data of accounts from actors
create table ACCOUNT
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
alter table ACCOUNT
add constraint PK_ACCOUNT PRIMARY KEY (Username);


--Foreign Key
alter table ACCOUNT
add constraint FK_ACCOUNT_PERSON foreign key PersonalID references PERSON(ID);


--Check
alter table ACCOUNT
add constraint CK_ACCOUNT_Role check (Role in (0, 1, 2));


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/