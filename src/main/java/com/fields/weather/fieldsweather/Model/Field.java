package com.fields.weather.fieldsweather.Model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FieldTable")
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @NonNull
    private String name;
    
    private Date created;
    private String updated;
    private String countryCode;

    private Boundary boundaries;

    /*@NonNull
    @DBRef
    private List<Actor> actors;
    @NonNull*/
    @JsonIgnore
    @DBRef
    public WeatherPolygon agroPolygon;
   

    public Field() {
    }

    public Field(String name, Date created, String updated, String countryCode,Boundary boundaries) {
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.countryCode = countryCode;
        this.boundaries =boundaries;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setDescription(String countryCode) {
        this.countryCode = countryCode;
    }

   
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Boundary getBoundries() {
        return boundaries;
    }

    public void setBoundaries(Boundary boundaries) {
        this.boundaries = boundaries;
    }

}