package com.fields.weather.fieldsweather.Service.Interface;

import java.util.List;


import com.fields.weather.fieldsweather.Model.Field;

public interface iFieldService {
    List<Field> findAll();

    Field findById(String fieldId);
    
    List<Field> findByName(String name);
    
    Field save(Field field);

    Field update(Field field, String fieldId);

    void delete(String fieldId);
}
