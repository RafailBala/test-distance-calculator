package com.example.testdistancecalculator.repo;

import com.example.testdistancecalculator.models.Distance;
import org.springframework.data.repository.CrudRepository;

public interface DistanceRepository extends CrudRepository<Distance,Long> {
    Distance findByFromCityIdAndToCityId(long idFrom, long idTo);
}

