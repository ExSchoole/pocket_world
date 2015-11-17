package org.exschool.pocketworld.building;

public class Building {
	
	private int position;
	private String type;
	
	public Building(int position, String TypeOfBuilding){
		this.position = position;
		type = TypeOfBuilding;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int pos) {
		this.position = pos;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
