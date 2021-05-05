package com.fields.weather.fieldsweather.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.fields.weather.fieldsweather.Model.Field;
import com.fields.weather.fieldsweather.Service.Interface.iFieldService;
import com.fields.weather.fieldsweather.repository.FieldRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FieldService implements iFieldService {

    private final static int HISTORY_DAYS_COUNT = 7;

    //private final WeatherClient weatherClient = new AgroMonitoringClient();
    @Autowired
    private final FieldRepository fieldRepository;

    @Autowired
    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
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
        return fieldRepository.save(field);
    }


    public Field update(Field field, String fieldId) {
        if (fieldRepository.existsById(fieldId)) {
            field.setId(fieldId);
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
