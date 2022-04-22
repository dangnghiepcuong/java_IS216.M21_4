/*          INSERT VALUE
*/


select * from user_sys_privs; 
alter session set NLS_DATE_FORMAT='DD/MM/YYYY';


INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('MOH', 'MOH', 0, 1, NULL  );
INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('79001', '79001', 1, 1, NULL  );
INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('0975364142', '123', 2, 1, NULL  );
INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('0375262108', '123', 2, 1, NULL  );
INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('0391848573', '123', 2, 1, NULL  );
INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('0168205674', '123', 2, 1, NULL  );
INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('0385334113', '123', 2, 1, NULL  );
INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('0392545773', '123', 2, 1, NULL  );
INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('0332743065', '123', 2, 1, NULL  );
INSERT INTO ACCOUNT(Username,Password,Role,Status,Note) VALUES('0974182863', '123', 2, 1, NULL  );

INSERT INTO PERSON(ID,LastName,FirstName,Birthday,Gender,Hometown,Province,District,Town,Street,Phone,Email,Guardian,Note) VALUES('100000000000', 'Nguyen Van', 'A', '01-JAN-1995', 1, '44', '01', 'A', 'B', 'C', '0975364142', NULL, NULL, NULL  );
INSERT INTO PERSON(ID,LastName,FirstName,Birthday,Gender,Hometown,Province,District,Town,Street,Phone,Email,Guardian,Note) VALUES('100000000001', 'Nguyen Thi', 'B', '01-01-1996', 0, '44', '79', 'A', 'B', 'C', '0375262108', NULL, NULL, NULL  );
INSERT INTO PERSON(ID,LastName,FirstName,Birthday,Gender,Hometown,Province,District,Town,Street,Phone,Email,Guardian,Note) VALUES('100000000002', 'Nguyen Van Thi', 'C', '01-01-2014', 0, '44', '44', 'A', 'B', 'C', '0391848573', NULL, NULL, NULL  );
INSERT INTO PERSON(ID,LastName,FirstName,Birthday,Gender,Hometown,Province,District,Town,Street,Phone,Email,Guardian,Note) VALUES('100000000003', NULL, 'D', '01-01-2005', 1, '44', '79', 'A', 'B', 'C', '0168205674', NULL, NULL, NULL  );
INSERT INTO PERSON(ID,LastName,FirstName,Birthday,Gender,Hometown,Province,District,Town,Street,Phone,Email,Guardian,Note) VALUES('100000000004', NULL, 'E', '01-01-2006', 1, '44', '79', 'A', 'B', 'C', '0385334113', NULL, NULL, NULL  );
INSERT INTO PERSON(ID,LastName,FirstName,Birthday,Gender,Hometown,Province,District,Town,Street,Phone,Email,Guardian,Note) VALUES('100000000005', NULL, 'F', '01-01-2007', 0, '44', '79', 'A', 'B', 'C', '0392545773', NULL, NULL, NULL  );
INSERT INTO PERSON(ID,LastName,FirstName,Birthday,Gender,Hometown,Province,District,Town,Street,Phone,Email,Guardian,Note) VALUES('100000000006', NULL, 'G', '01-01-2008', 0, '44', '79', 'A', 'B', 'C', '0332743065', NULL, NULL, NULL  );
INSERT INTO PERSON(ID,LastName,FirstName,Birthday,Gender,Hometown,Province,District,Town,Street,Phone,Email,Guardian,Note) VALUES('100000000007', NULL, 'H', '01-01-2009', 1, '44', '79', 'A', 'B', 'C', '0974182863', NULL, NULL, NULL  );
INSERT INTO PERSON(ID,LastName,FirstName,Birthday,Gender,Hometown,Province,District,Town,Street,Phone,Email,Guardian,Note) VALUES('100000000008', NULL, 'I', '01-01-2010', NULL, '44', '79', 'A', 'B', 'C', '0123456789', NULL, NULL, NULL  );

INSERT INTO VACCINE(ID,Name,Technology,Country,Note) VALUES('Astra', 'Astra Zeneca', 'Vector Virus', 'Vuong Quoc Anh', NULL  );
INSERT INTO VACCINE(ID,Name,Technology,Country,Note) VALUES('Vero', 'Vero Cell', 'Bat hoat', 'Trung Quoc', NULL  );
INSERT INTO VACCINE(ID,Name,Technology,Country,Note) VALUES('Sputnik', 'Sputnik V', 'Tai to hop', 'Lien Bang Nga', NULL  );
INSERT INTO VACCINE(ID,Name,Technology,Country,Note) VALUES('Pfizer', 'Corminaty', 'mRNA', 'Hoa Ky', NULL  );
INSERT INTO VACCINE(ID,Name,Technology,Country,Note) VALUES('Moderna', 'Moderna', 'mRNA', 'Hoa Ky', NULL  );

