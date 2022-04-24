--20521890 - Trương Nguyễn Quang Thái
/*          TABLE: ANNOUNCEMENT          */
--Create a table struct without constraint
--<Attribute name> <Data type>
create table ANNOUNCEMENT
(
--ID 
    ID varchar2(50),
    
    --OrganizationID FK references ORGANIZATION(ID)
    OrgID varchar2(5),
    
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


--Check
    --check PublishDate = SYSDATE


/*	TRIGGERS	*/
--PublishDate bang ngay hom nay
create or replace trigger ANNO_PublishDate
after insert on ANNOUNCEMENT
for each row
BEGIN
        --IF  to_char(sysdate,'dd-mm-yyyy') >= :new.PublishDate
        if ((Extract(Year From :new.PublishDate)=Extract(Year From sysdate))
            and (Extract(month From :new.PublishDate)=Extract(month From sysdate))
            and (Extract(day From :new.PublishDate)=Extract(day From sysdate)))
        then
            DBMS_OUTPUT.PUT_LINE('Da them thanh cong');
        else
            RAISE_APPLICATION_ERROR(-20000,'Publish date must follows the system date!') ;
        end if;
END;

/*	STORED PROCEDURES	*/
create or replace procedure ANN_INSERT_RECORD ( par_OrgID ANNOUNCEMENT.OrgID%type,
                                                par_Tittle ANNOUNCEMENT.Tittle%type,
                                                par_Content ANNOUNCEMENT.Content%type,
                                                par_PuslishDate ANNOUNCEMENT.PublishDate%type,
                                                par_Note ANNOUNCEMENT.Note%type DEFAULT NULL)
as
    Temp_ID ANNOUNCEMENT.ID%type;
begin 
    
    --Give next value of ID
    Temp_ID := ANN_ID(par_OrgID);
    
    --Insert
    insert into ANNOUNCEMENT(ID, OrgID, Title, Content, PublishDate, Note)
    values (par_OrgID, par_Tittle, par_Content, par_PuslishDate, par_Note);
       
end;


/*	STORED FUNCTIONS	*/




/*	RECORDS	*/