package org.exschool.pocketworld.buildQueue.service;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;

import java.util.List;

/**
 * Created by skandy on 02.12.15.
 */
public interface BuildQueueService {
    BuildQueueRecord get(Long id);
    List<BuildQueueRecord> getAll();
    BuildQueueRecord save (BuildQueueRecord entity);
    BuildQueueRecord changeStatus(BuildQueueRecord entity, Status status);
    void delete(BuildQueueRecord entity);
    void deleteAllDone();

}
