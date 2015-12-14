package org.exschool.pocketworld.city.center.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.city.center.builder.CityCenterDtoBuilder;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

@RunWith(BlockJUnit4ClassRunner.class)
public class CityCenterServiceImplTest {

    @Autowired
	CityCenterServiceImpl cityCenterService = new CityCenterServiceImpl();

    @Test
    public void testAddBuilding() {
        Building b = new Building("mall", 2);
        cityCenterService.cityCenterInfo();
        assertTrue(cityCenterService.addBuilding(4, b));
        assertFalse(cityCenterService.addBuilding(-1, b));
        assertFalse(cityCenterService.addBuilding(1, b));
        assertFalse(cityCenterService.addBuilding(5, null));
    }

    @Test
    public void testNoBuildingTypeIfBuildingWithAllBuildingTypesAreBuilt() {
        CityCenterDto cityCenterDtoMock = cityCenterDto(Sets.newHashSet(BuildingType.asListLowerCase()));
        Collection<String> availableForBuildBuildingTypes = cityCenterService.availableForBuildBuildingTypes(cityCenterDtoMock.getBuildingTypes());
        assertEquals(0, availableForBuildBuildingTypes.size());
    }

    @Test
    public void testAllBuildingTypesAvailableIfNoBuildingsBuilt() {
        CityCenterDto cityCenterDtoMock = cityCenterDto(new HashSet<String>());
        Collection<String> availableForBuildBuildingTypes = cityCenterService.availableForBuildBuildingTypes(cityCenterDtoMock.getBuildingTypes());
        assertEquals(BuildingType.asListLowerCase().size(), availableForBuildBuildingTypes.size());
    }

    private static CityCenterDto cityCenterDto(Collection<String> buildingTypesAsStrings) {
        return CityCenterDtoBuilder.builder().buildings(buildings(buildingTypesAsStrings)).build();
    }

    private static Map<Integer, Building> buildings(Collection<String> buildingTypesAsStrings) {
        Map<Integer, Building> map = new HashMap<>();
        Random random = new Random();
        for (String buildingTypesAsString : buildingTypesAsStrings) {
            int key = random.nextInt();

            if (!map.containsKey(key)) {
                map.put(key, new Building(buildingTypesAsString, 1));
                continue;
            }

            while (map.get(key) != null) {
                key = random.nextInt();
                map.put(key, new Building(buildingTypesAsString, 1));
            }
        }
        return map;
    }
}
