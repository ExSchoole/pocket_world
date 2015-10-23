package org.exschool.pocketworld.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by kgavrylchenko on 10/23/2015.
 */
@Repository
public class DAO {
    @Autowired
    private SessionFactory sessionFactory;

    public <T> T get(Class<T> entityClass, Serializable id) {
        return (T) currentSession().get(entityClass, id);
    }

    public <T> void save(T entity) {
        currentSession().save(entity);
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
