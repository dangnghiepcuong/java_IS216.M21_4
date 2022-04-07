--20520418 - Đặng Nghiệp Cường
/*          TABLE: SCHEDULE          */
--Create a table struct without constraint
--<Attribute name> <Data type>

--Store data of schedules from organizations
create table SCHEDULE
(
	--Identity of a schedule, created from OrgID+DateNumber+NO
	ID varchar2(26),

	--OrganizationID FK references ORGANIZATION(ID)
	OrgID varchar2(16),

	--VaccineID FK references VACCINE(ID)
	VaccineID varchar2(8),

	--The serial of the using vaccine in the schedule
	Serial varchar2(100),

	--The number of limited registers in the morning
	LimitDay number(5),

	--The number of limited registers in the afternoon
	LimitNoon number(5),

	--The number of limited registers in the evening
	LimitNight number(5),

	--The number of registered in the morning
	DayRegister number(5),

	--The number of registered in the afternoon
	NoonRegister number(5),

	--The number of registered in the evening
	NightRegister number(5),

	--Note of record
	Note varchar2(2000)
)



/*	CONSTRAINT	*/
--Primary Key
alter table SCHEDULE
add constraint PK_SCHEDULE primary key

--Foreign Key
alter table SCHEDULE
add constraint FK_SCHED_ORG foreign key OrgID references ORGANIZATION(ID)

alter table SCHEDULE
add constraint FK_SCHED_VAC foreign key VaccineID references VACCINE(ID)

--Check
alter table SCHEDULE
add constraint CK_Date 


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/