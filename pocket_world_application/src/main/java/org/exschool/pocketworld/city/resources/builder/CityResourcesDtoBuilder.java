package org.exschool.pocketworld.city.resources.builder;

import org.exschool.pocketworld.building.ResourceBuildingDto;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.BuildingResourceId;
import org.exschool.pocketworld.resource.building.model.ProductionId;
import org.exschool.pocketworld.resource.building.model.TimeId;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CityResourcesDtoBuilder {

    private ResourceDto resourceDto;
    private Map<Integer, ResourceBuildingDto> resourceBuildings = new HashMap<>();
    private Map<BuildingResourceId, Integer> resourceInfo;
    private Map<TimeId, Integer> timeInfo;
    private Map<ProductionId, Integer> productionInfo;
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

    public CityResourcesDtoBuilder resourceBuildings(Map<Integer, ResourceBuildingDto> resourceBuildings) {
        this.resourceBuildings.putAll(resourceBuildings);
        return this;
    }

    public CityResourcesDtoBuilder resourceBuildings(Collection<ResourceBuildingDto> resourceBuildingDtos) {
        for (ResourceBuildingDto resourceBuilding : resourceBuildingDtos) {
            resourceBuildings.put(resourceBuilding.getPosition(), resourceBuilding);
        }
        return this;
    }

    public CityResourcesDtoBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    public CityResourcesDtoBuilder resourceInfo(Map<BuildingResourceId, Integer> resourceInfo){
    	this.resourceInfo = resourceInfo;
    	return this;
    }
    
    public CityResourcesDtoBuilder timeInfo(Map<TimeId, Integer> timeInfo){
    	this.timeInfo = timeInfo;
    	return this;
    }
    
    public CityResourcesDtoBuilder productionInfo(Map<ProductionId, Integer> productionInfo){
    	this.productionInfo = productionInfo;
    	return this;
    }

    public CityResourcesDto build() {
        CityResourcesDto cityResourcesDto = new CityResourcesDto();
        cityResourcesDto.setNickName(nickname);
        cityResourcesDto.setResourceDto(resourceDto);
        cityResourcesDto.setResourceBuildings(resourceBuildings);
        cityResourcesDto.setProductionInfo(productionInfo);
        cityResourcesDto.setResourceInfo(resourceInfo);
        cityResourcesDto.setTimeInfo(timeInfo);
        return cityResourcesDto;
    }
}
