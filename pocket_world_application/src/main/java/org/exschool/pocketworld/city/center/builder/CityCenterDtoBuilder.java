package org.exschool.pocketworld.city.center.builder;

import java.util.Map;

import org.exschool.pocketworld.building.BuildingDto;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.info.building.BuildingInfoDto;
import org.exschool.pocketworld.resource.ResourceDto;

public final class CityCenterDtoBuilder {

    private ResourceDto resourceDto;
    private Map<String, BuildingInfoDto> buildingsInfo;
    private Map<Integer, BuildingDto> buildings;
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

    public CityCenterDtoBuilder buildings(Map<Integer, BuildingDto> buildings) {
        this.buildings = buildings;
        return this;
    }

    public CityCenterDtoBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    public CityCenterDtoBuilder buildingsInfo(Map<String, BuildingInfoDto> buildingsInfo){
    	this.buildingsInfo = buildingsInfo;
    	return this;
    }

    public CityCenterDto build() {
        CityCenterDto cityCenterDto = new CityCenterDto();
        cityCenterDto.setNickName(nickname);
        cityCenterDto.setResourceDto(resourceDto);
        cityCenterDto.setBuildings(buildings);
        cityCenterDto.setBuildingsInfo(buildingsInfo);
        return cityCenterDto;
    }
}
