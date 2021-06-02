package com.data.inserter.service.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableBatchProcessing
@EnableScheduling
@EnableAsync
public class DBConfiguration {
	
	  @Autowired 
	  public DataSource dataSource;
	
	@Bean
	public DataSource dataSource() {
		
		final DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/emailaddressbatch");
		dataSource.setUsername("root");
		dataSource.setPassword("Happy@700");
		return dataSource;
	}

	}
	
	
	
	
	
	
	
	

