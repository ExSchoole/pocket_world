package org.exschool.pocketworld.buildQueue.service;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.BuildQueueBuilder;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BuildQueueBootstrap {
    @Autowired
    Dao dao;

    private volatile boolean bootstraped = false;
    public void fillDatabase(){
        if(bootstraped) return;
        List<BuildQueueRecord> recordsToSave = new ArrayList<>();
        recordsToSave.add(BuildQueueBuilder.builder().id(1L).name("testname1").level(1).type(Type.BUILDING).buildEnd(new DateTime(System.currentTimeMillis())).userId(1L).status(Status.QUEUED).buildingId(1L).build());
        recordsToSave.add(BuildQueueBuilder.builder().id(1L).name("testname1").level(1).type(Type.BUILDING).buildEnd(new DateTime(System.currentTimeMillis())).userId(1L).status(Status.QUEUED).buildingId(2L).build());
        recordsToSave.add(BuildQueueBuilder.builder().id(1L).name("testname1").level(1).type(Type.BUILDING).buildEnd(new DateTime(System.currentTimeMillis())).userId(1L).status(Status.DONE).buildingId(3L).build());
        recordsToSave.add(BuildQueueBuilder.builder().id(2L).name("testname2").level(1).type(Type.RESOURCE_BUILDING).buildEnd(new DateTime(System.currentTimeMillis())).userId(2L).status(Status.QUEUED).buildingId(2L).build());
        recordsToSave.add(BuildQueueBuilder.builder().id(3L).name("testname3").level(1).type(Type.BUILDING).buildEnd(new DateTime(System.currentTimeMillis())).userId(3L).status(Status.DONE).buildingId(3L).build());
        dao.saveAll(recordsToSave);
        bootstraped=true;
    }

}
