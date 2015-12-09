package org.exschool.pocketworld.resource.service;

import org.exschool.pocketworld.resource.model.Resources;

public interface ResourceService {
	void saveResources(Resources resources);
	Resources getResourcesById(Long id);
}
