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
after update on REGISTER
for each row
as
	set_NO REGISTER.NO%type;	
begin
	select REG_SIGNED_NO(
	if (:new.NO

/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/
--Put in PersonalID, SchedId, TimeRegistered. Return the NO of registion. If the registion meet the limit, return 0
create or replace function REG_SIGNED_NO (par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_TimeReg REGISTER.Time%type)
return number is
	--par_ to get the present value of the registered time limit number
	RegNumber REGISTER.NO%type;
	LimitReg REGISTER.NO%type;
begin
	--from the registered time, select its limit number from SCHEDULE
	select RegNumber, LimitReg
		case par_TimeReg when 0 then DayRegistered, LimitDay
			when 1 then NoonRegistered, LimitNoon
			when 2 then NightRegistered, LimitNight
		else
			raise_application_error(100001, 'Time registion not found!')
		end
	from SCHEDULE
	where SCHEDULE.ID = par_SchedID
	and SCHEDULE.PersonalID = par_PersonalID;	
	
	--If the number of registion meet the limit of at that time, return 0
	if (RegNumber = LimitReg)
	then
		return 0
	end if;
	
	--After getting the Limit number
	--Set the NO for registion by Limit number + 1
	update REGISTER
	set NO = RegNumber + 1;
	where REGISTER.PersonalID = par_PersonalID
	and REGISTER.SchedID = par_SchedID;
end REG_SIGNED_NO;

/*	RECORDS	*/