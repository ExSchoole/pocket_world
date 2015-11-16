package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.dao.Dao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceTest {
    private BuildingServiceImpl service;
    private Dao dao;
    private Building building1, building2;

    @Before
    public void init() {
        service = new BuildingServiceImpl();
        dao = Mockito.mock(Dao.class);
        service.setDao(dao);
        building1 = new Building();
        building1.setLevel(1);
        building1.setPosition(1);
        building1.setCityId(1L);
    }

    @Test
    public void getTest() {
        Mockito.when (dao.get(Building.class, 1)).thenReturn(building1);
        Assert.assertEquals(building1, service.get(1));
        Assert.assertNull(service.get(0));
    }

}