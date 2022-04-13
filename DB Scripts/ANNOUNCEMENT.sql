--20521890 - Trương Nguyễn Quang Thái
/*          TABLE: ANNOUNCEMENT          */
--Create a table struct without constraint
--<Attribute name> <Data type>
create table ANNOUNCEMENT
(
    ID varchar2(50),
    OrgID varchar2(16),
    Title varchar2(200),
    Content varchar2(4000),
    PublishDate date,
    Image blob,
    Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table ANNOUNCEMENT add constraint PK_ANNOUNCEMENT primary key (ID,OrgID);

--Foreign Key



--Check



/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/