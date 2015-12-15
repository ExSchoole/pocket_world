package org.exschool.pocketworld.city.resources.builder;

import org.exschool.pocketworld.building.ResourceBuildingDto;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.resource.ResourceDto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CityResourcesDtoBuilder {

    private ResourceDto resourceDto;
    private Map<Integer, ResourceBuildingDto> resourceBuildings = new HashMap<>();
    private String nickname;

    public static CityResourcesDtoBuilder builder() {
        return new CityResourcesDtoBuilder();
    }

    private CityResourcesDtoBuilder() {
    }


    public CityResourcesDtoBuilder resource(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
        return this;
    }

    public CityResourcesDtoBuilder resourceBuildings(Map<Integer, ResourceBuildingDto> resourceBuildings) {
        this.resourceBuildings.putAll(resourceBuildings);
        return this;
    }

    public CityResourcesDtoBuilder resourceBuildings(Collection<ResourceBuildingDto> resourceBuildingDtos) {
        for (ResourceBuildingDto resourceBuilding : resourceBuildingDtos) {
            resourceBuildings.put(resourceBuilding.getPosition(), resourceBuilding);
        }
        return this;
    }

    public CityResourcesDtoBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public CityResourcesDto build() {
        CityResourcesDto cityResourcesDto = new CityResourcesDto();
        cityResourcesDto.setNickName(nickname);
        cityResourcesDto.setResourceDto(resourceDto);
        cityResourcesDto.setResourceBuildings(resourceBuildings);
        return cityResourcesDto;
    }
}
