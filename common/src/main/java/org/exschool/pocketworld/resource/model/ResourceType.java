package org.exschool.pocketworld.resource.model;

import java.util.ArrayList;
import java.util.List;

public enum ResourceType {
	GOLD,
	TIMBER,
	CLAY,
	CORN;

	public static List<String> asListLowerCase(){
		List<String> list = new ArrayList<>();
		for(ResourceType resourceType : ResourceType.values()){
			list.add(resourceType.toString().toLowerCase());
		};
	return list;
	}
}
