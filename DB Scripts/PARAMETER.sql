--20520418 - Đặng Nghiệp Cường
/*          TABLE: PARAMETER          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table PARAMETER
(
	InjectionNO number,
	VaccineID varchar2(8),
	MinDistance number,
	PreDose varchar2(100),
	NextDose varchar2(100),
	Note varchar2(2000)
)


/*	CONSTRAINT	*/
--Primary Key
alter table PARAMETER
add constraint PK_PAR primary key (InjectionNO, VaccineID)


--Foreign Key
alter table PARAMETER
add constraint FK_PAR_INJ foreign key (InjectionNO) references INJECTION(InjNO)

alter table PARAMETER
add constraint FK_PAR_VAC foreign key (VaccineID) referencesVACCINE(ID)

--Check
alter table PARAMETER
add constraint CK_PreDose CHECK(PreDose in (0,1))


/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/