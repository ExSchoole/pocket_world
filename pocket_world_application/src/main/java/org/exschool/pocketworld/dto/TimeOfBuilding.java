package org.exschool.pocketworld.dto;

public class TimeOfBuilding {
	private int position;
	private int time;
	private String type;
	
	public TimeOfBuilding(int position, int time, String type){
		this.position = position;
		this.time = time;
		this.setType(type);
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

}
