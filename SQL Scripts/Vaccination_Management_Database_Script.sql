--------------------------------------------------------
--  File created - Tuesday-April-26-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ACCOUNT
--------------------------------------------------------

  CREATE TABLE "ACCOUNT" ("USERNAME" VARCHAR2(254), "PASSWORD" VARCHAR2(128), "ROLE" NUMBER(1,0) DEFAULT (null), "STATUS" NUMBER(1,0), "NOTE" VARCHAR2(2000))
--------------------------------------------------------
--  DDL for Table ANNOUNCEMENT
--------------------------------------------------------

  CREATE TABLE "ANNOUNCEMENT" ("ID" VARCHAR2(50), "ORGID" VARCHAR2(16), "TITLE" VARCHAR2(200), "CONTENT" VARCHAR2(4000), "PUBLISHDATE" DATE, "IMAGE" BLOB, "NOTE" VARCHAR2(2000))
--------------------------------------------------------
--  DDL for Table CERTIFICATE
--------------------------------------------------------

  CREATE TABLE "CERTIFICATE" ("PERSONALID" VARCHAR2(12), "DOSE" NUMBER(2,0), "CERTTYPE" NUMBER(1,0), "NOTE" VARCHAR2(2000))
--------------------------------------------------------
--  DDL for Table HEALTH
--------------------------------------------------------

  CREATE TABLE "HEALTH" ("PERSONALID" VARCHAR2(12), "FILLEDDATE" DATE, "HEALTHS" VARCHAR2(20), "NOTE" VARCHAR2(2000), "ID" NUMBER(*,0))
--------------------------------------------------------
--  DDL for Table INJECTION
--------------------------------------------------------

  CREATE TABLE "INJECTION" ("PERSONALID" VARCHAR2(12), "INJNO" NUMBER(5,0), "SCHEDID" VARCHAR2(15), "NOTE" VARCHAR2(2000), "DOSETYPE" VARCHAR2(50))
--------------------------------------------------------
--  DDL for Table ORGANIZATION
--------------------------------------------------------

  CREATE TABLE "ORGANIZATION" ("ID" VARCHAR2(6), "NAME" VARCHAR2(100), "PROVINCE" VARCHAR2(50), "DISTRICT" VARCHAR2(50), "TOWN" VARCHAR2(50), "STREET" VARCHAR2(100), "NOTE" VARCHAR2(2000))
--------------------------------------------------------
--  DDL for Table PARAMETER
--------------------------------------------------------

  CREATE TABLE "PARAMETER" ("INJECTIONNO" NUMBER, "VACCINEID" VARCHAR2(8), "MINDISTANCE" NUMBER, "DIFFDOSES" NUMBER(1,0), "NEXTDOSE" VARCHAR2(100), "NOTE" VARCHAR2(2000), "DOSETYPE" VARCHAR2(100))
--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "PERSON" ("ID" VARCHAR2(256), "LASTNAME" VARCHAR2(100), "FIRSTNAME" VARCHAR2(50), "BIRTHDAY" DATE, "GENDER" NUMBER(1,0), "HOMETOWN" VARCHAR2(50), "PROVINCE" VARCHAR2(50), "DISTRICT" VARCHAR2(50), "TOWN" VARCHAR2(50), "STREET" VARCHAR2(100), "PHONE" VARCHAR2(30), "EMAIL" VARCHAR2(254), "GUARDIAN" VARCHAR2(12), "NOTE" VARCHAR2(2000))
--------------------------------------------------------
--  DDL for Table REGION
--------------------------------------------------------

  CREATE TABLE "REGION" ("CODE" NUMBER(*,0), "NAME" VARCHAR2(50))
--------------------------------------------------------
--  DDL for Table REGISTER
--------------------------------------------------------

  CREATE TABLE "REGISTER" ("PERSONALID" VARCHAR2(12), "SCHEDID" VARCHAR2(15), "TIME" NUMBER(1,0), "NO" NUMBER(5,0), "STATUS" NUMBER(1,0), "IMAGE" BLOB, "NOTE" VARCHAR2(2000), "DOSETYPE" VARCHAR2(50))
--------------------------------------------------------
--  DDL for Table SCHEDULE
--------------------------------------------------------

  CREATE TABLE "SCHEDULE" ("ID" VARCHAR2(26), "ORGID" VARCHAR2(16), "ONDATE" DATE, "VACCINEID" VARCHAR2(8), "SERIAL" VARCHAR2(100), "LIMITDAY" NUMBER(5,0), "LIMITNOON" NUMBER(5,0), "LIMITNIGHT" NUMBER(5,0), "DAYREGISTERED" NUMBER(5,0), "NOONREGISTERED" NUMBER(5,0), "NIGHTREGISTERED" NUMBER(5,0), "NOTE" VARCHAR2(2000))
