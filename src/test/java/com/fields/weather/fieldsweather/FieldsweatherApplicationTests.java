package com.fields.weather.fieldsweather;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fields.weather.fieldsweather.Model.Field;
import com.fields.weather.fieldsweather.Model.WeatherHistory;
import com.fields.weather.fieldsweather.Model.WeatherPolygon;
import com.fields.weather.fieldsweather.Service.FieldService;
import com.fields.weather.fieldsweather.repository.FieldRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

@SpringBootTest
class FieldsweatherApplicationTests {


    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FieldService fieldService;

	@Value("classpath:field.json")
    private Resource resourceFile;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {
        //fieldRepository.deleteAll();
		Field field = objectMapper.treeToValue(objectMapper.readTree(resourceFile.getFile()), Field.class);
		if(fieldService.findById(field.getId())!=null)
		{
			fieldService.save(field);
		}
    }
	
    @Test
    public void testFindAllFields() throws IOException {
        List<Field> fields = fieldRepository.findAll();
        Assertions.assertNotNull(fields);
        Assertions.assertTrue(fields.size() > 0);

    }

    @Test
    public void testFindsByFieldName() throws IOException {
        String name = "Potato field";
        List<Field> fields = fieldService.findByName(name);
		Assertions.assertNotNull(fields);
        Assertions.assertTrue(fields.size()>0);
    }

    @Test
    public void testWeatherHistory() throws IOException {
        String name = "Potato field";
        List<Field> fields = fieldService.findByName(name);
        Assertions.assertNotNull(fields);
        Assertions.assertTrue(fields.size()>0);
        List<WeatherHistory> weatherHistory = fieldService.WeatherHistory(fields.get(0).getId());
		Assertions.assertNotNull(weatherHistory);
        Assertions.assertTrue(weatherHistory.size()>0);
    }
    @Test
    public void testCreatePolygon() throws IOException {
        String id = "a0f63e74-d7ef-4924-acb3-0e960ae9ec98";
        Field field = fieldService.findById(id);
        Assertions.assertNotNull(field);
        WeatherPolygon weatherPolygon = fieldService.CreatePolygon(field);
		Assertions.assertNotNull(weatherPolygon);
        Assertions.assertEquals(weatherPolygon.getName(), field.getName());
    }

}