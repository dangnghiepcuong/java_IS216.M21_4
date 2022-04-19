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
     insert into ACCOUNT(Username, Password, Role, status, Note) values 
     (par_Username, par_Password, par_Role, null, par_Note);    
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
begin
    select (TO_NUMBER(SUBSTR(ID, 1, -3)) + 1) as "SEQ" 
    into Last_Seq
    from ORGANIZATION
    where Province = par_Province and rownum = 1
    order by SEQ desc;
    if (Last_Seq = null) then
        Last_Seq := 1;
    else 
        Last_Seq := Last_Seq + 1;
    end if;
    
    for i in Last_Seq .. Last_Seq + par_Quantity - 1
        loop
            ORG_INSERT_RECORD(TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i),
            To_CHAR(Last_Seq), null);
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


 
BEGIN   
   ACC_DELETE_RECORD('012456789212');  
   dbms_output.put_line('ok');    
END;

/*	STORED FUNCTIONS	*/
create or replace function ACC_CONVERT_SEQ_TO_STR
(par_Last_Seq int)
return varchar2 is
	Div_Result int;
begin
	Div_Result := par_Last_Seq/100;
	if ( Div_Result >= 1 )
	then
		return TO_CHAR(par_Last_Seq);
	end if;

	Div_Result := par_Last_Seq/10;
	if ( Div_Result >= 1 )
	then
		return ('0' || TO_CHAR(par_Last_Seq));
	end if;

	return ('00' || TO_CHAR(par_Last_Seq));

end ACC_CONVERT_SEQ_TO_STR;




/*	RECORDS	*/
create or replace procedure ORG_INSERT_RECORD (par_ID ORGANIZATION.ID%type,                                            
                                             par_Province ORGANIZATION.Province%type,                                            
							   par_Note  ORGANIZATION.Note%type)                                           
as 
begin
    --insert new ORGANIZATION
	insert into ORGANIZATION(ID, Province, Note) 
	values (par_ID, par_Province, par_Note);
end ORG_INSERT_RECORD;



