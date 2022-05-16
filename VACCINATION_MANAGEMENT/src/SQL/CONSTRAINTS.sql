--------------------------------------------------------
--  File created - Thursday-May-12-2022   
--------------------------------------------------------
--------------------------------------------------------
--  Constraints for Table ACCOUNT
--------------------------------------------------------

  ALTER TABLE "ACCOUNT" MODIFY ("USERNAME" NOT NULL ENABLE);
  ALTER TABLE "ACCOUNT" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "ACCOUNT" ADD CONSTRAINT "PK_ACC" PRIMARY KEY ("USERNAME") USING INDEX  ENABLE;
  ALTER TABLE "ACCOUNT" ADD CONSTRAINT "CK_ACC_ROLE" CHECK (Role in (0, 1, 2)) ENABLE;
--------------------------------------------------------
--  Constraints for Table ANNOUNCEMENT
--------------------------------------------------------

  ALTER TABLE "ANNOUNCEMENT" ADD CONSTRAINT "PK_ANN" PRIMARY KEY ("ID", "ORGID") USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table CERTIFICATE
--------------------------------------------------------

  ALTER TABLE "CERTIFICATE" ADD CONSTRAINT "PK_CERT" PRIMARY KEY ("PERSONALID") USING INDEX  ENABLE;
  ALTER TABLE "CERTIFICATE" ADD CONSTRAINT "CK_CERT_CERTTYPE" CHECK (CertType in (0,1,2)) ENABLE;
  ALTER TABLE "CERTIFICATE" ADD CONSTRAINT "CK_CERT_DOSE" CHECK (Dose in (0,1,2,3,4)) ENABLE;
--------------------------------------------------------
--  Constraints for Table HEALTH
--------------------------------------------------------

  ALTER TABLE "HEALTH" ADD CONSTRAINT "PK_HEAL" PRIMARY KEY ("PERSONALID", "ID") USING INDEX  ENABLE;
  ALTER TABLE "HEALTH" ADD CONSTRAINT "CK_HEAL_FILLEDDATE" CHECK ("FILLEDDATE" IS NOT NULL) ENABLE;
--------------------------------------------------------
--  Constraints for Table INJECTION
--------------------------------------------------------

  ALTER TABLE "INJECTION" MODIFY ("PERSONALID" NOT NULL ENABLE)
  ALTER TABLE "INJECTION" ADD CONSTRAINT "PK_INJ" PRIMARY KEY ("PERSONALID", "INJNO") USING INDEX  ENABLE;
  ALTER TABLE "INJECTION" ADD CONSTRAINT "CK_INJ_INJNO" CHECK (InjNO in (1, 2, 3, 4)) ENABLE;
  ALTER TABLE "INJECTION" ADD CONSTRAINT "CK_INJ_SCHEDID" CHECK (SchedID is not null) ENABLE;
  ALTER TABLE "INJECTION" ADD CONSTRAINT "CK_INJ_DOSETYPE" CHECK (DoseType in ('basic', 'booster', 'repeat')) ENABLE;
--------------------------------------------------------
--  Constraints for Table ORGANIZATION
--------------------------------------------------------

  ALTER TABLE "ORGANIZATION" ADD CONSTRAINT "PK_ORG" PRIMARY KEY ("ID") USING INDEX  ENABLE;
  ALTER TABLE "ORGANIZATION" ADD CONSTRAINT "CK_ORG_PROVINCE" CHECK (Province is not null) ENABLE;
--------------------------------------------------------
--  Constraints for Table PARAMETER
--------------------------------------------------------

  ALTER TABLE "PARAMETER" ADD CONSTRAINT "CK_PAR_DIFFDOSES" CHECK (DiffDoses in (0,1)) ENABLE;
  ALTER TABLE "PARAMETER" ADD CONSTRAINT "PK_PAR" PRIMARY KEY ("INJECTIONNO", "VACCINEID", "DOSETYPE", "DIFFDOSES") USING INDEX  ENABLE;
  ALTER TABLE "PARAMETER" ADD CONSTRAINT "CK_PAR_INJECTIONNO" CHECK (InjectionNO in (1, 2, 3, 4)) ENABLE;
  ALTER TABLE "PARAMETER" ADD CONSTRAINT "CK_PAR_DOSETYPE" CHECK (DoseType in ('basic', 'booster', 'repeat')) ENABLE;
--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PERSON" ADD CONSTRAINT "PK_PERSON" PRIMARY KEY ("ID") USING INDEX  ENABLE;
  ALTER TABLE "PERSON" ADD CONSTRAINT "CK_PERSON_GENDER" CHECK (Gender in (0, 1, 2)) ENABLE;
--------------------------------------------------------
--  Constraints for Table REGION
--------------------------------------------------------

  ALTER TABLE "REGION" ADD CONSTRAINT "PK_REGION" PRIMARY KEY ("CODE") USING INDEX  ENABLE;
  ALTER TABLE "REGION" ADD CONSTRAINT "UNI_REGION_NAME" UNIQUE ("NAME") USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table REGISTER
