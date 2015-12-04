package org.exschool.pocketworld.city.center.service;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;

public interface CityCenterService {
    CityCenterDto cityCenterInfo();
    public boolean addBuilding(int position, Building newBuilding);
}
