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
    
    
    --Note on the injection
    Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table INJECTION 
add constraint PK_INJECTION primary key (PersonalID, InjID);


--Foreign Key
alter table INJECTION
add constraint FK_INJ_PERSON foreign key (PersonalID) references PERSON(ID);

alter table INJECTION
add constraint FK_INJ_SCHED foreign key (SchedID) references SCHEDULE(ID);


--Check
alter table INNJECTION
add constraint CK_INJECTION_InjNO check (InjNO in (1, 2, 3, 4));

alter table INNJECTION
add constraint CK_SchedID check(SchedID is not null);

alter table INNJECTION
add constraint CK_Type check(Type is not null and Type in (0,1,2));


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/