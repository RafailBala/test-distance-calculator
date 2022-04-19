package com.example.testdistancecalculator;

import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.DataList;
import com.example.testdistancecalculator.models.Distance;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class XML {

    public static Object xmlToBean(MultipartFile file, Class<?> load) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //Object object = unmarshaller.unmarshal(new File("src/main/resources/1.xml"));
        Object object = unmarshaller.unmarshal(file.getInputStream());
        return object;
    }

    public static void beanToXml(List<City> cityList, List<Distance> distanceList, String xmlPath) throws JAXBException, IOException {

        DataList dataList = new DataList();
        dataList.setCityList(cityList);
        dataList.setDistanceList(distanceList);

        JAXBContext context = JAXBContext.newInstance(DataList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
        StringWriter writer = new StringWriter();
        marshaller.marshal(dataList, writer);
        String str = writer.toString();
        //String xmlPath = "C:\\Users\\balae\\IdeaProjects\\test-distance-calculator\\src\\main\\resources\\1.xml";
        BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(xmlPath)));
        bfw.write(str);
        bfw.close();
    }
}
