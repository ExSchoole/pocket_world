package org.exschool.pocketworld.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"org.exschool.pocketworld.dao", "org.exschool.pocketworld.service" })
@Import(PersistenceJPAConfig.class)
public class SpringRootConfig {

}
