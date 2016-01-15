package org.exschool.pocketworld.info.resourcebuilding;

import org.exschool.pocketworld.resource.ResourceDto;

public class ResourceBuildingInfoDto {
	
	private String resourceType;

    private int level;

    private long time;
    
    private int production;

    private ResourceDto resourceDto;
    
    public ResourceBuildingInfoDto(ResourceBuildingInfo resourceBuildingInfo){
    	this.resourceType = resourceBuildingInfo.getResourceType().name().toLowerCase();
    	this.level = resourceBuildingInfo.getLevel();
    	this.time = resourceBuildingInfo.getTime();
    	this.production = resourceBuildingInfo.getProduction();
    	this.resourceDto = new ResourceDto(resourceBuildingInfo.getGold(),resourceBuildingInfo.getTimber(),
    						   resourceBuildingInfo.getClay(),resourceBuildingInfo.getCorn());
    }
    
    public String getResourceType(){
    	return resourceType;
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
	
	public int getProduction() {
		return production;
	}
}
