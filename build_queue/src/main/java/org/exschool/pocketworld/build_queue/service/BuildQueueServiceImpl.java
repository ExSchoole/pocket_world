package org.exschool.pocketworld.build_queue.service;

import org.exschool.pocketworld.build_queue.model.BuildQueue;
import org.exschool.pocketworld.build_queue.model.Status;
import org.exschool.pocketworld.dao.Dao;
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
    public BuildQueue get(Long id) {
        return dao.get(BuildQueue.class, id);
    }

    /**
     * Saves entity object to DB
     *
     * @param entity
     */
    @Override
    public BuildQueue save(BuildQueue entity) {
        return dao.save(entity);
    }

    /**
     * Updates status of entity object
     * @param entity
     * @param status
     * @return
     */
    @Override
    public BuildQueue changeStatus(BuildQueue entity, Status status) {
        BuildQueue record = dao.get(BuildQueue.class,entity.getId());
        dao.delete(entity);
        record.setStatus(status);
        dao.save(record);
        return record;
    }

    @Override
    public void delete(BuildQueue entity) {

        dao.delete(entity);
    }
}
