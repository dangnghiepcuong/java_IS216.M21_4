--20520418 - Ä?áº·ng Nghiá»‡p CÆ°á»?ng
/*          TABLE: SCHEDULE          */
--Create a table struct without constraint
--<Attribute name> <Data type>

--Store data of schedules from organizations
create table SCHEDULE
(
	--Identity of a schedule, created from OrgID+DateNumber+NO
	ID varchar2(26),

	--OrganizationID FK references ORGANIZATION(ID)
	OrgID varchar2(16),
    
    --Scheduled date
    OnDate date,

	--VaccineID FK references VACCINE(ID)
	VaccineID varchar2(8),

	--The serial of the using vaccine in the schedule
	Serial varchar2(100),

	--The number of limited registers in the morning
	LimitDay number(5),

	--The number of limited registers in the afternoon
	LimitNoon number(5),

	--The number of limited registers in the evening
	LimitNight number(5),

	--The number of registered in the morning
	DayRegistered number(5),

	--The number of registered in the afternoon
	NoonRegistered number(5),

	--The number of registered in the evening
	NightRegistered number(5),

	--Note of record
	Note varchar2(2000)
);
drop table SCHEDULE;
/*	CONSTRAINT	*/
--Primary Key
alter table SCHEDULE
add constraint PK_SCHED primary key (ID);

--Foreign Key
alter table SCHEDULE
add constraint FK_SCHED_ORG foreign key (OrgID) references ORGANIZATION(ID);

alter table SCHEDULE
add constraint FK_SCHED_VAC foreign key (VaccineID) references VACCINE(ID);

alter table INJECTION
drop constraint FK_INJECTION_SCHEDID;

alter table SCHEDULE
drop constraint PK_SCHED;

--Check
alter table SCHEDULE
add constraint CK_OnDate check (OnDate is not null);

                /*	TRIGGERS	*/
-- DayRegistered <= LimitDay
create or replace trigger SCHED_Limit_Registers
after insert or update on SCHEDULE
for each row
begin
	if (:new.DayRegistered > :new.LimitDay
	OR :new.NoonRegistered > :new.LimitNoon
	OR :new.NightRegistered > :new.LimitNight)
	then
		raise_application_error(10000, 'Number of register is limited!');
	end if;
end Limit_Registers;	

                /*	STORED PROCEDURES	*/
 --Increase 1 registion at Time parameter
create or replace procedure SCHED_INC_REG(par_ID SCHEDULE.ID%type, par_RegTime REGISTER.Time%type)
as
begin
	if (par_RegTime = 0)
	then
		update SCHEDULE
		set DayRegistered = DayRegistered + 1
		where SCHEDULE.ID = par_ID
 	elsif (par_RegTime = 1)
	then
		update SCHEDULE
		set NoonRegistered = NoonRegistered + 1
		where SCHEDULE.ID = par_ID
	elseif (par_RegTime = 2)
	then
		update SCHEDULE
		set NightRegistered = NightRegistered + 1
		where SCHEDULE.ID = par_ID
	endif;
end;

--Decrease 1 registion at Time parameter
create or replace procedure SCHED_DEC_REG(par_SchedID SCHEDULE.ID%type, RegTime REGISTER.Time%type)
as
begin
	if (par_RegTime = 0)
	then
		update SCHEDULE
		set DayRegistered = DayRegistered - 1
		where SCHEDULE.ID = par_ID
 	elseif (par_TimeReg = 1)
	then
		update SCHEDULE
		set NoonRegistered = NoonRegistered - 1
		where SCHEDULE.ID = par_ID
	elseif (par_TimeReg = 2)
	then
		update SCHEDULE
		set NightRegistered = NightRegistered - 1
		where SCHEDULE.ID = par_ID
	endif;
end SCHED_DEC_REG;

create or replace procedure SCHED_INSERT_RECORD
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

end SCHED_INSERT_RECORD;



SCHED_DELETE_RECORD(par_SchedID)

/*	STORED FUNCTIONS	*/
create or replace function SCHED_GENERATE_ID
(par_OrgID ORGANIZATION.ID%type, par_OnDate date)
return varchar2 is
    StringDate varchar2(10);
    n_Scheds_OnDate number(2);
begin
    --Convert par_OnDate to string
    StringDate := TO_CHAR(SUBSTR(par_OnDate,1,2)) 
                    || TO_CHAR(SUBSTR(par_OnDate,4,2)) 
                    || TO_CHAR(SUBSTR(par_OnDate,7,4));

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


/*	RECORDS	*/