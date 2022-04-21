/*	ALTER DATABASE COMMAND	
WRITE: create, insert, update, delete, select into
READ: select
*/

/*	20520418	- Dang Nghiep Cuong		
Written:
Executed:
*/

/*	20521252 -  Le Hoang Duyen		
Written:
Executed:
*/

/*	20521720 - Truong Y Nhi		
Written: 
Executed:
*/

/*	20521890 - Truong Nguyen Quang Thai	
Written: 5,10
Executed:
*/

/*	LOG	*/
/*
========================================================
                    TABLE ACCOUNT
========================================================
*/
create table ACCOUNT
(
    --Username of an account
    Username varchar2(254) not null,

    --Password of an account
    Password varchar2(128) not null,

    --Role of an account, value in {0, 1, 2} |
    -- 0: MOH, 1: Organizations, 2: Citizens and default value of Role is null
    Role number(1) DEFAULT (null),
    
    --Status of an account, value in {0,1} | 0: active, 1: blocked
    Status number(1),
    
    --Note of an account
    Note varchar2(2000)
    
);

                /*	CONSTRAINT	*/
--Primary Key
alter table ACCOUNT
add constraint PK_ACC primary key (Username);

--Check
alter table ACCOUNT
add constraint CK_ACC_Role check (Role in (0, 1, 2));

/*
========================================================
                    TABLE PERSON
========================================================
*/
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
    District varchar2(50),

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

