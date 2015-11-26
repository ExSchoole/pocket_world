package org.exschool.pocketworld.city.center.dto;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.resource.ResourceDto;
import java.util.Stack;

import java.util.Map;

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
}
