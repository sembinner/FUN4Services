package com.fun4.userservice.repository;

import com.fun4.userservice.manager.HibernateManager;
import com.fun4.userservice.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

public class UserRepository implements IUserRepository {

    public User getUserByUsername(String username) {
        Session session = HibernateManager.getInstance().getSessionFactory().openSession();

        Query<User> query = session.createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);

        try {
            return query.uniqueResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public User addUser(User user){
        this.storeData(user);
        return this.getUserByUsername(user.getUsername());
    }

    private void storeData(Object objectToSave) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(objectToSave);
            session.getTransaction().commit();
        }
    }

    private void updateData(Object objectToUpdate) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            session.beginTransaction();

            session.update(objectToUpdate);
            session.getTransaction().commit();
        }
    }

    private void deleteData(Object objectToDelete) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            session.beginTransaction();

            session.delete(objectToDelete);
            session.getTransaction().commit();
        }
    }
}
