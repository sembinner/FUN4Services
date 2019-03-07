package com.fun4.shopservice.repository;

import com.fun4.shopservice.manager.HibernateManager;
import com.fun4.shopservice.model.Shop;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ShopRepository {

    public List<Shop> getShops(Integer startIndex, Integer pageSize) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            Query<Shop> query = session.createQuery("from Shop s");

            // Pagination if requested
            if (startIndex != null && pageSize != null){
                query.setFirstResult(startIndex * pageSize);
                query.setMaxResults(pageSize);
            }

            return query.getResultList();
        } catch (Exception e){
            return null;
        }
    }

    public Shop getShopById(int shopId){
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()){
            Query<Shop> query = session.createQuery("from Shop where id = :id", Shop.class);
            query.setParameter("id", shopId);
            return query.uniqueResult();
        }
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
