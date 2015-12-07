package org.exschool.pocketworld.build_queue.service;

import org.exschool.pocketworld.build_queue.model.BuildQueue;
import org.exschool.pocketworld.build_queue.model.Status;

/**
 * Created by skandy on 02.12.15.
 */
public interface BuildQueueService {
    BuildQueue get(Long id);
    BuildQueue save (BuildQueue entity);
    BuildQueue changeStatus(BuildQueue entity, Status status);
    void delete(BuildQueue entity);

}
