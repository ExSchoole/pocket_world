package org.exschool.pocketworld.buildQueue.service;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.dao.Dao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<BuildQueueRecord> getAllActiveByUser(Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("status", Status.QUEUED);
        return dao.getAllBy("from BuildQueueRecord r where r.userId = :userId and status = :status", params);
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
    public void deleteAllByStatus(Status status) {
        List<BuildQueueRecord> all = dao.all(BuildQueueRecord.class);
        for (BuildQueueRecord record :all) {
            if (record.getStatus().equals(status)) {
                dao.delete(record);
            }
        }
    }

}
