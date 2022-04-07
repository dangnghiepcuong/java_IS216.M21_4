-- 20521890 - Trương Nguyễn Quang Thái
/*          TABLE: ORGANIZATION          */
--Create a table struct without constraint
--<Attribute name> <Data type>
create table ORGANIZATION
(
    ID varchar2(16),
    Name varchar2(100),
    Province varchar2(50),
    District varchar2(50),
    Town varchar2(50),
    Street varchar2(100),
    Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table ORGANIZATION 
add constraint PK_ORGANIZATION primary key (ID);

--Foreign Key
alter table ORGANIZATION 
add constraint FK_ORG_ACC foreign key (ID) references ACCOUNT(OrgID);


--Check
    --Check name is not null
alter table ORGANIZATION
add constraint CHK_NAME check (Name is not null);

alter table ORGANIZATION
add constraint CHK_PROVINCE check (Province is not null);
/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/
--


/*	STORED FUNCTIONS	*/
--



/*	RECORDS	*/