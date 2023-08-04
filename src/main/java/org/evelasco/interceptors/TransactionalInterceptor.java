package org.evelasco.interceptors;


import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.evelasco.configs.MysqlConn;

import java.sql.Connection;
import java.util.logging.Logger;

@TransctionalJbdc
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MysqlConn
    private Connection conn;

    @Inject
    private Logger log;


    @AroundInvoke
    public Object transaction(InvocationContext invocation) throws Exception {
        if(conn.getAutoCommit()){
            conn.setAutoCommit(false);
        }
        try {
            log.info("-----> iniciando transaction "+invocation.getMethod().getName() +
                    "de la clase "+ invocation.getMethod().getDeclaringClass());
            Object resultado = invocation.proceed();

            conn.commit();
            log.info("-----> realizando el commit y finalizando transaction" +invocation.getMethod().getName() +
                    " de la clase "+ invocation.getMethod().getDeclaringClass());
            return resultado;
        }catch (RuntimeException e) {
            conn.rollback();
            throw e;
        }
    }

}
