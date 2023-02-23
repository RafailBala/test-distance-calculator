package com.example.testdistancecalculator.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.*;
import java.util.List;

@Entity
@XmlRootElement(name = "city")
@XmlType(propOrder = { "id", "name", "latitude","longitude"})
public class City {
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private float latitude;
    @NotNull
    private float longitude;
    @OneToMany(mappedBy="id", fetch= FetchType.EAGER)
    List<Distance> distanceList;
    public City() {
    }

    public City(Long id, String name, float latitude, float longitude) {
        this.id=id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(this.name).append(" = ")
                .append('{')
                .append('(')
                .append(this.latitude).append(',')
                .append(this.longitude)
                .append(")")
                .append('}');
        return str.toString();
    }
    @XmlAttribute(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "latitude")
    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    @XmlElement(name = "longitude")
    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    @XmlTransient
    public List<Distance> getDistanceList() {
        return distanceList;
    }
    public void setDistanceList(List<Distance> distanceList) {
        this.distanceList = distanceList;
    }
}
