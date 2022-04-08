--20521252 - Lê Hoàng Duyên
/*          TABLE: VACCINE          */
--Create a table struct without constraint
--<Attribute name> <Data type>

--Store data of information from injection
create table INJECTION
(
    --PersonalID FK references PERSON(ID)
    PersonalID varchar2(12) not null,
    
    --Injection sequence number of the citizen, value in { 1, 2, 3, 4}
    InjNO number(5),
    
    --SchedlID FK references SCHEDULE(ID)
    SchedID varchar2(10),
    
    --Type of the injection, value in { 0, 1, 2} | 0: basic injection dose, 1: basic injection dose, 2: booster dose  
    Type number(1),
    
    --Note on the injection
    Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table INJECTION 
add constraint PK_INJECTION primary key (PersonalID, InjID);


--Foreign Key
alter table INJECTION
add constraint FK_INJECTION_PerID foreign key (PersonalID) references PERSON(ID);

alter table INJECTION
add constraint FK_INJECTION_SchedID foreign key (SchedID) references SCHEDULE(ID);


--Check
alter table INNJECTION
add constraint CK_INJECTION_InjNO check (InjNO in (


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/
--Insert
create or replace procedure INJ_INSERT_RECORD (par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type , par_Note INJECTION.Note%type DEFAULT NULL)
as
	PreInj INJECTION%rowtype;
begin
	select * into PreInj
	from INJECTION
	where INJECTION.PersonalID = :new.PersonalID
	having InjNO = MAX(InjNO);

	--If cannot find a previous injection, it means this is the first injection. Then allow to register.
	EXCEPTION
		when no_data_found
 		then commit
	END;


/*	STORED FUNCTIONS	*/




/*	RECORDS	*/