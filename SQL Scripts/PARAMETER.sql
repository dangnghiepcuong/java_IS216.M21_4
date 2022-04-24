--20520418 - Ä?áº·ng Nghiá»‡p CÆ°á»?ng
/*          TABLE: PARAMETER          */
--Create a table struct without constraint
--<Attribute name> <Data type>

create table PARAMETER
(
	--InjectionNO for parameter to references to
	InjectionNO number,

	--VaccineID for parameter to references to
	VaccineID varchar2(8),
    
    	--Dose type (basic, booster, repeat)
    	DoseType varchar2(100),

	--Minimum spacing time between the register dose and this referencing dose
	MinDistance number,

	--Verify the difference between previous doses
	DiffDose number,

	--The allowed vaccine for the registion dose
	NextDose varchar2(100),

	--Note of the parameter
	Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table PARAMETER
add constraint PK_PAR primary key (InjectionNO, VaccineID, DoseType, DiffDoses);

alter table PARAMETER
drop constraint PK_PAR;
--Foreign Key
alter table PARAMETER
add constraint FK_PAR_INJ foreign key (InjectionNO) references INJECTION(InjNO);

alter table PARAMETER
add constraint FK_PAR_VAC foreign key (VaccineID) references VACCINE(ID);

--Check
alter table PARAMETER
add constraint CK_DiffDoses CHECK(DiffDoses in (0,1));

alter table PARAMETER
add constraint CK_DoseType CHECK (DoseType in ('basic', 'booster', 'repeat'));

/*	TRIGGERS	*/



/*	STORED PROCEDURES	*/



/*	STORED FUNCTIONS	*/




/*	RECORDS	*/