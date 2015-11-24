package org.exschool.pocketworld.city.resources.builder;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.resource.ResourceDto;

import java.util.Map;

public final class CityResourcesDtoBuilder {

    private ResourceDto resourceDto;
    private Map<Integer, Building> buildings;
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

    public CityResourcesDtoBuilder buildings(Map<Integer, Building> buildings) {
        this.buildings = buildings;
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
        cityResourcesDto.setBuildings(buildings);
        return cityResourcesDto;
    }
}
