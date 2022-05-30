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

select ID into temp_ID
    from ORGANIZATION
    where ProvinceName = 'Hà N?i' 
    and TO_NUMBER(SUBSTR(ID,3,5)) = (select COUNT(ID) 
                                    from ORGANIZATION
                                    where ProvinceName = 'Hà N?i');