package org.exschool.pocketworld.building.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Embeddable
public class CityBuildings {
	
	//@ElementCollection(targetClass=Building.class,fetch = FetchType.EAGER)
	
	@Embedded
	//@CollectionTable(name="city_buildings", joinColumns=@JoinColumn(name="player_id"))
	List<Building> city_buildings= new ArrayList<>();
	
	public List<Building> getBuildings() {
		return city_buildings;
	}
	
	public void setBuildings(List<Building> buildings) {
		this.city_buildings = buildings;
	}

}
