package org.exschool.pocketworld.building.model;


import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.exschool.pocketworld.resource.model.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Embeddable
public class BuildingResource {
	
	@ElementCollection(targetClass=Resource.class,fetch = FetchType.EAGER)
	@CollectionTable(name="building_resources", joinColumns=@JoinColumn(name="resource_type"))
	private List<Resource> building_resources= new ArrayList<>();;
	private int level;

	private BuildingType buildingType;
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public BuildingType getBildingType() {
		return buildingType;
	}
	public void setBildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}
	public List<Resource> getResources() {
		return building_resources;
	}
	public void setResources(List<Resource> building_resources) {
		this.building_resources = building_resources;
	}

	


	
	
	
}
