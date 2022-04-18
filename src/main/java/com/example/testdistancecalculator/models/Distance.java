package com.example.testdistancecalculator.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement(name = "distance")
@XmlType(propOrder = { "id", "fromCity", "toCity","distance"})
public class Distance {
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Column(name = "from_city")
    private String fromCity;
    @NotNull
    @Column(name = "to_city")
    private String toCity;
    @NotNull
    private float distance;

    public Distance() {
    }
    public Distance(long id, String fromCity, String toCity, float distance) {
        this.id = id;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(this.id).append(" = ")
                .append('{')
                .append('(')
                .append(this.fromCity).append(',')
                .append(this.toCity).append(',')
                .append(this.distance)
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
    @XmlElement(name = "fromCity")
    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }
    @XmlElement(name = "toCity")
    public String getToCity() {
        return toCity;
    }
    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
    @XmlElement(name = "distance")
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
