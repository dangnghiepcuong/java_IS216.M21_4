--20521252 - Lê Hoàng Duyên
/*          TABLE: VACCINE          */
--Create a table struct without constraint
--<Attribute name> <Data type>

--Store data of information from injection
create table INJECTION
(
    --PersonalID FK references PERSON(ID)
    PersonalID varchar2(12) not null,
    
    --Injection sequence number of the citizen, value in { 1, 2, 3, 4}
    InjNO number(2),
    
    --SchedlID FK references SCHEDULE(ID)
    SchedID varchar2(15),
    
    --Injection type
    DoseType varchar(50),
    
    --Note on the injection
    Note varchar2(2000)
);
/*	CONSTRAINT	*/
--Primary Key
alter table INJECTION 
add constraint PK_INJ primary key (PersonalID, InjNO);

--Foreign Key
alter table INJECTION
add constraint FK_INJ_PERSON foreign key (PersonalID) references PERSON(ID);

alter table INJECTION
add constraint FK_INJ_SCHED foreign key (SchedID) references SCHEDULE(ID);


--Check
alter table INJECTION
add constraint CK_INJ_InjNO check (InjNO in (1, 2, 3, 4));

alter table INJECTION
add constraint CK_INJ_SchedID check(SchedID is not null);

alter table INJECTION
add constraint CK_INJ_DoseType check((DoseType is not null) and (DoseType in (0,1,2)));


            /*	TRIGGERS	*/



            /*	STORED PROCEDURES	*/
create or replace procedure INJ_INSERT_RECORD
(par_PersonalID PERSON.ID%type, 
par_InjNO INJECTION.InjNO%type, 
par_SchedID SCHEDULE.ID%type, 
par_DoseType INJECTION.DoseType%type,
par_Note INJECTION.NOte%type DEFAULT NULL)
as
begin
    insert into INJECTION(PersonalID, InjNO, SchedID, DoseType, Note)
    values (par_PersonalID, par_InjNO, par_SchedID, par_DoseType, par_Note);
end INJ_INSERT_RECORD;

            /*	STORED FUNCTIONS	*/
--Count the injections of a person
create or replace function INJ_COUNT_INJ(par_PersonalID PERSON.ID%type)
return number
is
    var_n_Injection INJECTION.InjNO%type;
begin            
    select COUNT(*) into var_n_Injection 
    from INJECTION INJ
    where INJ.PersonalID = par_PersonalID;
    
    return var_n_Injection;
end INJ_COUNT_INJ;
            
--Check the differences between the injections            
create or replace function INJ_DIFFERENCE(par_PersonalID PERSON.ID%type)
return boolean 
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
begin
    if (INJ_COUNT_INJ(par_PersonalID) <= 1)
    then
        return false;
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
            return true;
        end if;

        crow_INJ_be := crow_INJ_af;
    end loop;

    return false;
end INJ_DIFFERENCE;



/*	RECORDS	*/