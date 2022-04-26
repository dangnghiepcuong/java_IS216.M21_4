--------------------------------------------------------
--  File created - Tuesday-April-26-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACC_INSERT_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "ACC_INSERT_RECORD" 
(par_Username varchar2, par_Password varchar2, par_Role number, par_Status number, par_Note varchar2 DEFAULT NULL)
is   
begin 
     insert into ACCOUNT(Username, Password, Role, status, Note) values 
     (par_Username, par_Password, par_Role, par_Status, par_Note);    
     
     commit;
     
     EXCEPTION
        when DUP_VAL_ON_INDEX
        then
            raise_application_error(1000,'Username has been registered by another user!');
end ACC_INSERT_RECORD;

--------------------------------------------------------
--  DDL for Procedure ORG_INSERT_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "ORG_INSERT_RECORD" (par_ID ORGANIZATION.ID%type,                                            
                                             par_Province ORGANIZATION.Province%type,                                            
							   par_Note  ORGANIZATION.Note%type DEFAULT NULL)                                           
as 
begin
    --insert new ORGANIZATION
	insert into ORGANIZATION(ID, Province, Note) 
	values (par_ID, par_Province, par_Note);
    
    commit;
end ORG_INSERT_RECORD;

--------------------------------------------------------
--  DDL for Procedure ACC_CREATE_ORG
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "ACC_CREATE_ORG" 
(par_Quantity number, par_Province varchar2)
is
    Last_Seq int;
    temp_ID ORGANIZATION.ID%type;
    temp_String varchar2(100);
begin
    --select out the last ID of the ORG in the par_Province
    select ID into temp_ID
    from ORGANIZATION
    where Province = par_Province and rownum = 1
    order by ID desc;
    
    --The start ID is (the selected ID + 1)
    Last_Seq := TO_NUMBER(SUBSTR(TO_CHAR(temp_ID), -3, 3)) + 1;

    --Loop to create the continue ORGs with the serializable ID
    for i in Last_Seq .. Last_Seq + par_Quantity - 1
    loop
        --Create account
        temp_ID := TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i);
        ACC_INSERT_RECORD(temp_ID,  temp_ID, 1, 1, null);
        --Create ORG
        ORG_INSERT_RECORD(temp_ID, par_Province, null);
    end loop;
    
    commit;

--If there was no ORG in the par_Province, the start ID is 001
EXCEPTION
    when no_data_found
 	then Last_Seq := 1;
    
    --Loop to create the continue ORGs with the serializable ID
    for i in Last_Seq .. Last_Seq + par_Quantity - 1
    loop
        --Create account
        temp_ID := TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i);
        ACC_INSERT_RECORD(temp_ID, temp_ID, 1, 1, null);
        --Create ORGs
        ORG_INSERT_RECORD(temp_ID, par_Province, null);
    end loop;

end ACC_CREATE_ORG;
--------------------------------------------------------
--  DDL for Procedure ACC_DELETE_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "ACC_DELETE_RECORD" (par_Username varchar2)
is
begin
    delete from PERSON where PERSON.Phone = par_Username;
    delete from ACCOUNT where ACCOUNT.Username = par_Username;
    
    commit;
end ACC_DELETE_RECORD;

--------------------------------------------------------
--  DDL for Procedure ACC_UPDATE_PASSWORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "ACC_UPDATE_PASSWORD" 
(par_Username varchar2, par_OldPass varchar2, par_NewPass varchar2)
is
    Pass varchar2(128);
begin
    select Password into Pass from ACCOUNT where Username = par_Username;
    if (par_OldPass != Pass) then
        DBMS_Output.Put_line('Mat khau khong dung!');
    else
        update ACCOUNT set Password = par_NewPass 
        where  Username = par_Username;
    end if;
    
    commit;
end ACC_UPDATE_PASSWORD;
--------------------------------------------------------
--  DDL for Procedure HEAL_INSERT_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "HEAL_INSERT_RECORD" 
(par_ID HEALTH.ID%type,
par_PersonalID PERSON.ID%type, 
par_FilledDate HEALTH.FilledDate%type, 
par_Healths HEALTH.Healths%type, 
par_Note HEALTH.Note%type DEFAULT NULL)
as

begin
    insert into HEALTH(ID, PersonalID, FilledDate, Healths, Note)
    values(par_ID, par_PersonalID, par_FilledDate, par_Healths, par_Note);
    commit;
