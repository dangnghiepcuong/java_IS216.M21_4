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

select * from certificate;

/* DELETE */
delete from parameter;

delete from health;

delete from injection;

delete from register;

delete from schedule;

delete from vaccine;

delete from organization
where ID != '44001' and ID != 'MOH';

delete from person;

delete from account
where username != '44001' and username != 'MOH'
and role = 1;

select ORG.ID, Name, Province, District, Town, Street, COUNT(SCHED.ID) 
from ORGANIZATION ORG left outer join SCHEDULE SCHED on ORG.ID = SCHED.OrgID 
where Province like '%' and (OnDate > '18-MAY-2022' or OnDate is null) 
group by ORG.ID, Name, Province, District, Town, Street 
order by TO_NUMBER(SUBSTR(ID,3,LENGTH(ID))), Province, District, Town;

commit;