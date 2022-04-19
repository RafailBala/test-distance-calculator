package com.example.testdistancecalculator.repo;

import com.example.testdistancecalculator.models.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City,Long> {
}