/*
========================================================
                    TABLE VACCINE
========================================================
*/
create table VACCINE 
(
	--ID of vaccine
    	ID varchar2(8),

	--Name of vaccine
    	Name varchar2(100),

	--Technology used to produce vaccine
    	Technology varchar2(100),

	--The country produces the vaccine
    	Country varchar2(100),

	--Note of the vaccine
    	Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table VACCINE add constraint PK_VACCINE primary key (ID);

--Foreign Key

--Check
alter table VACCINE add constraint UNI_Name unique (Name);

/*
========================================================
                    TABLE INJECTION
========================================================
*/
create table INJECTION
(
    --PersonalID FK references PERSON(ID)
    PersonalID varchar2(12) not null,
    
    --Injection sequence number of the citizen, value in { 1, 2, 3, 4}
    InjNO number(5),
    
    --SchedlID FK references SCHEDULE(ID)
    SchedID varchar2(10),
    
    
    --Note on the injection
    Note varchar2(2000)
);

                /*	CONSTRAINT	*/
--Primary Key
alter table INJECTION 
add constraint PK_INJECTION primary key (PersonalID, InjNO);

--Foreign Key
alter table INJECTION
add constraint FK_INJECTION_PerID foreign key (PersonalID) references PERSON(ID);

alter table INJECTION
add constraint FK_INJECTION_SchedID foreign key (SchedID) references SCHEDULE(ID);

--Check
alter table INJECTION
add constraint CK_INJECTION_InjNO check (InjNO in (1, 2, 3, 4));

/*
========================================================
                TABLE ORGANIZATION
========================================================
*/
create table ORGANIZATION
(
    --Identifier of organization, ID FK references ACCOUNT(OrgID)
    ID varchar2(5),
    
    --Name of organization..
    Name varchar2(100),
    
    --Province of organization
    Province varchar2(50),
    
    --District of organization
    District varchar2(50),
    
    --Town of organization
    Town varchar2(50),
    
    --Street of organization
    Street varchar2(100),
    
    --Note of organization
    Note varchar2(2000)
);

            /*	CONSTRAINT	*/
--Primary Key
alter table ORGANIZATION 
add constraint PK_ORG primary key (ID);

--Foreign Key
alter table ORGANIZATION 
add constraint FK_ORG_ACC foreign key (ID) references ACCOUNT(Username);

--Check
alter table ORGANIZATION
add constraint CK_PROVINCE check (Province is not null);

/*
========================================================
                TABLE SCHEDULE
========================================================
*/
create table SCHEDULE
(
	--Identity of a schedule, created from OrgID+DateNumber+NO
	ID varchar2(26),

	--OrganizationID FK references ORGANIZATION(ID)
	OrgID varchar2(5),
    
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
	DayRegister number(5),

	--The number of registered in the afternoon
	NoonRegister number(5),

	--The number of registered in the evening
	NightRegister number(5),

	--Note of record
	Note varchar2(2000)
);

/*	CONSTRAINT	*/
--Primary Key
alter table SCHEDULE
add constraint PK_SCHED primary key (ID);

--Foreign Key
alter table SCHEDULE
add constraint FK_SCHED_ORG foreign key (OrgID) references ORGANIZATION(ID);

alter table SCHEDULE
add constraint FK_SCHED_VAC foreign key (VaccineID) references VACCINE(ID);

--Check
alter table SCHEDULE
add constraint CK_OnDate check (OnDate is not null);

/*
========================================================
                TABLE REGISTER
========================================================
*/
create table REGISTER
(
	--PersonalID of a person
	PersonalID varchar2(12),

	--ScheduleID of a schedule
	SchedID varchar2(26),
	
	--The time for injection (morning, afternoon or night)
	Time number(1),
	
	--Number order of registion
	NO number(5),
	
	--Status of the registered injection (registered, attended, injected, canceled)
	Status number(1),

	--Image of the vaccinated confirmation paper signed by the organization hold the schedule
	Image blob,
	
	--Note of the registion
	Note varchar2(2000)
);

/*	CONSTRAINT	*/
--Primary Key
alter table REGISTER
add constraint PK_REG primary key (PersonalID, SchedID);

--Foreign Key
alter table REGISTER
add constraint FK_REG_PERSON foreign key (PersonalID) references PERSON(ID);

alter table REGISTER
add constraint FK_REG_SCHED foreign key (SchedID) references SCHEDULE(ID);

--Check
alter table REGISTER
add constraint CK_REG_Time CHECK(Time in (0,1,2));

alter table REGISTER
add constraint CK_REG_Status CHECK(Status in (0,1,2,3));

/*
========================================================
                TABLE CERTIFICATE
========================================================
*/
create table CERTIFICATE
(
	--PersonalID FK references PERSON(ID)
	PersonalID varchar2(12),
	
	--Number of injected doses
	Dose number(2),
	
	--Type of certificate: 0 (red), 1 (yellow) or 2 (green).
	CertType number(1),
	
	--Note of certificate
	Note varchar2(2000)
);

/*	CONSTRAINT	*/
--Primary Key
alter table CERTIFICATE
add constraint PK_CERT primary key (PersonalID);

--Foreign Key
alter table CERTIFICATE
add constraint FK_CERT_PERSON foreign key (PersonalID) references PERSON(ID);

--Check
alter table CERTIFICATE
add constraint CK_Dose CHECK(Dose in (1,2,3,4));

alter table CERTIFICATE
add constraint CK_CertType CHECK(CertType in (0,1,2)); 

/*
========================================================
                TABLE HEALTH
========================================================
*/
create table HEALTH
(
	PersonalID varchar2(12),
	FilledDate date,
	Healths varchar2(20),
	Note varchar2(2000)
);

/*	CONSTRAINT	*/
--Primary Key
alter table HEALTH
add constraint PK_HEAL primary key (PersonalID);

--Foreign Key
alter table HEALTH
add constraint FK_HEAL_PERSON foreign key (PersonalID) references PERSON(ID);

/*
========================================================
                TABLE ANNOUNCEMENT
========================================================
*/
create table ANNOUNCEMENT
(
--ID 
    ID varchar2(50),
    
    --OrganizationID FK references ORGANIZATION(ID)
    OrgID varchar2(16),
    
    --Titel for announcement
    Title varchar2(200),
    
    --Content for announcement
    Content varchar2(4000),
    
    --Publication date of the ANNOUNCEMENT
    PublishDate date,
    
    --Attach pictures
    Image blob,
    
    --Health notes
    Note varchar2(2000)
);

/*	CONSTRAINT	*/
--Primary Key
alter table ANNOUNCEMENT 
add constraint PK_ANN primary key (ID,OrgID);

--Foreign Key
alter table ANNOUNCEMENT
add constraint FK_ANN_ORG foreign key (OrgID) references ORGANIZATION(ID);

/*
========================================================
                TABLE PARAMETER
========================================================
*/
create table PARAMETER
(
	--InjectionNO for parameter to references to
	InjectionNO number,

	--VaccineID for parameter to references to
	VaccineID varchar2(8),
    
    --Dose type (basic, booster, repeat)
    DoseType varchar2(100),

	--Minimum spacing time between the register dose and this referencing dose
	MinDistance number,

	--Verify the difference between previous doses
	PreDose number(1),

	--The allowed vaccine for the registion dose
	NextDose varchar2(100),

	--Note of the parameter
	Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table PARAMETER
add constraint PK_PAR primary key (InjectionNO, VaccineID);

--Foreign Key
alter table PARAMETER
add constraint CK_PAR_InjectionNO check (InjectionNO in (1, 2, 3, 4));

alter table PARAMETER
add constraint FK_PAR_VAC foreign key (VaccineID) references VACCINE(ID);

--Check
alter table PARAMETER
add constraint CK_PreDose CHECK(PreDose in (0,1));

alter table PARAMETER
add constraint CK_DoseType CHECK (DoseType in ('basic', 'booster', 'repeat'));

/*
========================================================
                TABLE STATISTIC
========================================================
*/
create table STATISTIC
(
	--Title of statistic
	Title varchar2(200),

	--Statistic data
	Data number
);
