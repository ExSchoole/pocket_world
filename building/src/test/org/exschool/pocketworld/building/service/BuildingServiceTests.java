package org.exschool.pocketworld.building.service;


import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.building.service.BuildingServiceImpl;
import org.exschool.pocketworld.dao.Dao;
import org.m

@RunWith(MockitoJUnitRanner.class)
public class BuildingServiceTest {
    private BuildingService service;
    private Dao dao;
    private Building building1, building2;

    @Before
    public void init() {
        service = new BuildingServiceImpl();
        dao = mock(Dao.class);
        ((BuildingServiceImpl) service).setDao(dao);
        building1 = new Building();
        building1.setLevel(1);
        building1.setPosition(1);
        building1.setCityId(1);


    }

    @Test
    public void testGet() {
        when (dao.get(Building.class, 1)).thenReturn(building1);
        assertEquals(building1, service.get(1));
        assertNull(service.get(0));
    }

}