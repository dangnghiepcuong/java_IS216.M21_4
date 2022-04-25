--
--20521720 - Trương Ý Nhi
/*          TABLE: HEALTH          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table HEALTH
(
<<<<<<< Updated upstream
=======
      ID int,
>>>>>>> Stashed changes
	PersonalID varchar2(12),
	FilledDate date,
	Healths varchar2(20),
	Note varchar2(2000),
);

/*	CONSTRAINT	*/
--Primary Key
alter table HEALTH
add constraint PK_HEAL primary key (PersonalID)


--Foreign Key
alter table HEALTH
add constraint FK_HEAL_PERSON foreign key (PersonalID) references PERSON(ID)


--Check


/*	TRIGGERS	*/
---check Filleddate=SYSDATE

create or replace trigger HEALTH_FilledDate
after insert or update on HEALTH
for each row
begin
<<<<<<< Updated upstream
if((Extract(Year From : new.FilledDate) = Extract(Year From SYSDATE))
and (Extract(Month From : new.FilledDate) = Extract(Year From SYSDATE))
and (Extract(Year From : new.FilledDate) = Extract(Year From SYSDATE))
then 
DBMS_OUTPUT.PUT_LINE('Successfully added filled date');
else
RAISE_APPLICATION_ERROR(-10000,'Filled date must be according to SYSDATE!);
=======
    if((Extract(Day From : new.FilledDate) != Extract(Day From SYSDATE))
    and (Extract(Month From : new.FilledDate) != Extract(Month From SYSDATE))
    and (Extract(Year From : new.FilledDate) != Extract(Year From SYSDATE))
    then 
        RAISE_APPLICATION_ERROR(-10000,'Filled date must be according to SYSDATE!);
>>>>>>> Stashed changes
end if;
end HEALTH_FilledDate;


/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/
--return the next ID for filling form

create or replace function NUM__COUNT_HEAL_FORM_ID(par_personalID PERSON.ID%type)
return number is
--return value of count health form
Count_HEAL_FORM int;
begin
if(par_personalID = NULL)
then 
return NULL;
end if;
select Count(PersonalID) into Count_HEAL_FORM
from HEALTH
return Count_HEAL_FORM;
end NUM_COUNT_HEAL_FORM_ID;




/*	RECORDS	*/