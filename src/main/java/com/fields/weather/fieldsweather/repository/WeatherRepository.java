package com.fields.weather.fieldsweather.repository;

import com.fields.weather.fieldsweather.Model.WeatherPolygon;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WeatherRepository extends MongoRepository<WeatherPolygon, String> {
    @Query("{'WeatherPolygon' :{'$ref' : 'Field' , '$id' : ?0}}")
    WeatherPolygon findWeatherPolygonByFieldid(String fieldid);
}