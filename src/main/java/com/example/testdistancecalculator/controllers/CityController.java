package com.example.testdistancecalculator.controllers;

import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.Distance;
import com.example.testdistancecalculator.repo.CityRepository;
import com.example.testdistancecalculator.repo.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//@RestController
@Controller
public class CityController {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistanceRepository distanceRepository;

    @GetMapping("/city")
    public String getCity(Model model) {
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("cities", cities);
        return "city";
    }
    @PostMapping("/city/{id}")
    public String cityDelete(@PathVariable(value="id") long id,
                             Model model) {
        City city=cityRepository.findById(id);
        cityRepository.delete(city);
        List<Distance> distanceList=distanceRepository.findByFromCityOrToCityEquals(city.getName(),city.getName());
        for(int i=0;i<distanceList.size();i++){
            //удаляем все вхождения города в таблице расстояний
            distanceRepository.delete(distanceList.get(i));
        }
        return "redirect:/city";
    }
}
