package org.exschool.pocketworld.city.center.builder;

import java.util.Map;

import org.exschool.pocketworld.building.BuildingInterim;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.resource.ResourceDto;

public final class CityCenterDtoBuilder {

    private ResourceDto resourceDto;
    private Map<Integer, BuildingInterim> buildings;
    private String nickname;

    public static CityCenterDtoBuilder builder() {
        return new CityCenterDtoBuilder();
    }

    private CityCenterDtoBuilder() {
    }


    public CityCenterDtoBuilder resource(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
        return this;
    }

    public CityCenterDtoBuilder buildings(Map<Integer, BuildingInterim> buildings) {
        this.buildings = buildings;
        return this;
    }

    public CityCenterDtoBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public CityCenterDto build() {
        CityCenterDto cityCenterDto = new CityCenterDto();
        cityCenterDto.setNickName(nickname);
        cityCenterDto.setResourceDto(resourceDto);
        cityCenterDto.setBuildings(buildings);
        return cityCenterDto;
    }
}
