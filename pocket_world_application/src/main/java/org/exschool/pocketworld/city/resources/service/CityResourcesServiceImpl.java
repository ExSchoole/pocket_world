package org.exschool.pocketworld.city.resources.service;

import org.exschool.pocketworld.building.Building;
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
        Map<Integer, Building> buildings = buildings();
        String nickname = "User login";
        return CityResourcesDtoBuilder.builder()
                .resource(resourceDto)
                .buildings(buildings)
                .nickname(nickname)
                .build();
    }

    private Map<Integer, Building> buildings() {
        Map<Integer, Building> buildings = new HashMap<>();
        buildings.put(2, new Building("clay", 1));
        buildings.put(4, new Building("gold", 2));
        buildings.put(8, new Building("timber", 3));
        buildings.put(12, new Building("corn", 4));
        return buildings;
    }
}