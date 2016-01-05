package org.exschool.pocketworld.city.center.service;

import static org.exschool.pocketworld.building.model.BuildingType.MALL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import org.exschool.pocketworld.building.BuildingInterim;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.city.center.builder.CityCenterDtoBuilder;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.google.common.collect.Sets;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class CityCenterServiceImplTest {

	@InjectMocks
  	CityCenterService cityCenterService = new CityCenterServiceImpl();
	
	@Mock
	BuildingService buildingService;
	
    @Test
    public void testAddBuilding() {
    	cityCenterService.cityCenterInfo();
        BuildingInterim b = new BuildingInterim(MALL.name().toLowerCase(), 2);
        assertTrue(cityCenterService.addBuilding(1l,4, b));
        assertFalse(cityCenterService.addBuilding(1l,-1, b));
        assertFalse(cityCenterService.addBuilding(1l,1, b));
        assertFalse(cityCenterService.addBuilding(1l,5, null));
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

    private static Map<Integer, BuildingInterim> buildings(Collection<String> buildingTypesAsStrings) {
        Map<Integer, BuildingInterim> map = new HashMap<>();
        Random random = new Random();
        for (String buildingTypesAsString : buildingTypesAsStrings) {
            int key = random.nextInt();

            if (!map.containsKey(key)) {
                map.put(key, new BuildingInterim(buildingTypesAsString, 1));
                continue;
            }

            while (map.get(key) != null) {
                key = random.nextInt();
                map.put(key, new BuildingInterim(buildingTypesAsString, 1));
            }
        }
        return map;
    }
}
