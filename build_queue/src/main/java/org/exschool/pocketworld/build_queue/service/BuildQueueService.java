package org.exschool.pocketworld.build_queue.service;

import org.exschool.pocketworld.build_queue.model.BuildQueueRecord;
import org.exschool.pocketworld.build_queue.model.Status;

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
