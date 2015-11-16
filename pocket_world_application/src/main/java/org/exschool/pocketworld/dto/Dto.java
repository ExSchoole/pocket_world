package org.exschool.pocketworld.dto;

import java.util.ArrayList;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.resource.Resource;

public class Dto {

	private ArrayList<Building> buildings;
	private Resource MyRes;
	private String NickName;
	
	public Dto(ArrayList<Building> buildings, Resource MyRes, String NickName){
	     this.setMyRes(MyRes);
	     this.setNickName(NickName);
	   	 this.buildings = buildings;   	 
    }

	
	public String getNickName() {
	    return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}
    
	public Resource getMyRes() {
		return MyRes;
	}
    public void setMyRes(Resource myRes) {
		MyRes = myRes;
	}
    
    public ArrayList<Building> getbuildings() {
		return buildings;
	}
    public void setbuildings(ArrayList<Building> buildings) {
    	this.buildings = buildings;
	}
}
