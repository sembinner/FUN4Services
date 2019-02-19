package com.example.userservice.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateManager {
    private SessionFactory sessionFactory = buildSessionFactory();

    private static HibernateManager instance;

    public static HibernateManager getInstance(){
        if(instance == null){
            instance = new HibernateManager();
        }
        return instance;
    }

    private SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutdown() {
        getSessionFactory().close();
    }
}
