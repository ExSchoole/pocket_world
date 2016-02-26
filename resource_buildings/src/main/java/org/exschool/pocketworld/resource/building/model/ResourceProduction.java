package org.exschool.pocketworld.resource.building.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.exschool.pocketworld.resource.model.ResourceType;

@Entity
@Table(name = "ResourceProduction")
public class ResourceProduction {
	
	@Id
	private Long playerId;
	
	private int goldDelta;
	
	private int timberDelta;
	
	private int clayDelta;
	
	private int cornDelta;
	
    private Date lastUpdate;
    
	
	public ResourceProduction(){
		
	}
		
	public ResourceProduction(Long playerId, int goldDelta, int timberDelta, int clayDelta, 
						 int cornDelta, Date lastUpdate){
		this.playerId = playerId;
		this.goldDelta = goldDelta;
		this.timberDelta = timberDelta;
		this.clayDelta = clayDelta;
		this.lastUpdate = lastUpdate;
	}
    
	public int getSpeed(ResourceType resourceType){
		switch (resourceType){
			case GOLD :   return this.goldDelta;
			case TIMBER : return this.timberDelta;
			case CLAY :   return this.clayDelta;
			case CORN :   return this.cornDelta;
		}
		
		return 0;
	}
	
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public int getGoldDelta() {
		return goldDelta;
	}

	public void setGoldDelta(int goldDelta) {
		this.goldDelta = goldDelta;
	}

	public int getTimberDelta() {
		return timberDelta;
	}

	public void setTimberDelta(int timberDelta) {
		this.timberDelta = timberDelta;
	}

	public int getClayDelta() {
		return clayDelta;
	}

	public void setClayDelta(int clayDelta) {
		this.clayDelta = clayDelta;
	}

	public int getCornDelta() {
		return cornDelta;
	}

	public void setCornDelta(int cornDelta) {
		this.cornDelta = cornDelta;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clayDelta;
		result = prime * result + cornDelta;
		result = prime * result + goldDelta;
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result + timberDelta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceProduction other = (ResourceProduction) obj;
		if (clayDelta != other.clayDelta)
			return false;
		if (cornDelta != other.cornDelta)
			return false;
		if (goldDelta != other.goldDelta)
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (playerId == null) {
			if (other.playerId != null)
				return false;
		} else if (!playerId.equals(other.playerId))
			return false;
		if (timberDelta != other.timberDelta)
			return false;
		return true;
	}
	
}