end HEAL_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure INJ_INSERT_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "INJ_INSERT_RECORD" 
(par_PersonalID PERSON.ID%type, 
par_InjNO INJECTION.InjNO%type, 
par_SchedID SCHEDULE.ID%type, 
par_DoseType INJECTION.DoseType%type,
par_Note INJECTION.NOte%type DEFAULT NULL)
as
begin
    insert into INJECTION(PersonalID, InjNO, SchedID, DoseType, Note)
    values (par_PersonalID, par_InjNO, par_SchedID, par_DoseType, par_Note);

    commit;
end INJ_INSERT_RECORD;

--------------------------------------------------------
--  DDL for Procedure ORG_UPDATE_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "ORG_UPDATE_RECORD" (par_ID ORGANIZATION.ID%type,
                                                par_Name ORGANIZATION.Name%type,
                                                par_District ORGANIZATION.District%type,
                                                par_Town ORGANIZATION.Town%type,
                                                par_Street ORGANIZATION.Street%type,
                                                par_Note ORGANIZATION.Note%type DEFAULT NULL)
as
begin
    	--Update record ORGANIZATION
    	update ORGANIZATION
    	set Name = par_Name,
        	District = par_District,
        	Town = par_Town,
        	Street = par_Street,
        	Note = par_Note
    	where ID = par_ID;
        
        commit;
end ORG_UPDATE_RECORD;
--------------------------------------------------------
--  DDL for Procedure PERSON_INSERT_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "PERSON_INSERT_RECORD" (par_ID PERSON.ID%type, 
    par_LastName PERSON.LastName%type, par_FirstName PERSON.FirstName%type,
    par_Birthday PERSON.Birthday%type, par_Gender PERSON.Gender%type,
    par_HomeTown PERSON.HomeTown%type, par_Province PERSON.Province%type,
    par_District PERSON.District%type, par_Town PERSON.Town%type,
    par_Street PERSON.Street%type,
    par_Phone PERSON.Phone%type, par_Email PERSON.Email%type DEFAULT NULL,
    par_Guardian PERSON.Guardian%type DEFAULT NULL, par_Note PERSON.Note%type DEFAULT NULL) 
as
begin
    --create new PERSON
    insert into PERSON(ID, LastName, FirstName, Birthday, Gender, HomeTown,
    Province, District, Town, Street, Phone, Email, Guardian, Note) 
    values (par_ID, par_LastName, par_FirstName, par_Birthday, par_Gender,
    par_HomeTown, par_Province, par_District, par_Town, par_Street, par_Phone,
    par_Email, par_Guardian, par_Note);

    commit;

    EXCEPTION
        when DUP_VAL_ON_INDEX
        then
            raise_application_error(1000,'ID has been registered by another user!');
end PERSON_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure PERSON_UPDATE_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "PERSON_UPDATE_RECORD" (par_ID PERSON.ID%type,
par_HomeTown PERSON.HomeTown%type, par_Province PERSON.Province%type,
par_District PERSON.District%type, par_Town PERSON.Town%type,
par_Street PERSON.Street%type, par_Phone PERSON.Phone%type,
par_Email PERSON.Email%type, par_Note PERSON.Note%type DEFAULT NULL) 
as
    temp_Pass ACCOUNT.Password%type;
    temp_User ACCOUNT.Username%type;
begin
    --Update PERSON except Phone
    update PERSON
    set HomeTown = par_HomeTown, Province = par_Province, District = par_District,
    Town = par_town, Street = par_Street, Email = par_Email, Note = par_Note
    where ID = par_ID;

    --Update Phone of PERSON

    --Select old password and old phone of PERSON
     select Phone into temp_User
    from PERSON
    where ID = par_ID;

    select Password into temp_Pass
    from ACCOUNT
    where Username = temp_User;

    --Create ACCOUNT
     ACC_INSERT_RECORD (par_Phone, temp_Pass, 2, 1, NULL);
     --Delete ACCOUNT
     ACC_DELETE_RECORD (temp_User);

     --update Phone of PERSON
     update PERSON
     set Phone = par_Phone
     where ID = par_ID;

    commit;

end PERSON_UPDATE_RECORD;

--------------------------------------------------------
--  DDL for Procedure SCHED_DEC_REG
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "SCHED_DEC_REG" 
(par_SchedID SCHEDULE.ID%type, par_Time REGISTER.Time%type)
as
begin
	if (par_Time = 0)
	then
		update SCHEDULE
		set DayRegistered = DayRegistered - 1
		where SCHEDULE.ID = par_SchedID;
 	elsif (par_Time = 1)
	then
		update SCHEDULE
		set NoonRegistered = NoonRegistered - 1
		where SCHEDULE.ID = par_SchedID;
	elsif (par_Time = 2)
	then
		update SCHEDULE
		set NightRegistered = NightRegistered - 1
		where SCHEDULE.ID = par_SchedID;
	end if;
    commit;
