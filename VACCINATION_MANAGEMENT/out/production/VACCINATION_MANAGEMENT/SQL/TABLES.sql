--------------------------------------------------------
--  File created - Tuesday-June-07-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ACCOUNT
--------------------------------------------------------

  CREATE TABLE "ACCOUNT" ("USERNAME" VARCHAR2(254), "PASSWORD" VARCHAR2(128), "ROLE" NUMBER(1,0) DEFAULT (null), "STATUS" NUMBER(1,0), "NOTE" VARCHAR2(2000));
--------------------------------------------------------
--  DDL for Table ANNOUNCEMENT
--------------------------------------------------------

  CREATE TABLE "ANNOUNCEMENT" ("ID" VARCHAR2(50), "ORGID" VARCHAR2(16), "TITLE" VARCHAR2(200), "PUBLISHDATE" DATE, "IMAGE" BLOB, "NOTE" VARCHAR2(2000), "CONTENT" CLOB);
--------------------------------------------------------
--  DDL for Table CERTIFICATE
--------------------------------------------------------

  CREATE TABLE "CERTIFICATE" ("PERSONALID" VARCHAR2(12), "DOSE" NUMBER(2,0), "CERTTYPE" NUMBER(1,0), "NOTE" VARCHAR2(2000));
--------------------------------------------------------
--  DDL for Table HEALTH
--------------------------------------------------------

  CREATE TABLE "HEALTH" ("PERSONALID" VARCHAR2(12), "FILLEDDATE" DATE, "HEALTHS" VARCHAR2(20), "NOTE" VARCHAR2(2000), "ID" NUMBER(*,0));
--------------------------------------------------------
--  DDL for Table INJECTION
--------------------------------------------------------

  CREATE TABLE "INJECTION" ("PERSONALID" VARCHAR2(12), "INJNO" NUMBER(5,0), "SCHEDID" VARCHAR2(15), "NOTE" VARCHAR2(2000), "DOSETYPE" VARCHAR2(50));
--------------------------------------------------------
--  DDL for Table ORGANIZATION
--------------------------------------------------------

  CREATE TABLE "ORGANIZATION" ("ID" VARCHAR2(10), "NAME" VARCHAR2(100), "PROVINCENAME" VARCHAR2(50), "DISTRICTNAME" VARCHAR2(50), "TOWNNAME" VARCHAR2(50), "STREET" VARCHAR2(100), "NOTE" VARCHAR2(2000));
--------------------------------------------------------
--  DDL for Table PARAMETER
--------------------------------------------------------

  CREATE TABLE "PARAMETER" ("INJECTIONNO" NUMBER, "VACCINEID" VARCHAR2(8), "MINDISTANCE" NUMBER, "DIFFDOSES" NUMBER(1,0), "NEXTDOSE" VARCHAR2(100), "NOTE" VARCHAR2(2000), "DOSETYPE" VARCHAR2(100));
--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "PERSON" ("ID" VARCHAR2(256), "LASTNAME" VARCHAR2(100), "FIRSTNAME" VARCHAR2(50), "BIRTHDAY" DATE, "GENDER" NUMBER(1,0), "HOMETOWN" VARCHAR2(50), "PROVINCENAME" VARCHAR2(50), "DISTRICTNAME" VARCHAR2(50), "TOWNNAME" VARCHAR2(50), "STREET" VARCHAR2(100), "PHONE" VARCHAR2(30), "EMAIL" VARCHAR2(254), "GUARDIAN" VARCHAR2(12), "NOTE" VARCHAR2(2000), "AVATAR" BLOB);
--------------------------------------------------------
--  DDL for Table REGION
--------------------------------------------------------

  CREATE TABLE "REGION" ("PROVINCECODE" VARCHAR2(2), "PROVINCENAME" VARCHAR2(50), "NOTE" VARCHAR2(2000), "DISTRICTCODE" VARCHAR2(3), "DISTRICTNAME" VARCHAR2(50), "TOWNCODE" VARCHAR2(5), "TOWNNAME" VARCHAR2(50));
--------------------------------------------------------
--  DDL for Table REGISTER
--------------------------------------------------------

  CREATE TABLE "REGISTER" ("PERSONALID" VARCHAR2(12), "SCHEDID" VARCHAR2(15), "TIME" NUMBER(1,0), "NO" NUMBER(5,0), "STATUS" NUMBER(1,0), "IMAGE" BLOB, "NOTE" VARCHAR2(2000), "DOSETYPE" VARCHAR2(50), "ID" NUMBER);
--------------------------------------------------------
--  DDL for Table SCHEDULE
--------------------------------------------------------

  CREATE TABLE "SCHEDULE" ("ID" VARCHAR2(26), "ORGID" VARCHAR2(16), "ONDATE" DATE, "VACCINEID" VARCHAR2(8), "SERIAL" VARCHAR2(100), "LIMITDAY" NUMBER(5,0), "LIMITNOON" NUMBER(5,0), "LIMITNIGHT" NUMBER(5,0), "DAYREGISTERED" NUMBER(5,0), "NOONREGISTERED" NUMBER(5,0), "NIGHTREGISTERED" NUMBER(5,0), "NOTE" VARCHAR2(2000));
--------------------------------------------------------
--  DDL for Table STATISTIC
--------------------------------------------------------

  CREATE TABLE "STATISTIC" ("TITLE" VARCHAR2(200), "DATA" NUMBER, "LASTUPDATE" DATE, "NOTE" VARCHAR2(2000));
--------------------------------------------------------
--  DDL for Table VACCINE
--------------------------------------------------------

  CREATE TABLE "VACCINE" ("ID" VARCHAR2(10), "NAME" VARCHAR2(100), "TECHNOLOGY" VARCHAR2(100), "COUNTRY" VARCHAR2(100), "NOTE" VARCHAR2(2000));
