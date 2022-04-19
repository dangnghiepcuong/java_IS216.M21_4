--20520418 - Đặng Nghiệp Cường
/*          TABLE: PARAMETER          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table PARAMETER
(
	--InjectionNO for parameter to references to
	InjectionNO number,

	--VaccineID for parameter to references to
	VaccineID varchar2(8),

	--Minimum spacing time between the register dose and this referencing dose
	MinDistance number,

	--Verify the difference between previous doses
	PreDose number(1),

	--The allowed vaccine for the registion dose
	NextDose varchar2(100),

	--Note of the parameter
	Note varchar2(2000)
);


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