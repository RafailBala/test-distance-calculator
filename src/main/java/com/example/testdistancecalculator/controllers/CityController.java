package com.example.testdistancecalculator.controllers;

import com.example.testdistancecalculator.dao.service.CityService;
import com.example.testdistancecalculator.dto.CityDto;
import com.example.testdistancecalculator.models.City;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
@Log
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/city")
    public String getCity(Model model) {

        Iterable<CityDto> cities=   cityService.getAll().stream().map(post -> modelMapper.map(post, CityDto.class))
        				.collect(Collectors.toList());
        model.addAttribute("cities", cities);
        return "city";
    }
    @PostMapping("/city/{id}")
    public String cityDelete(@PathVariable(value="id") long id,
                             Model model) {
        City city =cityService.get(id);
        cityService.delete(city);
        return "redirect:/city";
    }
}
