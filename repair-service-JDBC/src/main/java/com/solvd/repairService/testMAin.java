package com.solvd.repairService;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.repairService.helpers.parsers.Jackson;
import com.solvd.repairService.helpers.parsers.Paths;
import com.solvd.repairService.model.*;
import com.solvd.repairService.model.Test.A;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.time.LocalDate;
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
          /*  ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            LocalDate date = LocalDate.now().plusDays(7L);
            ServiceCenters center = new ServiceCenters(5L, "someAddress", "someName", null, null, date);
            String json = objectMapper.writeValueAsString(center);
            LOGGER.debug(json);*/

            /*System.out.println("\n");
            String a = "{\"id\":5,\"address\":\"someAddress\",\"name\":\"someName\",\"photo\":null,\"description\":null}";
            ServiceCenters returnedServiceCenters = objectMapper.readValue(a, ServiceCenters.class);
            LOGGER.debug(returnedServiceCenters);*/

            /*File file = new File(Paths.SERVICE_JSON.path());
            Object returnedServiceCenters = objectMapper.readValue(file, ServiceCenters.class);*/

           var a = Jackson.set(new Orders());
           LOGGER.debug(a);
        }

    }
}
//
