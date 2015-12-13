package org.exschool.pocketworld.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"org.exschool.pocketworld.dao", "org.exschool.pocketworld.player",
                "org.exschool.pocketworld.city", "org.exschool.pocketworld.resource.building.service"})
@Import(HibernateConfiguration.class)
public class SpringRootConfig {

}
