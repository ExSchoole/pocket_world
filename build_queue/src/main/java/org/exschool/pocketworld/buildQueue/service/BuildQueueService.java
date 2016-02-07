package org.exschool.pocketworld.buildQueue.service;

import java.util.Date;
import java.util.List;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;

/**
 * Created by skandy on 02.12.15.
 */
public interface BuildQueueService {
    BuildQueueRecord get(Long id);
    List<BuildQueueRecord> getAll();
    List<BuildQueueRecord> getAllByUser(Long userId);
    void updateStatus(Status status, int buildingPosition, Long userId, String type);
    void updateAll(Status newStatus, Long userId, Date date);
    List<BuildQueueRecord> getAllByUserStatus(Long id, Status status);
    List<BuildQueueRecord> getAllByUserStatusDate(Long userId, Status status, Date date);
    BuildQueueRecord save (BuildQueueRecord entity);
    BuildQueueRecord changeStatus(Long id, Status status);
    void delete(BuildQueueRecord entity);
    void deleteAllByStatus(Status status);

}
