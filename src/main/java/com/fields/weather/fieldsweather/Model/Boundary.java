package com.fields.weather.fieldsweather.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;



public class Boundary implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    private Date created;
    private Date updated;
    @NonNull
    private GeoJson geoJson;

    public Boundary()
    {

    }

    public Boundary(String id, String name, Date created, Date updated, GeoJson geoJson) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.geoJson = geoJson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public GeoJson getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(GeoJson geoJson) {
        this.geoJson = geoJson;
    }
}
