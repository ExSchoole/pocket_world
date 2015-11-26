package org.exschool.pocketworld.city.center.service;

import org.exschool.pocketworld.city.center.dto.CityCenterDto;

import java.util.List;

public interface CityCenterService {
    CityCenterDto cityCenterInfo();
    List<String> getBuildingTypesAvailableToBuild();
}
