package org.exschool.pocketworld.buildQueue.service;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     * @param entity
     * @param status
     * @return
     */
    @Override
    public BuildQueueRecord changeStatus(BuildQueueRecord entity, Status status) {
        BuildQueueRecord record = dao.get(BuildQueueRecord.class,entity.getId());
        dao.delete(entity);
        record.setStatus(status);
        dao.save(record);
        return record;
    }

    @Override
    public void delete(BuildQueueRecord entity) {

        dao.delete(entity);
    }

    @Override
    public void deleteAllDone() {
        List<BuildQueueRecord> all = dao.all(BuildQueueRecord.class);
        for (BuildQueueRecord record:all) {
            if (record.getStatus().equals(Status.DONE)) {
                dao.delete(record);
            }

        }
    }
}
