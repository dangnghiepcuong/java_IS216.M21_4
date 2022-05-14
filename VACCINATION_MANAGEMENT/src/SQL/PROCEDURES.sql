--------------------------------------------------------
--  File created - Thursday-May-12-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACC_CREATE_ORG
--------------------------------------------------------


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


  CREATE OR REPLACE EDITIONABLE PROCEDURE "ACC_INSERT_RECORD" 
(par_Username varchar2, par_Password varchar2, par_Role number, par_Status number, par_Note varchar2 DEFAULT NULL)
is   
begin 
     insert into ACCOUNT(Username, Password, Role, status, Note) values 
     (par_Username, par_Password, par_Role, par_Status, par_Note);    

     EXCEPTION
        when DUP_VAL_ON_INDEX
        then
            raise_application_error(-20012,'Username has been registered by another user!');
end ACC_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure ACC_RESET_PASSWORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "ACC_RESET_PASSWORD" 
(par_Username varchar2, par_NewPassword varchar2)
is
begin
     update ACCOUNT
     set Password = par_NewPassword
     where Username = par_Username;
end ACC_RESET_PASSWORD;
--------------------------------------------------------
--  DDL for Procedure ACC_UPDATE_PASSWORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "ACC_UPDATE_PASSWORD" 
(par_Username varchar2, par_OldPass varchar2, par_NewPass varchar2)
is
    Pass varchar2(128);
begin
    select Password into Pass from ACCOUNT where Username = par_Username;
    if (par_OldPass != Pass) 
    then
        raise_application_error(-20013,'Incorrect password!');
    else
        update ACCOUNT set Password = par_NewPass 
        where  Username = par_Username;
    end if;

    commit;

    EXCEPTION
        when no_data_found
        then
            raise_application_error(-20014,'Username does not exist!');
end ACC_UPDATE_PASSWORD;
--------------------------------------------------------
--  DDL for Procedure CERT_INSERT_RECORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "CERT_INSERT_RECORD" 
(
    par_PersonalID PERSON.ID%type
)
AS 
    count_Dose number;
BEGIN
    insert into CERTIFICATE(PersonalID, Dose, CertType, Note)
    values (par_PersonalID, 0, 0, NULL);
    
    commit;
END CERT_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure CERT_UPDATE_RECORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "CERT_UPDATE_RECORD" 
(
    par_PersonalID PERSON.ID%type
)
AS 
    count_Dose number;
BEGIN
    select COUNT(INJ.InjNO) into count_Dose
    from INJECTION INJ
    where INJ.PersonalID = par_PersonalID;
    
    if (count_Dose = 0)
    then
        update CERTIFICATE CERT
        set Dose = count_Dose, CertType = 0
        where CERT.PersonalID = par_PersonalID;
    elsif (count_Dose = 1)
    then
        update CERTIFICATE CERT
        set Dose = count_Dose, CertType = 1
        where CERT.PersonalID = par_PersonalID;
    elsif (count_Dose = 2)
    then
        update CERTIFICATE CERT
        set Dose = count_Dose, CertType = 2
        where CERT.PersonalID = par_PersonalID;
    else
        update CERTIFICATE CERT
        set Dose = count_Dose, CertType = 2
        where CERT.PersonalID = par_PersonalID;
    end if;
    
    commit;
END CERT_UPDATE_RECORD;
--------------------------------------------------------
--  DDL for Procedure HEAL_INSERT_RECORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "HEAL_INSERT_RECORD" 
(
par_PersonalID PERSON.ID%type, 
par_FilledDate HEALTH.FilledDate%type, 
par_Healths HEALTH.Healths%type, 
par_Note HEALTH.Note%type DEFAULT NULL
)
as
    var_ID HEALTH.ID%type;
begin
    var_ID := HEAL_FORM_ID(par_PersonalID);

    insert into HEALTH(ID, PersonalID, FilledDate, Healths, Note)
    values(var_ID, par_PersonalID, par_FilledDate, par_Healths, par_Note);
    commit;
end HEAL_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure INJ_INSERT_RECORD
--------------------------------------------------------


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

    CERT_UPDATE_RECORD(par_PersonalID);

    commit;
end INJ_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure ORG_INSERT_RECORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "ORG_INSERT_RECORD" (par_ID ORGANIZATION.ID%type,                                            
                                             par_Province ORGANIZATION.Province%type,                                            
							   par_Note  ORGANIZATION.Note%type DEFAULT NULL)                                           
as 
begin
    --insert new ORGANIZATION
	insert into ORGANIZATION(ID, Province, Note) 
	values (par_ID, par_Province, par_Note);
