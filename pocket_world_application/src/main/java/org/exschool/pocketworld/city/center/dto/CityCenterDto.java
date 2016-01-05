package org.exschool.pocketworld.city.center.dto;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.exschool.pocketworld.building.BuildingInterim;
import org.exschool.pocketworld.resource.ResourceDto;

public class CityCenterDto {

    private Map<Integer, BuildingInterim> buildings;
    private ResourceDto resourceDto;
    private String nickName;

    public CityCenterDto() {
    }

    public CityCenterDto(Map<Integer, BuildingInterim> buildings, ResourceDto resourceDto, String nickName) {
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

    public Map<Integer, BuildingInterim> getBuildings() {
        return buildings;
    }

    public void setBuildings(Map<Integer, BuildingInterim> buildings) {
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
        for(BuildingInterim building: getBuildings().values()) {
            result.add(building.getType().toLowerCase());
        }
        return result;
    }

}
