---....
--20520418 - Ä?áº·ng Nghiá»‡p CÆ°á»?ng
/*          TABLE: REGISTER          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table REGISTER
(
	--PersonalID of a person
	PersonalID varchar2(12),

	--ScheduleID of a schedule
	SchedID varchar2(26),
	
    --Dose type
    DoseType varchar2(50),
    
	--The time for injection (morning, afternoon or night)
	Time number(1),
	
	--Number order of registion
	NO number(5),
	
	--Status of the registered injection (registered, attended, injected, canceled)
	Status number(1),

	--Image of the vaccinated confirmation paper signed by the organization hold the schedule
	Image blob,
	
	--Note of the registion
	Note varchar2(2000)
);



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
add constraint CK_REG_Time check(Time in (0,1,2));

alter table REGISTER
add constraint CK_REG_Status check(Status in (0,1,2,3));

alter table REGISTER
add constraint CK_REG_DoseType check(DoseType in ('basic', 'booster', 'repeat'));



                /*	TRIGGERS	*/
--NO <= the number of Limit due to the register Time
create or replace trigger REG_NO_LIMIT
before insert on REGISTER
for each row
declare
	set_NO REGISTER.NO%type;	
begin
	--Use S_FUNC to calculate the NO of registion
	set_NO := REG_SIGNED_NO(:new.PersonalID, :new.SchedID, :new.Time);
    
	if (set_NO = 0)
	then
		raise_application_error(100003, 'The registion is limited!');
	end if;
end REG_NO_LIMIT;


--The registered schedule must follow the rule of vaccination (spacing time and vaccine type)
--+ Spacing time: the registered injection must follow the spacing rule of the previous vaccine injection (if have)
--+ Vaccine tpy: the vaccine used in the registered schedule must be compitable with the previous vaccine injection (if have)

create or replace trigger REG_VACCINATION_RULE
before insert on REGISTER
for each row
as
	PreInj INJECTION%rowtype;
	ParCase PARAMETER%rowtype;
	RegVac VACCINE.ID%type;
	PreVac VACCINE.ID%type;
begin
    --set date format before process date data
    alter session set NLS_DATE_FORMAT='DD-MM-YYYY';

	--Find the previous injection info
	select * into PreInj
	from INJECTION
	where INJECTION.PersonalID = :new.PersonalID
    and rownum = 1
    order by InjNO desc
	
	/* Resolve this EXCEPTION in REG_INSERT_RECORD
	--If cannot find a previous injection, it means this is the first injection. Then allow to register.
	EXCEPTION
		when no_data_found
 		then commit
	END;
	*/

	--Check the completed doses: If the citizen have done 4 doses (2 basic, 1 booster, 1 repeat) or 3 doses (2 basic, 1 repeat), she cannot register anymore. => Previous dose is repeat type can not register
	if ( PreInj.Type = repeat )
	then
		raise_application_error(10006,'You have completed all vaccination doses!') 
	end if;


	--select out the vaccine used in the previous injection
	select VaccineID into PrevVac 
	from SCHEDULE
	where SCHEDULE.ID = PreInj.SchedID;
	
	--select out the vaccine used in this registion
	select VaccineID into RegVac 
	from SCHEDULE
	where SCHEDULE.ID = :new.SchedID;
	
	--Reference the PreInj to PARAMETER cases to select out the rule
	select * into ParCase
	from PARAMETER
	where PARAMETER.InjectionNO = PrevInj.InjNO
	and PARAMETER.VaccineID = PreVac
	and PARAMETER.PreDose = INJ_Difference(:new.PersonalID);
	
	

	--Check spacing rule: :new.OnDate - OnDate from PrevInj.SchedID must equal ParCase.MinDistance
	if (:new.OnDate - (select OnDate from SCHEDULE where SCHEDULE.ID = PrevInj.SchedID) < ParCase.MinDistance-3)
	then
		raise_application_error(100004, 'Cannot register to this schedule due to the invalid in spacing rule!')
	end if;

	--Check vaccine combination rule: vaccine from registered schedule must be contained in ParCase.NextDose	
	if (CONTAINS(ParCase.NextDose, RegVac, 1) > 0)
	then
		commit
	else
		raise_application_error(10005, 'Cannot register to this schedule due to the incompitable with the previous injection!')
	end if;

end REG_VACCINATION_RULE;

--The citizen whose vaccination target type is vaccination delaying or incompitable vaccination cannot register
--The citizen affected by Covid-19 can complete basic dose after healing. If you have completed basic dose before affected by Covid-19, it doesn't necessary to make an additional dose. 
--Summary: citizen can't do vaccination during affected time, and can do vaccination immedietly after healing but still follow the vaccination rule about spacing time and vaccine type

