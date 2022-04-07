--20521720 - Trương �? Nhi
/*          TABLE: VACCINE          */
--Create a table struct without constraint
--<Attribute name> <Data type>
create table VACCINE 
(
    ID varchar2(8),
    Name varchar2(100),
    Technology varchar2(100),
    Country varchar2(100),
    Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table VACCINE add constraint PK_VACCINE primary key (ID);


--Foreign Key



--Check
alter table VACCINE add constraint UNI_Name unique (Name);

/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/