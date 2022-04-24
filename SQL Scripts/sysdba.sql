/* CREATE USER AND GRANT DEBUG PRIVILEGE */
--1. Create new account for new schema

create user vaccination identified by vaccination;

grant all privileges to vaccination;

--2. Check debug privileges (DEBUG ANY PROCEDURE, DEBUG CONNECT ANY)

select * from all_tab_privs;

--3. If user doesn't have those privileges, grant them.

grant debug any procedure to vaccination;

grant debug connect session to vaccination;

GRANT EXECUTE ON DBMS_DEBUG_JDWP To VACCINATION;

--4. Execute this block
begin
dbms_network_acl_admin.append_host_ace
    (host => '127.0.0.1',
        lower_port => null,
        upper_port => null,
        ace=> sys.xs$ace_type(privilege_list=>sys.xs$name_list('jdwp'),
                    principal_name=>'vaccination',
                    principal_type=>sys.xs_acl.ptype_db)
    );
end;

 select * from dba_network_acls;

DBMS_NETWORK_ACL_ADMIN.assign_acl (acl=> 'xedba.xml',
HOST => '127.0.0.1',
lower_port  => NULL,
upper_port  => NULL);

commit;