package org.exschool.pocketworld.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kgavrylchenko on 10/23/2015.
 */
@Repository
public class Dao {
	private static final Logger logger = LoggerFactory.getLogger(Dao.class);
	
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

    /**
     * Return the persistent instance of the given entity class with the given parameters,
     * It uses HQL. If there are there several entities that meet given parameters, returns first item.  
     *  
     * @param  clazz  			a persistent class
     * @param  whereParameters  pairs of key/value parameters which will be added to "where" part of the query 
     * @return        			a persistent instance or null
     */
    public <T> T getBy(Class<T> entityClass, Map<String,String> whereParameters){
    	return getAllBy(entityClass, whereParameters).get(0);
    };
    
    
    /**
     * Return the persistent instances of the given entity class which meet given parameters,
     *  
     * @param  clazz  			a persistent class
     * @param  whereParameters  pairs of key/value parameters which will be added to "where" part of the query 
     * @return        			list of persistent instances or null
     */
    @SuppressWarnings("unchecked")
	public <T> List<T> getAllBy(Class<T> entityClass, Map<String,String> whereParameters){
     	final Integer numberOfKeyValueParamPairs = whereParameters.keySet().size();
    	Integer currentParamIndex = 0;
     	Query query;
    	StringBuilder queryBilder = new StringBuilder();
    	queryBilder.append("from " + entityClass.getName() + "  as e ");
    	queryBilder.append("where ");
    		   for(String key: whereParameters.keySet()){
    			  currentParamIndex++; 	
    			  queryBilder.append("e."+key+" = '"+whereParameters.get(key) +"' ");
    			  if(currentParamIndex != numberOfKeyValueParamPairs){
    				  queryBilder.append(" and ");
    			  }else{
    				  queryBilder.append(" ");
    			  }
    		   }
    		   logger.info("getByAll() query:"+queryBilder.toString());
    		   query = currentSession().createQuery(queryBilder.toString());
    		   return (List<T>) query.list();
    };
    
    /**
     * Return the persistent instance of the given entity class with the given parameters,
     * It uses Criterians. If there are there several entities that meet given parameters, returns first item.  
     *  
     * @param  clazz  			a persistent class
     * @param  criterions       criterion to match the search against, for creations of Criterion use
     * 							Restrictions class e.g Restrictions.eq(propertyName, value)
     * @return        			a persistent instance or null
     */
   	public <T> T getBy(Class<T> entityClass, List<Criterion> criterions){
       	return getAllBy(entityClass,criterions).get(0);
     }
    
    
   	
   	/**
     * Return the persistent instances of the given entity class with the given parameters,
     * It uses Criterians. 
     *  
     * @param  clazz  			a persistent class
     * @param  criterions       criterion to match the search against, for creations of Criterion use
     * 							Restrictions class e.g Restrictions.eq(propertyName, value)
     * @return        			list of persistent instances or null
     */
    @SuppressWarnings("unchecked")
	public <T> List<T> getAllBy(Class<T> entityClass, List<Criterion> criterions){
    	Criteria criteria = currentSession().createCriteria(entityClass);
    	for(Criterion criterion:criterions){
    		criteria.add(criterion);
    	}
    	return (List<T>) criteria.list();
    }
    	
    	
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
