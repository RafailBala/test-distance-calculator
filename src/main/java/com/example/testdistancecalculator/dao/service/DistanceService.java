package com.example.testdistancecalculator.dao.service;

import com.example.testdistancecalculator.dao.DistanceDAO;
import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.Distance;
import com.example.testdistancecalculator.repo.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistanceService implements DistanceDAO {
    @Autowired
    private DistanceRepository distanceRepository;

    @Override
    public List<Distance> getAll() {
        return (List<Distance>) distanceRepository.findAll();
    }

    @Override
    public Distance get(long distanceID) {
        return distanceRepository.findById(distanceID).orElseThrow();
    }

    @Override
    public void saveObj(Distance distance) {
        distanceRepository.save(distance);
    }

    @Override
    public Distance getDistance(City fromCity, City toCity) {
        return distanceRepository.findByFromCityIdAndToCityId(fromCity.getId(), toCity.getId());
    }
}
