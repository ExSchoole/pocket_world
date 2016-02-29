package org.exschool.pocketworld.dto;

import org.exschool.pocketworld.resource.ResourceDto;

public class BuildingInfo {
	private ResourceDto resourceDto;
	private Integer time;
	private Integer level;
    private String type;
    
    public BuildingInfo(){
    	
    }
    
    public BuildingInfo(ResourceDto resourceDto, Integer time, Integer level, String type){
    	this.resourceDto = resourceDto;
    	this.time = time;
    	this.level = level;
    	this.type = type;
    }
    
	public ResourceDto getResourceDto() {
		return resourceDto;
	}
	public void setResourceDto(ResourceDto resourceDto) {
		this.resourceDto = resourceDto;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
