package com.cleber.helpDeskapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cleber.helpDeskapi.service.DbService;

@Configuration
@Profile("test")
public class DevConfig {

	@Autowired
	private DbService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	@Bean
	public boolean instanciaDb() {
		
		if(value.equals("create")) {
		this.dbService.instaciaDbService();
		}
		return false;
	}
}
