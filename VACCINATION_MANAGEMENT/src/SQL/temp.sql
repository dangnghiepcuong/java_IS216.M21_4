select * from account;

select * from organization;

select * from vaccine;

select * from schedule
order by OnDate desc;

select * from person where ID = '281325656';

select * from register;

select * from injection
order by PersonalID;

select * from health;

select * from parameter;

select COUNT(*) from region;

select * from certificate;

select * from announcement;

select * from statistic;

DELETE FROM HEALTH;
DELETE FROM INJECTION;
DELETE FROM REGISTER;
DELETE FROM SCHEDULE;
DELETE FROM ANNOUNCEMENT;
DELETE FROM CERTIFICATE;

DELETE FROM PERSON;
DELETE FROM ORGANIZATION;
DELETE FROM ACCOUNT;

commit;

delete account where role = 2;

insert into ACCOUNT (Username, Password, Role, Status, Note)
values ('MOH', '123', 0, 1, null);

insert into ORGANIZATION (ID, Name, ProvinceName, DistrictName, TownName, Street, Note)
values ('01000', 'B? Y t?', 'Hà N?i', 'Ba ?ình', 'Kim Mã', '138A ???ng Gi?ng Võ', null);

select VAC.ID, Name, Technology, Country, COUNT(SCHED.ID) 
from VACCINE VAC, SCHEDULE SCHED
where VAC.ID = SCHED.VaccineID
and DayRegistered + NoonRegistered + NightRegistered > 0
group by VAC.ID, Name, Technology, Country;

select * from Announcement join Organization 
on Announcement.OrgID = Organization.ID 
where (sysdate - PublishDate) <= 28 
and PublishDate <= sysdate 
order by PublishDate desc;