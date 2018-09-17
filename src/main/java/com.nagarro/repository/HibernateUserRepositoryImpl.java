package com.nagarro.repository;

import com.nagarro.models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Layer to interact with Database
 * @author Sanyam Goel created on 17/9/18
 */
@Repository
public class HibernateUserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * authentication function to check user
     * @param username
     * @param password
     * @return user
     */
    @Override
    public User authenticate(String username, String password) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User WHERE username=:username AND password=:password";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            user = (User) query.getSingleResult();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

}