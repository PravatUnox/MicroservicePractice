package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigService {
	
	@Bean
	public RestTemplate getRestTemplte() {
		return new RestTemplate();
	}

}
