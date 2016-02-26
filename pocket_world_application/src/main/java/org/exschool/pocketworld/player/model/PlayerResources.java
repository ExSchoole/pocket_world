package org.exschool.pocketworld.player.model;

import javax.persistence.Embeddable;

import org.exschool.pocketworld.resource.model.ResourceType;

/**
 * Created by skandy on 26.11.15.
 */
@Embeddable
public class PlayerResources {
    private Integer goldAmount;
    private Integer timberAmount;
    private Integer clayAmount;
    private Integer cornAmount;

    public PlayerResources() {
    }

    public PlayerResources(int goldAmount, int timberAmount, int clayAmount, int cornAmount) {
        this.goldAmount = goldAmount;
        this.timberAmount = timberAmount;
        this.clayAmount = clayAmount;
        this.cornAmount = cornAmount;
    }

    public int getAmount(ResourceType resourceType){
    	switch (resourceType){
    		case GOLD : 	return this.goldAmount;
    		case TIMBER : 	return this.timberAmount;
    		case CLAY : 	return this.clayAmount;
    		case CORN :	 	return this.cornAmount;
    	}
    	
    	return 0;
    }
    
    public void setAmount(ResourceType resourceType, int amount){
    	switch (resourceType){
    		case GOLD : 	this.setGoldAmount(amount); break;
    		case TIMBER : 	this.setTimberAmount(amount); break;
    		case CLAY : 	this.setClayAmount(amount); break;
    		case CORN : 	this.setCornAmount(amount); break;
    	}
    }
    
    public int getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
    }

    public int getTimberAmount() {
        return timberAmount;
    }

    public void setTimberAmount(int timberAmount) {
        this.timberAmount = timberAmount;
    }

    public int getClayAmount() {
        return clayAmount;
    }

    public void setClayAmount(int clayAmount) {
        this.clayAmount = clayAmount;
    }

    public int getCornAmount() {
        return cornAmount;
    }

    public void setCornAmount(int cornAmount) {
        this.cornAmount = cornAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerResources that = (PlayerResources) o;

        if (clayAmount != null ? !clayAmount.equals(that.clayAmount) : that.clayAmount != null) return false;
        if (cornAmount != null ? !cornAmount.equals(that.cornAmount) : that.cornAmount != null) return false;
        if (goldAmount != null ? !goldAmount.equals(that.goldAmount) : that.goldAmount != null) return false;
        if (timberAmount != null ? !timberAmount.equals(that.timberAmount) : that.timberAmount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goldAmount != null ? goldAmount.hashCode() : 0;
        result = 31 * result + (timberAmount != null ? timberAmount.hashCode() : 0);
        result = 31 * result + (clayAmount != null ? clayAmount.hashCode() : 0);
        result = 31 * result + (cornAmount != null ? cornAmount.hashCode() : 0);
        return result;
    }
}
