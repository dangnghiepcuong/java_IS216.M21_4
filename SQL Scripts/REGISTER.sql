--20520418 - Ä?áº·ng Nghiá»‡p CÆ°á»?ng
/*          TABLE: REGISTER          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table REGISTER
(
	--PersonalID of a person
	PersonalID varchar2(12),

	--ScheduleID of a schedule
	SchedID varchar2(15),
	
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
		raise_application_error(-20003, 'The registion is limited!');
	end if;
end REG_NO_LIMIT;


create or replace trigger REG_VACCINATION_RULE
--The registered schedule must follow the rule of vaccination (spacing time and vaccine type)
--+ Dose type: If the citizen have done 4 doses (2 basic, 1 booster, 1 repeat) or 3 doses (2 basic, 1 repeat), she cannot register anymore. => Previous dose is repeat type can not register
--+ Spacing time: the registered injection must follow the spacing rule of the previous vaccine injection (if have)
--+ Vaccine type: the vaccine used in the registered schedule must be compitable with the previous vaccine injection (if have)
before insert on REGISTER
for each row
declare
	PreInj INJECTION%rowtype;
    LastReg REGISTER%rowtype;
	ParCase PARAMETER%rowtype;
	RegVac VACCINE.ID%type;
	PreVac VACCINE.ID%type;
    var_PreOnDate SCHEDULE.OnDate%type;
    var_OnDate SCHEDULE.OnDate%type;
    var_Contains_Pos int;
    var_Diff_Days number;
begin

	--select out the previous injection info
	select * into PreInj
	from INJECTION INJ
	where INJ.PersonalID = :new.PersonalID
    and rownum = 1
    order by InjNO desc;

	--check the completed doses: If the citizen have done 4 doses (2 basic, 1 booster, 1 repeat) or 3 doses (2 basic, 1 repeat), 
    --she cannot register anymore until the next notification from MOH. 
    --=> If the previous dose is 'repeat' type, can not register.
	if ( PreInj.DoseType = 'repeat' )
	then
		raise_application_error(-20000,
        'You have completed all vaccination doses!'); 
	end if;

	--select out the vaccine used in the previous injection
	select VaccineID into PreVac 
	from SCHEDULE SCHED
	where SCHED.ID = PreInj.SchedID;

	--select out the vaccine used in this registion
	select VaccineID into RegVac 
	from SCHEDULE SCHED
	where SCHED.ID = :new.SchedID;

	--Reference the PreInj to PARAMETER cases to select out the rules
	select * into ParCase
	from PARAMETER PAR
	where PAR.InjectionNO = PreInj.InjNO
	and PAR.VaccineID = PreVac
	and PAR.DiffDoses = INJ_DIFFERENCE(:new.PersonalID)
    and PAR.DoseType = PreInj.DoseType;

    --select out the dates of 2 injections
    select OnDate into var_PreOnDate
    from SCHEDULE SCHED
    where SCHED.ID = PreInj.SchedID;
    
    select OnDate into var_OnDate
    from SCHEDULE SCHED
    where SCHED.ID = :new.SchedID;
    
    --Check spacing rule between 2 injections      
    if (abs(months_between(var_OnDate, var_PreOnDate)) < (ParCase.MinDistance-3)/30)
	then
		raise_application_error(-20001, 
        'Cannot register to this schedule due to the invalid in spacing rule!');
	end if;

	--Check vaccine combination rule: vaccine from registered schedule must be contained in ParCase.NextDose	
    if (ParCase.NextDose not like '%'||RegVac||'%')
	then
		raise_application_error(-20002, 
        'Cannot register to this schedule due to the incompitable with the previous injection!');
	end if;

    --If cannot find a previous injection, it means this is the first injection. Then allow to register.
	EXCEPTION
		when no_data_found
        then NULL;
end REG_VACCINATION_RULE;



create or replace trigger REG_VACCINATION_TARGET
--The citizen whose vaccination target type is vaccination delaying or incompitable vaccination cannot register
--The citizen affected by Covid-19 can complete basic dose after healing. If you have completed basic dose before affected by Covid-19, it doesn't necessary to make an additional dose. 
--Summary: citizen can't do vaccination during affected time, and can do vaccination immedietly after healing but still follow the vaccination rule about spacing time and vaccine type.
before insert on REGISTER
for each row
declare
	LastHealth HEALTH%rowtype;
    LastReg REGISTER%rowtype;
    var_BirthDay PERSON.BirthDay%type;
begin   
    --select out the day of birth to calc age
    select BirthDay into var_BirthDay
    from PERSON
    where PERSON.ID = :new.PersonalID;
    
    --Check age
    if ( months_between(sysdate, var_BirthDay) < 60)
    then
        raise_application_error(-20007,
        'Your age is lower than the regulation!');
    end if;

    --select out the last medical declaration of the citizen
	select * into LastHealth
	from HEALTH
	where HEALTH.PersonalID = :new.PersonalID
	and rownum = 1
    order by ID desc;
    
    --Check expire date of filled form (within 7 days)
    if ( months_between(sysdate, LastHealth.FilledDate) > 0.25) 
    then
        raise_application_error(-20008,'You must fill out medical form before registion with 7 days');
    end if;
    
	--Check vaccination target type
	if ( SUBSTR(LastHealth.Healths, 4, 1) = '1' )
	then	
		raise_application_error
        (-20005, 'You can not register vaccination due to your target type!');
	end if;

	--Check Covid-19 affected
	if ( (SUBSTR(LastHealth.Healths, 3, 1) = '1') 
    and (abs(months_between(SYSDATE, LastHealth.FilledDate)) < 0.46) ) --14/30=0.466667
	then
		raise_application_error
        (-20006, 'You have been affected by Covid-19 recent days, please wait until you completely healed');
	end if;
    
    --select out the last register
    
    EXCEPTION
        when no_data_found
        then
            NULL;
end REG_VACCINATION_TARGET;


create or replace trigger REG_VACCINATION_AGE_STATUS
before insert on REGISTER
for each row
declare
    LastReg number(1);
    var_BirthDay PERSON.BirthDay%type;
begin
    --select out the day of birth to calc age
    select BirthDay into var_BirthDay
    from PERSON
    where PERSON.ID = :new.PersonalID;

    --Check age
    if ( months_between(sysdate, var_BirthDay) < 60)
    then
        raise_application_error(-20007,
        'Your age is lower than the regulation!');
    end if;

    --select out the last registion
    select Status into LastReg
    from REGISTER REG, SCHEDULE SCHED
    where REG.PersonalID = :new.PersonalID
    and REG.SchedID = SCHED.ID
    and rownum = 1
    order by OnDate desc;

    if (LastReg < 2)
    then
        raise_application_error(-20008,
        'You must complete your previous registion before register a new one!');
    end if;

    EXCEPTION
        when no_data_found
        then
            NULL;    
end REG_VACCINATION_AGE_STATUS;



                /*	STORED PROCEDURES	*/
