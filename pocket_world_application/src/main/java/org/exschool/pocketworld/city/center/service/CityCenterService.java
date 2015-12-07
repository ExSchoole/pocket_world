package org.exschool.pocketworld.city.center.service;

import org.exschool.pocketworld.city.center.dto.CityCenterDto;

import java.util.Collection;
import java.util.Set;

public interface CityCenterService {
    CityCenterDto cityCenterInfo();

    Collection<String> availableForBuildBuildingTypes(Set<String> builtBuildingTypes);
}
