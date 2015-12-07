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
    public static final int MIN_POSITION = 1;
    public static final int MAX_POSITION = 12;
    private Map<Integer, Building> buildings;

    @Override
    public CityCenterDto cityCenterInfo() {
        ResourceDto resourceDto = new ResourceDto(1, 1, 1, 1);
        this.buildings = buildings();
        String nickname = "User login";
        return CityCenterDtoBuilder.builder()
                .resource(resourceDto)
                .buildings(buildings)
                .nickname(nickname)
                .build();
    }

    private Map<Integer, Building> buildings() {
        Map<Integer, Building> buildings = new HashMap<>();
        buildings.put(1, new Building("mall", 1));
        buildings.put(3, new Building("factory", 2));
        buildings.put(6, new Building("market", 3));
        buildings.put(9, new Building("shop", 4));
        return buildings;
    }

    public boolean addBuilding(int position, Building newBuilding) {
        if (newBuilding != null && position <= MAX_POSITION && position >= MIN_POSITION) {
            if (!buildings.containsKey(position)) {
                this.buildings.put(position, newBuilding);
                return true;
            }
        }

        return false;
    }
}
