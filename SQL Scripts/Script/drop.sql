--------------------------------------------------------
--  File created - Tuesday-April-26-2022   
--------------------------------------------------------
DELETE FROM PARAMETER;
DELETE FROM REGION;
DELETE FROM STATISTIC;
DELETE FROM HEALTH;
DELETE FROM INJECTION;
DELETE FROM REGISTER;
DELETE FROM SCHEDULE;
DELETE FROM VACCINE;
DELETE FROM PERSON;
DELETE FROM ORGANIZATION;
DELETE FROM ACCOUNT;

alter table ACCOUNT drop constraint CK_ACC_Role;


alter table ORGANIZATION drop constraint FK_ORG_ACC
alter table PERSON drop constraint FK_PERSON_GUAR;
alter table PERSON drop constraint FK_PERSON_ACC;
alter table SCHEDULE drop constraint FK_SCHED_ORG;
alter table SCHEDULE drop constraint FK_SCHED_VAC;
alter table REGISTER drop constraint FK_REG_SCHED;
alter table REGISTER drop constraint FK_REG_PERSON;
alter table INJECTION drop constraint FK_INJ_ScheID;
alter table INJECTION drop constraint FK_INJ_PERSON;
alter table CERTIFICATE drop constraint FK_CERT_PERSON;
alter table HEALTH drop constraint FK_HEAL_PERSON;
alter table ANNOUNCEMENT drop constraint FK_ANN_ORG;
alter table PARAMETER drop constraint FK_PAR_INJ;
alter table PARAMETER drop constraint FK_PAR_VAC;

alter table VACCINE drop constraint UNI_VAC_Name;
alter table ORGANIZATION drop constraint CK_ORG_PROVINCE;
alter table PERSON drop constraint CK_PERSON_Gender;
alter table SCHEDULE drop constraint CK_SCHED_OnDate;
alter table SCHEDULE drop constraint CK_SCHED_LimitDay;
alter table SCHEDULE drop constraint CK_SCHED_LimitNoon;
alter table SCHEDULE drop constraint CK_SCHED_LimitNight;
alter table SCHEDULE drop constraint CK_SCHED_DayRegistered;
alter table SCHEDULE drop constraint CK_SCHED_NoonRegistered;
alter table SCHEDULE drop constraint CK_SCHED_NightRegistered;
alter table REGISTER drop constraint CK_REG_Time;
alter table REGISTER drop constraint CK_REG_Status;
alter table REGISTER drop constraint CK_REG_DoseType;
alter table INJECTION drop constraint CK_INJ_InjNO;
alter table INJECTION drop constraint CK_INJ_ScheID;
alter table INJECTION drop constraint CK_INJ_DoseType;
alter table CERTIFICATE drop constraint CK_CERT_Dose;
alter table CERTIFICATE drop constraint CK_CERT_CertType;
alter table PARAMETER drop constraint CK_PAR_DiffDoses;
alter table PARAMETER drop constraint CK_PAR_DoseType;
alter table STATISTIC drop constraint CK_STAT_Data;

alter table VACCINE drop constraint PK_VACCINE;
alter table ACCOUNT drop constraint PK_ACC;
alter table ORGANIZATION drop constraint PK_ORG;
alter table PERSON drop constraint PK_PERSON;
alter table SCHEDULE drop constraint PK_SCHED;
alter table REGISTER drop constraint PK_REG;
alter table INJECTION drop constraint PK_INJ;
alter table CERTIFICATE drop constraint PK_CERT;
alter table HEALTH drop constraint PK_HEAL;
alter table ANNOUNCEMENT drop constraint PK_ANN;
alter table PARAMETER drop constraint PK_PAR;

DROP TABLE "ACCOUNT";
DROP TABLE "ANNOUNCEMENT";
DROP TABLE "CERTIFICATE";
DROP TABLE "HEALTH";
DROP TABLE "INJECTION";
DROP TABLE "ORGANIZATION";
DROP TABLE "PARAMETER";
DROP TABLE "PERSON";
DROP TABLE "REGION";
DROP TABLE "REGISTER";
DROP TABLE "SCHEDULE";
DROP TABLE "STATISTIC";
DROP TABLE "VACCINE";
DROP PROCEDURE "ACC_CREATE_ORG";
DROP PROCEDURE "ACC_DELETE_RECORD";
DROP PROCEDURE "ACC_INSERT_RECORD";
DROP PROCEDURE "ACC_UPDATE_PASSWORD";
DROP PROCEDURE "HEAL_INSERT_RECORD";
DROP PROCEDURE "INJ_INSERT_RECORD";
DROP PROCEDURE "ORG_INSERT_RECORD";
DROP PROCEDURE "ORG_UPDATE_RECORD";
DROP PROCEDURE "PERSON_INSERT_RECORD";
DROP PROCEDURE "PERSON_UPDATE_RECORD";
DROP PROCEDURE "REG_INSERT_RECORD";
DROP PROCEDURE "REG_UPDATE_STATUS";
DROP PROCEDURE "SCHED_DEC_REG";
DROP PROCEDURE "SCHED_INC_REG";
DROP PROCEDURE "SCHED_INSERT_RECORD";
DROP PROCEDURE "TEST_DATE";
DROP PROCEDURE "TEST_TRIGGER";
DROP PROCEDURE "VAC_INSERT_RECORD";
DROP FUNCTION "ACC_CONVERT_SEQ_TO_STR";
DROP FUNCTION "ANN_ID";
DROP FUNCTION "HEAL_FORM_ID";
DROP FUNCTION "INJ_COUNT_INJ";
DROP FUNCTION "INJ_DIFFERENCE";
DROP FUNCTION "ORG_COUNT_SCHED";
DROP FUNCTION "REG_SIGNED_NO";
DROP FUNCTION "SCHED_GENERATE_ID";