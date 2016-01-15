package org.exschool.pocketworld.info.building;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.exschool.pocketworld.building.model.BuildingType;

@Entity
@Table(name = "BuildingInfo")
public class BuildingInfo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "building_type")
    private BuildingType buildingType;

    private int level;

    private long time;

    private int gold;
    
    private int timber;
    
    private int clay;
    
    private int corn;
    
    public BuildingInfo(){
    	
    }
    
    public BuildingInfo(BuildingType buildingType, int level, long time, int gold, int timber, int clay, int corn){
    	this.buildingType = buildingType;
    	this.level = level;
    	this.time = time;
    	this.gold = gold;
    	this.timber = timber;
    	this.clay = clay;
    	this.corn = corn;
    }
    
    public BuildingType getBuildingType(){
    	return buildingType;
    }

	public long getTime() {
		return time;
	}


	public int getLevel() {
		return level;
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