INSERT INTO ORGANIZATION(ID,Name,Province,District,Town,Street,Note) VALUES('MOH', 'Bo Y te', '79', 'Quan 3', 'Phuong 6', '51 Pham Ngoc Thach', NULL  );
INSERT INTO ORGANIZATION(ID,Name,Province,District,Town,Street,Note) VALUES('79001', 'Benh vien Nhan Dan 115', '79', 'Quan 10', 'Phuong 12', '527 Su Van Hanh', NULL  );

INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010105202201', '79001', '01-05-2022', 'Vero', 'VERO052022', 3, 2, 0, 0, 0, 0, NULL  );
INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010107202201', '79001', '01-07-2022', 'Vero', 'VERO072022', 3, 2, 0, 0, 0, 0, NULL  );
INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010108202201', '79001', '01-08-2022', 'Vero', 'VERO082022', 3, 2, 0, 0, 0, 0, NULL  );
INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010111202201', '79001', '01-11-2022', 'Vero', 'VERO112022', 3, 2, 0, 0, 0, 0, NULL  );
INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010105202202', '79001', '01-05-2022', 'Astra', 'ASTRA052022', 3, 2, 0, 0, 0, 0, NULL  );
INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010107202202', '79001', '01-07-2022', 'Astra', 'ASTRA072022', 3, 2, 0, 0, 0, 0, NULL  );
INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010108202202', '79001', '01-08-2022', 'Astra', 'ASTRA082022', 3, 2, 0, 0, 0, 0, NULL  );
INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010105202203', '79001', '01-05-2022', 'Pfizer', 'PFIZER052022', 3, 2, 0, 0, 0, 0, NULL  );
INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010107202203', '79001', '01-07-2022', 'Pfizer', 'PFIZER072022', 3, 2, 0, 0, 0, 0, NULL  );
INSERT INTO SCHEDULE(ID,OrgID,OnDate,VaccineID,Serial,LimitDay,LimitNoon,LimitNight,DayRegistered,NoonRegistered,NightRegistered,Note) VALUES('7900010108202203', '79001', '01-08-2022', 'Pfizer', 'PFIZER082022', 3, 2, 0, 0, 0, 0, NULL  );

INSERT INTO REGION(Code,Name,Note) VALUES('01', 'Ha Noi', NULL  );
INSERT INTO REGION(Code,Name,Note) VALUES('79', 'Ho Chi Minh', NULL  );
INSERT INTO REGION(Code,Name,Note) VALUES('44', 'Binh Duong', NULL  );

INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(1, 'Astra', 'basic', 56, 0, 'Astra, Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 2 (c? b?n)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(2, 'Astra', 'basic', 28, 0, 'Astra, Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 3 (b? sung)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(2, 'Astra', 'basic', 28, 1, 'Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 3 (b? sung)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Astra', 'booster', 90, 0, 'Astra, Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 4 (nh?c l?i)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Astra', 'booster', 90, 1, 'Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 4 (nh?c l?i)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Astra', 'repeat', NULL, 0, 'Astra, Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Astra', 'repeat', NULL, 1, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Astra', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Astra', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(1, 'Vero', 'basic', 21, 0, 'Vero', 'M?i ?ang ??ng ký là m?i 2 (c? b?n)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(2, 'Vero', 'basic', 28, 0, 'Vero, Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 3 (b? sung)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Vero', 'booster', 90, 0, 'Vero, Pfizer, Moderna, Astra', 'M?i ?ang ??ng ký là m?i 4 (nh?c l?i)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Vero', 'booster', 90, 1, 'Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 4 (nh?c l?i)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Vero', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Vero', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(1, 'Sputnik', 'basic', 21, 0, 'Sputnik V', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(2, 'Sputnik', 'basic', 28, 0, 'Sputnik V, Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Sputnik', 'booster', 90, 0, 'Sputnik V', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Sputnik', 'booster', 90, 1, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Sputnik', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Sputnik', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(1, 'Pfizer', 'basic', 21, 0, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(2, 'Pfizer', 'basic', 28, 0, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(2, 'Pfizer', 'basic', 28, 1, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Pfizer', 'booster', 90, 0, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Pfizer', 'booster', 90, 1, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Pfizer', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Pfizer', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Pfizer', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Pfizer', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(1, 'Moderna', 'basic', 28, 0, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(2, 'Moderna', 'basic', 28, 0, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(2, 'Moderna', 'basic', 28, 1, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Moderna', 'booster', 90, 0, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Moderna', 'booster', 90, 1, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Moderna', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(3, 'Moderna', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Moderna', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,PrevDose,NextDose,Note) VALUES(4, 'Moderna', 'repeat', NULL, 1, NULL, NULL  );

INSERT INTO STATISTIC(Title,Data,Note) VALUES('BasicDose_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('BoosterDose_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('RepeatDose_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('Children_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('Teenager_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('Adult_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('OldPeople_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('Affected_Children_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('Affected_Teenager_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('Affected_Adult_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('Affected_OldPeople_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('Users_National', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('ProvinceName_Dose', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('ProvinceName_Affected', 0, NULL  );

