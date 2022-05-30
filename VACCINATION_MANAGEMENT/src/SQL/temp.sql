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

select COUNT(*) from region;

select * from certificate;

alter table REGION
add constraint PK_REGION primary key (ProvinceCode,DistrictCode,TownCode);

insert into REGION (ProvinceCode, ProvinceName, DistrictCode, DistrictName, TownCode, TownName, Note) values ('62', 'T?nh Kon Tum', '618', 'Huy?n Ia H'' Drai', '23537', 'Xã Ia Dom', null);

insert into REGION (ProvinceCode, ProvinceName, DistrictCode, DistrictName, TownCode, TownName, Note) values ('62', 'T?nh Kon Tum', '618', 'Huy?n Ia H'' Drai', '23535', 'Xã Ia ?al', null);

select * from REGION where towncode = '23535';

commit;