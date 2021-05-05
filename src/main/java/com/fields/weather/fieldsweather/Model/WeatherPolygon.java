package com.fields.weather.fieldsweather.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "WeatherPolygon")
public class WeatherPolygon implements Serializable {
    @Id
    private String id;
    @NonNull
    private String name;

    public Date created_at;
    private String updated;
    private String countryCode;

    @NonNull
    public GeoJson geo_json;

    private List<Double> center;
    private double area;
    public String user_id;

   
      @NonNull
      @DBRef public Field field;
     /* 
     * @NonNull
     * 
     * @DBRef private List<Director> directors;
     */

    public WeatherPolygon() {
    }

    public WeatherPolygon(String name, Date created, String updated, String countryCode, GeoJson geoJson,
            List<Double> center, Double area, String user_id) {
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.countryCode = countryCode;
        this.geo_Json = geoJson;
        this.center = center;
        this.area = area;
        this.user_id = user_id;

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

   /* public GeoJson getGeoJson() {
        return geo_Json;
    }

    public void setGeoJson(GeoJson geoJson) {
        this.geo_Json = geoJson;
    }*/

    public List<Double> getCenter() {
        return center;
    }

    public void setCenter(List<Double> center) {
        this.center = center;
    }

    public double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    /*public String getUserID() {
        return user_id;
    }

    public void setUserID(String userid) {
        this.user_id = userid;
    }*/

}
