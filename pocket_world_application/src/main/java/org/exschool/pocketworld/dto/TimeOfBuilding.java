package org.exschool.pocketworld.dto;

public class TimeOfBuilding {
	private int position;
	private int time;
	private String type;
	private String buildingType;
	
	public TimeOfBuilding(int position, int time, String type, String buildingType){
		this.position = position;
		this.time = time;
		this.type = type;
		this.buildingType = buildingType;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

}
