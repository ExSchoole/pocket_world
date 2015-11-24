package org.exschool.pocketworld.city.resources.service;

import org.exschool.pocketworld.building.ResourceBuilding;
import org.exschool.pocketworld.city.resources.builder.CityResourcesDtoBuilder;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.resource.ResourceDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CityResourcesServiceImpl implements CityResourcesService {

    @Override
    public CityResourcesDto cityResourcesInfo() {
        ResourceDto resourceDto = new ResourceDto(1,1,1,1);
        Map<Integer, ResourceBuilding> resourceBuildings = buildings();
        String nickname = "User login";
        return CityResourcesDtoBuilder.builder()
                .resource(resourceDto)
                .buildings(resourceBuildings)
                .nickname(nickname)
                .build();
    }

    private Map<Integer, ResourceBuilding> buildings() {
        Map<Integer, ResourceBuilding> buildings = new HashMap<>();
        buildings.put(2, new ResourceBuilding("clay", 1));
        buildings.put(4, new ResourceBuilding("gold", 2));
        buildings.put(8, new ResourceBuilding("timber", 3));
        buildings.put(12, new ResourceBuilding("corn", 4));
        return buildings;
    }
}