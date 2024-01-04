package com.solvd.repairService;

import com.solvd.repairService.DAO.JDBC.UsersDAO;
import com.solvd.repairService.helpers.parsers.Stax;
import com.solvd.repairService.helpers.parsers.StaxStreamProcessor;
import com.solvd.repairService.model.CustomerProfiles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        var a = Stax.get(new CustomerProfiles());
        LOGGER.debug(a);
    }
}