end ORG_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure ORG_UPDATE_RECORD
--------------------------------------------------------


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
--  DDL for Procedure PAR_INSERT_RECORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "PAR_INSERT_RECORD" 
(
  par_INJNO NUMBER 
, par_VACCINEID VARCHAR2 
, par_DOSETYPE VARCHAR2 
, par_MINDISTANCE NUMBER 
, par_DIFFDOSES NUMBER 
, par_NEXTDOSE VARCHAR2 
, par_NOTE VARCHAR2 DEFAULT NULL
) AS 
BEGIN
  insert into PARAMETER (InjectionNO, VaccineID, DoseType, MinDistance, DiffDoses, NextDose, Note)
  values (par_InjNO, par_VaccineID, par_DoseType, par_MinDistance, par_DiffDoses, par_NextDose, par_Note);
END PAR_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure PERSON_INSERT_RECORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "PERSON_INSERT_RECORD" 
(   par_ID PERSON.ID%type, 
    par_LastName PERSON.LastName%type, 
    par_FirstName PERSON.FirstName%type,
    par_Birthday PERSON.Birthday%type, 
    par_Gender PERSON.Gender%type,
    par_HomeTown PERSON.HomeTown%type, 
    par_Province PERSON.Province%type,
    par_District PERSON.District%type, 
    par_Town PERSON.Town%type,
    par_Street PERSON.Street%type,
    par_Phone PERSON.Phone%type, 
    par_Email PERSON.Email%type DEFAULT NULL,
    par_Guardian PERSON.Guardian%type DEFAULT NULL, 
    par_Note PERSON.Note%type DEFAULT NULL) 
as
begin
    --create new PERSON
    insert into PERSON(ID, LastName, FirstName, Birthday, Gender, HomeTown,
    Province, District, Town, Street, Phone, Email, Guardian, Note) 
    values (par_ID, par_LastName, par_FirstName, par_Birthday, par_Gender,
    par_HomeTown, par_Province, par_District, par_Town, par_Street, par_Phone,
    par_Email, par_Guardian, par_Note);

    CERT_INSERT_RECORD(par_ID);

    EXCEPTION
        when DUP_VAL_ON_INDEX
        then
            raise_application_error(-20015,'ID has been registered by another user!');
end PERSON_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure PERSON_UPDATE_RECORD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "PERSON_UPDATE_RECORD" (
par_OldID PERSON.ID%type, par_ID PERSON.ID%type, 
par_LastName PERSON.LastName%type, par_FirstName PERSON.FirstName%type, 
par_Birthday PERSON.Birthday%type, par_Gender PERSON.Gender%type,
par_HomeTown PERSON.HomeTown%type, par_Province PERSON.Province%type,
par_District PERSON.District%type, par_Town PERSON.Town%type,
par_Street PERSON.Street%type, 
par_Phone PERSON.Phone%type, par_OldPhone PERSON.Phone%type,
par_Email PERSON.Email%type, par_Note PERSON.Note%type DEFAULT NULL) 
as
    var_AccChanged boolean;
    var_OldPass ACCOUNT.Password%type;
begin
    var_AccChanged := false;
    --if phone number (username) changed, create new account
    --if username changed, create new account
    if (par_Phone != par_OldPhone)
    then
        var_AccChanged := true;
        
        --select out account before changed
        select Password into var_OldPass
        from ACCOUNT
        where Username = par_OldPhone;
        
        ACC_INSERT_RECORD(par_Phone, var_OldPass, 2, 1);
    end if;
    
    --if ID changed, create new PERSON record with the new ID
    --then update PersonalID of all records from other tables that ref to the old PERSON.ID
    --delete the old person record
    if (par_OldID != par_ID)
    then
        --new certificate also created
        PERSON_INSERT_RECORD(par_ID, par_LastName, par_FirstName, par_Birthday, par_Gender
        , par_HomeTown, par_Province, par_District, par_Town, par_Street, par_Phone, par_Email);
        
        update INJECTION
        set PersonalID = par_ID
        where PersonalID = par_OldID;
        
        update REGISTER
        set PersonalID = par_ID
        where PersonalID = par_OldID;
        
        delete certificate where PersonalID = par_OldID;
        
        update HEALTH
        set PersonalID = par_ID
        where PersonalID = par_OldID;
        
        delete PERSON
        where ID = par_OldID;
    else --if ID not changed, update other attribute
        update PERSON
        set Phone = par_Phone,
        LastName = par_LastName, FirstName = par_FirstName, 
        Birthday = par_Birthday, Gender = par_Gender, 
        HomeTown = par_HomeTown, Province = par_Province, 
        District = par_District, Town = par_town, Street = par_Street, 
        Email = par_Email, Note = par_Note
        where ID = par_OldID;
    end if;

    if (var_AccChanged)
    then
        delete ACCOUNT
        where Username = par_OldPhone;
    end if;

    commit;

end PERSON_UPDATE_RECORD;

--------------------------------------------------------
--  DDL for Procedure REG_BEFORE_INSERT_RECORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "REG_BEFORE_INSERT_RECORD" 
(par_PersonalID PERSON.ID%type, par_BoosterAvai out number, par_DoseType out REGISTER.DoseType%type)
AS 
    PreInj INJECTION%rowtype;
	PreVac VACCINE.ID%type;
