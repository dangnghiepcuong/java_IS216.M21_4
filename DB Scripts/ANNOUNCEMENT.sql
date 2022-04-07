--20521890 - Trương Nguyễn Quang Thái
/*          TABLE: ANNOUNCEMENT          */
--Create a table struct without constraint
--<Attribute name> <Data type>
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
    
    --Publication date of the ANNOUNCEannouncementMENT
    PublishDate date,
    
    --Attach pictures
    Image blob,
    
    --Health notes
    Note varchar2(2000)
);


/*	CONSTRAINT	*/s
--Primary Key
alter table ANNOUNCEMENT 
add constraint PK_ANNOUNCEMENT primary key (ID,OrgID);

--Foreign Key
alter table ANNOUNCEMENT
add constraint FK_ANN_ORG foreign key (OrgID) references ORGANIZATION(ID);


--Check
    --check PublishDate = SYSDATE
alter table ANNOUNCEMENT 
add constraint CK_PublishDate check(PublishDate<=Sysdate)

/*	TRIGGERS	*/
create or replace trigger ANNO_PublishDate
before insert on ANNOUNCEMENT
for each row
as
    begin
        declare @date Date
        select @date =Sysdate
        from dual
        
        if(:new.PublishDate<@date)
            print ('Thanh cong')
        else
            begin
                print('That bai')
                rollback
                
            end
    end
        
Select Sysdate
From dual

Drop trigger ANNO_PublishDate

create or replace trigger ANNO_PublishDate
after insert on ANNOUNCEMENT
for each row
BEGIN
        IF sysdate > PublishDate
             print('Thanh cong')
        ELSE
             begin
                print('That bai')
                rollback
             end
END;


/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/