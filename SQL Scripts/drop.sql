--
/*	ALTER DATABASE COMMAND	

/*
========================================================
                    TABLE ACCOUNT
========================================================
*/
drop table ACCOUNT;
alter table ACCOUNT drop constraint CK_ACC_Role;
alter table ACCOUNT drop constraint PK_ACC;
alter table ORGANIZATION drop constraint FK_ORG_ACC;

--
/*
========================================================
                    TABLE PERSON
========================================================
*/
drop table PERSON;

alter table PERSON drop constraint PK_PERSON;
alter table PERSON drop constraint FK_PERSON_GUAR;
alter table PERSON drop constraint FK_PERSON_ACC;

alter table PERSON drop constraint CK_PERSON_Gender;


/*
========================================================
                    TABLE VACCINE
========================================================
*/
drop table VACCINE;
alter table VACCINE drop constraint UNI_Name;
alter table VACCINE drop constraint PK_VACCINE;


/*
========================================================
                    TABLE INJECTION
========================================================
*/
drop table INJECTION;

alter table INJECTION drop constraint PK_INJ;
alter table INJECTION drop constraint CK_INJ_InjNO;
alter table INJECTION drop constraint CK_INJ_ScheID;
alter table INJECTION drop constraint CK_INJ_DoseType;
alter table INJECTION drop constraint FK_INJ_ScheID;
alter table INJECTION drop constraint FK_INJ_PERSON;




/*
========================================================
                TABLE ORGANIZATION
========================================================
*/
drop table ORGANIZATION;
alter table ORGANIZATION drop constraint CK_ORG_NAME;
alter table ORGANIZATION drop constraint CK_ORG_PROVINCE;
alter table ORGANIZATION drop constraint PK_ORG;


/*
========================================================
                TABLE SCHEDULE
========================================================
*/
drop table SCHEDULE;
alter table SCHEDULE drop constraint PK_SCHED;
alter table SCHEDULE drop constraint FK_SCHED_ORG;
alter table SCHEDULE drop constraint FK_SCHED_VAC;
alter table SCHEDULE drop constraint CK_SCHED_OnDate;
alter table SCHEDULE drop constraint CK_SCHED_LimitDay;

alter table SCHEDULE drop constraint CK_SCHED_LimitNoon;

alter table SCHEDULE drop constraint CK_SCHED_LimitNight;

alter table SCHEDULE drop constraint CK_SCHED_DayRegistered;

alter table SCHEDULE drop constraint CK_SCHED_NoonRegistered;

alter table SCHEDULE drop constraint CK_SCHED_NightRegistered;

drop trigger SCHED_Limit_Registers;

/*
========================================================
                TABLE REGISTER
========================================================
*/
drop table REGISTER;
alter table REGISTER drop constraint PK_REG;
alter table REGISTER drop constraint FK_REG_SCHED;
alter table REGISTER drop constraint FK_REG_PERSON;
alter table REGISTER drop constraint CK_REG_Time;
alter table REGISTER drop constraint CK_REG_Status;
alter table REGISTER drop constraint CK_REG_DoseType;

drop trigger REG_NO_LIMIT;
drop trigger REG_VACCINATION_RULE;
drop trigger REG_VACCINATION_TARGET;


/*
========================================================
                TABLE CERTIFICATE
========================================================
*/
drop table CERTIFICATE;
alter table CERTIFICATE drop constraint PK_CERT;
alter table CERTIFICATE drop constraint CK_CERT_Dose;
alter table CERTIFICATE drop constraint CK_CERT_CertType;
alter table CERTIFICATE drop constraint FK_CERT_PERSON;


/*
========================================================
                TABLE HEALTH
========================================================
*/

alter table HEALTH drop constraint PK_HEAL;
alter table HEALTH drop constraint FK_HEAL_PERSON;
drop trigger HEALTH_FilledDate;

drop table HEALTH;
/*
========================================================
                TABLE ANNOUNCEMENT
========================================================
*/
alter table ANNOUNCEMENT drop constraint PK_ANN;
alter table ANNOUNCEMENT drop constraint FK_ANN_ORG;
drop trigger ANNO_PublishDate;
drop table ANNOUNCEMENT;
/*
========================================================
                TABLE PARAMETER
========================================================
*/

alter table PARAMETER drop constraint PK_PAR;
alter table PARAMETER drop constraint CK_PAR_DiffDoses;
alter table PARAMETER drop constraint CK_PAR_DoseType;
alter table PARAMETER drop constraint FK_PAR_INJ;
alter table PARAMETER drop constraint FK_PAR_VAC;

drop table PARAMETER;
/*
========================================================
                TABLE STATISTIC
========================================================
*/
alter table STATISTIC drop constraint CK_Data;

drop table STATISTIC;

