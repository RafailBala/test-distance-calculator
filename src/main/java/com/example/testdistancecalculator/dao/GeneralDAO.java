package com.example.testdistancecalculator.dao;

import java.util.List;

public interface GeneralDAO <T>{
    List<T> getAll();
    T get(long id);
    void saveObj(T obj);
}
