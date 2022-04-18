package com.example.testdistancecalculator.canc;

import com.example.testdistancecalculator.models.City;
import com.example.testdistancecalculator.models.DataList;
import com.example.testdistancecalculator.models.Distance;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XML {

    public static Object xmlToBean(MultipartFile file, Class<?> load) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //Object object = unmarshaller.unmarshal(new File("src/main/resources/1.xml"));
        Object object = unmarshaller.unmarshal(file.getInputStream());
        return object;
    }

    public static void beanToXml(int num1, int num2,String xmlPath) throws JAXBException, IOException {
        long a;
        float b, c;
        List<City> cityList = new ArrayList<>();
        List<Distance> distanceList = new ArrayList<>();
        ;
        for (int i = 0; i < num1; i++) {
            a = (long) i;
            b = (float) ((10+i/num1) + 0.123 * i);
            c = (float) ((12+i/num1) + 0.321 * i);
            City city = new City(a, "A" + i, b, c);
            cityList.add(city);
        }
        int count = 0;
        while (count < num2) {
            b = (float) (5 + 0.5 * count);
            a = (long) count;
            if (num1 > count) {
                for (int j = 0; j < cityList.size(); j++) {
                    Distance distance = new Distance(num2-count-j, cityList.get(count).getName(), cityList.get(j).getName(), b);
                    distanceList.add(distance);
                }
            }
            count++;
        }
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
