package com.example.testdistancecalculator.controllers;
import com.example.testdistancecalculator.canc.XML;
import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.DataList;
import com.example.testdistancecalculator.models.Distance;
import com.example.testdistancecalculator.repo.CityRepository;
import com.example.testdistancecalculator.repo.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    private CityRepository cityRepository;
    @Autowired
    private DistanceRepository distanceRepository;

    @GetMapping("/upload")
    public String getUpload(Model model) {
        return "upload";
    }

    //для REST ответа
/*
    @PostMapping(value = "/upload")
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file) throws JAXBException, IOException {

        try {
            addData(file);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
 */
//добавление готового файла
    @PostMapping(value ="/upload/add")
    public String addData(Model model) throws JAXBException, IOException {
        //File в MultipartFile
        MultipartFile file= new MockMultipartFile("data.xml",new FileInputStream("src/main/resources/data.xml"));
        addData(file);
        return "upload";
    }

    private void addData(MultipartFile file) throws JAXBException, IOException {
        Object object= XML.xmlToBean(file, DataList.class);
        DataList dataList = (DataList) object;
        List<City> cityList = dataList.getCityList();
        List<Distance> distanceList=dataList.getDistanceList();
        for(int i=0;i<cityList.size();i++){
            cityRepository.save(cityList.get(i));
            //System.out.println(cityList.get(i));
        }
        for(int i=0;i<distanceList.size();i++) {
            distanceRepository.save(distanceList.get(i));
            //System.out.println(distanceList.get(i));
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
