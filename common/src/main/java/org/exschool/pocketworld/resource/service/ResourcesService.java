package org.exschool.pocketworld.resource.service;

import java.util.List;

import org.exschool.pocketworld.resource.model.Resource;

public interface ResourcesService {
	Resource saveResources(Resource resources);
	List<Resource> getAllResourcesByUserId(Long id);
	Resource getResourcesByUserId(Long id);
	void saveAllResources(List<Resource> resources);
}
