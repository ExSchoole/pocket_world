package org.exschool.pocketworld.info.resourcebuilding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.exschool.pocketworld.resource.model.ResourceType;


@Entity
@Table(name = "BuildingResourceInfo")
public class ResourceBuildingInfo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceType resourceType;

    private int level;

    private long time;
    
    private int production;

    private int gold;
    
    private int timber;
    
    private int clay;
    
    private int corn;
    
    public ResourceBuildingInfo(){
    	
    }
    
    public ResourceBuildingInfo(ResourceType resourceType, int level, long time, int production, 
    							int gold, int timber, int clay, int corn){
    	this.resourceType = resourceType;
    	this.level = level;
    	this.time = time;
    	this.production = production;
    	this.gold = gold;
    	this.corn = corn;
    	this.timber = timber;
    	this.clay = clay;
    }

    public ResourceType getResourceType(){
    	return resourceType;
    }
    
	public int getLevel() {
		return level;
	}

	public long getTime() {
		return time;
	}
	
	public int getProduction() {
		return production;
	}

	public int getGold() {
		return gold;
	}

	public int getTimber() {
		return timber;
	}

	public int getClay() {
		return clay;
	}

	public int getCorn() {
		return corn;
	}

}