--------------------------------------------------------
--  DDL for Table STATISTIC
--------------------------------------------------------

  CREATE TABLE "STATISTIC" ("TITLE" VARCHAR2(200), "DATA" NUMBER)
--------------------------------------------------------
--  DDL for Table VACCINE
--------------------------------------------------------

  CREATE TABLE "VACCINE" ("ID" VARCHAR2(8), "NAME" VARCHAR2(100), "TECHNOLOGY" VARCHAR2(100), "COUNTRY" VARCHAR2(100), "NOTE" VARCHAR2(2000))
REM INSERTING into ACCOUNT
SET DEFINE OFF;
REM INSERTING into ANNOUNCEMENT
SET DEFINE OFF;
REM INSERTING into CERTIFICATE
SET DEFINE OFF;
REM INSERTING into HEALTH
SET DEFINE OFF;
REM INSERTING into INJECTION
SET DEFINE OFF;
REM INSERTING into ORGANIZATION
SET DEFINE OFF;
REM INSERTING into PARAMETER
SET DEFINE OFF;
REM INSERTING into PERSON
SET DEFINE OFF;
REM INSERTING into REGION
SET DEFINE OFF;
REM INSERTING into REGISTER
SET DEFINE OFF;
REM INSERTING into SCHEDULE
SET DEFINE OFF;
REM INSERTING into STATISTIC
SET DEFINE OFF;
REM INSERTING into VACCINE
SET DEFINE OFF;
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
ALTER TRIGGER "REG_VACCINATION_AGE_STATUS" ENABLE
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
ALTER TRIGGER "REG_VACCINATION_RULE" ENABLE
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
        raise_application_error(-20008,'You must fill out medical form before registion with 7 days');
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
            (-20009,'You have not fill out any medical form within 7 days yet!');
end REG_VACCINATION_TARGET;
ALTER TRIGGER "REG_VACCINATION_TARGET" ENABLE
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
        raise_application_error(-20008,'You must fill out medical form before registion with 7 days');
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
            (-20009,'You have not fill out any medical form within 7 days yet!');
end REG_VACCINATION_TARGET;
ALTER TRIGGER "REG_VACCINATION_TARGET" ENABLE
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
ALTER TRIGGER "REG_VACCINATION_RULE" ENABLE
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
ALTER TRIGGER "REG_VACCINATION_AGE_STATUS" ENABLE
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
        ACC_INSERT_RECORD(TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i), 
                            TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i),
                            1, 1, null);
        --Create ORG
        ORG_INSERT_RECORD(TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i),
        par_Province, null);
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
        ACC_INSERT_RECORD(TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i), 
                            TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i),
                            1, 1, null);
        --Create ORGs
        ORG_INSERT_RECORD(TO_CHAR(par_Province)||ACC_CONVERT_SEQ_TO_STR(i),
        par_Province, null);
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
    par_LimitDay, par_LimitNoon, par_LimitNight, 0, 0, 0, par_Note);
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
--------------------------------------------------------
--  DDL for Function ACC_CONVERT_SEQ_TO_STR
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "ACC_CONVERT_SEQ_TO_STR" 
(par_Last_Seq int)
return varchar2 is
begin
	if ( par_Last_Seq >= 100 )
	then
		return TO_CHAR(par_Last_Seq);
	end if;
    
    if ( par_Last_Seq >= 10 )
	then
		return ('0' || TO_CHAR(par_Last_Seq));
	end if;
    
    return ('00' || TO_CHAR(par_Last_Seq));

end ACC_CONVERT_SEQ_TO_STR;
--------------------------------------------------------
--  DDL for Function ANN_ID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "ANN_ID" (par_OrgID ANNOUNCEMENT.OrgID%type)
return number
is
    Value_OrgID int;
    temp_OrgID ANNOUNCEMENT.OrgID%type;
begin 
    --select out the last ID of the ORG in the Organization
    select OrgID into temp_OrgID
    from ANNOUNCEMENT
    where OrgID=par_OrgID and rownum=1
    order by OrgID desc;

    --The next value of ID
    Value_OrgID := TO_NUMBER(SUBSTR(TO_CHAR(par_OrgID), -3, 3)) + 1;

EXCEPTION 
    when no_data_found
 	then Value_OrgID := 1;

    return Value_OrgID;
