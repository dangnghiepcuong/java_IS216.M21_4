GRANT DEBUG ANY PROCEDURE TO VACCINATION;
GRANT DEBUG CONNECT SESSION TO VACCINATION;
GRANT EXECUTE ON DBMS_DEBUG_JDWP To VACCINATION;
COMMIT;
-- Here you want to again substitute the VACCINATION user with your user doing the debugging
BEGIN
DBMS_NETWORK_ACL_ADMIN.APPEND_HOST_ACE
   (host=>'127.0.0.1',
     ace=> SYS.XS$ACE_TYPE(privilege_list=>SYS.XS$NAME_LIST('JDWP'),
               principal_name=>'VACCINATION',
               principal_type=>SYS.XS_ACL.PTYPE_DB) 
   );
END;
COMMIT;

grant debug on any procedure to VACCINATION;

select *
from all_tab_privs
where privilege in ('EXECUTE', 'DEBUG')
    and table_name in ('PCK_LANCAMENTOSERVICO', 'PCK_LANCAMENTO', 'PCK_UTIL');