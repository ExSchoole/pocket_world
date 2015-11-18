package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.dao.Dao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.exschool.pocketworld.building.service.TestSpringConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.junit.*;




import java.util.Arrays;
import java.util.LinkedList;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class BuildingServiceTest {
    @InjectMocks
    private BuildingServiceImpl service;
    @Mock
    private Dao dao;
    private Building building1, building2;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new BuildingServiceImpl();
        dao = Mockito.mock(Dao.class);
        service.setDao(dao);
        building1 = new Building();
        building1.setId(1L);
        building1.setLevel(1);
        building1.setPosition(1);
        building1.setCityId(1L);
        building1.setId(1L);
    }

    @Test
    public void getTest() {
        Mockito.when (dao.get(Building.class, 1L)).thenReturn(building1);
        Assert.assertEquals(building1, service.get(1L));
        Assert.assertNull(service.get(0L));
    }

    @Test
    public void getAllTest() {
        LinkedList<Building> allBuildings = new LinkedList<Building>(Arrays.asList(building1));
        Mockito.when(dao.all(Building.class)).thenReturn(allBuildings);
        Assert.assertEquals(allBuildings, service.allBuildings());
        Mockito.when (dao.all(Building.class)).thenReturn(null);
        Assert.assertNull(service.allBuildings());
    }

    @Test
    public void saveTest()
    {
        /*playerService.savePlayer(player);
        assert playerService.getPlayerByLogin("player-login").equals(player);
*/
        service.save(building1);
        assert service.get(1L).equals(building1);


    }



}