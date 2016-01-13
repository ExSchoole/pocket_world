package org.exschool.pocketworld.city.center.dto;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.exschool.pocketworld.building.BuildingDto;
import org.exschool.pocketworld.resource.ResourceDto;

public class CityCenterDto {

    private Map<Integer, BuildingDto> buildings;
    private ResourceDto resourceDto;
    private String nickName;

    public CityCenterDto() {
    }

    public CityCenterDto(Map<Integer, BuildingDto> buildings, ResourceDto resourceDto, String nickName) {
        this.resourceDto = resourceDto;
        this.nickName = nickName;
        this.buildings = buildings;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Map<Integer, BuildingDto> getBuildings() {
        return buildings;
    }

    public void setBuildings(Map<Integer, BuildingDto> buildings) {
        this.buildings = buildings;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }

    public Set<String> getBuildingTypes() {
        Set<String> result = new HashSet<>();
        for(Map.Entry<Integer, BuildingDto> building: buildings.entrySet()) {
            result.add(building.getValue().getType());
        }
        return result;
    }

}
