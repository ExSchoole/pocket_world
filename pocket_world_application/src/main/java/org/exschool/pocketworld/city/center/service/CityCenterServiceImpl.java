package org.exschool.pocketworld.city.center.service;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.city.center.builder.CityCenterDtoBuilder;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.resource.ResourceDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CityCenterServiceImpl implements CityCenterService {

    @Override
    public CityCenterDto cityCenterInfo() {
        ResourceDto resourceDto = new ResourceDto(1,1,1,1);
        Map<Integer, Building> buildings = buildings();
        String nickname = "User login";
        return CityCenterDtoBuilder.builder()
                .resource(resourceDto)
                .buildings(buildings)
                .nickname(nickname)
                .build();
    }

    private Map<Integer, Building> buildings() {
        Map<Integer, Building> buildings = new HashMap<>();
        buildings.put(1, new Building("type", 1));
        buildings.put(3, new Building("type", 1));
        buildings.put(6, new Building("type", 1));
        buildings.put(9, new Building("type", 1));
        return buildings;
    }
}
