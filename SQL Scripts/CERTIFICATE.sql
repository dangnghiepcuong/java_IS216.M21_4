--
--20521720 - Trương Ý Nhi
/*          TABLE: CERTIFICATE 	*/
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
add constraint CK_ CertType CHECK(CertType in (0,1,2)) 


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/

--Count the number of certificate type

create or replace function NUM_COUNT_CERT (par_Type CERTIFICATE.Type%type)
return number is
--return value of count certificate type
Count_CERT int;
begin
if(par_Type = NULL)
then
return NULL;
end if;
select Count(Type) into Count_CERT
from CERTIFICATE 
return Count_CERT;
end NUM_COUNT_CERT;

--Count the number of dose

create or replace function NUM_COUNT_DOSE (par_Dose CERTIFICATE.Dose%type)
return number is
--return value of count dose
Count_Dose int;
begin
if(par_Dose = NULL)
then
return NULL;
end if;
select Count(Dose) into Count_Dose
from CERTIFICATE
return Count_Dose;
end NUM_COUNT_DOSE;


/*	RECORDS	*/