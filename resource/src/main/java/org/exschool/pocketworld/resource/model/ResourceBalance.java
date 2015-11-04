package org.exschool.pocketworld.resource.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import org.exschool.pocketworld.building.model.Building;

@Embeddable
public class ResourceBalance {
	
	
	@ElementCollection(targetClass=Resource.class,fetch = FetchType.EAGER)
	@CollectionTable(name="player_resources", joinColumns=@JoinColumn(name="player_id"))
	List<Resource> player_resources = new ArrayList<>();
	
	
	public List<Resource> getResources() {
		return player_resources;
	}

	public void setResources(List<Resource> player_resources) {
		this.player_resources = player_resources;
	}

	@Override
	public String toString() {
		return "ResourceBalance [resources=" + player_resources + "]";
	}
	
	
	
}
