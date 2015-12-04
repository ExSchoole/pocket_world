package org.exschool.pocketworld.city.center;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.city.center.service.CityCenterService;
import org.exschool.pocketworld.city.center.service.CityCenterServiceImpl;
import org.junit.Test;
import org.mockito.Mockito;


public class CityCenterServiceTest {

	
	@Test
	public void testAddBuilding(){
		Building b = Mockito.mock(Building.class);
		CityCenterService cityCenterService = new CityCenterServiceImpl();
		cityCenterService.cityCenterInfo();
		assertTrue(cityCenterService.addBuilding(4, b));
		assertFalse(cityCenterService.addBuilding(-1, b));
		assertFalse(cityCenterService.addBuilding(1, b));
		assertFalse(cityCenterService.addBuilding(5, null));
	}
}
