package org.exschool.pocketworld.city.center.service;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.city.center.builder.CityCenterDtoBuilder;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.resource.ResourceDto;
import org.springframework.stereotype.Service;

import java.util.*;

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

    /**
     * Gets list of available building types (Buildings of those types has not been built yet)
     *
     * @param buildingTypesOfBuiltBuildings - BuildingTypes of building which are already built in the city
     * @return list of building types which we allowed to build
     */
    @Override
    public List<String> availableForBuildBuildingTypes(Set<String> buildingTypesOfBuiltBuildings){
        List<String> allBuildingTypes = BuildingType.asListLowerCase();
        List<String> result = new ArrayList<>();
        for(String buildingType: allBuildingTypes){
            if(!buildingTypesOfBuiltBuildings.contains(buildingType)) {
                result.add(buildingType);
            }
        }
        return result;
    }

    private Map<Integer, Building> buildings() {
        Map<Integer, Building> buildings = new HashMap<>();
        buildings.put(1, new Building("mall", 1));
        buildings.put(3, new Building("plant", 2));
        buildings.put(6, new Building("marketplace", 3));
        buildings.put(9, new Building("pool", 4));
        return buildings;
    }
}
