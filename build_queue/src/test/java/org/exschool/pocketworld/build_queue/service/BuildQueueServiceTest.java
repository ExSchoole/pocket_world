package org.exschool.pocketworld.build_queue.service;

import org.exschool.pocketworld.build_queue.model.BuildQueue;
import org.exschool.pocketworld.build_queue.model.Status;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class BuildQueueServiceTest {
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
        BuildQueue buildQueue = BuildQueueBuilder.builder().build();
        BuildQueue savedBuildQueue = buildQueueService.save(buildQueue);
        assertNotNull(savedBuildQueue);
        assertNotNull(savedBuildQueue.getId());
        assertAllFieldsEquals(buildQueue, savedBuildQueue);
    }
    @Test
    public void testChangeStatus() {
        Long existingId =1L;
        BuildQueue existing = buildQueueService.get(existingId);
        assertNotNull(existing);
        existing.setStatus(Status.DONE);
        buildQueueService.save(existing);
        BuildQueue saved = buildQueueService.get(existingId);
        assertAllFieldsEquals(saved,existing);

    }

    @Test
    public void testDelete() {
        Long existingId=2L;
        BuildQueue existing = buildQueueService.get(existingId);
        assertNotNull(existing);
        buildQueueService.delete(existing);
        BuildQueue deleted = buildQueueService.get(existingId);
        assertNull(deleted);
    }

    private void assertAllFieldsEquals(BuildQueue buildQueue1, BuildQueue buildQueue2) {
        assertEquals(buildQueue1.getId(),buildQueue2.getId());
        assertEquals(buildQueue1.getName(),buildQueue2.getName());
        assertEquals(buildQueue1.getLevel(),buildQueue2.getLevel());
        assertEquals(buildQueue1.getType(),buildQueue2.getType());
        assertEquals(buildQueue1.getBuildEnd(),buildQueue2.getBuildEnd());
        assertEquals(buildQueue1.getUserId(),buildQueue2.getUserId());
        assertEquals(buildQueue1.getStatus(),buildQueue2.getStatus());
        assertEquals(buildQueue1.getBuildingId(),buildQueue2.getBuildingId());


    }
}
