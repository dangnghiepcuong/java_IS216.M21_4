-- 20521890 - Trương Nguyễn Quang Thái
/*          TABLE: ORGANIZATION          */
--Create a table struct without constraint
--<Attribute name> <Data type>
create table ORGANIZATION
(
    --Identifier of organization, ID FK references ACCOUNT(OrgID)
    ID varchar2(16),
    
    --Name of organization
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
add constraint FK_ORG_ACC foreign key (ID) references ACCOUNT(OrgID);


--Check
    --Check name is not null
alter table ORGANIZATION
add constraint CHK_NAME check (Name is not null);

alter table ORGANIZATION
add constraint CHK_PROVINCE check (Province is not null);
/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/
--Insert
create or replace procedure ORG_INSERT_RECORD (par_ID ORGANIZATION.ID%type,
                                             par_Name ORGANIZATION.Name%type,
                                             par_Province ORGANIZATION.Province%type,
                                             par_District ORGANIZATION.District%type,
                                             par_Town ORGANIZATION.Town%type,
                                             par_Street ORGANIZATION.Street%type
			par_Note  ORGANIZATION.Note%type)                                           
as 
begin
    --insert new ORGANIZATION
	insert into ORGANIZATION(ID, Name, Province, District, Town, Street, Note) 
	values (par_ID, par_Name, par_Province, par_District, par_Town, par_Street, par_Note);
end ORG_INSERT_RECORD;

--Delete
create or replace procedure ORG_DELETE_RECORD (par_ID ORGANIZATION.ID%type)
as
begin
    delete *
    from ORGANIZATION
    where ID = par_ID;
end ORG_DELETE_RECORD;

--Update
create or replace procedure ORG_UPDATE_RECORD (par_ID ORGANIZATION.ID%type,
                                                par_Name ORGANIZATION.Name%type,
                                                par_Province ORGANIZATION.Province%type)
as
begin
    update ORGANIZATION
    set Name = par_Name,
        Province = par_Province
    where ID = par_ID;
end ORG_UPDATE_RECORD;

EXEC ORG_UPDATE_RECORD(5,'h','ha')

--Annouce


/*	STORED FUNCTIONS	*/
--



/*	RECORDS	*/