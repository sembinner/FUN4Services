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

    public int getTotalCountForShop(int shopId){
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
//            return Math.toIntExact((Long)session.createCriteria(Product.class).setProjection(Projections.rowCount()).uniqueResult());
            Query query = session.createQuery("select count(p.id) from Product p" +
                    " where p.shopId = :shopId");
            query.setParameter("shopId", shopId);

            return Math.toIntExact((Long)query.uniqueResult());
        }
    }

    public List<Product> getAllProducts(Integer startIndex, Integer pageSize, String type, String order, String shopId, String categoryId) {
        try (Session session = HibernateManager.getInstance().getSessionFactory().openSession()) {
            String queryString = "from Product p";

            //Create shopId field
            if (shopId !=  null) {
                queryString += " where p.shopId=:shopId";
            }

            // No categories in the database yet
//            if (!categoryId.equals("null)) {
//                System.out.println("categoryId not null, add where to the query");
//            }

            //Setting sorting if needed
            if (type != null & order != null) {
                if (type.equals("PRICE")) {
                    queryString += " ORDER BY p.price";
                } else {
                    queryString += " ORDER BY p.name";
                }

                if (order.equals("ASCENDING")) {
                    queryString += " ASC";
                } else {
                    queryString += " DESC";
                }

            }

            Query<Product> query = session.createQuery(queryString);

            //Setting shopId field
            if (shopId != null) {
                query.setParameter("shopId", Integer.parseInt(shopId));
            }

            // No categories in the database yet
//            if (!categoryId.equals("null)) {
//                System.out.println("categoryId not null, add where to the query");
//                query.setParameter("categoryId", categoryId);
//            }

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
