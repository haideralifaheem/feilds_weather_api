package com.fields.weather.fieldsweather.Service.Interface;

import java.util.List;

import com.fields.weather.fieldsweather.Model.Field;
import com.fields.weather.fieldsweather.Model.WeatherDetail;
import com.fields.weather.fieldsweather.Model.WeatherPolygon;

public interface IAgroMoniteringService {
    WeatherPolygon createPolygon(Field field);

    List<WeatherDetail> weatherHistory(String polygonId, String startDay, String endDay);
}
