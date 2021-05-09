package com.fields.weather.fieldsweather.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.fields.weather.fieldsweather.Model.Field;
import com.fields.weather.fieldsweather.Model.WeatherHistory;
import com.fields.weather.fieldsweather.Model.WeatherPolygon;
import com.fields.weather.fieldsweather.Service.Interface.iFieldService;
import com.fields.weather.fieldsweather.repository.FieldRepository;
import com.fields.weather.fieldsweather.repository.WeatherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class FieldService implements iFieldService {

    @Value("${api.key}")
    private String agroApiKey;
    @Value("${api.polygon.url}")
    private String apiCreatePolygonUrl;
    @Value("${api.weather.url}")
    private String apiWeatherHistoryUrl;
    private AgroMonitoringService weatherService = new AgroMonitoringService();
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
        if (fieldRepository.existsById(field.getId())) {
            return update(field, field.getId());
        }
        else{
            WeatherPolygon createdPolygon = weatherService.createPolygon(agroApiKey,apiCreatePolygonUrl,field);
            weatherRepository.save(createdPolygon);
            field.agroPolygon = createdPolygon  ;
            return fieldRepository.save(field);
        }
        
    }

    public WeatherPolygon CreatePolygon(Field field) {
            WeatherPolygon createdPolygon = weatherService.createPolygon(agroApiKey,apiCreatePolygonUrl,field);
            return createdPolygon ;
    }


    public Field update(Field field, String fieldId) {
        if (fieldRepository.existsById(fieldId)) {
            Field oldfield=fieldRepository.findById(fieldId)
            .orElseThrow(() -> new EntityNotFoundException("Field not found: " + fieldId.toString()));
            if(oldfield.agroPolygon!=null)
            {
                weatherRepository.delete(oldfield.agroPolygon);
            }
            WeatherPolygon newPolygon = weatherService.createPolygon(agroApiKey,apiCreatePolygonUrl,field);
            field.agroPolygon=newPolygon;
            weatherRepository.save(newPolygon);
            fieldRepository.save(field);
            return field;
        } else {
            throw new EntityNotFoundException("Field not found: " + fieldId.toString());
        }
    }

    public void delete(String fieldId) {
        if (fieldRepository.existsById(fieldId)) {
            Field field =findById(fieldId);
            weatherRepository.delete(field.agroPolygon);
            fieldRepository.deleteById(fieldId);
        } else {
            throw new EntityNotFoundException("Field not found: " + fieldId.toString());
        }
    }

    public List<WeatherHistory> WeatherHistory(String fieldId) {
        Field field=fieldRepository.findById(fieldId).get();
        if (field!=null) {
            Date currentDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DAY_OF_MONTH, -7);
            Date startDate = c.getTime();
            return weatherService.weatherHistory(agroApiKey,apiWeatherHistoryUrl,field.agroPolygon.getId(),startDate,currentDate);
        } else {
            throw new EntityNotFoundException("Field not found: " + fieldId.toString());
        }
    }

}
