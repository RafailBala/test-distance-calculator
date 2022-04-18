package com.example.testdistancecalculator.repo;

import com.example.testdistancecalculator.models.Distance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistanceRepository extends CrudRepository<Distance,Long> {
    Distance findByFromCityAndToCityEquals(String c1,String c2);
    List<Distance> findByFromCityOrToCityEquals(String c1,String c2);
}

