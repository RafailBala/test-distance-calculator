package com.example.testdistancecalculator.dto;

import com.example.testdistancecalculator.models.City;
import lombok.Data;

@Data
public class DistanceDto {
    private Long id;
    private City fromCity;
    private City toCity;
    private float distance;

}
