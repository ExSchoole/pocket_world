package org.exschool.pocketworld.city.resources.builder;

import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;

import java.util.Map;

public final class CityResourcesDtoBuilder {

    private ResourceDto resourceDto;
    private Map<Integer, ResourceBuilding> resourceBuildings;
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

    public CityResourcesDtoBuilder resourceBuildings(Map<Integer, ResourceBuilding> resourceBuildings) {
        this.resourceBuildings = resourceBuildings;
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
