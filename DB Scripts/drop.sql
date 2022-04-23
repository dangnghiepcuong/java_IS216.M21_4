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
alter table PERSON drop constraint CK_PERSON_Gender;
alter table PERSON drop constraint PK_PERSON;
alter table PERSON drop constraint FK_PERSON_GUAR;
alter table REGISTER drop constraint FK_REG_PERSON;
alter table INJECTION drop constraint FK_INJ_PERSON;
alter table CERTIFICATE drop constraint FK_CERT_PERSON;
alter table HEALTH drop constraint FK_HEAL_PERSON;


/*
========================================================
                    TABLE VACCINE
========================================================
*/
drop table VACCINE;
alter table VACCINE drop constraint UNI_Name;
alter table VACCINE drop constraint PK_VACCINE;
alter table SCHEDULE drop constraint FK_SCHED_VAC;
alter table PARAMETER drop constraint FK_PAR_VAC;


/*
========================================================
                    TABLE INJECTION
========================================================
*/
drop table INJECTION;
alter table INJECTION drop constraint CK_INJECTION_InjNO;
alter table INJECTION drop constraint CK_ScheID;
alter table INJECTION drop constraint CK_Type;
alter table INJECTION drop constraint PK_INJECTION;
alter table PARAMETER drop constraint FK_PAR_INJ;



/*
========================================================
                TABLE ORGANIZATION
========================================================
*/
drop table ORGANIZATION;
alter table ORGANIZATION drop constraint CHK_NAME;
alter table ORGANIZATION drop constraint CHK_PROVINCE;
alter table ORGANIZATION drop constraint PK_ORG;
alter table SCHEDULE drop constraint FK_SCHED_ORG;
alter table ANNOUNCEMENT drop constraint FK_ANN_ORG;


/*
========================================================
                TABLE SCHEDULE
========================================================
*/
drop table SCHEDULE;
alter table SCHEDULE drop constraint PK_SCHED;
alter table INJECTION drop constraint FK_INJECTION_SCHEID;
alter table SCHEDULE drop constraint CK_OnDate;
drop trigger SCHED_Limit_Registers;
alter table REGISTER drop constraint FK_REG_SCHED;


/*
========================================================
                TABLE REGISTER
========================================================
*/
drop table REGISTER;
alter table REGISTER drop constraint PK_REG;
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
alter table CERTIFICATE drop constraint CK_Dose;
alter table CERTIFICATE drop constraint CK_CertType;


/*
========================================================
                TABLE HEALTH
========================================================
*/
drop table HEALTH;
alter table HEALTH drop constraint PK_HEAL;
drop trigger HEALTH_FilledDate;
/*
========================================================
                TABLE ANNOUNCEMENT
========================================================
*/
drop table ANNOUNCEMENT;
alter table ANNOUNCEMENT drop constraint PK_ANN;
drop trigger ANNO_PublishDate;
/*
========================================================
                TABLE PARAMETER
========================================================
*/
drop table PARAMETER;
alter table PARAMETER drop constraint PK_PAR;
alter table PARAMETER drop constraint CK_PreDose;
alter table PARAMETER drop constraint CK_DoseType;

/*
========================================================
                TABLE STATISTIC
========================================================
*/
drop table STATISTIC;

