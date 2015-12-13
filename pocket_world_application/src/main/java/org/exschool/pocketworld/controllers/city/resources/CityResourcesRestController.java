package org.exschool.pocketworld.controllers.city.resources;

import org.exschool.pocketworld.dto.PositionOfBulding;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/city/resources")
public class CityResourcesRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityResourcesRestController.class);

    @Autowired
    private ResourceBuildingService resourceBuildingService;

    @RequestMapping(value="/types",method=RequestMethod.GET)
    @ResponseBody
    public List<String> getResourceBuildingsTypesList(){
        LOGGER.info("getResourceBuildingsList is invoked");
        return ResourceType.asListLowerCase();
    }

    @RequestMapping(value="/buildings", method=RequestMethod.POST)
    public ResponseEntity<Void> createResourceBuilding(@RequestBody PositionOfBulding positionOfBulding){
        final Integer INITIAL_BUILDING_LEVEL = 0;
        LOGGER.info("RequestBody:" + positionOfBulding);
        ResourceType resourceType = ResourceType.valueOf(positionOfBulding.getType().toUpperCase());
        ResourceBuilding resourceBuilding = ResourceBuildingBuilder.builder()
                               .buildingType(resourceType)
                               .level(INITIAL_BUILDING_LEVEL)
                               .position(positionOfBulding.getPosition())
                               .build();
        resourceBuildingService.save(resourceBuilding);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
