--20520418 - Dang Nghiep Cuong
/*          TABLE: VACCINE          */
--Create a table struct without constraint
--<Attribute name> <Data type>
create table STATISTIC
(
	--Title of statistic
	Title varchar2(200),

	--Statistic data
	Data number
);


/*	CONSTRAINT	*/
--Primary Key
alter table STATISTIC add constraint PK_STAT primary key (Title);

--Foreign Key

--Check
alter table STATISTIC add constraint CK_STAT_Data check(Data>0);

/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/