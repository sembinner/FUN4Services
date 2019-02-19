package com.example.userservice.repository;

import com.example.userservice.manager.HibernateManager;
import com.example.userservice.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

public class UserRepository {

    public UserRepository() {
    }

    public User getUserByUsername(String username) {
        try(Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }
}
