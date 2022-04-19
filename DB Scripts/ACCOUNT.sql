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

--Create a new personal accoount
create or replace procedure ACC_INSERT_RECORD
(par_Username varchar2, par_Password varchar2, par_Role number, par_Note varchar2)
is   
begin 
     insert into ACCOUNT(Username, Password, Role, Note) values 
     (par_Username, par_Password, par_Role, null, par_Note);    
end;


--Delete an account
create or replace procedure ACC_DELETE_RECORD (par_Username varchar2)
is
begin
    delete from PERSON where PERSON.Phone = par_Username;
    delete from ACCOUNT where ACCOUNT.Username = par_Username;
end;  


 --Create a Quantity of accounts for organizations in a province
create or replace procedure ACC_CREATE_ORG
(par_Quantity number, par_Province varchar2)
is
begin
    
    
end;
 
BEGIN   
   ACC_DELETE_RECORD('012456789212');  
   dbms_output.put_line('ok');    
END;

/*	STORED FUNCTIONS	*/




/*	RECORDS	*/




