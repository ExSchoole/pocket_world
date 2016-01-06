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

import org.exschool.pocketworld.building.BuildingDto;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.city.center.builder.CityCenterDtoBuilder;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.player.service.PlayerService;
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
	CityService cityService;
	@Mock
  	PlayerService playerService;
	@Mock
	BuildingService buildingService;
	
    @Test
    public void testAddBuilding() {
    	cityCenterService.cityCenterInfo();
        BuildingDto b = new BuildingDto(MALL.name().toLowerCase(), 1, 1);
        assertTrue(cityCenterService.addBuilding(new BuildingDto(MALL.name().toLowerCase(), 1, 1)));
        assertFalse(cityCenterService.addBuilding(new BuildingDto(MALL.name().toLowerCase(), 1, -1)));
        assertFalse(cityCenterService.addBuilding(new BuildingDto(MALL.name().toLowerCase(), 1, 1)));
        assertFalse(cityCenterService.addBuilding(null));
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

    private static Map<Integer, BuildingDto> buildings(Collection<String> buildingTypesAsStrings) {
        Map<Integer, BuildingDto> map = new HashMap<>();
        Random random = new Random();
        for (String buildingTypesAsString : buildingTypesAsStrings) {
            int key = random.nextInt();

            if (!map.containsKey(key)) {
                map.put(key, new BuildingDto(buildingTypesAsString, 1, 1));
                continue;
            }

            while (map.get(key) != null) {
                key = random.nextInt();
                map.put(key, new BuildingDto(buildingTypesAsString, 1, 1));
            }
        }
        return map;
    }
}
