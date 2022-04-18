package com.example.testdistancecalculator.controllers;

import com.example.testdistancecalculator.canc.Canculations;
import com.example.testdistancecalculator.exception.DistanceNotFoundException;
import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.Distance;
import com.example.testdistancecalculator.models.Method;
import com.example.testdistancecalculator.repo.CityRepository;
import com.example.testdistancecalculator.repo.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

//@RestController
@Controller
@RequestMapping("/distance")
public class DistanceController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistanceRepository distanceRepository;

    @GetMapping
    public String getDistance(Model model) {
        Iterable<City> cities = cityRepository.findAll();
        Iterable<Method> methods = Arrays.asList(Method.values());
        model.addAttribute("cities", cities);
        model.addAttribute("methods", methods);
        return "distance";
    }
/*
    @PostMapping
    public Response distanceCalculation(
            @RequestParam String fromCity,
            @RequestParam String toCity,
            @RequestParam String method, Model model) throws DistanceNotFoundException {
        try {
            City city1 = cityRepository.findById(Long.parseLong(fromCity));
            City city2 = cityRepository.findById(Long.parseLong(toCity));
            int resultCrow = 0;
            int resultMatr = 0;

            if (method.equals(Method.CROWFIGHT.getNameMethod())) {
                resultCrow = (int) Canculations.distanceСalculationCrowfight(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(), city2.getLongitude());
                model.addAttribute("resultCrow", resultCrow);
            } else if (method.equals(Method.MATR.getNameMethod())) {
                resultMatr = getResultMatr(city1, city2);
                model.addAttribute("resultMatr", resultMatr);
            } else {
                resultCrow = (int) Canculations.distanceСalculationCrowfight(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(), city2.getLongitude());
                resultMatr = getResultMatr(city1, city2);
                model.addAttribute("resultCrow", resultCrow);
                model.addAttribute("resultMatr", resultMatr);
            }
            model.addAttribute("cities", cityRepository.findAll());
            model.addAttribute("methods", Arrays.asList(Method.values()));
            model.addAttribute("method", method);
            model.addAttribute("citiesNames", city1.getName() + " и " + city2.getName());

        } catch (Exception e) {
            throw new DistanceNotFoundException(fromCity, toCity);
        }
        return new Response("OK");
    }
*/
    @PostMapping
    public String distanceCalculation(Model model,
                                      @RequestParam("fromcity") String fromCity,
                                      @RequestParam("tocity") String toCity,
                                      @RequestParam("method") String method) throws DistanceNotFoundException {
        City city1 = cityRepository.findById(Long.parseLong(fromCity));
        City city2 = cityRepository.findById(Long.parseLong(toCity));
        int resultCrow = 0;
        int resultMatr=0;

        if (method.equals(Method.CROWFIGHT.getNameMethod())) {
            resultCrow = (int) Canculations.distanceСalculationCrowfight(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(), city2.getLongitude());
            model.addAttribute("resultCrow", resultCrow);
        } else if (method.equals(Method.MATR.getNameMethod())) {
            resultMatr = getResultMatr(city1, city2);
            model.addAttribute("resultMatr", resultMatr);
        } else {
            resultCrow = (int) Canculations.distanceСalculationCrowfight(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(), city2.getLongitude());
            resultMatr = getResultMatr(city1, city2);
            model.addAttribute("resultCrow", resultCrow);
            model.addAttribute("resultMatr", resultMatr);
        }
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("methods", Arrays.asList(Method.values()));
        model.addAttribute("method", method);
        model.addAttribute("citiesNames", city1.getName() + " и " + city2.getName());
        System.out.println(resultCrow);
        System.out.println(resultMatr);
        return "distance";
    }

    private int getResultMatr(City city1, City city2) throws DistanceNotFoundException {
        if(!city1.equals(city2)) {
            int resultMatr;
            Distance d = distanceRepository.findByFromCityAndToCityEquals(city1.getName(), city2.getName());
            if (d == null) {
                d = distanceRepository.findByFromCityAndToCityEquals(city2.getName(), city1.getName());
            } else if (d == null)
                throw new DistanceNotFoundException(city1.getName(), city2.getName());
            resultMatr = (int) d.getDistance();
            return resultMatr;
        }
        else return 0;
    }

}
