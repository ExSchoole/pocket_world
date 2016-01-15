package org.exschool.pocketworld.building;

import org.exschool.pocketworld.building.model.Building;

public class BuildingDto {

    private String type;
    private int level;
    private int position;

    public BuildingDto(){
    	
    }
    
    public BuildingDto(String type, int level,int position) {
        this.type = type;
        this.level = level;
        this.position = position;
    }
    
    public BuildingDto(Building building){
    	this.type = building.getBuildingType().name().toLowerCase();
    	this.level = building.getLevel();
    	this.position = building.getPosition();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
    
    
}
