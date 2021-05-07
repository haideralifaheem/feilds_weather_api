package com.fields.weather.fieldsweather;



import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class MongoConfig {
    @Value("${spring.data.mongodb.uri}")
    private String connecString;
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(connecString);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), "Fields");
    }
}