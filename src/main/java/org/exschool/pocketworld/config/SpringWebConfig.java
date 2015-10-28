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
	
	
	
	/**
	 * 
	 * 
	 * 
	 * <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.2.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd">

	<mvc:annotation-driven/>
	<context:component-scan base-package="org.exschool.pocketworld.controllers, org.exschool.pocketworld.dao, org.exschool.pocketworld.service"></context:component-scan>	

	<bean id="velocityConfig" class="org.springframework.web.servlet.view.velocity.VelocityConfigurer">
    	<property name="resourceLoaderPath" value="/WEB-INF/views/"/>
	</bean>
	
	<bean id="viewResolver" class="org.springframework.web.servlet.view.velocity.VelocityViewResolver">
    	<property name="cache" value="true"/>
   		<property name="prefix" value=""/>
    	<property name="suffix" value=".vm"/>
	</bean>
	
	<!--  this part should be in jpaContext.xml --> 
	
	<context:annotation-config/>
	<bean class="org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor" />
	<bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="persistenceUnitName" value="punit"/>
		<property name="dataSource" ref="dataSource"/>
		<property name="packagesToScan" value="org.exschool.pocketworld.model"/>
		<property name="jpaVendorAdapter">
			<bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
				<property name="showSql" value="true"></property>
			</bean>
		</property>
		<property name="jpaPropertyMap">
			<map>
				<entry key="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect" />
				<entry key="hibernate.hbm2ddl.auto" value="create" />
				<entry key="hibernate.format_sql" value="true" />
			</map>
		</property>
	</bean>
	
	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="org.postgresql.Driver" />
		<property name="url" value="jdbc:postgresql://178.62.228.69:5432/testDB" />
		<property name="username" value="postgres" />
		<property name="password" value="practice123" />
	</bean>
	
	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory" />
	</bean>
	
	<tx:annotation-driven transaction-manager="transactionManager"/>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
}
