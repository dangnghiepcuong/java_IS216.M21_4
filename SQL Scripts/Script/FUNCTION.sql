--------------------------------------------------------
--  File created - Tuesday-April-26-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Function ACC_CONVERT_SEQ_TO_STR
--------------------------------------------------------
  CREATE OR REPLACE EDITIONABLE FUNCTION "ACC_CONVERT_SEQ_TO_STR" 
(par_Last_Seq int)
return varchar2 is
begin
	if ( par_Last_Seq < 10 )
	then
		return ('00' || TO_CHAR(par_Last_Seq));
	end if;
    
    if ( par_Last_Seq < 100 )
	then
		return ('0' || TO_CHAR(par_Last_Seq));
	end if;
    
    if ( par_Last_Seq <1000 )
    then
        return (TO_CHAR(par_Last_Seq));
    end if;

	return TO_CHAR(par_Last_Seq);

end ACC_CONVERT_SEQ_TO_STR;

--------------------------------------------------------
--  DDL for Function ANN_ID
--------------------------------------------------------
  CREATE OR REPLACE EDITIONABLE FUNCTION "ANN_ID" (par_OrgID ANNOUNCEMENT.OrgID%type)
return number
is
    par_ID number;
    temp_OrgID ANNOUNCEMENT.OrgID%type;
begin 
    --select out the last ID of the ORG in the Organization
    select OrgID into temp_OrgID
    from ANNOUNCEMENT
    where OrgID=par_OrgID and rownum=1
    order by OrgID desc;

    --The next value of ID
    par_ID := TO_NUMBER(SUBSTR(TO_CHAR(par_OrgID), -3, 3)) + 1;
	
	return par_ID;

EXCEPTION 
    when no_data_found
 	then par_ID := 1;

    return par_ID;
end "ANN_ID";

--------------------------------------------------------
--  DDL for Function PERSON_AGE
--------------------------------------------------------
  CREATE OR REPLACE EDITIONABLE FUNCTION "PERSON_AGE" (par_PersonalID PERSON.ID%type)
RETURN NUMBER 
AS 
    var_BirthDay PERSON.BirthDay%type;
    var_Age number;
BEGIN
    --select out day of birth
    select BirthDay into var_BirthDay
    from PERSON
    where PERSON.ID = par_PersonalID;
    
    --count months between sysdate and birthday then div 12
    var_Age := trunc(months_between(sysdate, var_BirthDay)/12);
    RETURN var_Age;
END PERSON_AGE;

--------------------------------------------------------
--  DDL for Function HEAL_FORM_ID
--------------------------------------------------------
  CREATE OR REPLACE EDITIONABLE FUNCTION "HEAL_FORM_ID" (par_PersonalID PERSON.ID%type)
return number
is
    var_ID HEALTH.ID%type;
begin
    --select out the last filled form ID
    select ID into var_ID
    from HEALTH HEAL
    where HEAL.PersonalID = par_PersonalID
    and rownum = 1
    order by ID desc;

    return var_ID + 1;

    EXCEPTION
        when no_data_found
        then
            return 1;
end HEAL_FORM_ID;
--------------------------------------------------------
--  DDL for Function INJ_COUNT_INJ
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "INJ_COUNT_INJ" (par_PersonalID PERSON.ID%type)
return number
is
    var_n_Injection INJECTION.InjNO%type;
begin            
    select COUNT(*) into var_n_Injection 
    from INJECTION INJ
    where INJ.PersonalID = par_PersonalID;

    return var_n_Injection;
end INJ_COUNT_INJ;

--------------------------------------------------------
--  DDL for Function INJ_DIFFERENCE
--------------------------------------------------------
  CREATE OR REPLACE EDITIONABLE FUNCTION "INJ_DIFFERENCE" (par_PersonalID PERSON.ID%type)
return number 
is
    cursor c_INJ 
    is
        select SchedID
        from INJECTION INJ
        where INJ.PersonalID = par_PersonalID;

    crow_INJ_be c_INJ%rowtype;
    crow_INJ_af c_INJ%rowtype;

    var_VaccineID_1 VACCINE.ID%type;
    var_VaccineID_2 VACCINE.ID%type;
    
    var_Distinct int;
begin
      
    select COUNT(distinct SCHED.VaccineID) into var_Distinct
    from INJECTION INJ, SCHEDULE SCHED
    where INJ.PersonalID = par_PersonalID
    and INJ.SchedID = SCHED.ID;
    
    if (var_Distinct > 1)
    then
        return 1;
    end if;
    
    return 0;
end INJ_DIFFERENCE;
--------------------------------------------------------
--  DDL for Function ORG_COUNT_SCHED
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "ORG_COUNT_SCHED" 
(par_OrgID ORGANIZATION.ID%type, par_StartDate date DEFAULT NULL, par_EndDate date DEFAULT NULL)
return number 
is 
    Count_Sched int;
    var_StartDate date;
    var_EndDate date;
begin
    --set value 0 for Count_Sched
    Count_Sched := 0; 

    var_StartDate := par_StartDate;
    var_EndDate := par_EndDate;

    --if StartDate > EndDate, then swap their value.
    if (par_StartDate > par_EndDate) 
    then
        begin
        var_StartDate := par_EndDate;
        var_EndDate := par_StartDate;
        end;
    end if;

    --from the SCHEDULES, count OrgID
    select COUNT(OrgID) into Count_Sched
    from SCHEDULE SCHED
    where SCHED.OnDate >= par_StartDate 
    and SCHED.OnDate <= par_EndDate;
    
    return Count_Sched;
end ORG_COUNT_SCHED;
--------------------------------------------------------
--  DDL for Function REG_SIGNED_NO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "REG_SIGNED_NO" 
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
    end if;	

	--If the number of registion meet the limit of at that time, return 0
	if (RegNumber = LimitReg)
	then
		return 0;
	end if;

    --Else return the NO for registion
	return (RegNumber + 1);

end REG_SIGNED_NO;
--------------------------------------------------------
--  DDL for Function SCHED_GENERATE_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "SCHED_GENERATE_ID" 
(par_OrgID ORGANIZATION.ID%type, par_OnDate date)
return varchar2 is
    StringDate varchar2(10);
    n_Scheds_OnDate number(2);
begin
    --Convert par_OnDate to string
    StringDate := TO_CHAR(SUBSTR(TO_CHAR(par_OnDate,'dd-MM-yyyy'),1,2)) 
                    || TO_CHAR(SUBSTR(TO_CHAR(par_OnDate,'dd-MM-yyyy'),4,2)) 
                    || TO_CHAR(SUBSTR(TO_CHAR(par_OnDate,'dd-MM-yyyy'),7,4));

    --Count the number of scheds of the org on par_OnDate to calc the NO of sched
    select COUNT(ID) into n_Scheds_OnDate
    from SCHEDULE
    where SCHEDULE.OrgID = par_OrgID
    and SCHEDULE.OnDate = par_OnDate;

    --SchedID = OrgID + Date + NO of sched
    return par_OrgID || StringDate || TO_CHAR(n_Scheds_OnDate+1);

    --Exception when there is no sched before
    EXCEPTION
        when no_data_found
        then
            n_Scheds_OnDate := 0;
            return par_OrgID || StringDate || TO_CHAR(n_Scheds_OnDate+1);

end SCHED_GENERATE_ID;