--Insert value for a registion
create or replace procedure REG_INSERT_RECORD 
(par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_Time REGISTER.Time%type)
as
	set_NO REGISTER.NO%type;
    var_DoseType REGISTER.DoseType%type;
    PreInj INJECTION%rowtype;
begin
	--Use S_FUNC to calculate the NO of registion
	set_NO := REG_SIGNED_NO(par_PersonalID, par_SchedID, par_Time);
    
	--Check the type of registing dose
	--Find the previous injection info
	select * into PreInj
	from INJECTION
	where INJECTION.PersonalID = par_PersonalID
	and rownum = 1
	order by InjNo desc;

    if (PreInj.InjNO = 1)
	then
		var_DoseType := 'basic';
	elsif (PreInj.InjNO = 2)
	then
		var_DoseType := 'booster';
    else
        var_DoseType := 'repeat';
	end if;

	--insert new registion
	insert into REGISTER(PersonalID, SchedID, DoseType, Time, NO, Status, Image, Note) 
    values (par_PersonalID, par_SchedID, var_DoseType, par_Time, set_NO, 0, NULL, NULL);
    --increase the registered number in schedule
    SCHED_INC_REG(par_SchedID, par_Time);
    commit;
    
    --If cannot find a previous injection, it means this is the first injection. Then allow to register.
	EXCEPTION
		when no_data_found
        then
        var_DoseType := 'basic';
        insert into REGISTER(PersonalID, SchedID, DoseType, Time, NO, Status, Image, Note) 
    values (par_PersonalID, par_SchedID, var_DoseType, par_Time, set_NO, 0, NULL, NULL);
 		--increase the registered number in schedule
    SCHED_INC_REG(par_SchedID, par_Time);
        commit;
        
end REG_INSERT_RECORD;



--Canceled a registion	
create or replace procedure REG_DELETE_RECORD (par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type)
as
	TimeReg REGISTER.Time%type;
begin
	select Time into TimeReg
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
    var_DoseType REGISTER.DoseType%type;
    var_n_Injection INJECTION.InjNO%type;
begin
    --select out the time of registion
    select Time, DoseType into var_Time, var_DoseType
    from REGISTER REG
    where REG.PersonalID = par_PersonalID
    and REG.SchedID = par_SchedID;

	--Update status
	update REGISTER
	set Status = par_Status
	where REGISTER.PersonalID = par_PersonalID
	and REGISTER.SchedID = par_SchedID;

    --If the citizen is ready for vaccination
    if (par_Status = 1)
    then
        commit;
    --If the registion is completed by Injected status, insert new injection for the citizen
	elsif (par_Status = 2)
	then
        select COUNT(*) into var_n_Injection 
        from INJECTION INJ
        where INJ.PersonalID = par_PersonalID;

		INJ_INSERT_RECORD(par_PersonalID, var_n_Injection, par_SchedID, 
        var_DoseType);
	--If the registion is canceled, decrease the number of registion
    elsif (par_Status = 3)
    then 
        SCHED_DEC_REG(par_SchedID, var_Time);
    end if;
    
    --There was no injection before, this the first injection
    EXCEPTION
        when no_data_found
        then
            INJ_INSERT_RECORD(par_PersonalID, 1, par_SchedID, var_DoseType);
end REG_UPDATE_STATUS;

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


