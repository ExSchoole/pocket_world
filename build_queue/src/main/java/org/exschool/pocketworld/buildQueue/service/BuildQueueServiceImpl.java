package org.exschool.pocketworld.buildQueue.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.dao.Dao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by skandy on 03.12.15.
 */

@Service("buildQueueService")
@Transactional
public class BuildQueueServiceImpl implements BuildQueueService {
    /**
     * dao - data access object
     */
    @Autowired
    private Dao dao;

    @Override
    public BuildQueueRecord get(Long id) {
        return dao.get(BuildQueueRecord.class, id);
    }

    @Override
    public List<BuildQueueRecord> getAll() {
        return dao.all(BuildQueueRecord.class);
    }

    @Override
    public List<BuildQueueRecord> getAllByUser(Long userId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BuildQueueRecord.class)
                .add(Property.forName("userId").eq(userId));
        return dao.getAllBy(detachedCriteria);
    }
    
    /**
     * Saves entity object to DB
     *
     * @param entity
     */
    @Override
    public BuildQueueRecord save(BuildQueueRecord entity) {
        return dao.save(entity);
    }

    /**
     * Updates status of entity object
     * @param id
     * @param status
     * @return
     */
    @Override
    public BuildQueueRecord changeStatus(Long id, Status status) {
        BuildQueueRecord record = dao.get(BuildQueueRecord.class, id);
        record.setStatus(status);
        return dao.save(record);
    }

    @Override
    public void delete(BuildQueueRecord entity) {
        dao.delete(entity);
    }

    
    @Override
    public List<BuildQueueRecord> getAllByUserStatus(Long userId, Status status) {
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BuildQueueRecord.class)
                .add(Property.forName("userId").eq(userId))
    			.add(Property.forName("status").eq(status));
    			
    	    	
    	 return dao.getAllBy(detachedCriteria);
    }
    
    @Override
    public List<BuildQueueRecord> getAllByUserStatusDate(Long userId, Status status, Date date) {
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BuildQueueRecord.class)
                .add(Property.forName("userId").eq(userId))
    			.add(Property.forName("status").eq(status))
    			.add(Restrictions.lt("buildEnd", date));
    	    	
    	 return dao.getAllBy(detachedCriteria);
    }
    

    @Override
    public void updateAll(Status status, Long userId, Date date){
    	String sqlUpdateStatus = "UPDATE buildqueue SET status=:status "
				+ "WHERE user_id=:user_id "
				+ "AND build_end<:build_end ";
 
    	Map<String, List<Serializable>> parametrs = new HashMap<>();
    	parametrs.put("status", new ArrayList<Serializable>(Arrays.asList(status)));
    	parametrs.put("user_id", new ArrayList<Serializable>(Arrays.asList(userId)));
		parametrs.put("build_end", new ArrayList<Serializable>(Arrays.asList(date)));
    
    	dao.update(sqlUpdateStatus, parametrs);
    }
    
    @Override
    public void updateStatus(Status status, int buildingPosition, Long userId, String type){
    	String sqlUpdateStatus = "UPDATE buildqueue SET status=:status "
				+ "WHERE position=:position "
				+ "AND build_type=:build_type "		
				+ "AND user_id=:user_id";
    	
    	Map<String, List<Serializable>> parametrs = new HashMap<>();
    	parametrs.put("status", new ArrayList<Serializable>(Arrays.asList(status)));
    	parametrs.put("position", new ArrayList<Serializable>(Arrays.asList(buildingPosition)));
    	parametrs.put("build_type", new ArrayList<Serializable>(Arrays.asList(type.toUpperCase())));
    	parametrs.put("user_id", new ArrayList<Serializable>(Arrays.asList(userId)));
    	
    	dao.update(sqlUpdateStatus, parametrs);
    }
    
    @Override
    public void deleteAllByStatus(Status status) {
        List<BuildQueueRecord> all = dao.all(BuildQueueRecord.class);
        for (BuildQueueRecord record :all) {
            if (record.getStatus().equals(status)) {
                dao.delete(record);
            }
        }
    }

}
