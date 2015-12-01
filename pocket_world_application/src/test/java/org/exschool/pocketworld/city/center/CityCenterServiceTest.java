package org.exschool.pocketworld.city.center;

import com.google.common.collect.Sets;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.center.service.CityCenterService;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class CityCenterServiceTest {

    @Autowired
    CityCenterService cityCenterService;

    @Test
    public void testNoBuildingTypeIfBuildingWithAllBuildingTypesAreBuilt(){
        CityCenterDto cityCenterDtoMock = Mockito.mock(CityCenterDto.class);
        Mockito.when(cityCenterDtoMock.getBuildingTypes()).thenReturn(Sets.newHashSet(BuildingType.asListLowerCase()));
        List<String> availableForBuildBuildingTypes = cityCenterService.availableForBuildBuildingTypes(cityCenterDtoMock.getBuildingTypes());
        assertEquals(0, availableForBuildBuildingTypes.size());
    }

    @Test
    public void testAllBuildingTypesAvailableIfNoBuildingsBuilt(){
        CityCenterDto cityCenterDtoMock = Mockito.mock(CityCenterDto.class);
        Mockito.when(cityCenterDtoMock.getBuildingTypes()).thenReturn(new HashSet<String>());
        List<String> availableForBuildBuildingTypes = cityCenterService.availableForBuildBuildingTypes(cityCenterDtoMock.getBuildingTypes());
        assertEquals(BuildingType.asListLowerCase().size(), availableForBuildBuildingTypes.size());
    }


}
