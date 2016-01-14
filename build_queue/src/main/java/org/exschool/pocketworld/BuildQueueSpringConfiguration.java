package org.exschool.pocketworld;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by skandy on 14.01.16.
 */
@Configuration
@ComponentScan({"org.exschool.pocketworld.buildQueue.service", "org.exschool.pocketworld.city.service"})
public class BuildQueueSpringConfiguration {
}
