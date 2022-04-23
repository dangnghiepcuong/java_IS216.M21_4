
-- 20521890 - Trương Nguyễn Quang Thái
/*          TABLE: ORGANIZATION          */
--Create a table struct without constraint
--<Attribute name> <Data type>
create table ORGANIZATION
(
    --Identifier of organization, ID FK references ACCOUNT(OrgID)
    ID varchar2(5),
    
    --Name of organization..
    Name varchar2(100),
    
    --Province of organization
    Province varchar2(50),
    
    --District of organization
    District varchar2(50),
    
    --Town of organization
    Town varchar2(50),
    
    --Street of organization
    Street varchar2(100),
    
    --Note of organization
    Note varchar2(2000)
);


/*	CONSTRAINT	*/
--Primary Key
alter table ORGANIZATION 
add constraint PK_ORG primary key (ID);

--Foreign Key
alter table ORGANIZATION 
add constraint FK_ORG_ACC foreign key (ID) references ACCOUNT(OrgID);


--Check
    --Check name is not null
alter table ORGANIZATION
add constraint CHK_NAME check (Name is not null);

alter table ORGANIZATION
add constraint CHK_PROVINCE check (Province is not null);
/*	TRIGGERS	*/



                /*	STORED PROCEDURES	*/
--Insert
create or replace procedure ORG_INSERT_RECORD (par_ID ORGANIZATION.ID%type,                                            
                                             par_Province ORGANIZATION.Province%type,                                            
							   par_Note  ORGANIZATION.Note%type DEFAULT NULL)                                           
as 
begin
    --insert new ORGANIZATION
	insert into ORGANIZATION(ID, Province, Note) 
	values (par_ID, par_Province, par_Note);
end ORG_INSERT_RECORD;

                                
--Delete
create or replace procedure ORG_DELETE_RECORD (par_ID ORGANIZATION.ID%type)
as
begin
    ----delete record ORGANIZATION
    delete 
    from ORGANIZATION
    where ID = par_ID;
end ORG_DELETE_RECORD;

--Update
create or replace procedure ORG_UPDATE_RECORD (par_ID ORGANIZATION.ID%type,
                                                par_Name ORGANIZATION.Name%type,
                                                par_District ORGANIZATION.District%type,
                                                par_Town ORGANIZATION.Town%type,
                                                par_Street ORGANIZATION.Street%type,
                                                par_Note ORGANIZATION.Note%type DEFAULT NULL)
as
begin
    	--Update record ORGANIZATION
    	update ORGANIZATION
    	set Name = par_Name,
        	District = par_District,
        	Town = par_Town,
        	Street = par_Street,
        	Note = par_Note
    	where ID = par_ID;
end ORG_UPDATE_RECORD;

--Annouce


                    /*	STORED FUNCTIONS	*/
-- Count the number of schedules were hold by the ORG from StartDate to EndDate
create or replace function ORG_COUNT_SCHED 
(par_OrgID ORGANIZATION.ID%type, par_StartDate date DEFAULT NULL, par_EndDate date DEFAULT NULL)
return number 
is 
    Count_Sched int;
    var_StartDate date;
    var_EndDate date;
begin
    --set value 0 for Count_Sched
    Count_Sched := 0; 

    var_StartDate := par_StartDate;
    var_EndDate := par_EndDate;

    --if StartDate > EndDate, then swap their value.
    if (par_StartDate > par_EndDate) 
    then
        begin
        var_StartDate := par_EndDate;
        var_EndDate := par_StartDate;
        end;
    end if;

    --from the SCHEDULES, count OrgID
    select COUNT(OrgID) into Count_Sched
    from SCHEDULE SCHED
    where SCHED.OnDate >= par_StartDate 
    and SCHED.OnDate <= par_EndDate;
    
    return Count_Sched;
end ORG_COUNT_SCHED;

--Count the number of injections have been done by the ORG from StartDate to EndDate
create or replace function ORG_COUNT_INJ (par_ID ORGANIZATION.ID%type,
                                            par_StatDate date,
                                            par_EndDate date)
return number is 
	--return value of count schedule
   	Count_Inj int;
    
begin
    	if (par_StatDate <= par_EndDate) then
    	begin
    		--count the number of injections have been done by the ORG from StartDate to EndDate
        		select COUNT(Status) into Count_Inj, sched.ID
        		from SCHEDULE SCHED join REGISTER REG on REG.SchedID = SCHED.ID
        		where reg.Status=2 and SCHED.OnDate >= par_StatDate
              		and SCHED.OnDate <= par_EndDate
        		group by SCHED.ID;
        
        		if(Count_Inj = 0) then
            			dbms_output.put_line('no previous injections');
        		end if;
    	end;
    	else 
        		DBMS_OUTPUT.PUT_LINE('start date must be less than or equal to end date');
   	end if;

	--Nghiep Cuong: FIX Function
    	if (par_StatDate <= par_EndDate) then
	begin
		select COUNT(REG.Status) into Count_Inj
		from SCHEDULE SCHED, REGISTER REG
		where SCHED.ID = REG.SchedID
		and SCHED.OnDate >= par_StartDate
		and SCHED.OnDate <= par_EndDate
		and REG.Status = 2;
	end;
	else
		raise_application_error(100007,'Start date must be less than or equal to end date!');
	end if;

end;



/*	RECORDS	*/