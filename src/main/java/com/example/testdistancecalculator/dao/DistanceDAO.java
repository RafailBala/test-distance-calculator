package com.example.testdistancecalculator.dao;

import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.Distance;

public interface DistanceDAO extends GeneralDAO<Distance>{
    Distance getDistance(City fromCity, City toCity);
}
