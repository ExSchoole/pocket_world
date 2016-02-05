package org.exschool.pocketworld.buildQueue.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.dao.Dao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
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
    public void updateAll(Status status, Long userId, Type type){
    	String sqlUpdateStatus = String.format("UPDATE buildqueue SET status=? "
    			+ "FROM %s "
				+ "WHERE buildqueue.building_id = id AND buildqueue.user_id=? "
				+ "AND buildqueue.build_end<?", type.name());
    	String sqlUpdateLevel = String.format("UPDATE %s SET level=buildqueue.level "
    			+ "FROM buildqueue "
    			+"WHERE buildqueue.building_id = id AND user_id=? AND buildqueue.build_end<?", type.name());
    	
    	List<Serializable> parametrs = new ArrayList<Serializable>(Arrays.asList(status.name(), userId, 
    										new Date()));
    	System.out.println("TIME: "+parametrs.get(2));
    	dao.update(sqlUpdateStatus, parametrs);
    	parametrs.remove(0);
    	dao.update(sqlUpdateLevel, parametrs);
    }
    
    @Override
    public void updateStatus(Status status, int buildingPosition, String type, Long userId){
    	String sqlUpdateStatus = String.format("UPDATE buildqueue SET status=? FROM %s "
				+ "WHERE buildqueue.building_id = id AND buildqueue.position=?  "
				+ "AND buildqueue.build_type=? AND buildqueue.user_id=?", type);
    	String sqlUpdateLevel = String.format("UPDATE %s SET level=buildqueue.level FROM buildqueue "
    			+"WHERE buildqueue.building_id = id AND buildqueue.position=? "
    			+ "AND buildqueue.build_type=? AND buildqueue.user_id=?", type);
    	List<Serializable> parametrs = new ArrayList<Serializable>(Arrays.asList(status.name(), 
    								   buildingPosition, type.toUpperCase(), userId));
    	dao.update(sqlUpdateStatus, parametrs);
    	parametrs.remove(0);
    	dao.update(sqlUpdateLevel,parametrs);
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
