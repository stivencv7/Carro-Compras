package org.evelasco.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.evelasco.util.HibernateConn;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;


@ApplicationScoped
public class ProducerSources {

    @Inject
    private Logger log;

    @Resource(name = "jdbc/ConexionBD")
    private DataSource ds;
    @Produces
    @RequestScoped
    @MysqlConn
    private Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());

    }

    private void closeConn(@Disposes @MysqlConn Connection connection) throws SQLException {
        connection.close();
        log.info("cerrando el pool de conexión");
    }

    @Produces
    @RequestScoped
    private EntityManager beanEntityManger(){
        return HibernateConn.getEntityManager();
    }

    private void closeJpa(@Disposes EntityManager entityManager){
        if(entityManager.isOpen()){
            entityManager.close();
            log.info("cerrando la conexion del entityManager");
        }
    }


}