end;
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
    /*
    if (INJ_COUNT_INJ(par_PersonalID) <= 1)
    then
        return 0;
    end if;
    fetch c_INJ into crow_INJ_be;
    open c_INJ;
    loop
        fetch c_INJ into crow_INJ_af;
        exit when c_INJ%notfound;
        --select out the vaccine id of 2 injection in theirs schedule
        select ID into var_VaccineID_1
        from SCHEDULE SCHED
        where SCHED.ID = crow_INJ_be.SchedID;
        select ID into var_VaccineID_2
        from SCHEDULE SCHED
        where SCHED.ID = crow_INJ_af.SchedID;
        if (var_VaccineID_1 != var_VaccineID_2)
        then
            return 1;
        end if;
        crow_INJ_be := crow_INJ_af;
    end loop;
    return 0;*/
    
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
--------------------------------------------------------
--  Constraints for Table ACCOUNT
--------------------------------------------------------

  ALTER TABLE "ACCOUNT" MODIFY ("USERNAME" NOT NULL ENABLE)
  ALTER TABLE "ACCOUNT" MODIFY ("PASSWORD" NOT NULL ENABLE)
  ALTER TABLE "ACCOUNT" ADD CONSTRAINT "PK_ACC" PRIMARY KEY ("USERNAME") USING INDEX  ENABLE
  ALTER TABLE "ACCOUNT" ADD CONSTRAINT "CK_ACC_ROLE" CHECK (Role in (0, 1, 2)) ENABLE
