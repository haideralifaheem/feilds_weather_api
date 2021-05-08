package com.fields.weather.fieldsweather.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fields.weather.fieldsweather.Model.Field;
import com.fields.weather.fieldsweather.Model.WeatherHistory;
import com.fields.weather.fieldsweather.Model.WeatherPolygon;
import com.fields.weather.fieldsweather.Service.Interface.IAgroMoniteringService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AgroMonitoringService implements IAgroMoniteringService {
    

    private final RestTemplate restTemplate;

    public AgroMonitoringService() {
        this.restTemplate = new RestTemplateBuilder().build();
       
    }
    
    @Override
    public WeatherPolygon createPolygon(String ApiKey,String url,Field field) {
        //String url =  "http://demo8720528.mockable.io/polygon";
        //String url = "http://api.agromonitoring.com/agro/1.0/polygons";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("appid",ApiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String polygonRequest = GetWeatherJson(field);
        HttpEntity<String> requestEntity = new HttpEntity<>(polygonRequest, headers);

        ResponseEntity<WeatherPolygon> responseEntity = restTemplate
                .postForEntity(uriBuilder.toUriString(), requestEntity, WeatherPolygon.class);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Agro Monitoring Create Polygon Service Error!" + responseEntity.toString());
        }
    }

    @Override
    public List<WeatherHistory> weatherHistory(String ApiKey,String url,String polygonId, Date startDay, Date endDay) {
        //String url = "http://demo8720528.mockable.io/weatherHistory";
        //String url = "http://api.agromonitoring.com/agro/1.0/weather/history";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("appid", ApiKey)
                .queryParam("polyid", polygonId)
                .queryParam("start", startDay.getTime()) 
                .queryParam("end", endDay.getTime());

        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return getWeatherList(responseEntity.getBody());
        } else {
            throw new RuntimeException(
                    "Agro Monitoring Historical Weather Data Service Error!" + responseEntity.toString());
        }
    }

    private String GetWeatherJson(Field field)
    {
        Gson gson = new Gson();
        JsonObject jsonString = new JsonObject();
        JsonElement jsonElement = gson.toJsonTree(field.getBoundries().getGeoJson());
        jsonElement.getAsJsonObject().addProperty("name",  field.getName());
        jsonString.addProperty("name", field.getName());
        jsonString.add("geo_json", jsonElement);
        return jsonString.toString();
    }
        
    //Todo Parse the weather data and create a list of Weather
    private List<WeatherHistory> getWeatherList(String data) {
        List<WeatherHistory> weatherHistories = new ArrayList<WeatherHistory>();
        JsonArray convertedData = new Gson().fromJson(data, JsonArray.class);
        for (JsonElement jsonElement : convertedData) {
            JsonObject weatherElement =jsonElement.getAsJsonObject();
            String timestemp = weatherElement.get("dt").getAsString();
            JsonObject weatherMain = weatherElement.get("main").getAsJsonObject();
            Double temperature = weatherMain.get("temp").getAsDouble();
            Integer humidity = weatherMain.get("humidity").getAsInt();
            Double temperatureMax = weatherMain.get("temp_min").getAsDouble();
            Double temperatureMin = weatherMain.get("temp_max").getAsDouble();
            
            WeatherHistory history = new WeatherHistory(timestemp, temperature, humidity, temperatureMax, temperatureMin);
            
            weatherHistories.add(history);
            
        }
        
        return weatherHistories;
        
    }
}
