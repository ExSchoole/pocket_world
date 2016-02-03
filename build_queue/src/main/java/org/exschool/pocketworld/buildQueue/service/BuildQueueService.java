package org.exschool.pocketworld.buildQueue.service;

import java.util.List;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;

/**
 * Created by skandy on 02.12.15.
 */
public interface BuildQueueService {
    BuildQueueRecord get(Long id);
    List<BuildQueueRecord> getAll();
    List<BuildQueueRecord> getAllByUser(Long userId);
    void updateStatus(Status status, int buildingPosition, String type, Long userId);
    void updateAll(Status newStatus, Long userId, Type type);
    List<BuildQueueRecord> getAllByUserStatusType(Long id, Status status, Type type);
    BuildQueueRecord save (BuildQueueRecord entity);
    BuildQueueRecord changeStatus(Long id, Status status);
    void delete(BuildQueueRecord entity);
    void deleteAllByStatus(Status status);

}
