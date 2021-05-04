package com.fields.weather.fieldsweather.repository;


import java.util.List;

import com.fields.weather.fieldsweather.Model.Field;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FieldRepository extends MongoRepository<Field, String> {
    List<Field> findByName(String name);
}