package com.fun4.shopservice.repository;

import com.fun4.shopservice.manager.HibernateManager;
import com.fun4.shopservice.model.Shop;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;

public class ShopRepository {

    public List<Shop> getShops(Integer startIndex, Integer pageSize) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            Query<Shop> query = session.createQuery("from Shop s where personal = false", Shop.class);

            // Pagination if requested
            if (startIndex != null && pageSize != null) {
                query.setFirstResult(startIndex * pageSize);
                query.setMaxResults(pageSize);
            }

            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Shop> getPersonalPages(Integer startIndex, Integer pageSize) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            Query<Shop> query = session.createQuery("from Shop s where personal = true", Shop.class);

            if (startIndex != null && pageSize != null) {
                query.setFirstResult(startIndex * pageSize);
                query.setMaxResults(pageSize);
            }

            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Shop getShopById(int shopId) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            Query<Shop> query = session.createQuery("from Shop where id = :id", Shop.class);
            query.setParameter("id", shopId);
            return query.uniqueResult();
        }
    }

    public int getTotalCount() {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            return Math.toIntExact((Long) session.createCriteria(Shop.class)
                    .add(Restrictions.eq("personal", false))
                    .setProjection(Projections.rowCount()).uniqueResult());
//            Query query = session.createQuery("from Shop where personal = false", Shop.class);

//            return query.getResultList().size();
        }
    }

    public Shop getPersonalPage(int userId) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            Query<Shop> query = session.createQuery("from Shop s where userId = :userId AND personal = true", Shop.class);
            query.setParameter("userId", userId);
            return query.uniqueResult();
        }
    }

    public List<Shop> getShopsForUser(int userId, Integer startIndex, Integer pageSize) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            Query<Shop> query = session.createQuery(" from Shop where userId = :userId", Shop.class);
            query.setParameter("userId", userId);

            if (startIndex != null && pageSize != null) {
                query.setFirstResult(startIndex * pageSize);
                query.setMaxResults(pageSize);
            }

            return query.getResultList();
        }
    }

    public Shop addShop(Shop shop) {
        this.storeData(shop);
        return this.getShopById(shop.getId());
    }

    public Shop updateShop(Shop shop) {
        this.updateData(shop);
        return this.getShopById(shop.getId());
    }

    public void deleteShop(int shopId) {
        this.deleteData(this.getShopById(shopId));
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
