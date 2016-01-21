package org.exschool.pocketworld.city.center.dto;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.exschool.pocketworld.building.BuildingDto;
import org.exschool.pocketworld.building.model.BuildingResourceId;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.model.TimeId;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.model.ResourceType;

public class CityCenterDto {

    private Map<Integer, BuildingDto> buildings;
	private Map<BuildingResourceId, Integer> resourceInfo;
    private Map<TimeId, Integer> timeInfo;
    private ResourceDto resourceDto;
    private String nickName;

    public CityCenterDto() {
    }

    public CityCenterDto(Map<Integer, BuildingDto> buildings, Map<BuildingResourceId, Integer> resourceInfo, 
    					 Map<TimeId, Integer> timeInfo, ResourceDto resourceDto, String nickName) {
    	this.resourceDto = resourceDto;
    	this.resourceInfo = resourceInfo;
    	this.timeInfo = timeInfo;
        this.nickName = nickName;
        this.buildings = buildings;
    }

    public Set<String> getBuildingTypes() {
        Set<String> result = new HashSet<>();
        for(Map.Entry<Integer, BuildingDto> building: buildings.entrySet()) {
            result.add(building.getValue().getType());
        }
        return result;
    }

    
	public int getTimeByBuildingTypeLevel(BuildingType buildingType, int level) {
		return  timeInfo.get(new TimeId(buildingType,level));
	}
	
	public ResourceDto getResourceByBuildingTypeResourceTypeLevel(BuildingType buildingType, int level) {
	  ResourceDto resourceDto = new ResourceDto();
	  resourceDto.setClay(resourceInfo.get(new BuildingResourceId(buildingType, ResourceType.CLAY, level)));
	  resourceDto.setGold(resourceInfo.get(new BuildingResourceId(buildingType, ResourceType.GOLD, level)));
	  resourceDto.setCorn(resourceInfo.get(new BuildingResourceId(buildingType, ResourceType.CORN, level)));
      resourceDto.setTimber(resourceInfo.get(new BuildingResourceId(buildingType, ResourceType.TIMBER, level)));
		
	  return resourceDto;
	}
    
	public Map<BuildingResourceId, Integer> getResourceInfo() {
		return resourceInfo;
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
}
