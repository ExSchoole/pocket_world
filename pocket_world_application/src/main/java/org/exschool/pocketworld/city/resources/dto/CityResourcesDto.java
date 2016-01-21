package org.exschool.pocketworld.city.resources.dto;


import java.util.Map;

import org.exschool.pocketworld.building.ResourceBuildingDto;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.BuildingResourceId;
import org.exschool.pocketworld.resource.building.model.ProductionId;
import org.exschool.pocketworld.resource.building.model.TimeId;
import org.exschool.pocketworld.resource.model.ResourceType;

public class CityResourcesDto {

	private Map<Integer, ResourceBuildingDto> resourceBuildings;
	private Map<BuildingResourceId, Integer> resourceInfo;
    private Map<TimeId, Integer> timeInfo;
    private Map<ProductionId, Integer> productionInfo;
    public Map<BuildingResourceId, Integer> getResourceInfo() {
		return resourceInfo;
	}

	private ResourceDto resourceDto;
    private String nickName;

    public CityResourcesDto() {
    }

    public CityResourcesDto(Map<Integer, ResourceBuildingDto> buildings, Map<BuildingResourceId, Integer> resourceInfo,
    						Map<TimeId, Integer> timeInfo, 
    						Map<ProductionId, Integer> productionInfo, 
    						ResourceDto resourceDto, String nickName) {
    	this.resourceDto = resourceDto;
        this.nickName = nickName;
        this.resourceBuildings = buildings;
    }

	public int getProductionByBuildingTypeLevel(ResourceType buildingType, int level) {
		return productionInfo.get(new ProductionId(buildingType,level));
	}
	
	public int getTimeByBuildingTypeLevel(ResourceType buildingType, int level) {
		return timeInfo.get(new TimeId(buildingType,level));
	}

	public ResourceDto getResourcesByBuildingTypeLevel(ResourceType buildingType, ResourceType resourceType, 
													   int level) {
	  ResourceDto resourceDto = new ResourceDto();
	  resourceDto.setClay(resourceInfo.get(new BuildingResourceId(buildingType, ResourceType.CLAY, level)));
	  resourceDto.setGold(resourceInfo.get(new BuildingResourceId(buildingType, ResourceType.GOLD, level)));
	  resourceDto.setCorn(resourceInfo.get(new BuildingResourceId(buildingType, ResourceType.CORN, level)));
	  resourceDto.setTimber(resourceInfo.get(new BuildingResourceId(buildingType, ResourceType.TIMBER, level)));
		
	  return resourceDto;
	}
	
	public void setResourceInfo(Map<BuildingResourceId, Integer> resourceInfo) {
		this.resourceInfo = resourceInfo;
	}

	public Map<TimeId, Integer> getTimeInfo() {
		return timeInfo;
	}

	public void setTimeInfo(Map<TimeId, Integer> timeInfo) {
		this.timeInfo = timeInfo;
	}

	public Map<ProductionId, Integer> getProductionInfo() {
		return productionInfo;
	}

	public void setProductionInfo(Map<ProductionId, Integer> productionInfo) {
		this.productionInfo = productionInfo;
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
