--20521720 - Trương Ý Nhi
/*          TABLE: HEALTH          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table HEALTH
(
	PersonalID varchar2(12),
	FilledDate date,
	Healths varchar2(20),
	Note varchar2(2000)
)

/*	CONSTRAINT	*/
--Primary Key
alter table HEALTH
add constraint PK_HEAL primary key (PersonalID)


--Foreign Key
alter table HEALTH
add constraint FK_HEAL_PERSON foreign key (PersonalID) references PERSON(ID)


--Check



/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/