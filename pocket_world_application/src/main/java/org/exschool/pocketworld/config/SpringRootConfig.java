package org.exschool.pocketworld.config;

import org.exschool.pocketworld.BuildQueueSpringConfiguration;
import org.exschool.pocketworld.BuildingSpringConfiguration;
import org.exschool.pocketworld.ChatSpringConfiguration;
import org.exschool.pocketworld.ResourceBuildingSpringConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"org.exschool.pocketworld.dao",
        "org.exschool.pocketworld.player",
        "org.exschool.pocketworld.city.center",
        "org.exschool.pocketworld.city.resources",
        "org.exschool.pocketworld.resource.building.service",
        "org.exschool.pocketworld.buildQueue",
        "org.exschool.pocketworld.chat",
        "org.exschool.pocketworld.init"})
@Import({HibernateConfiguration.class,
        BuildingSpringConfiguration.class,
        ResourceBuildingSpringConfiguration.class,
        BuildQueueSpringConfiguration.class,
        ChatSpringConfiguration.class})
public class SpringRootConfig {

}