end SCHED_DEC_REG;
--------------------------------------------------------
--  DDL for Procedure SCHED_INC_REG
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "SCHED_INC_REG" 
(par_SchedID SCHEDULE.ID%type, par_Time REGISTER.Time%type)
as
begin
	if (par_Time = 0)
	then
		update SCHEDULE
		set DayRegistered = DayRegistered + 1
		where SCHEDULE.ID = par_SchedID;
 	elsif (par_Time = 1)
	then
		update SCHEDULE
		set NoonRegistered = NoonRegistered + 1
		where SCHEDULE.ID = par_SchedID;
	elsif (par_Time = 2)
	then
		update SCHEDULE
		set NightRegistered = NightRegistered + 1
		where SCHEDULE.ID = par_SchedID;
	end if;
    commit;
end SCHED_INC_REG;

--------------------------------------------------------
--  DDL for Procedure REG_INSERT_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "REG_INSERT_RECORD" 
(par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_Time REGISTER.Time%type)
as
	set_NO REGISTER.NO%type;
    var_DoseType REGISTER.DoseType%type;
    PreInj INJECTION%rowtype;
	PreVac VACCINE.ID%type;
begin
	--Use S_FUNC to calculate the NO of registion
	set_NO := REG_SIGNED_NO(par_PersonalID, par_SchedID, par_Time);
    
    --If the schedule is full, then raise error
    if (set_NO = 0) 
    then
        raise_application_error(-20010,'The registion is limited!');
    end if;
    
	--Check the type of registing dose
	--Find the previous injection info
	select * into PreInj
	from INJECTION
	where INJECTION.PersonalID = par_PersonalID
	and rownum = 1
	order by InjNo desc;

    --select out the privous vaccineID to choose the suitable dose type
    select VaccineID into PreVac
    from SCHEDULE SCHED
    where SCHED.ID = PreInj.SchedID;

    --If the registion is the second dose and the previous vaccine is Vero
    --=>dose type is booster
    if (PreInj.InjNO = 1)
	then
		var_DoseType := 'basic';
	elsif (PreInj.InjNO = 2)
	then
        if (PreVac = 'Vero')
        then
            var_DoseType := 'booster';
        else
            var_DoseType := 'repeat';
        end if;
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
--------------------------------------------------------
--  DDL for Procedure REG_UPDATE_STATUS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "REG_UPDATE_STATUS" 
(par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_Status REGISTER.Status%type)
as
    var_Time REGISTER.Time%type;
    var_DoseType REGISTER.DoseType%type;
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
    commit;

    --If the citizen is ready for vaccination
    if (par_Status = 1)
    then
        commit;
    --If the registion is completed by Injected status, insert new injection for the citizen
	elsif (par_Status = 2)
	then
        INJ_INSERT_RECORD(par_PersonalID, 
        INJ_COUNT_INJ(par_PersonalID) + 1, par_SchedID, var_DoseType);
        commit;
	--If the registion is canceled, decrease the number of registion
    elsif (par_Status = 3)
    then 
        SCHED_DEC_REG(par_SchedID, var_Time);
    end if;
    
    --There was no injection before, this the first injection
end REG_UPDATE_STATUS;

--------------------------------------------------------
--  DDL for Procedure SCHED_INSERT_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "SCHED_INSERT_RECORD" 
(par_OrgID ORGANIZATION.ID%type, par_OnDate date, par_VaccineID VACCINE.ID%type,
par_Serial varchar2, 
par_LimitDay SCHEDULE.LimitDay%type DEFAULT 0, 
par_LimitNoon SCHEDULE.LimitNoon%type DEFAULT 0, 
par_LimitNight SCHEDULE.LimitNight%type DEFAULT 0, 
par_Note SCHEDULE.Note%type DEFAULT NULL) --Insert schedule record
as
    var_SchedID SCHEDULE.ID%type;
begin
    var_SchedID := SCHED_GENERATE_ID(par_OrgID, par_OnDate);
    
    insert into SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note)
    values(var_SchedID, par_OrgID, par_OnDate, par_VaccineID, par_Serial, 
    par_LimitDay, par_LimitNoon, par_LimitNight);
    commit;
end SCHED_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure TEST_DATE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "TEST_DATE" 
( DATE1 date,
  DATE2 date)
