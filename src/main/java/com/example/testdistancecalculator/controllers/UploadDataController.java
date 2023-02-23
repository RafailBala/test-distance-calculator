package com.example.testdistancecalculator.controllers;

import com.example.testdistancecalculator.XML;
import com.example.testdistancecalculator.dao.service.CityService;
import com.example.testdistancecalculator.dao.service.DistanceService;
import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.DataList;
import com.example.testdistancecalculator.models.Distance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


//@RestController
@Controller
@RequestMapping
public class UploadDataController {
    @Autowired
    private DistanceService distanceService;
    @Autowired
    private CityService cityService;

    @GetMapping("/upload")
    public String getUpload(Model model) {
        return "upload";
    }

    //добавление готового файла
    @PostMapping(value ="/upload/add")
    public String addData(Model model) throws JAXBException, IOException {
        //File в MultipartFile
        MultipartFile file= new MockMultipartFile("data.xml",new FileInputStream("src/main/resources/data.xml"));
        addData(file);
        //XML.beanToXml(10,30,"C:\\Users\\balaev\\IdeaProjects\\test-distance-calculator\\src\\main\\resources\\1.xml");
        return "upload";
    }

    private void addData(MultipartFile file) throws JAXBException, IOException {
        Object object= XML.xmlToBean(file, DataList.class);
        DataList dataList = (DataList) object;
        List<City> cityList = dataList.getCityList();
        List<Distance> distanceList=dataList.getDistanceList();
        for(int i=0;i<cityList.size();i++){
            cityService.saveObj(cityList.get(i));
        }
        for(int i=0;i<distanceList.size();i++) {
            distanceService.saveObj(distanceList.get(i));
        }
    }

    @PostMapping(value = "/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,Model model) throws JAXBException, IOException {
            boolean a=false;
        try {
            addData(file);
        }
        catch (Exception e) {
            a=true;
            model.addAttribute("fileError",a);
        }
        finally {
            a=false;
        }
        return "upload";
    }

}
