package org.exschool.pocketworld.city.resources.dto;


import org.exschool.pocketworld.building.ResourceBuildingDto;
import org.exschool.pocketworld.resource.ResourceDto;

import java.util.Map;

public class CityResourcesDto {

    private Map<Integer, ResourceBuildingDto> resourceBuildings;
    private ResourceDto resourceDto;
    private String nickName;

    public CityResourcesDto() {
    }

    public CityResourcesDto(Map<Integer, ResourceBuildingDto> buildings, ResourceDto resourceDto, String nickName) {
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

    public Map<Integer, ResourceBuildingDto> getResourceBuildings() {
        return resourceBuildings;
    }

    public void setResourceBuildings(Map<Integer, ResourceBuildingDto> resourceBuildings) {
        this.resourceBuildings = resourceBuildings;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }
    
}
