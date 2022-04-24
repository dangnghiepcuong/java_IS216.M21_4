--20521252 - Lê Hoàng Duyên
/*          TABLE: PERSON          */
--Create a table struct without constraint
--<Attribute name> <Data type>

--Store data of information from citizens
create table PERSON
(
    --Identity of the citizen
    ID varchar2(12) not null,
    
    --Last name of the citizen
    LastName varchar2(100),

    --First name of the citizen
    FirstName varchar2(50),

    --Birthday of the citizen
    Birthday date,

    --Gender of the citizen, value in {0, 1, 2} | 0: female, 1: male, 2: orther
    Gender number(1),

    --Hometown of the citizen
    Hometown varchar2(50),

    --Province address of citizen
    Province varchar2(50),

    --District address of the citizen
    District varchar2(2),

    --Town address of the citizen
    Town varchar2(50),

    --Street address of the citizen
    Street varchar2(100),

    --Phone number of the citizen
    Phone varchar2(30),

    --Email of the citizen
    Email varchar2(254),

    --Guardian of the citizen (store a PersonalID)
    Guardian varchar2(12),

    --Note on the citizen
    Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table PERSON
add constraint PK_PERSON primary key (ID);


--Foreign Key
alter table PERSON
add constraint FK_PERSON_GUAR foreign key (Guardian) references PERSON(ID);

alter table PERSON
add constraint FK_PERSON_ACC foreign key (Phone) references ACCOUNT(Username);

--Check

--Check Gender has a value in { 0, 1, 2}
alter table PERSON
add constraint CK_PERSON_Gender check (Gender in (0, 1, 2));


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/
create or replace procedure PERSON_INSERT_RECORD(par_ID PERSON.ID%type, 
    par_LastName PERSON.LastName%type, par_FirstName PERSON.FirstName%type,
    par_Birthday PERSON.Birthday%type, par_Gender PERSON.Gender%type,
    par_HomeTown PERSON.HomeTown%type, par_Province PERSON.Province%type,
    par_District PERSON.District%type, par_Town PERSON.Town%type,
    par_Street PERSON.Street%type,
    par_Phone PERSON.Phone%type, par_Email PERSON.Email%type,
    par_Guardian PERSON.Guardian%type, par_Note PERSON.Note%type DEFAULT NULL) 
as
begin
    --create new PERSON
    insert into PERSON(ID, LastName, FirstName, Birthday, Gender, HomeTown,
    Province, District, Town, Street, Phone, Email, Guardian, Note) 
    values (par_ID, par_LastName, par_FirstName, par_Birthday, par_Gender,
    par_HomeTown, par_Province, par_Distruct, par_Town, par_Street, par_Phone,
    par_Email, par_Guardian, par_Note);
    
    commit;
    
    EXCEPTION
        when DUP_VAL_ON_INDEX
        then
            raise_application_error(1000,'ID has been registered by another user!');
end PERSON_INSERT_RECORD;


/*	STORED FUNCTIONS	*/



/*	RECORDS	*/