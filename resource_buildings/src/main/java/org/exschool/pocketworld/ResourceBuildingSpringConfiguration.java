package org.exschool.pocketworld;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.exschool.pocketworld.resource.building.service", "org.exschool.pocketworld.city.service"})
public class ResourceBuildingSpringConfiguration {
}
