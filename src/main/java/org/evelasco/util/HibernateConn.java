package org.evelasco.util;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateConn {

    private static final EntityManagerFactory entityManager=entityManagerFactory();

    private static EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("hibernateConn");
    }

    public static EntityManager getEntityManager(){
        return entityManager.createEntityManager();
    }
}
