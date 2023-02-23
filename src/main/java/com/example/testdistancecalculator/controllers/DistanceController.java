package com.example.testdistancecalculator.controllers;

import com.example.testdistancecalculator.Canculations;
import com.example.testdistancecalculator.dao.service.CityService;
import com.example.testdistancecalculator.dao.service.DistanceService;
import com.example.testdistancecalculator.dto.CityDto;
import com.example.testdistancecalculator.exception.DistanceNotFoundException;
import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.Distance;
import com.example.testdistancecalculator.models.Method;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/distance")
public class DistanceController {
    @Autowired
    private DistanceService distanceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public String getDistance(Model model) {
        Iterable<CityDto> cities=   cityService.getAll().stream().map(post -> modelMapper.map(post, CityDto.class))
                .collect(Collectors.toList());
        Iterable<Method> methods = Arrays.asList(Method.values());
        model.addAttribute("cities", cities);
        model.addAttribute("methods", methods);
        return "distance";
    }

    @PostMapping
    public String distanceCalculation(Model model,
                                      @RequestParam("fromcity") String fromCity,
                                      @RequestParam("tocity") String toCity,
                                      @RequestParam("method") String method) throws DistanceNotFoundException {
        long fromCityID=Long.parseLong(fromCity);
        long toCityID=Long.parseLong(toCity);
        City city1 = cityService.get(fromCityID);
        City city2 = cityService.get(toCityID);
        int resultCrow = 0;
        int resultMatr=0;
        if (method.equals(Method.CROWFIGHT.getNameMethod())) {
            resultCrow = (int) Canculations.distanceСalculationCrowfight(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(), city2.getLongitude());
            model.addAttribute("resultCrow", resultCrow);
        } else if (method.equals(Method.MATR.getNameMethod())) {
            resultMatr = getResultMatr(fromCityID, toCityID);
            model.addAttribute("resultMatr", resultMatr);
        } else {
            resultCrow = (int) Canculations.distanceСalculationCrowfight(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(), city2.getLongitude());
            resultMatr = getResultMatr(fromCityID, toCityID);
            model.addAttribute("resultCrow", resultCrow);
            model.addAttribute("resultMatr", resultMatr);
        }
        Iterable<CityDto> cities=   cityService.getAll().stream().map(post -> modelMapper.map(post, CityDto.class))
                .collect(Collectors.toList());
        model.addAttribute("cities", cities);
        model.addAttribute("methods", Arrays.asList(Method.values()));
        model.addAttribute("method", method);
        CityDto city1Dto = modelMapper.map(city1, CityDto.class);
        CityDto city2Dto = modelMapper.map(city2, CityDto.class);
        model.addAttribute("citiesNames", city1Dto.getName() + " и " + city2Dto.getName());
        System.out.println(resultCrow);
        System.out.println(resultMatr);
        return "distance";
    }

    private int getResultMatr(long fromCityID, long toCityID ) throws DistanceNotFoundException {
        City fromCity =cityService.get(fromCityID);
        City toCity=cityService.get(toCityID);
        if(fromCityID!=(toCityID)) {
            int resultMatr;
            Distance d = distanceService.getDistance(fromCity, toCity);
            if (d == null) {
                d = distanceService.getDistance(toCity, fromCity);
            } else if (d == null)
                throw new DistanceNotFoundException(cityService.get(fromCityID).getName(),
                                                    cityService.get(toCityID).getName());
            resultMatr = (int) d.getDistance();
            return resultMatr;
        }
        else return 0;
    }

}
