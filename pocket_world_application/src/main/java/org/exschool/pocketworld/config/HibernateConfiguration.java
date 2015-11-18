package org.exschool.pocketworld.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan("org.exschool.pocketworld.config")
@PropertySource("file:${user.home}/application.properties")
public class HibernateConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"org.exschool.pocketworld.player.model",
				});
		sessionFactory.setHibernateProperties(additionalProperties());
		return sessionFactory;
	}
	
	 @Bean
	 public DriverManagerDataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
	      dataSource.setUrl(env.getRequiredProperty("db.url"));
	      dataSource.setUsername(env.getRequiredProperty("db.userName"));
	      dataSource.setPassword(env.getRequiredProperty("db.password"));
	      return dataSource;
	 }
	 
	 @Bean
	 @Autowired
	 public HibernateTransactionManager transactionManager(SessionFactory s) {
	       HibernateTransactionManager txManager = new HibernateTransactionManager();
	       txManager.setSessionFactory(s);
	       return txManager;
	 }
	 
	 private Properties additionalProperties() {
	      Properties properties = new Properties();
	      properties.setProperty("hibernate.hbm2ddl.auto", "create");
	      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	      properties.setProperty("hibernate.format_sql", "true");
	      properties.setProperty("hibernate.show_sql", "true");
	      return properties;
	 }
	
	
}
