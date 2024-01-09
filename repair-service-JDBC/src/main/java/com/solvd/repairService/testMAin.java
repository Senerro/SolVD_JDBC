package com.solvd.repairService;

import com.solvd.repairService.helpers.parsers.JAXB;
import com.solvd.repairService.helpers.parsers.StaxPath;
import com.solvd.repairService.helpers.parsers.StaxStreamProcessor;
import com.solvd.repairService.model.*;
import com.solvd.repairService.model.dto.OrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class testMAin {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private final static Scanner scanner = new Scanner(System.in);

    private static final Logger LOGGER = LogManager.getLogger(testMAin.class);

    public static void main(String[] args) throws Exception {
        /*XMLInputFactory factory = XMLInputFactory.newInstance();
        File file = new File("src/main/resources/Stax/xml/CustomerProfile.xml");
        try (FileInputStream fis = new FileInputStream(file)) {
            XMLEventReader reader = factory.createXMLEventReader(fis);
            while (reader.hasNext()) {
               XMLEvent event = reader.nextEvent();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        {
            /*JAXBContext context = JAXBContext.newInstance(Equipments.class, Problems.class);
            ArrayList<Problems> problemsArrayList = new ArrayList<>();
            Problems problem1 = new Problems(5L, 1L);
            problemsArrayList.add(problem1);
            Problems problem2 = new Problems(6L, 2L);
            problemsArrayList.add(problem2);

            Equipments equipment = new Equipments("laptop", "Asus", "G502VM", 5050);
            equipment.addProblem(problem1);
            equipment.addProblem(problem2);
            StringWriter writer = new StringWriter();
            Marshaller marsh = context.createMarshaller();
            marsh.marshal(equipment, writer);
           // System.out.println(writer);*/
        }
        {
            //JAXB.set(new Equipments());
              var a = JAXB.get(new Orders());
            LOGGER.info(a);


        }

    }
}
//
