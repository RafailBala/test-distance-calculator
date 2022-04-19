package com.example.testdistancecalculator.repo;

import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.Distance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DistanceRepository extends CrudRepository<Distance,Long> {
    Distance findByFromCityIdAndToCityId(long idFrom, long idTo);
}

