package com.fields.weather.fieldsweather.Service;

import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import com.fields.weather.fieldsweather.Model.Field;
import com.fields.weather.fieldsweather.Model.WeatherDetail;
import com.fields.weather.fieldsweather.Model.WeatherPolygon;
import com.fields.weather.fieldsweather.Service.Interface.IAgroMoniteringService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AgroMonitoringService implements IAgroMoniteringService {
    private final static String PERSONAL_API_KEY = "7211e78911af030e76b4824fa88bda70";

    private final RestTemplate restTemplate;

    public AgroMonitoringService() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    @Override
    public WeatherPolygon createPolygon(Field field) {
        String url = "http://api.agromonitoring.com/agro/1.0/polygons";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("appid", PERSONAL_API_KEY);

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
    public List<WeatherDetail> weatherHistory(String polygonId, String startDay, String endDay) {
        String url = "http://api.agromonitoring.com/agro/1.0/weather/history";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("appid", PERSONAL_API_KEY)
                .queryParam("polyid", polygonId)
                .queryParam("start", startDay)
                .queryParam("end", endDay);

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
    private List<WeatherDetail> getWeatherList(String data) {
        return new ArrayList<>();
    }
}
