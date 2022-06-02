INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_BASICDOSE', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_BOOSTERDOSE', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_REPEATDOSE', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_BASICDOSE_CHILDREN', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_BASICDOSE_TEENAGER', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_BASICDOSE_ADULT', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_BASICDOSE_OLDPEOPLE', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_AFFECTED_CHILDREN', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_AFFECTED_TEENAGER', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_AFFECTED_ADULT', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_AFFECTED_OLDPEOPLE', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_USERS', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_PROVINCE', 0, NULL  );
INSERT INTO STATISTIC(Title,Data,Note) VALUES('STAT_PROVINCENAME', 0, NULL  );

INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(1, 'Astra', 'basic', 56, 0, 'Astra, Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 2 (c? b?n)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(2, 'Astra', 'basic', 28, 0, 'Astra, Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 3 (b? sung)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(2, 'Astra', 'basic', 28, 1, 'Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 3 (b? sung)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Astra', 'booster', 90, 0, 'Astra, Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 4 (nh?c l?i)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Astra', 'booster', 90, 1, 'Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 4 (nh?c l?i)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Astra', 'repeat', NULL, 0, 'Astra, Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Astra', 'repeat', NULL, 1, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Astra', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Astra', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(1, 'Vero', 'basic', 21, 0, 'Vero', 'M?i ?ang ??ng ký là m?i 2 (c? b?n)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(2, 'Vero', 'basic', 28, 0, 'Vero, Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 3 (b? sung)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Vero', 'booster', 90, 0, 'Vero, Pfizer, Moderna, Astra', 'M?i ?ang ??ng ký là m?i 4 (nh?c l?i)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Vero', 'booster', 90, 1, 'Pfizer, Moderna', 'M?i ?ang ??ng ký là m?i 4 (nh?c l?i)'  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Vero', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Vero', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(1, 'Sputnik', 'basic', 21, 0, 'Sputnik V', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(2, 'Sputnik', 'basic', 28, 0, 'Sputnik V, Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Sputnik', 'booster', 90, 0, 'Sputnik V', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Sputnik', 'booster', 90, 1, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Sputnik', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Sputnik', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(1, 'Pfizer', 'basic', 21, 0, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(2, 'Pfizer', 'basic', 28, 0, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(2, 'Pfizer', 'basic', 28, 1, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Pfizer', 'booster', 90, 0, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Pfizer', 'booster', 90, 1, 'Pfizer, Moderna', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Pfizer', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Pfizer', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Pfizer', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Pfizer', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(1, 'Moderna', 'basic', 28, 0, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(2, 'Moderna', 'basic', 28, 0, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(2, 'Moderna', 'basic', 28, 1, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Moderna', 'booster', 90, 0, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Moderna', 'booster', 90, 1, 'Moderna, Pfizer', NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Moderna', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(3, 'Moderna', 'repeat', NULL, 1, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Moderna', 'repeat', NULL, 0, NULL, NULL  );
INSERT INTO PARAMETER(InjectionNO,VaccineID,DoseType,MinDistance,DiffDoses,NextDose,Note) VALUES(4, 'Moderna', 'repeat', NULL, 1, NULL, NULL  );

/*1. Tạo ra Bộ Y tế. --*/
insert into ACCOUNT values ('MOH', '123', 0, 1, NULL);
select * from account;

insert into ORGANIZATION values ('MOH', 'Bộ Y tế', '01', 'Ba Đình', 'Giảng Võ', '138A', NULL);
select * from organization;


/*2. Thêm thông tin vaccine và quy định tiêm chủng. --Kiểm tra mã vaccine không được trùng */
exec VAC_INSERT_RECORD('Astra', 'Astra Zeneca', 'Vector Virus', 'Vuong Quoc Anh'); 
exec VAC_INSERT_RECORD('Vero', 'Vero Cell', 'Bat hoat', 'Trung Quoc');
exec VAC_INSERT_RECORD('Sputnik', 'Sputnik V', 'Tai to hop', 'Lien Bang Nga');
exec VAC_INSERT_RECORD('Pfizer', 'Corminaty', 'mRNA', 'Hoa Ky');
exec VAC_INSERT_RECORD('Moderna', 'Moderna', 'mRNA', 'Hoa Ky');

select * from vaccine;
exec VAC_INSERT_RECORD('Astra', 'Astra Zeneca', 'Vector Virus', 'Vuong Quoc Anh');




/*3. Tạo ra các đơn vị tiêm chủng. --Kiểm tra hàm sinh ID tự động cho ORG */
exec ACC_CREATE_ORG( 5, '44'); 
exec ACC_CREATE_ORG( 2, '44');

select * from organization;
/*4. Cập nhật thông tin ORG*/
exec ORG_UPDATE_RECORD('44001', 'BV Đa khoa Huyện Dầu Tiếng', 'Dầu Tiếng', 'Dầu Tiếng', 'ABC');

/*5. Tạo tài khoản người dùng */
--Cuong
exec ACC_INSERT_RECORD('0332743065', '123', 2, 1); 
--Duyen
exec ACC_INSERT_RECORD('0395309824', '123', 2, 1); 
--Nhi
exec ACC_INSERT_RECORD('0374540896', '123', 2, 1); 
--Thai
exec ACC_INSERT_RECORD('0347666292', '123', 2, 1);
--Nguyen Van A 0339770526/123
exec ACC_INSERT_RECORD('0339770526', '123', 2, 1); 

select * from account where Role = 2;

/*6. Thiết lập thông tin người dùng */
exec PERSON_INSERT_RECORD('20520418', 'Đặng Nghiệp', 'Cường', '17-AUG-2002', 1, 'Bình Dương', '44', 'Dầu Tiếng', 'Dầu Tiếng', 'ABC123', '0332743065', 'dangnghiepcuong@gmail.com');
exec PERSON_INSERT_RECORD('20521252', 'Lê Hoàng', 'Duyên', '27-OCT-2002', 0, 'Quảng Trị', '48', 'Cẩm Mỹ', 'Sông Ray', 'ấp 8', '0395309824', '20521252@gm.uit.edu.vn');
exec PERSON_INSERT_RECORD('20521890', 'Trương Nguyễn Quang', 'Thái', '14-FEB-2002', 1, 'Phú Yên', '39', 'Tuy An', 'An Hòa Hải', 'Tân Qui', '0347666292', '20521890@gm.uit.edu.vn');
exec PERSON_INSERT_RECORD('20521720', 'Trương Ý', 'Nhi', '23-JUN-2000', 0, 'Quảng Bình', '25', 'A', 'B', 'C', '0374540896', '20521720@gm.uit.edu.vn');
exec PERSON_INSERT_RECORD('04180282', 'Nguyen Van', 'A', '26-MAR-2020', 0, 'Bình Dương', '02', 'Thủ Đức', 'Linh Trung', 'khu phố 6', '0339770526'); 

select * from person;
/*7. Tạo lịch tiêm chủng ngày 28/4/2022 --Kiểm tra hàm sinh ID tự động cho SCHED của cùng một ORG tạo ra trong cùng một ngày */
exec SCHED_INSERT_RECORD('44001', '28-APR-2022', 'Astra', NULL, 100, 100, 100);
exec SCHED_INSERT_RECORD('44001', '28-APR-2022', 'Vero', NULL, 100, 100, 100);
exec SCHED_INSERT_RECORD('44001', '28-APR-2022', 'Pfizer', NULL, 100, 100, 100);

select * from SCHEDULE where OnDate = '28-APR-2022';

/*8. Khai báo y tế ngày 26/04/2022 --Kiểm tra hàm sinh ID tự động của form khai báo */
--Cuong
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20520418'), '20520418', '26-APR-2022', '0000'); 
--Duyen /*Dính Covid*/
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521252'), '20521252', '26-APR-2022', '0010'); 
 --Nhi /*Đối tượng trì hoãn/chống chỉ định*/
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521720'), '20521720', '26-APR-2022', '0001');
--Quang Thai chua khai bao trong vong 7 ngay.
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('04180282'), '04180282', '26-APR-2022', '0000'); 

select * from HEALTH;
/*9. Đăng ký tiêm chủng. --Kiểm tra Trigger: Dựa vào lần khai báo cuối đối tượng đang dính Covid, đối tượng trì hoãn/chống chỉ định không được tiêm, đối tượng chưa khái báo trong vòng 7 ngày và đối tượng chưa đủ tuổi tiêm chủng thì không được đăng ký tiêm chủng.*/

exec REG_INSERT_RECORD('20520418', '44001280420221', 1);
exec REG_INSERT_RECORD('20521252', '44001280420222', 0);
exec REG_INSERT_RECORD('20521720', '44001280420223', 1);
exec REG_INSERT_RECORD('20521890', '44001280420223', 2);

exec REG_INSERT_RECORD('04180282', '44001280420222', 1);


/*10. Thái, Nhi, Duyên thực hiện khai báo lại. --Kiểm tra hàm sinh ID tự động của form khai báo */
--Thai 
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521890'), '20521890', '26-APR-2022', '0000'); 
--Duyen
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521252'), '20521252', '26-APR-2022', '0000'); 
--Nhi
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521720'), '20521720', '26-APR-2022', '0000'); 

select * from HEALTH order by PersonalID;

/*11. Thái, Nhi, Duyên thực hiện đăng ký tiêm chủng lại. --Kiểm tra hàm tăng số lượng người đã đăng ký lịch tiêm và hàm sinh STT tự động. */

exec REG_INSERT_RECORD('20521252', '44001280420222', 0);
exec REG_INSERT_RECORD('20521720', '44001280420223', 1);
exec REG_INSERT_RECORD('20521890', '44001280420223', 2);

select * from REGISTER;

select * from SCHEDULE;

/*12. Điểm danh tại buổi tiêm (update trạng thái đăng ký thành điểm danh).  */

exec REG_UPDATE_STATUS('20520418', '44001280420221', 1);
exec REG_UPDATE_STATUS('20521252', '44001280420222', 1);
exec REG_UPDATE_STATUS('20521720', '44001280420223', 1);
exec REG_UPDATE_STATUS('20521890', '44001280420223', 1);

select * from REGISTER;
/*13. Thực hiện tiêm chủng (update trạng thái đăng ký thành đã tiêm). --Kiểm tra hàm tự động thêm thông tin mũi tiêm cho công dân sau khi tiêm */

exec REG_UPDATE_STATUS('20520418', '44001280420221', 2);
exec REG_UPDATE_STATUS('20521252', '44001280420222', 2);
exec REG_UPDATE_STATUS('20521720', '44001280420223', 2);
exec REG_UPDATE_STATUS('20521890', '44001280420223', 2);

--Mũi tiêm được hệ thống cập nhật tự động
select * from INJECTION;

/*14. Tạo lịch tiêm chủng mới trong ngày 28-05-2022. */
exec SCHED_INSERT_RECORD('44001', '28-MAY-2022', 'Astra', NULL, 100, 100, 100);
exec SCHED_INSERT_RECORD('44001', '28-MAY-2022', 'Vero', NULL, 100, 100, 100);
exec SCHED_INSERT_RECORD('44001', '28-MAY-2022', 'Pfizer', NULL, 100, 100, 100);

select * from SCHEDULE where OnDate = '28-MAY-2022';

/*15. Khai báo y tế ngày 22-05-2022 (nếu không được thì khai báo lại ngày 25-05-2022). */
--Cuong
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20520418'), '20520418', '22-MAY-2022', '0000'); 
--Thai
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521890'), '20521890', '22-MAY-2022', '0000'); 
--Duyen
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521252'), '20521252', '22-MAY-2022', '0000'); 
--Nhi
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521720'), '20521720', '22-MAY-2022', '0000'); 

/*16. Đăng ký tiêm chủng mũi 2. 
--Kiểm tra Trigger: Loại vaccine được phép tiêm trộn so với mũi trước đó.
--Kiểm tra Trigger: Khoảng cách giữa 2 mũi Vaccine */

--Cường mũi 1 là Astra, mũi 2 cần đợi hai tháng sau, trigger không cho phép đăng ký
exec REG_INSERT_RECORD('20520418', '44001280520221', 1);

--Duyên mũi 1 là Vero, mũi 2 đăng ký Astra, trigger sẽ kiểm tra và không cho phép
exec REG_INSERT_RECORD('20521252', '44001280520221', 0);

--Nhi mũi 1 là Pfizer, mũi 2 đăng ký Pfizer, trigger cho phép
exec REG_INSERT_RECORD('20521720', '44001280520223', 1);

--Thái mũi 1 là Pfizer, mũi 2 đăng ký Pfizer, trigger cho phép
exec REG_INSERT_RECORD('20521890', '44001280520223', 2);

/*17. Thực hiện đăng ký lại tiêm mũi 2.
--Kiểm tra Trigger: Loại vaccine được phép tiêm trộn so với mũi trước đó. */

--Duyên
exec REG_INSERT_RECORD('20521252', '44001280520222', 0);

select * from REGISTER REG, SCHEDULE SCHED
where REG.SchedID = SCHED.ID
and OnDate = '28-MAY-2022';

/*18. Cập nhật tình trạng đăng ký tiêm chủng (chuyển sang 'đã tiêm'). */
--Duyên Nhi
exec REG_UPDATE_STATUS('20521252', '44001280520222', 2);
exec REG_UPDATE_STATUS('20521720', '44001280520223', 2);
exec REG_UPDATE_STATUS('20521890', '44001280520223', 2);
--Thông tin mũi tiêm thứ 2 được cập nhật tự động.
select * from INJECTION order by InjNO;

/*19. Tạo lịch tiêm chủng đợt 3. */
--Lịch Moderna vào tháng 6 dành cho Cường tiêm mũi 2, Duyên tiêm mũi 3 (bổ sung).
exec SCHED_INSERT_RECORD('44001', '28-JUN-2022', 'Moderna', NULL, 100, 100, 100);

--Lịch Moderna vào tháng 8 dành cho Nhi và Thái nhưng mỗi buổi chỉ giới hạn 1 người tiêm duy nhất!
exec SCHED_INSERT_RECORD('44001', '28-AUG-2022', 'Moderna', NULL, 1, 1, 1);

select * from SCHEDULE where VaccineID = 'Moderna';

/*20. Khai báo y tế. */
--Cường, Duyên khai báo ngày 27-06-2022
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20520418'), '20520418', '27-JUN-2022', '0000'); 
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521252'), '20521252', '27-JUN-2022', '0000'); 

--Nhi, Thái khai báo ngày 27-08-2022
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521890'), '20521890', '27-AUG-2022', '0000'); 
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521720'), '20521720', '27-AUG-2022', '0000'); 

/*21. Đăng ký tiêm chủng đợt 3.
--Kiểm tra Trigger: Loại vaccine được phép tiêm trộn so với mũi trước đó.
--Kiểm tra Trigger: Khoảng cách giữa 2 mũi Vaccine
--Thủ tục kiểm tra: Giới hạn số lượng người đăng ký vào một buổi. */

--Trigger kiểm tra Cường đã đủ 2 tháng sau mũi 1 Astra và được tiêm trộn mũi 2 là Moderna.
exec REG_INSERT_RECORD('20520418', '44001280620221', 1);

--Trigger kiểm tra Duyên đã tiêm 2 mũi Vero, cần tiêm thêm mũi bổ sung là Vero hoặc Pfizer, Moderna.
exec REG_INSERT_RECORD('20521252', '44001280620221', 0);

--Trigger kiểm tra Nhi, Thái đã 3 tháng sau khi hoàn thành liều cơ bản, cho phép tiêm mũi 3 (nhắc lại).
--Mỗi buổi chỉ được 1 người đăng ký, Nhi và Thái đăng ký cùng một buổi chiều, Thái đăng ký sau nên không được.
exec REG_INSERT_RECORD('20521720', '44001280820221', 1);
exec REG_INSERT_RECORD('20521890', '44001280820221', 1);

--Thái đăng ký lại, chọn buổi sáng.
exec REG_INSERT_RECORD('20521890', '44001280820221', 0);

select * 
from REGISTER REG, SCHEDULE SCHED
where (REG.SchedID = SCHED.ID)
and (OnDate = '28-JUN-2022'
    or OnDate = '28-AUG-2022');

/*22. Cập nhật trạng thái tiêm */
exec REG_UPDATE_STATUS('20520418', '44001280620221', 2);
exec REG_UPDATE_STATUS('20521252', '44001280620221', 2);
exec REG_UPDATE_STATUS('20521720', '44001280820221', 2);
exec REG_UPDATE_STATUS('20521890', '44001280820221', 2);

select * from INJECTION order by InjNO;

/*23. Tạo lịch tiêm chủng đợt 4*/
--Lịch tiêm Pfizer
exec SCHED_INSERT_RECORD('44001', '28-SEP-2022', 'Pfizer', NULL, 100, 100, 100);

/*24. Khai báo y tế ngày 27/08/2022*/
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20520418'), '20520418', '27-SEP-2022', '0000'); 
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521252'), '20521252', '27-SEP-2022', '0000'); 
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521890'), '20521890', '27-SEP-2022', '0000'); 
exec HEAL_INSERT_RECORD( HEAL_FORM_ID('20521720'), '20521720', '27-SEP-2022', '0000'); 

/*25. Đăng ký tiêm chủng đợt 4 */

--Trigger kiểm tra Cường đã đủ 3 tháng sau khi hoàn thành liều cơ bản (Astra, Moderna), mũi tiếp theo sẽ là vaccine mRNA (Pfizer)
exec REG_INSERT_RECORD('20520418', '44001280920221', 1);

--Trigger kiểm tra Duyên đã đủ 3 tháng sau khi hoàn thành liều cơ bản (Vero, Vero, Moderna), mũi tiếp theo sẽ là vaccine mRNA (Pfizer)
exec REG_INSERT_RECORD('20521252', '44001280920221', 0);

--Trigger kiểm tra Nhi, Thái đã tiêm mũi nhắc lại, không thể đăng ký thêm nữa.
exec REG_INSERT_RECORD('20521720', '44001280920221', 2);
exec REG_INSERT_RECORD('20521890', '44001280920221', 2);

/*26. Cập nhật trạng thái mũi tiêm */
exec REG_UPDATE_STATUS('20520418', '44001280920221', 2);
exec REG_UPDATE_STATUS('20521252', '44001280920221', 2);

--Kiểm tra thông tin mũi tiêm được thêm tự động
select * 
from INJECTION INJ
where INJ.DoseType = 'repeat';

commit;