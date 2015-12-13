package org.exschool.pocketworld.city.center.service;

import static org.exschool.pocketworld.building.model.BuildingType.MALL;
import static org.exschool.pocketworld.building.model.BuildingType.MARKETPLACE;
import static org.exschool.pocketworld.building.model.BuildingType.PLANT;
import static org.exschool.pocketworld.building.model.BuildingType.POOL;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.building.service.BuildingServiceImpl;
import org.exschool.pocketworld.city.center.builder.CityCenterDtoBuilder;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.resource.ResourceDto;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Service
public class CityCenterServiceImpl implements CityCenterService {
    public static final int MIN_POSITION = 1;
    public static final int MAX_POSITION = 12;
    private Map<Integer, Building> buildings;

    {
        buildings = buildings();
    }

    private BuildingService buildingService = new BuildingServiceImpl();
    private org.exschool.pocketworld.building.model.Building BuildingEntity;
    
    @Override
    public CityCenterDto cityCenterInfo() {
        ResourceDto resourceDto = new ResourceDto(1, 1, 1, 1);
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
    public Collection<String> availableForBuildBuildingTypes(final Set<String> buildingTypesOfBuiltBuildings) {
        return Collections2.filter(BuildingType.asListLowerCase(), new Predicate<String>() {
            @Override
            public boolean apply(String buildingType) {
                return !buildingTypesOfBuiltBuildings.contains(buildingType);
            }
        });
    }

    private Map<Integer, Building> buildings() {
        Map<Integer, Building> buildings = new HashMap<>();
        buildings.put(1, new Building(MALL.name().toLowerCase(), 1));
        buildings.put(3, new Building(PLANT.name().toLowerCase(), 2));
        buildings.put(6, new Building(MARKETPLACE.name().toLowerCase(), 3));
        buildings.put(9, new Building(POOL.name().toLowerCase(), 4));
        return buildings;
    }

    public boolean addBuilding(int position, Building newBuilding) {
        if (newBuilding != null && position <= MAX_POSITION && position >= MIN_POSITION) {
            if (!buildings.containsKey(position)) {
                this.buildings.put(position, newBuilding);
                
                BuildingEntity = new org.exschool.pocketworld.building.model.Building();
                BuildingEntity.setCityId(1L);
                BuildingEntity.setLevel(newBuilding.getLevel());
                BuildingEntity.setPosition(position);                
                BuildingEntity.setBuildingType(BuildingType.valueOf(newBuilding.getType().toUpperCase()));
                
                buildingService.save(BuildingEntity);
                
                org.exschool.pocketworld.building.model.Building b = new org.exschool.pocketworld.building.model.Building();
                return true;
            }
        }

        return false;
    }
    
}
