package com.fields.weather.fieldsweather.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Field")
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String Id;
    @NonNull
    private String name;
    
    private Date created;
    private Date updated;
    private String countryCode;

    private Boundary Boundries;

    /*@NonNull
    @DBRef
    private List<Actor> actors;
    @NonNull
    @DBRef
    private List<Director> directors;*/
   

    public Field() {
    }

    public Field(String name, Date created, Date updated, String countryCode,Boundary boundry) {
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.countryCode = countryCode;
        this.Boundries =boundry;
        
    }

    public String getfieldId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
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

   
    public Date getCreatedDate() {
        return created;
    }

    public void setCreatedDate(Date created) {
        this.created = created;
    }

    public Date getUpdatedDate() {
        return updated;
    }

    public void setUpdatedDate(Date updated) {
        this.updated = updated;
    }

    public Boundary getBoundries() {
        return Boundries;
    }

    public void setUpdatedDate(Boundary boundary) {
        this.Boundries = boundary;
    }

}