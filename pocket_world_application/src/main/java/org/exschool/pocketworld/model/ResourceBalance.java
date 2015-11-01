package org.exschool.pocketworld.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Embeddable
public class ResourceBalance {
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="player_resources", joinColumns=@JoinColumn(name="player_id"))
	List<Resource> resources = new ArrayList<>();
	
	
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "ResourceBalance [resources=" + resources + "]";
	}
	
	
	
}
