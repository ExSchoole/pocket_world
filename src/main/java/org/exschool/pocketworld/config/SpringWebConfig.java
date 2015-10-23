package org.exschool.pocketworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan({ "org.exschool.pocketworld.controllers" })
public class SpringWebConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
                        .addResourceLocations("/resources/");
	}
	
	@Bean
	public VelocityConfig velocityConfig() {
	    VelocityConfigurer cfg = new VelocityConfigurer();
	    cfg.setResourceLoaderPath("/WEB-INF/views/");
	    return cfg;
	}
	
	@Bean
	public VelocityViewResolver viewResolver() {
		VelocityViewResolver viewResolver = new VelocityViewResolver();
		viewResolver.setViewClass(VelocityToolboxView.class);
		viewResolver.setSuffix(".vm");
		return viewResolver;
	}
	
}