--------------------------------------------------------

  ALTER TABLE "REGISTER" ADD CONSTRAINT "CK_REG_DOSETYPE" CHECK (DoseType in ('basic', 'booster', 'repeat')) ENABLE;
  ALTER TABLE "REGISTER" ADD CONSTRAINT "PK_REG" PRIMARY KEY ("PERSONALID", "SCHEDID", "ID") USING INDEX  ENABLE;
  ALTER TABLE "REGISTER" ADD CONSTRAINT "CK_REG_TIME" CHECK (Time in (0,1,2)) ENABLE;
  ALTER TABLE "REGISTER" ADD CONSTRAINT "CK_REG_STATUS" CHECK (Status in (0,1,2,3)) ENABLE;
--------------------------------------------------------
--  Constraints for Table SCHEDULE
--------------------------------------------------------

  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "PK_SCHED" PRIMARY KEY ("ID") USING INDEX  ENABLE;
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_LIMITDAY" CHECK (LimitDay >= 0) ENABLE;
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_LIMITNOON" CHECK (LimitNoon >= 0) ENABLE;
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_LIMITNIGHT" CHECK (LimitNight >= 0) ENABLE;
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_DAYREGISTERED" CHECK (DayRegistered >= 0) ENABLE;
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_NOONREGISTERED" CHECK (NoonRegistered >= 0) ENABLE;
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_NIGHTREGISTERED" CHECK (NightRegistered >= 0) ENABLE;
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "CK_SCHED_ONDATE" CHECK (OnDate is not null) ENABLE;
--------------------------------------------------------
--  Constraints for Table STATISTIC
--------------------------------------------------------

  ALTER TABLE "STATISTIC" ADD CONSTRAINT "PK_STAT" PRIMARY KEY ("TITLE") USING INDEX  ENABLE;
  ALTER TABLE "STATISTIC" ADD CONSTRAINT "CK_STAT_DATA" CHECK (Data>=0) ENABLE;
--------------------------------------------------------
--  Constraints for Table VACCINE
--------------------------------------------------------

  ALTER TABLE "VACCINE" ADD CONSTRAINT "UNI_VAC_NAME" UNIQUE ("NAME") USING INDEX  ENABLE;
  ALTER TABLE "VACCINE" ADD CONSTRAINT "PK_VACCINE" PRIMARY KEY ("ID") USING INDEX  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ANNOUNCEMENT
--------------------------------------------------------

  ALTER TABLE "ANNOUNCEMENT" ADD CONSTRAINT "FK_ANN_ORG" FOREIGN KEY ("ORGID") REFERENCES "ORGANIZATION" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CERTIFICATE
--------------------------------------------------------

  ALTER TABLE "CERTIFICATE" ADD CONSTRAINT "FK_CERT_PERSON" FOREIGN KEY ("PERSONALID") REFERENCES "PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HEALTH
--------------------------------------------------------

  ALTER TABLE "HEALTH" ADD CONSTRAINT "FK_HEAL_PERSON" FOREIGN KEY ("PERSONALID") REFERENCES "PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INJECTION
--------------------------------------------------------

  ALTER TABLE "INJECTION" ADD CONSTRAINT "FK_INJ_PERSON" FOREIGN KEY ("PERSONALID") REFERENCES "PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ORGANIZATION
--------------------------------------------------------

  ALTER TABLE "ORGANIZATION" ADD CONSTRAINT "FK_ORG_ACC" FOREIGN KEY ("ID") REFERENCES "ACCOUNT" ("USERNAME") ENABLE;
  ALTER TABLE "ORGANIZATION" ADD CONSTRAINT "FK_ORG_REGION" FOREIGN KEY ("PROVINCE") REFERENCES "REGION" ("CODE") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PARAMETER
--------------------------------------------------------

  ALTER TABLE "PARAMETER" ADD CONSTRAINT "FK_PAR_VAC" FOREIGN KEY ("VACCINEID") REFERENCES "VACCINE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" ADD CONSTRAINT "FK_PERSON_REGION" FOREIGN KEY ("PROVINCE") REFERENCES "REGION" ("CODE") ENABLE;
  ALTER TABLE "PERSON" ADD CONSTRAINT "FK_PERSON_GUAR" FOREIGN KEY ("GUARDIAN") REFERENCES "PERSON" ("ID") ENABLE;
  ALTER TABLE "PERSON" ADD CONSTRAINT "FK_PERSON_ACC" FOREIGN KEY ("PHONE") REFERENCES "ACCOUNT" ("USERNAME") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REGISTER
--------------------------------------------------------

  ALTER TABLE "REGISTER" ADD CONSTRAINT "FK_REG_PERSON" FOREIGN KEY ("PERSONALID") REFERENCES "PERSON" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SCHEDULE
--------------------------------------------------------

  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "FK_SCHED_ORG" FOREIGN KEY ("ORGID") REFERENCES "ORGANIZATION" ("ID") ENABLE;
  ALTER TABLE "SCHEDULE" ADD CONSTRAINT "FK_SCHED_VAC" FOREIGN KEY ("VACCINEID") REFERENCES "VACCINE" ("ID") ENABLE;
