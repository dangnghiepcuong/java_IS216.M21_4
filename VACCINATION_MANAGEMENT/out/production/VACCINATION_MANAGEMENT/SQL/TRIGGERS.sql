--------------------------------------------------------
--  File created - Friday-May-27-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Trigger PERSON_VALUE
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "PERSON_VALUE" 
BEFORE INSERT OR UPDATE ON PERSON 
FOR EACH ROW
BEGIN
    if (:new.FirstName like '% %')
    then
        raise_application_error(-20008, 'First name can not contain space!');
    end if;

    if (:new.BirthDay > sysdate)
    then
        raise_application_error(-20009, 'Birthday must not be a future day!');
    end if;

    if (:new.Email != '')
    then
        if (:new.Email like '% %' or :new.Email not like '%@%')
        then
            raise_application_error(-20010, 'Email must not contains space and must contains "@" !');
        end if;
    end if;
END;

--------------------------------------------------------
--  DDL for Trigger REG_VACCINATION_AGE_STATUS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "REG_VACCINATION_AGE_STATUS" 
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
        raise_application_error(-20000,
        'Your age is lower than the regulation!');
    end if;

    --select out the last registion
    select Status into LastReg
    from REGISTER REG
    where REG.PersonalID = :new.PersonalID
    and Status = 0 or Status = 1;

    if (LastReg < 2)
    then
        raise_application_error(-20001,
        'You must complete your previous registion before register a new one!');
    end if;

    EXCEPTION
        when no_data_found
        then
            NULL;    
end REG_VACCINATION_AGE_STATUS;

--------------------------------------------------------
--  DDL for Trigger REG_VACCINATION_RULE
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "REG_VACCINATION_RULE" 
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
		raise_application_error(-20002,
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
		raise_application_error(-20003, 
        'Cannot register to this schedule due to the invalid in spacing rule!');
	end if;

	--Check vaccine combination rule: vaccine from registered schedule must be contained in ParCase.NextDose	
    if (ParCase.NextDose not like '%'||RegVac||'%')
	then
		raise_application_error(-20004, 
        'Cannot register to this schedule due to the incompitable with the previous injection!');
	end if;

    --If cannot find a previous injection, it means this is the first injection. Then allow to register.
	EXCEPTION
		when no_data_found
        then NULL;
end REG_VACCINATION_RULE;

--------------------------------------------------------
--  DDL for Trigger REG_VACCINATION_TARGET
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "REG_VACCINATION_TARGET" 
--The citizen whose vaccination target type is vaccination delaying or incompitable vaccination cannot register
--The citizen affected by Covid-19 can complete basic dose after healing. If you have completed basic dose before affected by Covid-19, it doesn't necessary to make an additional dose. 
--The citizen who have not filled out medical form with 7 days can not register.
--Summary: citizen can't do vaccination during affected time, and can do vaccination immedietly after healing but still follow the vaccination rule about spacing time and vaccine type.
before insert on REGISTER
for each row
declare
	LastHealth HEALTH%rowtype;
    var_OnDate SCHEDULE.OnDate%type;
begin   
    --select out the last medical declaration of the citizen
	select * into LastHealth
	from HEALTH
	where HEALTH.PersonalID = :new.PersonalID
	and rownum = 1
    order by ID desc;

    --Check expire date of filled form (within 7 days)
    select OnDate into var_OnDate
    from SCHEDULE SCHED
    where SCHED.ID = :new.SchedID;

    if ( months_between(var_OnDate, LastHealth.FilledDate) > 0.25) 
    then
        raise_application_error(-20005,'You must fill out medical form before registion with 7 days');
    end if;
    
    --Check the ability to be affected by Covid-19 virus
    if ( SUBSTR(LastHealth.Healths, 4, 1) = '1' )
	then	
		raise_application_error
        (-20005, 'You can not register vaccination due to your target type!');
	end if;

	--Check vaccination target type
	if ( SUBSTR(LastHealth.Healths, 4, 1) = '1' )
	then	
		raise_application_error
        (-20005, 'You can not register vaccination due to your target type!');
	end if;

	--Check Covid-19 affected
	if ( SUBSTR(LastHealth.Healths, 3, 1) = '1') 
    --and (abs(months_between(SYSDATE, LastHealth.FilledDate)) < 0.46) ) 
	then
		raise_application_error
        (-20006, 'You have been affected by Covid-19 recent days, please wait until you completely healed');
	end if;

    EXCEPTION
        when no_data_found
        then
            raise_application_error
            (-20007,'You have not fill out any medical form within 7 days yet!');
end REG_VACCINATION_TARGET;

--------------------------------------------------------
--  DDL for Trigger SCHED_VALUE
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "SCHED_VALUE" 
BEFORE INSERT OR UPDATE ON SCHEDULE
FOR EACH ROW
BEGIN
    if (:new.LimitDay < 0 or :new.LimitNoon < 0 or :new.LimitNight < 0)
    then
        raise_application_error(-20010,'Number of registion can not be negative!');
    end if;

    if (:new.DayRegistered > :new.LimitDay or :new.NoonRegistered > :new.LimitNoon
    or :new.NightRegistered > :new.LimitNight)
    then
        raise_application_error(-20011,'Number of registion is limited!');
    end if;
END;

