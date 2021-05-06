package com.fields.weather.fieldsweather.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fields.weather.fieldsweather.Model.Field;
import com.fields.weather.fieldsweather.Model.WeatherHistory;
import com.fields.weather.fieldsweather.Service.FieldService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "fields")
@RestController
@RequestMapping("/fields")
public class FieldController {

	@Autowired
	private FieldService fieldService;

	@ApiOperation(value = "Find Field by ID")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{fieldId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Field get(@PathVariable(value = "fieldId") String fieldid) {
		Field field = fieldService.findById(fieldid);
		return field;
	}

	@ApiOperation(value = "Find Field by Name")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "Name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Field> getbyTitle(@PathVariable(value = "name") String name) {
		List<Field> field = fieldService.findByName(name);
		return field;
	}

	@ApiOperation(value = "Find all Fields")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Field> findAll() {
		List<Field> fields = fieldService.findAll();
		return fields;
	}

	

	@ApiOperation(value = "Add a new Field")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/save",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Field create(@RequestBody Field field) throws Exception {
		Field createdField = fieldService.save(field);
		return createdField;
	}

	@ApiOperation(value = "Add a new Field")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/update/{fieldId}",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Field Update(@RequestBody Field field,@PathVariable(value = "fieldId") String fieldId) throws Exception {
		Field updatedField = fieldService.update(field,fieldId);
		return updatedField;
	}

	@ApiOperation(value = "Delete Field by ID")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{fieldId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "fieldId") String fieldId) {
		fieldService.delete(fieldId);
	}

	@ApiOperation(value = "Get Weather History by Field ID")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{fieldId}/weather", method = RequestMethod.GET)
	public List<WeatherHistory> WeatherHistory(@PathVariable(value = "fieldId") String fieldId) {
		return fieldService.WeatherHistory(fieldId);
	}
}
