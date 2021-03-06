package org.exschool.pocketworld.resource;

import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.resource.model.ResourceType;

public class ResourceDto {
	  private int gold;
      private int timber;
      private int clay;
      private int corn;
      
    public ResourceDto(){
    	
    }
      
    public ResourceDto(int gold, int timber, int clay, int corn){
    	this.gold = gold;
    	this.timber = timber;
    	this.clay = clay;
    	this.corn = corn;  	
    }
    
    public ResourceDto(PlayerResources playerResources){
    	this.gold = playerResources.getGoldAmount();
    	this.timber = playerResources.getTimberAmount();
    	this.clay = playerResources.getClayAmount();
    	this.corn = playerResources.getCornAmount();
    }
    
    public void setAmount(ResourceType resourceType, int amount){
    	switch (resourceType){
    		case GOLD : 	this.setGold(amount); break;
    		case TIMBER : 	this.setTimber(amount); break;
    		case CLAY : 	this.setClay(amount); break;
    		case CORN : 	this.setCorn(amount); break;
    	}
    }
    
	public int getGold() {
		return gold;
	}
	public void setGold(int rock) {
		this.gold = rock;
	}
	public int getTimber() {
		return timber;
	}
	public void setTimber(int timber) {
		this.timber = timber;
	}	
	public int getClay() {
		return clay;
	}
	public void setClay(int clay) {
		this.clay = clay;
	}
	public void setCorn(int corn) {
		this.corn = corn;
	}
	public int getCorn() {
		return corn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clay;
		result = prime * result + corn;
		result = prime * result + gold;
		result = prime * result + timber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		
		if (getClass() != obj.getClass())return false;
		ResourceDto other = (ResourceDto) obj;
		if (clay != other.clay)return false;
		if (corn != other.corn)return false;
		if (gold != other.gold)return false;
		if (timber != other.timber)return false;
		return true;
	}

}
