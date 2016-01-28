package org.exschool.pocketworld.buildQueue.service;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.BuildQueueBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class BuildQueueRecordServiceTest {
    @Autowired
    BuildQueueService buildQueueService;
    @Autowired
    BuildQueueBootstrap bootstrap;
    @Autowired
    Dao dao;

    @Before
    public void before() {
        bootstrap.fillDatabase();

    }

    @Test
    public void testCreate() {
        BuildQueueRecord buildQueueRecord = BuildQueueBuilder.builder().build();
        BuildQueueRecord savedBuildQueueRecord = buildQueueService.save(buildQueueRecord);
        assertNotNull(savedBuildQueueRecord);
        assertNotNull(savedBuildQueueRecord.getId());
        assertAllFieldsEquals(buildQueueRecord, savedBuildQueueRecord);
    }
    @Test
    public void testChangeStatus() {
        Long existingId =1L;
        BuildQueueRecord existingRecord = buildQueueService.get(existingId);
        BuildQueueRecord updatedRecord =buildQueueService.changeStatus(existingId, Status.DONE);
        assertNotEquals(existingRecord.getStatus(),updatedRecord.getStatus());




    }

    @Test
    public void testDelete() {
        Long existingId=2L;
        BuildQueueRecord existing = buildQueueService.get(existingId);
        assertNotNull(existing);
        buildQueueService.delete(existing);
        BuildQueueRecord deleted = buildQueueService.get(existingId);
        assertNull(deleted);
    }

    @Test
    public void testGetAllByUser() {
        Long userId=1L;
        List<BuildQueueRecord> existingRecords = buildQueueService.getAllByUser(userId);
        for (BuildQueueRecord record:existingRecords) {
            assertEquals(record.getUserId(),userId);
        }


    }

    @Test
    public void testDeleteAllByStatus() {
        buildQueueService.deleteAllByStatus(Status.DONE);
        List<BuildQueueRecord> all = buildQueueService.getAll();
        for (BuildQueueRecord record: all) {
            assertNotEquals(Status.DONE,record.getStatus());
        }

    }

    private void assertAllFieldsEquals(BuildQueueRecord buildQueueRecord1, BuildQueueRecord buildQueueRecord2) {
        assertEquals(buildQueueRecord1.getId(), buildQueueRecord2.getId());
        assertEquals(buildQueueRecord1.getName(), buildQueueRecord2.getName());
        assertEquals(buildQueueRecord1.getLevel(), buildQueueRecord2.getLevel());
        assertEquals(buildQueueRecord1.getType(), buildQueueRecord2.getType());
        assertEquals(buildQueueRecord1.getBuildEnd(), buildQueueRecord2.getBuildEnd());
        assertEquals(buildQueueRecord1.getUserId(), buildQueueRecord2.getUserId());
        assertEquals(buildQueueRecord1.getStatus(), buildQueueRecord2.getStatus());
        assertEquals(buildQueueRecord1.getBuildingId(), buildQueueRecord2.getBuildingId());


    }
}
