package org.exschool.pocketworld.city.resources.dto;


import java.util.Map;

import org.exschool.pocketworld.building.ResourceBuildingDto;
import org.exschool.pocketworld.info.resourcebuilding.ResourceBuildingInfoDto;
import org.exschool.pocketworld.resource.ResourceDto;

public class CityResourcesDto {

	private Map<String, ResourceBuildingInfoDto> resourceBuildingsInfo;
	private Map<Integer, ResourceBuildingDto> resourceBuildings;
    private ResourceDto resourceDto;
    private String nickName;

    public CityResourcesDto() {
    }

    public CityResourcesDto(Map<String, ResourceBuildingInfoDto> resourceBuildingsInfo, Map<Integer, 
    					ResourceBuildingDto> buildings, ResourceDto resourceDto, String nickName) {
        this.setResourceBuildingsInfo(resourceBuildingsInfo);
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

	public Map<String, ResourceBuildingInfoDto> getResourceBuildingsInfo() {
		return resourceBuildingsInfo;
	}

	public void setResourceBuildingsInfo(Map<String, ResourceBuildingInfoDto> resourceBuildingsInfo) {
		this.resourceBuildingsInfo = resourceBuildingsInfo;
	}
	
	public ResourceDto getResourcesInfoByTypeLevel(String typeOfBuilding, int level){
		if (resourceBuildingsInfo.containsKey(typeOfBuilding))
			return resourceBuildingsInfo.get(typeOfBuilding).getResourceDto();
		else
			return null;
	}
	
	public long getTimeByTypeLevel(String typeOfBuilding, int level){
		if (resourceBuildingsInfo.containsKey(typeOfBuilding))
			return resourceBuildingsInfo.get(typeOfBuilding).getTime();
		else
			return -1;
	}
	
	public int getProductionByTypeLevel(String typeOfBuilding, int level){
		if (resourceBuildingsInfo.containsKey(typeOfBuilding))
			return resourceBuildingsInfo.get(typeOfBuilding).getProduction();
		else
			return -1;
	}
    
}