BEGIN
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
    par_BoosterAvai := 0;
    if (PreInj.InjNO = 1) --the previous injection is basic
	then
        --default basic type
        par_BoosterAvai := 0;
		par_DoseType := 'basic';
    end if;
    
	if (PreInj.InjNO = 2) --the previous injection is basic
	then
        if (PreVac = 'Vero' or PreVac = 'Sputnik')
        then
            par_BoosterAvai := 0;
            par_DoseType := 'booster';
        else --the previous injection is basic
            --ask the user to select booster type or repeat type
            par_DoseType := '';
            par_BoosterAvai := 1;
        end if;
    end if;
    
    if (PreInj.InjNO = 3) --the previous injection could be is booster
    then
        --default repeat type
        par_BoosterAvai :=0;
        par_DoseType := 'repeat';
    end if;
    
    
EXCEPTION
    when no_data_found
    then
        par_BoosterAvai := 0;
        par_DoseType := 'basic';
END REG_BEFORE_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure REG_INSERT_RECORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "REG_INSERT_RECORD" 
(par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, 
par_Time REGISTER.Time%type, par_DoseType REGISTER.DoseType%type)
as
	set_NO REGISTER.NO%type;
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

	--insert new registion
	insert into REGISTER(PersonalID, SchedID, DoseType, Time, NO, Status, Image, Note) 
    values (par_PersonalID, par_SchedID, par_DoseType, par_Time, set_NO, 0, NULL, NULL);
    --increase the registered number in schedule
    SCHED_INC_REG(par_SchedID, par_Time);
    commit;

    --If cannot find a previous injection, it means this is the first injection. Then allow to register.
	EXCEPTION
		when no_data_found
        then
        insert into REGISTER(PersonalID, SchedID, DoseType, Time, NO, Status, Image, Note) 
    values (par_PersonalID, par_SchedID, 'basic', par_Time, set_NO, 0, NULL, NULL);
 		--increase the registered number in schedule
    SCHED_INC_REG(par_SchedID, par_Time);
        commit;

end REG_INSERT_RECORD;
--------------------------------------------------------
--  DDL for Procedure REG_UPDATE_STATUS
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "REG_UPDATE_STATUS" 
(par_PersonalID PERSON.ID%type, par_SchedID SCHEDULE.ID%type, par_Status REGISTER.Status%type)
as
    var_Time REGISTER.Time%type;
    var_DoseType REGISTER.DoseType%type;
begin
    --select out the time and dose type of registion from the last registion
    select Time, DoseType into var_Time, var_DoseType
    from REGISTER REG
    where REG.PersonalID = par_PersonalID
    and REG.SchedID = par_SchedID
    --last registion must not be a completed registion or a canceled registion
    and REG.Status != 3
    and REG.Status != 2;

	--Update status
    if (par_Status = 1)
    then
        update REGISTER
        set Status = par_Status
        where PersonalID = par_PersonalID
        and SchedID = par_SchedID
        and Status = 0; --The registion must be registered to be updated to check in
    elsif (par_Status = 2)
    then
        update REGISTER
        set Status = par_Status
        where PersonalID = par_PersonalID
        and SchedID = par_SchedID
        and Status = 1; --The registion must be check in to be updated to injected
    else
        update REGISTER
        set Status = par_Status
        where PersonalID = par_PersonalID
        and SchedID = par_SchedID
        and Status != 2; --The injected status can not be updated!
    end if;
    
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
    
EXCEPTION
    when no_data_found
    then
        NULL;
    --There was no injection before, this the first injection
end REG_UPDATE_STATUS;

--------------------------------------------------------
--  DDL for Procedure SCHED_DEC_REG
--------------------------------------------------------


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
--  DDL for Procedure SCHED_DELETE_RECORD
--------------------------------------------------------


  CREATE OR REPLACE EDITIONABLE PROCEDURE "SCHED_DELETE_RECORD" 
(PAR_SCHEDID VARCHAR2) AS
BEGIN
    --delete all registions of the deleting schedule
    delete from REGISTER REG
    where REG.SchedID = par_SchedID;
    
    --delete the specified schedule
    delete from SCHEDULE SCHED
    where SCHED.ID = par_SchedID;
    
    commit;
    
END SCHED_DELETE_RECORD;
--------------------------------------------------------
--  DDL for Procedure SCHED_INC_REG
--------------------------------------------------------


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


  CREATE OR REPLACE EDITIONABLE PROCEDURE "SCHED_INSERT_RECORD" 
(par_OrgID ORGANIZATION.ID%type, 
par_OnDate date, 
par_VaccineID VACCINE.ID%type,
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
--  DDL for Procedure VAC_INSERT_RECORD
--------------------------------------------------------


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
            raise_application_error(-20016,'Duplicate ID or name!');
end VAC_INSERT_RECORD;
