package com.example.testdistancecalculator.exception;

public class DistanceNotFoundException extends Exception{
    public DistanceNotFoundException() { }
    public DistanceNotFoundException (String fromCity,String toCity) {
        super("Расстояние между городами "+fromCity+" и "+toCity+"" +"нет в матрице расстояний!");
    }
}
