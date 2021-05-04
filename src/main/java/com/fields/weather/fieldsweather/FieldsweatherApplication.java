package com.fields.weather.fieldsweather;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableSwagger
@ComponentScan(basePackages = { "com.fields.weather.fieldsweather" })
@SpringBootApplication
public class FieldsweatherApplication {

	@Autowired
	private SpringSwaggerConfig swaggerConfig;
	public static void main(String[] args) {
		SpringApplication.run(FieldsweatherApplication.class, args);
	}
	
	@Bean
	public SwaggerSpringMvcPlugin groupOnePlugin() {
		return new SwaggerSpringMvcPlugin(swaggerConfig).includePatterns("/Field.*?")
				.swaggerGroup("admin");
	}
}