AS 
    var_Diff number(4,2);
    var_n number(4,2);
    var_predate date;
    var_date date;
    var_SchedID SCHEDULE.ID%type;
	PreInj INJECTION%rowtype;
	ParCase PARAMETER%rowtype;
	RegVac VACCINE.ID%type;
	PreVac VACCINE.ID%type;
    var_Contains_Pos int;
    var_NextDose PARAMETER.NextDose%type;
BEGIN
    /*
    var_Diff_Days := TO_DATE(TO_CHAR(Date1, 'YYYY-MM-DD'), 'YYYY-MM-DD');
    var_Diff_Days := TO_CHAR(Date1, 'YYYY-MM-DD');
    */
    var_SchedID := '44001280520221';
    
    select * into PreInj
	from INJECTION
	where INJECTION.PersonalID = '20520418'
    and rownum = 1
    order by InjNO desc;
    
    --select out the vaccine used in the previous injection
	select VaccineID into PreVac 
	from SCHEDULE SCHED
	where SCHED.ID = PreInj.SchedID;

	--select out the vaccine used in this registion
	select VaccineID into RegVac 
	from SCHEDULE SCHED
	where SCHED.ID = var_SchedID;
    
    var_Diff := INJ_DIFFERENCE('20520418');
    --Reference the PreInj to PARAMETER cases to select out the rule
	select * into ParCase
	from PARAMETER PAR
	where PAR.InjectionNO = PreInj.InjNO
	and PAR.VaccineID = PreVac
	and PAR.DiffDoses = var_Diff
    and PAR.DoseType = PreInj.DoseType;
    
    var_NextDose := ParCase.NextDose;
    var_n:= ParCase.MinDistance;
    
    --select out the dates of 2 injections
    select OnDate into var_predate
    from SCHEDULE SCHED
    where SCHED.ID = PreInj.SchedID;
    
    select OnDate into var_date
    from SCHEDULE SCHED
    where SCHED.ID = var_SchedID;
    
    var_n := abs(months_between(var_date, var_predate));
    
    if (var_n < (ParCase.MinDistance-3)/30)
	then
		raise_application_error(-20000, 
        'Cannot register to this schedule due to the invalid in spacing rule!');
	end if;         
    
    --Check vaccine combination rule: vaccine from registered schedule must be contained in ParCase.NextDose	
    if (ParCase.NextDose NOT LIKE '%'||RegVac||'%')
	then
		raise_application_error(-20001, 
        'Cannot register to this schedule due to the incompitable with the previous injection!');
	end if;

END TEST_DATE;
--------------------------------------------------------
--  DDL for Procedure TEST_TRIGGER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "TEST_TRIGGER" 
AS 
    LastHealth HEALTH%rowtype;
    var_SubStr varchar2(1);
BEGIN
  --Select out the last medical declaration of the citizen
	select * into LastHealth
	from HEALTH
	where HEALTH.PersonalID = '100000000000'
	and rownum = 1
    order by ID desc;

	--Check vaccination target type
    var_SubStr := SUBSTR(LastHealth.Healths, 4, 1);
	if ( SUBSTR(LastHealth.Healths, 4, 1) = '1' )
	then	
		raise_application_error
        (-20005, 'You can not register vaccination due to your target type!');
	end if;

	--Check Covid-19 affected
    var_SubStr := SUBSTR(LastHealth.Healths, 3, 1);
	if ( (SUBSTR(LastHealth.Healths, 3, 1) = '1') 
    and (abs(months_between(SYSDATE, LastHealth.FilledDate))*30 < 14) )
	then
		raise_application_error
        (-20006, 'You have been affected by Covid-19 recent days, please wait until you completely healed');
	end if;
END TEST_TRIGGER;
--------------------------------------------------------
--  DDL for Procedure VAC_INSERT_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "VAC_INSERT_RECORD" 
(par_ID VACCINE.ID%type, 
par_Name VACCINE.Name%type, 
par_Technology VACCINE.Technology%type, 
par_Country VACCINE.Country%type, 
par_Note VACCINE.Note%type DEFAULT NULL)
as
begin
    --Insert
    insert into VACCINE(ID, Name, Technology, Country, Note)
        values(par_ID, par_Name, par_Technology, par_Country, par_Note);
    commit;
    --Duplicate ID or name
    EXCEPTION
        when DUP_VAL_ON_INDEX
        then
            raise_application_error(-20011,'Duplicate ID or name!');
end VAC_INSERT_RECORD;
--