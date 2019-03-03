package com.fun4.productservice.repository;

import com.fun4.productservice.manager.HibernateManager;
import com.fun4.productservice.model.Product;
import com.fun4.productservice.model.SortingOrder;
import com.fun4.productservice.model.SortingType;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;

public class ProductRepository {

    public Product getProductById(int productId) {
        Session session = HibernateManager.getInstance().getSessionFactory().openSession();

        Query<Product> query = session.createQuery("from Product where id = :id", Product.class);
        query.setParameter("id", productId);

        try {
            return query.uniqueResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public Product addProduct(Product product) {
        this.storeData(product);
        return this.getProductById(product.getId());
    }

    public int getTotalCount(){
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            return Math.toIntExact((Long)session.createCriteria(Product.class).setProjection(Projections.rowCount()).uniqueResult());
        }
    }

    public List<Product> getAllProducts(Integer startIndex, Integer pageSize, String type, String order) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            String queryString = "from Product p";

            if (type != null & order != null){
                if (type.equals("PRICE")){
                    System.out.println("price ASC");
                    queryString += " ORDER BY p.price";
                } else {
                    System.out.println("price DESC");
                    queryString += " ORDER BY p.name";
                }

                if (order.equals("ASCENDING")){
                    System.out.println("name ASC");
                    queryString += " ASC";
                } else {
                    System.out.println("name DESC");
                    queryString += " DESC";
                }

            }

            Query<Product> query = session.createQuery(queryString);

            // Pagination
            if (startIndex != null && pageSize != null) {
                query.setFirstResult(startIndex * pageSize);
                query.setMaxResults(pageSize);
            }

            return query.getResultList();
        }
    }

    public List<Product> getProductsForUser(int userId, Integer startIndex, Integer pageSize) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(" from Product where userId = :userId", Product.class);
            query.setParameter("userId", userId);

            // Pagination
            if (startIndex != null && pageSize != null) {
                query.setFirstResult(startIndex * pageSize);
                query.setMaxResults(pageSize);
            }

            return query.getResultList();
        }
    }

    public Product updateProduct(Product product) {
        this.updateData(product);
        return this.getProductById(product.getId());
    }

    public void deleteProduct(int productId) {
        this.deleteData(this.getProductById(productId));
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
