--test git
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
    Password varchar2(128) not null,

    --Role of an account, value in {0, 1, 2} |
    -- 0: MOH, 1: Organizations, 2: Citizens and default value of Role is null
    Role number(1) DEFAULT (null),
    
    --Status of an account, value in {0,1} | 0: active, 1: blocked
    Status number(1),
    
    --Note of an account
    Note varchar2(2000)
    
);


/*	CONSTRAINT	*/
--Primary Key
alter table ACCOUNT
add constraint PK_ACC primary key (Username);


--Check
alter table ACCOUNT
add constraint CK_ACC_Role check (Role in (0, 1, 2));


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/