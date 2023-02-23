package com.example.testdistancecalculator.dao.service;

import com.example.testdistancecalculator.dao.CityDAO;
import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements CityDAO {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return (List<City>) cityRepository.findAll();
    }

    @Override
    public City get(long cityID) {
        return cityRepository.findById(cityID).orElseThrow();
    }

    @Override
    public void saveObj(City city) {
        cityRepository.save(city);
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }
}

