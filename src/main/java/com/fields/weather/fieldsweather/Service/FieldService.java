package com.fields.weather.fieldsweather.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.fields.weather.fieldsweather.Model.Field;
import com.fields.weather.fieldsweather.Model.WeatherPolygon;
import com.fields.weather.fieldsweather.Service.Interface.iFieldService;
import com.fields.weather.fieldsweather.repository.FieldRepository;
import com.fields.weather.fieldsweather.repository.WeatherRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class FieldService implements iFieldService {


    private final AgroMonitoringService weatherService = new AgroMonitoringService();
    @Autowired
    private final FieldRepository fieldRepository;

    @Autowired
    private final WeatherRepository weatherRepository;

    @Autowired
    public FieldService(FieldRepository fieldRepository,WeatherRepository weatherRepository) {
        this.fieldRepository = fieldRepository;
        this.weatherRepository = weatherRepository;
    }


    public List<Field> findAll() {
        List<Field> fields = new ArrayList<Field>();
        fieldRepository.findAll().forEach(fields::add);
        return fields;
    }


    public Field findById(String fieldId) {
        return fieldRepository.findById(fieldId)
        .orElseThrow(() -> new EntityNotFoundException("Field not found: " + fieldId.toString()));
    }


    public List<Field> findByName(String name) {
        return fieldRepository.findByName(name);
    }


    public Field save(Field field) {
        WeatherPolygon createdPolygon = weatherService.createPolygon(field);
        createdPolygon.field = field;
        weatherRepository.save(createdPolygon);
        return fieldRepository.save(field);
    }


    public Field update(Field field, String fieldId) {
        if (fieldRepository.existsById(fieldId)) {
            WeatherPolygon exitingPolygon = weatherRepository.findWeatherPolygonByFieldid(fieldId);
            weatherRepository.delete(exitingPolygon);
            WeatherPolygon newPolygon = weatherService.createPolygon(field);
            newPolygon.field=field;
            weatherRepository.save(newPolygon);
            fieldRepository.save(field);
            return field;
        } else {
            throw new EntityNotFoundException("Field not found: " + fieldId.toString());
        }
    }

    public void delete(String fieldId) {
        if (fieldRepository.existsById(fieldId)) {
            fieldRepository.deleteById(fieldId);
        } else {
            throw new EntityNotFoundException("Field not found: " + fieldId.toString());
        }
    }

}
