package org.exschool.pocketworld.build_queue.service;

import org.exschool.pocketworld.build_queue.model.BuildQueue;
import org.exschool.pocketworld.build_queue.model.Status;
import org.exschool.pocketworld.build_queue.model.Type;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.BuildQueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

@Service
@Transactional
public class BuildQueueBootstrap {
    @Autowired
    Dao dao;

    private volatile boolean bootstraped = false;
    public void fillDatabase(){
        if(bootstraped) return;
        BuildQueue buildQueue1 = BuildQueueBuilder.builder().id(1L).name("testname1").level(1).type(Type.BUILDING).buildEnd(new Date()).userId(1L).status(Status.QUEUED).buildingId(1L).build();
        BuildQueue buildQueue2 = BuildQueueBuilder.builder().id(2L).name("testname2").level(1).type(Type.RESOURCE_BUILDING).buildEnd(new Date()).userId(2L).status(Status.QUEUED).buildingId(2L).build();
        BuildQueue buildQueue3 = BuildQueueBuilder.builder().id(3L).name("testname3").level(1).type(Type.BUILDING).buildEnd(new Date()).userId(3L).status(Status.DONE).buildingId(3L).build();
        dao.saveAll(Arrays.asList(buildQueue1,buildQueue2,buildQueue3));
        bootstraped=true;
    }

}
