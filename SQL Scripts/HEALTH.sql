/*          TABLE: HEALTH          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table HEALTH
(
      ID int,
	PersonalID varchar2(12),
	FilledDate date,
	Healths varchar2(20),
	Note varchar2(2000),
);

/*	CONSTRAINT	*/
--Primary Key
alter table HEALTH
add constraint PK_HEAL primary key (PersonalID, ID);

--Foreign Key
alter table HEALTH
add constraint FK_HEAL_PERSON foreign key (PersonalID) references PERSON(ID);


--Check

/*	TRIGGERS	*/
---check Filleddate=SYSDATE

create or replace trigger HEALTH_FilledDate
after insert or update on HEALTH
for each row
begin
    if((Extract(Day From : new.FilledDate) != Extract(Day From SYSDATE))
    and (Extract(Month From : new.FilledDate) != Extract(Month From SYSDATE))
    and (Extract(Year From : new.FilledDate) != Extract(Year From SYSDATE))
    then 
        RAISE_APPLICATION_ERROR(-10000,'Filled date must be according to SYSDATE!);
end if;
end HEALTH_FilledDate;


/*	STORED PROCEDURES	*/
--insert

create or replace procedure HEAL_INSERT_RECORD
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


/*	STORED FUNCTIONS	*/
--return the next ID for filling form

create or replace function HEAL_FORM_ID(par_personalID PERSON.ID%type)
return number is
--return value of count health form
Count_HEAL_FORM number;
begin
if(par_personalID = NULL)
then 
return NULL;
end if;
select Count(PersonalID) into Count_HEAL_FORM
from HEALTH
return Count_HEAL_FORM;
end HEAL_FORM_ID;




/*	RECORDS	*/