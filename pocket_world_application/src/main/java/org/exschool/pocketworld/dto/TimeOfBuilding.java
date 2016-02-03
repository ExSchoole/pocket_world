package org.exschool.pocketworld.dto;

public class TimeOfBuilding {
	private int position;
	private int time;
	
	public TimeOfBuilding(int position, int time){
		this.setPosition(position);
		this.time = time;
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

}
