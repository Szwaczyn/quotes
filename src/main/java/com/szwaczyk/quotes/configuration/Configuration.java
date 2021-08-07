package com.szwaczyk.quotes.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@org.springframework.context.annotation.Configuration
@EnableSwagger2
public class Configuration {

	@Bean
	public Docket swaggerConfiguration() {

	    return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .paths(PathSelectors.ant("/quote/*"))
	                .apis(RequestHandlerSelectors.basePackage("com.szwaczyk.quotes.controller.rest"))
	                .build()
	                .apiInfo(this.getApiInfo());
	}

	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	            "Quotes api", 
	            null, 
	            "1.0.0", 
	            "Free", 
	            new Contact("Michal Szwaczyk", "https://github.com/Szwaczyn", "michal.szwaczyk@outlook.com"),
	            "License", 
	            "https://github.com/Szwaczyn", 
	            Collections.emptyList());
	}
}
