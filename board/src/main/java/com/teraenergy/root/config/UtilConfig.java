package com.teraenergy.root.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teraenergy.root.controller.HomeController;

@Configuration
public class UtilConfig {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		// deserialize 하는 동안 매핑 되지 않은것이 있어도 무시하고 진행
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        // ObjectMapper 에서 datetime 변환할때 jsr310 적용을 위해 JavaTimeModule 을 등록해야 함
//        objectMapper.registerModule(new JavaTimeModule());
        
        logger.info("bean register ObjectMapper .. ");
		return objectMapper; 
	}
	
	
}
