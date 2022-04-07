--20521720 - Trương Ý Nhi
/*          TABLE: REGISTER          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table CERTIFICATE
(
--PersonalID FK references PERSON(ID), personal identifier consisting of 12 digits
PersonalID varchar2(12),
--The number of injected dose
Dose number(2),
--The certificate of vaccination against covid,the value 0,1,2 respectively represents a red card,yellow card, green card 
CertType number(1),
--Notes on certificate information
Note varchar2(2000),
)


/*	CONSTRAINT	*/
--Primary Key



--Foreign Key



--Check



/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/