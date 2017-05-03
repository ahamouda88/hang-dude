package com.hangdude.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This Spring Configuration class, is used for starting the application using Spring Boot
 * 
 * @author ahamouda
 * 
 */
@Configuration
@ComponentScan(basePackages = { "com.hangdude.service", "com.hangdude.api.controller" })
public class SpringBootConfig extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(new Class[] { SpringBootConfig.class, SecurityConfig.class, WebConfig.class }, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootConfig.class);
	}
}
