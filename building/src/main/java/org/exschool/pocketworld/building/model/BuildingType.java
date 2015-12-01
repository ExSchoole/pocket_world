package org.exschool.pocketworld.building.model;

import java.util.ArrayList;
import java.util.List;

public enum BuildingType {
	STORAGE,
	GILOTHOME,
	TOWNHALL,
	MARKETPLACE,
	POOL,
	PLANT,
	FARM,
	BARN,
	SCHOOL,
	MALL;

	public static List<String> asList(){
		List<String> result = new ArrayList<>();
		for(BuildingType buildingType : BuildingType.values()){
			result.add(buildingType.toString());
		}
		return result;
	}

	public static List<String> asListLowerCase(){
		List<String> result = new ArrayList<>();
		for(BuildingType buildingType : BuildingType.values()){
			result.add(buildingType.toString().toLowerCase());
		}
		return result;
	}

}
