package org.exschool.pocketworld.buildQueue.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.BuildQueueBuilder;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BuildQueueBootstrap {
    @Autowired
    Dao dao;

    private List<BuildQueueRecord> buildQueueRecordsByOneUser = new ArrayList<>(); 
    private volatile boolean bootstraped = false;
    
    public void fillDatabase(){
        if(bootstraped) return;
        
        BuildQueueRecord buildQueueRecord1 = BuildQueueBuilder.builder().id(1L).name("testname1").level(1).type(Type.BUILDING).buildEnd(new DateTime(System.currentTimeMillis()).toDate()).userId(1L).position(1).status(Status.QUEUED).buildingId(1L).build();
        buildQueueRecordsByOneUser.add(buildQueueRecord1);
        
        BuildQueueRecord buildQueueRecord2 = BuildQueueBuilder.builder().id(2L).name("testname2").level(1).type(Type.RESOURCE_BUILDING).buildEnd(new DateTime(System.currentTimeMillis()).toDate()).userId(2L).position(2).status(Status.QUEUED).buildingId(2L).build();        
        BuildQueueRecord buildQueueRecord3 = BuildQueueBuilder.builder().id(3L).name("testname3").level(1).type(Type.BUILDING).buildEnd(new DateTime(System.currentTimeMillis()).toDate()).userId(3L).position(3).status(Status.DONE).buildingId(3L).build();
        
        dao.saveAll(Arrays.asList(buildQueueRecord1, buildQueueRecord2, buildQueueRecord3));
        bootstraped=true;
    }
    
    public List<BuildQueueRecord> getResords(){
    	return buildQueueRecordsByOneUser;
    }

}