create or replace trigger REG_VACCINATION_TARGET
after insert on REGISTER
as
	LastHealth HEALTH%rowtype;
begin
	--Select out the last medical declaration of the citizen
	select * into LastHealth
	from HEALTH
	where HEALTH.PersonalID = :new.PersonalID
	having Health.FilledDate = MAX(Health.FilledDate)
	
	--Check vaccination target type
	if ( SUBSTR(LastHealth.Healths, 4, 1) = '1' )
	then	
		raise_application_error(100005, 'You can not register vaccination due to your target type!')
	end if;

	--Check Covid-19 affected
	if ( (SUBSTR(LastHealth.Healths, 3, 1) = '1') and (extract(day from SYSDATE - LastHealth.FilledDate) <= 14) )
	then
		raise_application_error(100006, 'You have been affected by Covid-19 recent days, please wait until you completely healed')
	end if;

end REG_VACCINATION_TARGET;



                /*	STORED PROCEDURES	*/
--Insert value for a registion
create or replace procedure REG_INSERT_RECORD (par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_TimeReg REGISTER.Time%type)
as
	set_NO REGISTER.NO%type;
begin
	--Use S_FUNC to calculate the NO of registion
	select REG_SIGNED_NO(:new.PersonalID, :new.SchedID, :new.Time) into set_NO
	
	--Check the type of registing dose
	--Find the previous injection info
	select * into PreInj
	from INJECTION
	where INJECTION.PersonalID = :new.PersonalID
	and rownum = 1
	order by InjNo desc;
	
	--If cannot find a previous injection, it means this is the first injection. Then allow to register.
	EXCEPTION
		when no_data_found
 		then commit
	END;

	if (PreInj.InjNO = 2)
	then
		--Ask user to input type of register dose
	end if;
    
	--set date format before process date data
    alter session set NLS_DATE_FORMAT='DD/MM/YYYY';
	--insert new registion
	insert into REGISTER(PersonalID, SchedID, Type, Time, NO, Status, Image, Note) values (par_PersonalID, par_SchedID, par_Type, par_TimeReg, set_NO, 0, NULL, NULL);

end REG_INSERT_RECORD;



--Canceled a registion	
create or replace procedure REG_DELETE_RECORD (par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type)
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

--Update a registion status
create or replace procedure REG_UPDATE_STATUS 
(par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_Status REGISTER.Status%type)
as
    var_Time REGISTER.Time%type;
begin
    --select out the time of registion
    select Time into var_Time
    from REGISTER REG
    where REG.PersonalID = par_PersonalID
    and REG.SchedID = par_SchedID;
    
	--Update status
	update REGISTER
	set Status = par_Status
	where REGISTER.PersonalID = par_PersonalID
	and REGISTER.SchedID = par_SchedID

	--If the registion is completed by Injected status, insert new injection for the citizen
	if (par_Status = 2)
	then
		INJ_INSERT_RECORD(par_PersonalID, par_SchedID, par_Note DEFAULT NULL);
	end if;
    
    --If the registion is canceled, decrease the number of registion
    if (par_Status = 3)
    then 
        SCHED_DEC_REG(par_SchedID, TimeReg);
    end if;
end;

--Change time of a registion
REG_UPDATE_TIME(par_PersonalID, par_SchedID, par_Time) 

--Update a vaccination paper signed by the shecd-holding ORG
REG_UPDATE_IMAGE(par_PersonalID, par_SchedID, par_Img) 


                /*	STORED FUNCTIONS	*/
--Return the NO of registion. If the registion meet the limit, return 0
create or replace function REG_SIGNED_NO 
(par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_Time REGISTER.Time%type)
return number is
	--par_ to get the present value of the registered time limit number
	RegNumber REGISTER.NO%type;
	LimitReg REGISTER.NO%type;
begin
	--from the registered time, 
    --take out its limit number and number of registion from SCHEDULE
        
    if (par_Time = 0)
    then
        select LimitDay, DayRegistered
        into LimitReg, RegNumber
        from SCHEDULE SCHED
        where SCHED.ID = par_SchedID;
    elsif (par_Time = 1)
    then
        select LimitNoon, NoonRegistered
        into LimitReg, RegNumber
        from SCHEDULE SCHED
        where SCHED.ID = par_SchedID;
    elsif (par_Time = 2)
    then
        select LimitNight, NightRegistered
        into LimitReg, RegNumber
        from SCHEDULE SCHED
        where SCHED.ID = par_SchedID;
    else
        raise_application_error(100001, 'Time registion not found!');
    end if;	

	--If the number of registion meet the limit of at that time, return 0
	if (RegNumber = LimitReg)
	then
		return 0;
	end if;

    --Else return the NO for registion
	return (RegNumber + 1);

end REG_SIGNED_NO;

/*	RECORDS	*/


