package org.exschool.pocketworld.city.resources.dto;

import org.exschool.pocketworld.building.ResourceBuilding;
import org.exschool.pocketworld.resource.ResourceDto;

import java.util.Map;

public class CityResourcesDto {

    private Map<Integer, ResourceBuilding> resourceBuildings;
    private ResourceDto resourceDto;
    private String nickName;

    public CityResourcesDto() {
    }

    public CityResourcesDto(Map<Integer, ResourceBuilding> buildings, ResourceDto resourceDto, String nickName) {
        this.resourceDto = resourceDto;
        this.nickName = nickName;
        this.resourceBuildings = buildings;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Map<Integer, ResourceBuilding> getBuildings() {
        return resourceBuildings;
    }

    public void setBuildings(Map<Integer, ResourceBuilding> buildings) {
        this.resourceBuildings = buildings;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }
}
