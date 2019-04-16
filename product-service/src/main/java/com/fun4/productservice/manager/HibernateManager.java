package com.fun4.productservice.manager;

import org.hibernate.SessionFactory;

public class HibernateManager {
    private SessionFactory sessionFactory = buildSessionFactory();

    private static HibernateManager instance;
    private static ConfigurationManager configurationManager = new ConfigurationManager();

    public static HibernateManager getInstance(){
        if(instance == null){
            instance = new HibernateManager();
        }
        return instance;
    }

    private SessionFactory buildSessionFactory() {
        try {
            return configurationManager.getConfiguration().buildSessionFactory();
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
