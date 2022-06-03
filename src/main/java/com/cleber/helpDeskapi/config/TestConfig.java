package com.cleber.helpDeskapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cleber.helpDeskapi.service.DbService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DbService dbService;
	
	@Bean
	public void instanciaDb() {
		dbService.instaciaDbService();
	}
}
