/*	ALTER DATABASE COMMAND	
WRITE: create, insert, update, delete, select into
READ: select
*/

/*	20520418	- Dang Nghiep Cuong		
Written:
Executed:
*/

/*	20521252 -  Le Hoang Duyen		
Written: 1, 2
Executed: 1, 2
*/

/*	20521720 - Truong Y Nhi		
Written: 
Executed:
*/

/*	20521890 - Truong Nguyen Quang Thai	
Written: 
Executed:
*/

/*	LOG	*/
--1.
create table ACCOUNT
(
    --Username of an account
    Username varchar2(254) not null,

    --Password of an account
    Password varchar2(128),

    --PersonalID FK references PERSON(ID)
    PersonalID varchar2(12),

    --Role of an account, value in {0, 1, 2} | 0: MOH, 1: Organizations, 2: Citizens
    Role number(1)
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


