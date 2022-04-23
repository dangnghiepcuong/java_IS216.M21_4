--20520148 - Dang Nghiep Cuong
/*          TABLE: REGION          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table REGION
(
	--Province code
	Code int,

	--Province name
	Name varchar2(50)
);

/*	CONSTRAINT	*/
--Primary Key
alter table REGION
add constraint PK_REGION primary key (Code);

--Foreign Key

--Check
alter table REGION
add constraint CK_Name check(Name is unique);


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/