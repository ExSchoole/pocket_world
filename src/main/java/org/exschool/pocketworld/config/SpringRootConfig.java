package org.exschool.pocketworld.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.exschool.pocketworld.dao", "org.exschool.pocketworld.service" })
public class SpringRootConfig {

}