--------------------------------------------------------
--  Constraints for Table ANNOUNCEMENT
--------------------------------------------------------

  ALTER TABLE "ANNOUNCEMENT" ADD CONSTRAINT "PK_ANN" PRIMARY KEY ("ID", "ORGID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table CERTIFICATE
--------------------------------------------------------

  ALTER TABLE "CERTIFICATE" ADD CONSTRAINT "PK_CERT" PRIMARY KEY ("PERSONALID") USING INDEX  ENABLE
  ALTER TABLE "CERTIFICATE" ADD CONSTRAINT "CK_CERT_CERTTYPE" CHECK (CertType in (0,1,2)) ENABLE
--------------------------------------------------------
--  Constraints for Table HEALTH
--------------------------------------------------------

  ALTER TABLE "HEALTH" ADD CONSTRAINT "PK_HEAL" PRIMARY KEY ("PERSONALID", "ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table INJECTION
--------------------------------------------------------

  ALTER TABLE "INJECTION" MODIFY ("PERSONALID" NOT NULL ENABLE)
  ALTER TABLE "INJECTION" ADD CONSTRAINT "PK_INJ" PRIMARY KEY ("PERSONALID", "INJNO") USING INDEX  ENABLE
  ALTER TABLE "INJECTION" ADD CONSTRAINT "CK_INJ_INJNO" CHECK (InjNO in (1, 2, 3, 4)) ENABLE
  ALTER TABLE "INJECTION" ADD CONSTRAINT "CK_INJ_SCHEDID" CHECK (SchedID is not null) ENABLE
--------------------------------------------------------
--  Constraints for Table ORGANIZATION
--------------------------------------------------------

  ALTER TABLE "ORGANIZATION" ADD CONSTRAINT "PK_ORG" PRIMARY KEY ("ID") USING INDEX  ENABLE
  ALTER TABLE "ORGANIZATION" ADD CONSTRAINT "CK_ORG_PROVINCE" CHECK (Province is not null) ENABLE
  ALTER TABLE "ORGANIZATION" ADD CONSTRAINT "CK_ORG_NAME" CHECK (Name is not null) ENABLE
--------------------------------------------------------
--  Constraints for Table PARAMETER
--------------------------------------------------------

  ALTER TABLE "PARAMETER" ADD CONSTRAINT "CK_PAR_DIFFDOSES" CHECK (DiffDoses in (0,1)) ENABLE
  ALTER TABLE "PARAMETER" ADD CONSTRAINT "PK_PAR" PRIMARY KEY ("INJECTIONNO", "VACCINEID", "DOSETYPE", "DIFFDOSES") USING INDEX  ENABLE
  ALTER TABLE "PARAMETER" ADD CONSTRAINT "CK_PAR_INJECTIONNO" CHECK (InjectionNO in (1, 2, 3, 4)) ENABLE
--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "PERSON" ADD CONSTRAINT "PK_PERSON" PRIMARY KEY ("ID") USING INDEX  ENABLE
  ALTER TABLE "PERSON" ADD CONSTRAINT "CK_PERSON_GENDER" CHECK (Gender in (0, 1, 2)) ENABLE
--------------------------------------------------------
--  Constraints for Table REGION
--------------------------------------------------------

  ALTER TABLE "REGION" ADD CONSTRAINT "PK_REGION" PRIMARY KEY ("CODE") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table REGISTER
--------------------------------------------------------

  ALTER TABLE "REGISTER" ADD CONSTRAINT "CK_REG_DOSETYPE" CHECK (DoseType in ('basic', 'booster', 'repeat')) ENABLE
  ALTER TABLE "REGISTER" ADD CONSTRAINT "PK_REG" PRIMARY KEY ("PERSONALID", "SCHEDID") USING INDEX  ENABLE
  ALTER TABLE "REGISTER" ADD CONSTRAINT "CK_REG_TIME" CHECK (Time in (0,1,2)) ENABLE
  ALTER TABLE "REGISTER" ADD CONSTRAINT "CK_REG_STATUS" CHECK (Status in (0,1,2,3)) ENABLE
--------------------------------------------------------
--  Constraints for Table SCHEDULE
--------------------------------------------------------

  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "PK_SCHED" PRIMARY KEY ("ID") USING INDEX  ENABLE
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_LIMITDAY" CHECK (LimitDay >= 0) ENABLE
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_LIMITNOON" CHECK (LimitNoon >= 0) ENABLE
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_LIMITNIGHT" CHECK (LimitNight >= 0) ENABLE
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_DAYREGISTERED" CHECK (DayRegistered >= 0) ENABLE
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_NOONREGISTERED" CHECK (NoonRegistered >= 0) ENABLE
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_NIGHTREGISTERED" CHECK (NightRegistered >= 0) ENABLE
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_ONDATE" CHECK (OnDate is not null) ENABLE
--------------------------------------------------------
--  Constraints for Table STATISTIC
--------------------------------------------------------

  ALTER TABLE "STATISTIC" ADD CONSTRAINT "PK_STAT" PRIMARY KEY ("TITLE") USING INDEX  ENABLE
  ALTER TABLE "STATISTIC" ADD CONSTRAINT "CK_STAT_DATA" CHECK (Data>0) ENABLE
--------------------------------------------------------
--  Constraints for Table VACCINE
--------------------------------------------------------

  ALTER TABLE "VACCINE" ADD CONSTRAINT "UNI_NAME" UNIQUE ("NAME") USING INDEX  ENABLE
  ALTER TABLE "VACCINE" ADD CONSTRAINT "PK_VACCINE" PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Ref Constraints for Table ANNOUNCEMENT
--------------------------------------------------------

  ALTER TABLE "ANNOUNCEMENT" ADD CONSTRAINT "FK_ANN_ORG" FOREIGN KEY ("ORGID") REFERENCES "ORGANIZATION" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table CERTIFICATE
--------------------------------------------------------

  ALTER TABLE "CERTIFICATE" ADD CONSTRAINT "FK_CERT_PERSON" FOREIGN KEY ("PERSONALID") REFERENCES "PERSON" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table HEALTH
--------------------------------------------------------

  ALTER TABLE "HEALTH" ADD CONSTRAINT "FK_HEAL_PERSON" FOREIGN KEY ("PERSONALID") REFERENCES "PERSON" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table INJECTION
--------------------------------------------------------

  ALTER TABLE "INJECTION" ADD CONSTRAINT "FK_INJ_PERSON" FOREIGN KEY ("PERSONALID") REFERENCES "PERSON" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table ORGANIZATION
--------------------------------------------------------

  ALTER TABLE "ORGANIZATION" ADD CONSTRAINT "FK_ORG_ACC" FOREIGN KEY ("ID") REFERENCES "ACCOUNT" ("USERNAME") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table PARAMETER
--------------------------------------------------------

  ALTER TABLE "PARAMETER" ADD CONSTRAINT "FK_PAR_VAC" FOREIGN KEY ("VACCINEID") REFERENCES "VACCINE" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" ADD CONSTRAINT "FK_PERSON_GUAR" FOREIGN KEY ("GUARDIAN") REFERENCES "PERSON" ("ID") ENABLE
  ALTER TABLE "PERSON" ADD CONSTRAINT "FK_PERSON_ACC" FOREIGN KEY ("PHONE") REFERENCES "ACCOUNT" ("USERNAME") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table REGISTER
--------------------------------------------------------

  ALTER TABLE "REGISTER" ADD CONSTRAINT "FK_REG_PERSON" FOREIGN KEY ("PERSONALID") REFERENCES "PERSON" ("ID") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table SCHEDULE
--------------------------------------------------------

  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "FK_SCHED_ORG" FOREIGN KEY ("ORGID") REFERENCES "ORGANIZATION" ("ID") ENABLE
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "FK_SCHED_VAC" FOREIGN KEY ("VACCINEID") REFERENCES "VACCINE" ("ID") ENABLE
