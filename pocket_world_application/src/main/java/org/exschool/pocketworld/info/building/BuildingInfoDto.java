package org.exschool.pocketworld.info.building;

import org.exschool.pocketworld.resource.ResourceDto;

public class BuildingInfoDto {

    private String buildingType;

    private int level;

    private long time;

    private ResourceDto resourceDto;
    
    public BuildingInfoDto(BuildingInfo buildingInfo){
    	this.buildingType = buildingInfo.getBuildingType().name().toLowerCase();
    	this.level = buildingInfo.getLevel();
    	this.time = buildingInfo.getTime();
    	this.resourceDto = new ResourceDto(buildingInfo.getGold(),buildingInfo.getTimber(),
    						   buildingInfo.getClay(),buildingInfo.getCorn());	
    }
    
    public String getBuildingType(){
    	return buildingType;
    }

	public long getTime() {
		return time;
	}

	public int getLevel() {
		return level;
	}
	
	public ResourceDto getResourceDto(){
		return resourceDto;
	}
}
