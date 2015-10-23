package org.exschool.pocketworld.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kgavrylchenko on 10/23/2015.
 */
@Repository
public class DAO {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Return the persistent instance of the given entity class with the given identifier, 
     * or null if there is no such persistent instance. 
     *  
     * @param  clazz  a persistent class
     * @param  id     an identifier
     * @return        a persistent instance or null
     */
    @SuppressWarnings("unchecked")
	public <T> T get(Class<T> entityClass, Serializable id) {
        return (T) currentSession().get(entityClass, id);
    }
    
    /**
     * Return all persistent instances of the given entity class, 
     * or null if there is no such persistent instance. 
     *  
     * @param  clazz  a persistent class
     * @return        List of persistent instances or null
     */
    @SuppressWarnings("unchecked")
	public <T> List<T> all(Class<T> entityClass){
    	return currentSession().createCriteria(entityClass).list();
    }
    
    /**
     * Persist the given transient instance. 
     *  
     * @param  object  a transient instance of a persistent class
     * @return         the generated identifier
     */
    public <T> void save(T entity) {
        currentSession().saveOrUpdate(entity);
    }
    
    /**
     * Remove a persistent instance from the datastore. 
     *  
     * @param object - the instance to be removed
     */
    
    public <T> void delete(T entity) {
        currentSession().delete(entity);
    }

    
    
    
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
