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
--NO <= the number of Limit due to the register Time
create or replace trigger REG_NO_Limit
after insert on REGISTER
for each row
as
	set_NO REGISTER.NO%type;	
begin
	--Calc the NO of registion
	select REG_SIGNED_NO(:new.PersonalID, :new.SchedID, :new.Time) into set_NO

	if (set_NO = 0)
	then
		raise_application_error(10003, 'The registion is limited!')
	end if;
end;

--The registered injection must follow the spacing rule of the previous vaccine injection (if have)
--The vaccine used in the registered schedule must be compitable with the previous vaccine injection (if have)

create or replace trigger REG_VACCINATION_RULE
after insert on REGISTER
for each row
as
	PreInj INJECTION%rowtype;
	ConstCase CONSTRAINT%rowtype;
	RegVac VACCINE.ID%type;
	PreVac VACCINE.ID%type;
begin
	--Find the previous injection info
	select * into PreInj
	from INJECTION
	where INJECTION.PersonalID = :new.PersonalID
	having InjNO = MAX(InjNO);
	
	--If cannot find a previous injection, it means this is the first injection. Then allow to register.
	EXCEPTION
		when no_data_found
 		then commit
	END;

	--select out the vaccine used in the previous injection
	select VaccineID into PrevVac from PrevInj.SchedID;

	--select out the vaccine used in this registion
	select VaccineID into RegVac from :new.SchedID;
	
	--Reference the PreInj to CONSTRAINT cases to select out the rule
	select * into ConstCase
	from CONSTRAINT
	where CONSTRAINT.InjectionNO = PrevInj.InjNO
	and CONSTRAINT.VaccineID = PreVac
	and CONSTRAINT.PreDose = INJ_Difference(:new.PersonalID);
	
	--Check spacing rule: :new.OnDate - OnDate from PrevInj.SchedID must equal ConstCase.NextDistance
	if (:new.OnDate - (select OnDate from SCHEDULE where SCHEDULE.ID = PrevInj.SchedID) < ConstCase.NextDistance-3)
	then
		raise_application_error(100004, 'Cannot register to this schedule due to the invalid in spacing rule!')
	end if;

	--Check vaccine combination rule: vaccine from registered schedule must be contained in ConstCase.NextDose	
	if (CONTAINS(ConstCase.NextDose, RegVac, 1) > 0)
	then
		comit
	else
		raise_application_error(10005, 'Cannot register to this schedule due to the incompitable with the previous injection!')
	end if;

end REG_VACCINATION_RULE;




/*	STORED PROCEDURES	*/
--Insert value for a registion
create or replace procedure REG_INSERT_RECORD (par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_TimeReg REGISTER.Time%type)
as
	set_NO REGISTER.NO%type;
begin
	--Use S_FUNC to calculate the NO of registion
	select REG_SIGNED_NO(:new.PersonalID, :new.SchedID, :new.Time) into set_NO

	--insert new registion
	insert into REGISTER(PersonalID, SchedID, Time, NO, Status, Image, Note) values (par_PersonalID, par_SchedID, par_TimeReg, set_NO, 0, NULL, NULL);

	SCHED_INC_REG(SchedID, par_TimeReg);
end REG_INSERT_RECORD;

--Canceled a registion	
create or replace procedure REG_DELETE_RECORD(par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type)
as
	TimeReg REGISTER.Time%type;
begin
	select TimeReg = Time
	from REGISTER
	where REGISTER.PersonalID = par_PersonalID
	and REGISTER.SchedID = par_SchedID;

	delete *
	from REGISTER
	where REGISTER.PersonalID = par_PersonalID
	and REGISTER.SchedID = par_SchedID;

	SCHED_DEC_REG(par_SchedID, TimeReg);

end REG_DELETE_RECORD;


/*	STORED FUNCTIONS	*/
--Return the NO of registion. If the registion meet the limit, return 0
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
	
	return (RegNumber + 1);

end REG_SIGNED_NO;

/*	RECORDS	*/