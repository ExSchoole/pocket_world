package org.exschool.pocketworld.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ArrayOfBuildings {

	private Map<Integer,String> buildings;
	
	public ArrayOfBuildings(){
		String TypesOfBuildings[] = {"building_mall", "building_factory", "building_market", "building_shop"}; //Types of buildings from DB
		Random r = new Random();
		buildings = new HashMap<Integer,String>(); //List of buildings which player have
		
		for (int i=0; i<4; i++) 
			buildings.put(i+1, TypesOfBuildings[r.nextInt(4)]); //Fill player's list of buildings
	}
	
	public Map<Integer,String> getArrayOfBuildings(){
		return buildings;
	}
	
}
