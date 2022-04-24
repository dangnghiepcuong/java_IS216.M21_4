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


--Check

--Check Gender has a value in { 0, 1, 2}
alter table PERSON
add constraint CK_PERSON_Gender check (Gender in (0, 1, 2));


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/