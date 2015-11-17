package org.exschool.pocketworld.dto;

import java.util.Map;

import org.exschool.pocketworld.resource.Resource;

public class Dto {

	private Map<Integer,String> buildings;
	private Resource resources;
	private String nickName;
	
	public Dto(Map<Integer,String> buildings, Resource MyRes, String NickName){
	     this.setMyRes(MyRes);
	     this.setNickName(NickName);
	   	 this.buildings = buildings;   	 
    }

	
	public String getNickName() {
	    return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    
	public Resource getResources() {
		return resources;
	}
    public void setMyRes(Resource res) {
		resources = res;
	}
    
    public  Map<Integer,String> getbuildings() {
		return buildings;
	}
    public void setbuildings(Map<Integer,String> buildings) {
    	this.buildings = buildings;
	}
}
