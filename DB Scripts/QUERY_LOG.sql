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
--1.
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

--2
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

--3
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

--4
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

--5
create table ORGANIZATION
(
    --Identifier of organization, ID FK references ACCOUNT(OrgID)
    ID varchar2(16),
    
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

--6
create table ORGANIZATION
(
    --Identifier of organization, ID FK references ACCOUNT(OrgID)
    ID varchar2(16),
    
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

--7
create table SCHEDULE
(
	--Identity of a schedule, created from OrgID+DateNumber+NO
	ID varchar2(26),

	--OrganizationID FK references ORGANIZATION(ID)
	OrgID varchar2(16),

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

--8
create table REGISTER
(
	--PersonalID of a person
	PersonalID varchar2(12),

	--ScheduleID of a schedule
	SchedID varchar2(26),
	
	--The time for injection (morning, afternoon or night)
	Time number(1),
	
	--Number order of registion
	NO
	
	--Status of the registered injection (registered, attended, injected, canceled)
	Status number(1),

	--Image of the vaccinated confirmation paper signed by the organization hold the schedule
	Image blob,
	
	--Note of the registion
	Note varchar2(2000)
);

--9
create table CERTIFICATE
(
	--PersonalID FK references PERSON(ID)
	PersonalID varchar2(12),
	
	--Number of injected doses
	Dose number(2),
	
	--Type of certificate: 0 (red), 1 (yellow) or 2 (green).
	CertType number(1),
	
	--Note of certificate
	Note varchar2(2000),
);

--10
create table HEALTH
(
	PersonalID varchar2(12),
	FilledDate date,
	Healths varchar2(20),
	Note varchar2(2000)
);

--11
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

--11
create table PARAMETER
(
	--InjectionNO for parameter to references to
	InjectionNO number,

	--VaccineID for parameter to references to
	VaccineID varchar2(8),

	--Minimum spacing time between the register dose and this referencing dose
	MinDistance number,

	--Verify the difference between previous doses
	PreDose number(1),

	--The allowed vaccine for the registion dose
	NextDose varchar2(100),

	--Note of the parameter
	Note varchar2(2000)
);

--12
create table STATISTIC
(
	--Title of statistic
	Title varchar2(200),

	--Statistic data
	Data number
);
