--20520418 - Đặng Nghiệp Cường
/*          TABLE: REGISTER          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table REGISTER
(
	--PersonalID of a person
	PersonalID varchar2(12),

	--ScheduleID of a schedule
	SchedID varchar2(26),
	
	--The time for injection (morning, afternoon or night)
	Time number(1),
	
	--Number order of registion
	NO
	
	--Status of the registered injection (registered, attended, injected, canceled)
	Status number(1),

	--Image of the vaccinated confirmation paper signed by the organization hold the schedule
	Image blob,
	
	--Note of the registion
	Note varchar2(2000)
)



/*	CONSTRAINT	*/
--Primary Key
alter table REGISTER
add constraint PK_REG primary key (PersonalID, SchedID);

--Foreign Key
alter table REGISTER
add constraint FK_REG_PERSON foreign key (PersonalID) references PERSON(ID);

alter table REGISTER
add constraint FK_REG_SCHED foreign key (SchedID) references SCHEDULE(ID);

--Check
alter table REGISTER
add constraint CK_REG_Time CHECK(Time in (0,1,2));

alter table REGISTER
add constraint CK_REG_Status CHECK(Status in (0,1,2,3));


/*	TRIGGERS	*/
create or replace trigger REG_NO_Limit
after insert on REGISTER
for each row
begin
	

/*	STORED PROCEDURES	*/
create or replace procedure REG_SIGNED_NO (par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_TimeReg REGISTER.Time%type)
as
	LimitReg REGISTER.NO%type;
begin
	select LimitReg,
		case par_TimeReg when 0 then LimitDay
			when 1 then LimitNoon
			when 2 then LimitNight
		else
			raise_application_error(100001, 'Time registion not found!')
		end
	from SCHEDULE
	where SCHEDULE.ID = par_SchedID;
	
	update REGISTER
	set NO = LimitReg + 1;
	where REGISTER.PersonalID = par_PersonalID
	and REGISTER.SchedID = par_SchedID;
end REG_SIGNED_NO;

/*	STORED FUNCTIONS	*/




/*	RECORDS	*/