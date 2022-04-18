package com.example.testdistancecalculator.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="list")
public class DataList {
    List<City> cityList;
    List<Distance> distanceList;
    @XmlElement(name = "city")
    public List<City> getCityList() {
        return cityList;
    }
    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @XmlElement(name = "distance")
    public List<Distance> getDistanceList() {
        return distanceList;
    }
    public void setDistanceList(List<Distance> distanceList) {
        this.distanceList = distanceList;
    }
}
