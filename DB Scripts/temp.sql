select * from account;
select * from organization;

select * from vaccine;

select * from schedule;

select * from person;

select * from register;

delete from organization;
delete from account;

alter table ORGANIZATION
modify ID varchar(5);

select * from user_sys_privs;

select ID
from ORGANIZATION
where Province = 79 and rownum = 1
order by ID desc;

alter table REGISTER
add DoseType varchar2(50);