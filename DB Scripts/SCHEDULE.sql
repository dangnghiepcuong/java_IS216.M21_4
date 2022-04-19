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
);



/*	CONSTRAINT	*/
--Primary Key
alter table SCHEDULE
add constraint PK_SCHED primary key (ID);

--Foreign Key
alter table SCHEDULE
add constraint FK_SCHED_ORG foreign key OrgID references ORGANIZATION(ID);

alter table SCHEDULE
add constraint FK_SCHED_VAC foreign key VaccineID references VACCINE(ID);

--Check
alter table SCHEDULE
add constraint CK_SCHED_Date CHECK(Date > SYSDATE);

/*	TRIGGERS	*/
-- DayRegistered <= LimitDay
create or replace trigger SCHED_Limit_Registers
after insert or update on SCHEDULE
for each row
begin
	if (:new.DayRegistered > :new.LimitDay
	OR :new.NoonRegistered > :new.LimitNoon
	OR :new.NightRegistered > :new.LimitNight)
	then
		raise_application_error(10000, 'Number of register is limited!');
	end if;
end Limit_Registers;	

/*	STORED PROCEDURES	*/
 --Increase 1 registion at Time parameter
create or replace procedure SCHED_INC_REG(par_ID SCHEDULE.ID%type, par_RegTime REGISTER.Time%type)
as
begin
	if (par_RegTime = 0)
	then
		update SCHEDULE
		set DayRegistered = DayRegistered + 1
		where SCHEDULE.ID = par_ID
 	elseif (par_RegTime = 1)
	then
		update SCHEDULE
		set NoonRegistered = NoonRegistered + 1
		where SCHEDULE.ID = par_ID
	elseif (par_RegTime = 2)
	then
		update SCHEDULE
		set NightRegistered = NightRegistered + 1
		where SCHEDULE.ID = par_ID
	endif;
end;

--Decrease 1 registion at Time parameter
create or replace procedure SCHED_DEC_REG(par_SchedID SCHEDULE.ID%type, RegTime REGISTER.Time%type)
as
begin
	if (par_RegTime = 0)
	then
		update SCHEDULE
		set DayRegistered = DayRegistered - 1
		where SCHEDULE.ID = par_ID
 	elseif (par_TimeReg = 1)
	then
		update SCHEDULE
		set NoonRegistered = NoonRegistered - 1
		where SCHEDULE.ID = par_ID
	elseif (par_TimeReg = 2)
	then
		update SCHEDULE
		set NightRegistered = NightRegistered - 1
		where SCHEDULE.ID = par_ID
	endif;
end SCHED_DEC_REG;

/*	STORED FUNCTIONS	*/




/*	RECORDS	*/