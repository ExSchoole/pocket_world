package org.exschool.pocketworld.city.center.service;

import org.exschool.pocketworld.city.center.dto.CityCenterDto;

import java.util.List;
import java.util.Set;

public interface CityCenterService {
    CityCenterDto cityCenterInfo();
    List<String> availableForBuildBuildingTypes(Set<String> builtBuildingTypes);
}
