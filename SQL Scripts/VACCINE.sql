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
create or replace procedure VAC_INSERT_RECORD
(par_ID VACCINE.ID%type, 
par_Name VACCINE.Name%type, 
par_Technology VACCINE.Technology%type, 
par_Country VACCINE.Country%type, 
par_Note VACCINE.Note%type DEFAULT NULL)
as
begin
    --Insert
    insert into VACCINE(ID, Name, Technology, Country, Note)
        values(par_ID, par_Name, par_Technology, par_Country, par_Note);
        
    --Duplicate ID or name
    EXCEPTION
        when DUP_VAL_ON_INDEX
        then
            raise_application_error(10000,'Duplicate ID or name!');
end VAC_INSERT_RECORD;



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/