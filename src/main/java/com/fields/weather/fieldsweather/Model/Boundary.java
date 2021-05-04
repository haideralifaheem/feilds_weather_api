package com.fields.weather.fieldsweather.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

public class Boundary implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String Id;
    
    private Date created;
    private Date updated;
    @NonNull
    private GeoJsonPolygon Area;

    public Boundary()
    {

    }

    public Boundary(String id, String name, Date created, Date updated, GeoJsonPolygon area) {
        this.Id = id;
        this.created = created;
        this.updated = updated;
        this.Area = area;
    }

    public String getfieldId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
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

    public GeoJsonPolygon getArea() {
        return Area;
    }

    public void setArea(GeoJsonPolygon Area) {
        this.Area = Area;
    }
}
