package com.fields.weather.fieldsweather.Model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "WeatherHistory")
public class WeatherHistory implements Serializable{
    private static final long serialVersionUID = 1L;
    @JsonSetter("dt")
    private String timestamp;
    @JsonSetter("temp")
    private Double temperature;
    @JsonSetter("humidity")
    private Integer humidity;
    @JsonSetter("temp_max")
    private Double temperatureMax;
    @JsonSetter("temp_min")
    private Double temperatureMin;
    
    public WeatherHistory() {
    }

    public WeatherHistory(String timestamp, Double temperature, Integer humidity, Double temperatureMax, Double temperatureMin) {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

}
