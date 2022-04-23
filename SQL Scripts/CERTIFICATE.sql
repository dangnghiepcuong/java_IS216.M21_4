--20521720 - Trương Ý Nhi
/*          TABLE: REGISTER          */
--Create a table struct without constraint
--<Attribute name> <Data type>

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


/*	CONSTRAINT	*/
--Primary Key
alter table CERTIFICATE
add constraint PK_CERT primary key (PersonalID)

--Foreign Key
--alter table CERTIFICATE
add constraint FK_CERT_PERSON foreign key (PersonalID) references PERSON(ID)

--Check
alter table CERTIFICATE
add constraint CK_Dose CHECK(Dose in (1,2,3,4))

alter table CERTIFICATE
add constraintCK_ CertType CHECK(CertType in (0,1,2)) 


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/