select * from account;

select * from organization;

select * from vaccine;

select * from schedule
order by OnDate desc;

select * from person;

select * from register;

select * from injection
order by PersonalID;

select * from health;

select * from parameter;

select * from region;

/* DELETE */
delete from parameter;

delete from health;

delete from injection;

delete from register;

delete from schedule;

delete from vaccine;

delete from organization;

delete from person;

delete from account;


where SchedID = '44001221120221';

alter table ORGANIZATION
modify ID varchar(5);

select * from user_sys_privs;

select ID
from ORGANIZATION
where Province = 79 and rownum = 1
order by ID desc;

alter table REGISTER
add DoseType varchar2(50);

alter table INJECTION
add DoseType varchar2(50);

alter table INJECTION
modify InjNO varchar2(2);

alter table PERSON modify ID varchar2(256);

alter table PARAMETER rename column PreDose to DiffDoses;

    select * into PreInj
	from INJECTION INJ
	where INJ.PersonalID = '100000000000'
	and rownum = 1
	order by INJ.InjNo desc;
    
    select * 
    from INJECTION INJ
    where INJ.PersonalID = '100000000000'
    group by INJ.PersonalID
    having INJ.InjNO = MAX(InjNO);

update person
set FirstName = 'C??ng'
where ID = '20520418';

alter table REGION
modify CODE varchar2(2);

select * from ORGANIZATION ORG  where ORG.Province = '44'  and ORG.District = 'D?u Ti?ng'  and ORG.Town = 'D?u Ti?ng';
select Code from REGION where REGION.Name = 'B�nh D??ng';


select ORG.ID, Name, Province, District, Town, Street, COUNT(SCHED.ID) from ORGANIZATION ORG left join SCHEDULE SCHED on ORG.ID = SCHED.OrgID and ORG.Province = '44' and ORG.District like '%' and ORG.Town like '%' group by ORG.ID, Name, Province, District, Town, Street order by Province, District, Town

select DoseType, Time, NO, Status, Image, OnDate, VaccineID, Serial, Name, Province, District, Town, Street 
from REGISTER REG, SCHEDULE SCHED, ORGANIZATION ORG 
where '20520418' = REG.PersonalID 
and REG.SchedID = SCHED.ID 
and SCHED.OrgID = ORG.ID 
order by Status asc, OnDate desc

commit;