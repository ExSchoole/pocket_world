package org.exschool.pocketworld.city.center.dto;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.resource.ResourceDto;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CityCenterDto {

    private Map<Integer, Building> buildings;
    private ResourceDto resourceDto;
    private String nickName;

    public CityCenterDto() {
    }

    public CityCenterDto(Map<Integer, Building> buildings, ResourceDto resourceDto, String nickName) {
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

    public Map<Integer, Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Map<Integer, Building> buildings) {
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
        for(Building building: getBuildings().values()) {
            result.add(building.getType().toLowerCase());
        }
        return result;
    }

}
