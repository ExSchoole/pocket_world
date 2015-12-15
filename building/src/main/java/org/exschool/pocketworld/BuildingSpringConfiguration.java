package org.exschool.pocketworld;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.exschool.pocketworld.building.service", "org.exschool.pocketworld.city.service"})
public class BuildingSpringConfiguration {
}
