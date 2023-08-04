package org.evelasco.interceptors;


import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.evelasco.configs.MysqlConn;

import java.sql.Connection;
import java.util.logging.Logger;

@TransctionalJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager em;

    @Inject
    private Logger log;


    @AroundInvoke
    public Object transaction(InvocationContext invocation) throws Exception {

        try {
            log.info("-----> iniciando transaction "+invocation.getMethod().getName() +
                    "de la clase "+ invocation.getMethod().getDeclaringClass());

            em.getTransaction().begin();
            Object resultado = invocation.proceed();
            em.getTransaction().commit();

            log.info("-----> realizando el commit y finalizando transaction" +invocation.getMethod().getName() +
                    " de la clase "+ invocation.getMethod().getDeclaringClass());
            return resultado;
        }catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
