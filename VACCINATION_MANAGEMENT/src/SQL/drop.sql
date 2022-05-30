--------------------------------------------------------
--  FILE CREATED - TUESDAY-APRIL-26-2022   
--------------------------------------------------------

DELETE FROM HEALTH;
DELETE FROM INJECTION;
DELETE FROM REGISTER;
DELETE FROM SCHEDULE;
DELETE FROM ANNOUNCEMENT;

DELETE FROM PERSON;
DELETE FROM ORGANIZATION;
DELETE FROM ACCOUNT;

DELETE FROM PARAMETER;
DELETE FROM VACCINE;
DELETE FROM REGION;
DELETE FROM STATISTIC;

ALTER TABLE ORGANIZATION DROP CONSTRAINT FK_ORG_ACC;
ALTER TABLE PERSON DROP CONSTRAINT FK_PERSON_GUAR;
ALTER TABLE PERSON DROP CONSTRAINT FK_PERSON_ACC;
ALTER TABLE SCHEDULE DROP CONSTRAINT FK_SCHED_ORG;
ALTER TABLE SCHEDULE DROP CONSTRAINT FK_SCHED_VAC;
ALTER TABLE REGISTER DROP CONSTRAINT FK_REG_SCHED;
ALTER TABLE REGISTER DROP CONSTRAINT FK_REG_PERSON;
ALTER TABLE INJECTION DROP CONSTRAINT FK_INJ_SCHEID;
ALTER TABLE INJECTION DROP CONSTRAINT FK_INJ_PERSON;
ALTER TABLE CERTIFICATE DROP CONSTRAINT FK_CERT_PERSON;
ALTER TABLE HEALTH DROP CONSTRAINT FK_HEAL_PERSON;
ALTER TABLE ANNOUNCEMENT DROP CONSTRAINT FK_ANN_ORG;
ALTER TABLE PARAMETER DROP CONSTRAINT FK_PAR_VAC;
ALTER TABLE ORGANIZATION DROP CONSTRAINT FK_ORG_REGION;
ALTER TABLE PERSON DROP CONSTRAINT FK_PERSON_REGION;
ALTER TABLE ACCOUNT DROP CONSTRAINT CK_ACC_ROLE;
ALTER TABLE VACCINE DROP CONSTRAINT UNI_VAC_NAME;
ALTER TABLE ORGANIZATION DROP CONSTRAINT CK_ORG_PROER;
ALTER TABLE PERSON DROP CONSTRAINT UNI_PERSON_PHONE;
ALTER TABLE SCHEDULE DROP CONSTRAINT CK_SCHED_ONDATE;
ALTER TABLE SCHEDULE DROP CONSTRAINT CK_SCHED_LIMITDAY;
ALTER TABLE SCHEDULE DROP CONSTRAINT CK_SCHED_LIMITNOONVINCE;
ALTER TABLE PERSON DROP CONSTRAINT CK_PERSON_GEND;
ALTER TABLE SCHEDULE DROP CONSTRAINT CK_SCHED_LIMITNIGHT;
ALTER TABLE SCHEDULE DROP CONSTRAINT CK_SCHED_DAYREGISTERED;
ALTER TABLE SCHEDULE DROP CONSTRAINT CK_SCHED_NOONREGISTERED;
ALTER TABLE SCHEDULE DROP CONSTRAINT CK_SCHED_NIGHTREGISTERED;
ALTER TABLE "HEALTH" DROP CONSTRAINT "CK_HEAL_FILLEDDATE";
ALTER TABLE REGISTER DROP CONSTRAINT CK_REG_TIME;
ALTER TABLE REGISTER DROP CONSTRAINT CK_REG_STATUS;
ALTER TABLE REGISTER DROP CONSTRAINT CK_REG_DOSETYPE;
ALTER TABLE INJECTION DROP CONSTRAINT CK_INJ_INJNO;
ALTER TABLE INJECTION DROP CONSTRAINT CK_INJ_SCHEDID;
ALTER TABLE INJECTION DROP CONSTRAINT CK_INJ_DOSETYPE;
ALTER TABLE CERTIFICATE DROP CONSTRAINT CK_CERT_DOSE;
ALTER TABLE CERTIFICATE DROP CONSTRAINT CK_CERT_CERTTYPE;
ALTER TABLE PARAMETER DROP CONSTRAINT CK_PAR_DIFFDOSES;
ALTER TABLE PARAMETER DROP CONSTRAINT CK_PAR_DOSETYPE;
ALTER TABLE "PARAMETER" DROP CONSTRAINT "CK_PAR_INJECTIONNO";
ALTER TABLE STATISTIC DROP CONSTRAINT CK_STAT_DATA;

ALTER TABLE VACCINE DROP CONSTRAINT PK_VACCINE;
ALTER TABLE ACCOUNT DROP CONSTRAINT PK_ACC;
ALTER TABLE ORGANIZATION DROP CONSTRAINT PK_ORG;
ALTER TABLE PERSON DROP CONSTRAINT PK_PERSON;
ALTER TABLE SCHEDULE DROP CONSTRAINT PK_SCHED;
ALTER TABLE REGISTER DROP CONSTRAINT PK_REG;
ALTER TABLE INJECTION DROP CONSTRAINT PK_INJ;
ALTER TABLE CERTIFICATE DROP CONSTRAINT PK_CERT;
ALTER TABLE HEALTH DROP CONSTRAINT PK_HEAL;
ALTER TABLE ANNOUNCEMENT DROP CONSTRAINT PK_ANN;
ALTER TABLE PARAMETER DROP CONSTRAINT PK_PAR;
ALTER TABLE REGION DROP CONSTRAINT PK_REGION;

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
DROP PROCEDURE "ACC_RESET_PASSWORD";
DROP PROCEDURE "ACC_UPDATE_PASSWORD";
DROP PROCEDURE "HEAL_INSERT_RECORD";
DROP PROCEDURE "INJ_INSERT_RECORD";
DROP PROCEDURE "ORG_INSERT_RECORD";
DROP PROCEDURE "ORG_UPDATE_RECORD";
DROP PROCEDURE "PERSON_INSERT_RECORD";
DROP PROCEDURE "PERSON_UPDATE_RECORD";
DROP PROCEDURE "REG_INSERT_RECORD";
DROP PROCEDURE "REG_UPDATE_STATUS";
DROP PROCEDURE "REG_UPDATE_TIME";
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
DROP FUNCTION "PERSON_AGE";
DROP FUNCTION "REG_SIGNED_NO";
DROP FUNCTION "SCHED_GENERATE_ID";

DROP TRIGGER "SCHED_REGISTION_LIMIT";
DROP TRIGGER "PERSON_VALUE";
DROP TRIGGER "REG_VACCINATION_AGE_STATUS";
DROP TRIGGER "REG_VACCINATION_TARGET";
DROP TRIGGER "REG_REGISTION_LIMIT";

commit;