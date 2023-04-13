package com.example.test.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
        private static EntityManagerFactory factory;

        static public EntityManager getConnection(){
            if(factory == null || !factory.isOpen()){
                factory = Persistence.createEntityManagerFactory("Test");
            }
            return factory.createEntityManager();
        }
        static public void close(){
            if(factory !=null || factory.isOpen()){
                factory.close();
            }
        }
}
