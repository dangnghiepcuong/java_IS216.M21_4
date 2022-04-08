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
--Insert
create or replace procedure ORG_INSERT_RECORD (org_ID ORGANIZATION.ID%type,
                                             org_Name ORGANIZATION.Name%type,
                                             org_Province ORGANIZATION.Province%type,
                                             org_District ORGANIZATION.District%type,
                                             org_Town ORGANIZATION.Town%type,
                                             org_Street ORGANIZATION.Street%type)                                           
as 
begin
    --insert new ORGANIZATION
    
	insert into ORGANIZATION(ID, Name, Province, District, Town, Street, Note) 
    values (org_ID, org_Name, org_Province, org_District, org_Town, org_Street,
    NULL);
end;

--Delete
create or replace procedure ORG_DELETE_RECORD (Org_ID ORGANIZATION.ID%type)
as
begin
    delete 
    from ORGANIZATION
    where ID=Org_ID;
end;

--Update
create or replace procedure ORG_UPDATE_RECORD (Org_ID ORGANIZATION.ID%type,
                                                org_Name ORGANIZATION.Name%type,
                                                org_Province ORGANIZATION.Province%type)
as
begin
    update ORGANIZATION
    set Name=org_Name,
        Province=org_Province
    where ID=Org_ID;
end;

EXEC ORG_UPDATE_RECORD(5,'h','ha')

--Annouce


/*	STORED FUNCTIONS	*/
--



/*	RECORDS	*/