package com.solvd.repairService.helpers.parsers;

import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.Users;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Stax {
    public static CustomerProfiles get(CustomerProfiles profile) throws Exception {
        String nick = "";
        String phone = "";
        String login = "";
        String password = "";
        boolean role = false;

        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(Paths.get("src/main/resources/Stax/xml/CustomerProfile.xml")))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLEvent.START_ELEMENT &&
                        "nick".equals(reader.getLocalName())) {
                    nick = reader.getElementText();
                }
                if (event == XMLEvent.START_ELEMENT &&
                        "phone".equals(reader.getLocalName())) {
                    phone = reader.getElementText();
                }
                if (event == XMLEvent.START_ELEMENT &&
                        "login".equals(reader.getLocalName())) {
                    login = reader.getElementText();
                }
                if (event == XMLEvent.START_ELEMENT &&
                        "password".equals(reader.getLocalName())) {
                    password = reader.getElementText();
                }
                if (event == XMLEvent.START_ELEMENT && "role".equals(reader.getLocalName())) {
                    if (reader.getElementText().equals("true"))
                        role = true;
                    else role = false;
                }
            }
        }
        var user = new Users(login, password, role);
        profile.nick(nick);
        profile.phone(phone);
        profile.user(user);
        return profile;
    }
}
