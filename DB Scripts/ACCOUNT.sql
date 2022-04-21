--test git
--20521252 - L� Ho�ng Duy�n
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
(par_Username varchar2, par_Password varchar2, par_Role number, par_Status number, par_Note varchar2 DEFAULT NULL)
is   
begin 
     insert into ACCOUNT(Username, Password, Role, status, Note) values 
     (par_Username, par_Password, par_Role, par_Status, par_Note);    
     
     EXCEPTION
        when DUP_VAL_ON_INDEX
        then
            raise_application_error(1000,'Username has been registered by another user!');
end ACC_INSERT_RECORD;


--Delete an account
create or replace procedure ACC_DELETE_RECORD (par_Username varchar2)
is
begin
    delete from PERSON where PERSON.Phone = par_Username;
    delete from ACCOUNT where ACCOUNT.Username = par_Username;
end ACC_DELETE_RECORD;  

 --Create a Quantity of accounts for organizations in a province
create or replace procedure ACC_CREATE_ORG
(par_Quantity number, par_Province varchar2)
is
    Last_Seq int;
    temp_ID ORGANIZATION.ID%type;
    temp_String varchar2(100);
begin  
    select ID into temp_ID
    from ORGANIZATION
    where Province = par_Province and rownum = 1
    order by ID desc;
    
    Last_Seq := TO_NUMBER(SUBSTR(TO_CHAR(temp_ID), -3, 3)) + 1;

    for i in Last_Seq .. Last_Seq + par_Quantity - 1
    loop
        ACC_INSERT_RECORD(TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i), 
                            TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i),
                            1, 1, null);
        ORG_INSERT_RECORD(TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i),
        par_Province, null);
    end loop;

EXCEPTION
    when no_data_found
 	then Last_Seq := 1;

    for i in Last_Seq .. Last_Seq + par_Quantity - 1
    loop
        ACC_INSERT_RECORD(TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i), 
                            TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i),
                            1, 1, null);
        ORG_INSERT_RECORD(TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i),
        par_Province, null);
    end loop;

end ACC_CREATE_ORG;


 --Update password of an account
create or replace procedure ACC_UPDATE_PASSWORD 
(par_Username varchar2, par_OldPass varchar2, par_NewPass varchar2)
is
    Pass varchar2(128);
begin
    select Password into Pass from ACCOUNT where Username = par_Username;
    if (par_OldPass != Pass) then
        DBMS_Output.Put_line('Mat khau khong dung!');
    else
        update ACCOUNT set Password = par_NewPass 
        where  Username = par_Username;
    end if;
end ACC_UPDATE_PASSWORD;


 --Reset password of an account
create or replace procedure ACC_RESET_PASSWORD
(par_Username varchar2, par_VerifyCode int)
is
begin
    
    
end ACC_RESET_PASSWORD;


/*	STORED FUNCTIONS	*/
create or replace function ACC_CONVERT_SEQ_TO_STR
(par_Last_Seq int)
return varchar2 is
begin
	if ( par_Last_Seq >= 100 )
	then
		return TO_CHAR(par_Last_Seq);
	end if;
    
    if ( par_Last_Seq >= 10 )
	then
		return ('0' || TO_CHAR(par_Last_Seq));
	end if;
    
    return ('00' || TO_CHAR(par_Last_Seq));

end ACC_CONVERT_SEQ_TO_STR;




