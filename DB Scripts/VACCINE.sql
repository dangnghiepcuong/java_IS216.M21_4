--20521720 - Truong Y Nhi
/*          TABLE: VACCINE          */
--Create a table struct without constraint
--<Attribute name> <Data type>
create table VACCINE 
(
	--ID of vaccine
    	ID varchar2(8),

	--Name of vaccine
    	Name varchar2(100),

	--Technology used to produce vaccine
    	Technology varchar2(100),

	--The country produces the vaccine
    	Country varchar2(100),

	--Note of the vaccine
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