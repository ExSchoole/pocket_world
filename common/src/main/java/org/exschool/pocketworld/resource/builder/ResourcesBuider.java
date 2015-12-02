package org.exschool.pocketworld.resource.builder;

import org.exschool.pocketworld.resource.model.Resource;
import org.exschool.pocketworld.resource.model.ResourceType;

public final class ResourcesBuider {
	private ResourceType resourceType;
	private Long playerId;
	private int amount;
	
	
	public static ResourcesBuider builder() {
        return new ResourcesBuider();
    }
	private ResourcesBuider() {}
	
	public ResourcesBuider playerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }
	public ResourcesBuider amount(int amount) {
        this.amount = amount;
        return this;
    }
	
	public ResourcesBuider resourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        return this;
    }
	
	
	
	public Resource build() {
		Resource resource = new Resource();
		resource.setResourceType(resourceType);
		resource.setPlayerId(playerId);
		resource.setAmount(amount);
		
        return resource;
    }
}
