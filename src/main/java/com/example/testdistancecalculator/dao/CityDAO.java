package com.example.testdistancecalculator.dao;

import com.example.testdistancecalculator.models.City;

public interface CityDAO extends GeneralDAO<City>{
    void delete(City city);
}
