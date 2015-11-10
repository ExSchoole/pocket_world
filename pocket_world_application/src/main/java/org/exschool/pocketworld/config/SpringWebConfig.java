package org.exschool.pocketworld.config;

import com.sun.corba.se.spi.resolver.LocalResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
		Map<String, Object> propertiesMap = new HashMap<>();
		propertiesMap.put("input.encoding", "utf-8");
		propertiesMap.put("output.encoding", "utf-8");
	    VelocityConfigurer cfg = new VelocityConfigurer();
		cfg.setVelocityPropertiesMap(propertiesMap);
	    cfg.setResourceLoaderPath("/WEB-INF/views/");
	    return cfg;
	}
	
	@Bean
	public VelocityViewResolver viewResolver() {
		VelocityViewResolver viewResolver = new VelocityViewResolver();
		viewResolver.setViewClass(VelocityToolboxView.class);
		viewResolver.setSuffix(".vm");
		viewResolver.setContentType("text/html; charset=utf-8");
		return viewResolver;
	}

	//   - relates to i18n -
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource;
		messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localResolver(){
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}

	@Bean
	public HandlerInterceptor localeChangeInterceptor(){
		LocaleChangeInterceptor localeChangeInterceptor;
		localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
}
