package com.solvd.repairService.helpers.parsers;

import com.solvd.repairService.model.*;
import com.solvd.repairService.model.dto.OrderDTO;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import java.nio.file.Files;
import java.util.HashSet;

public class Stax {
    public static CustomerProfiles get(CustomerProfiles profile) throws Exception {
        String nick = "", phone = "", login = "", password = "";
        boolean role = false;

        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(java.nio.file.Paths.get(Paths.CUSTOMER_XML.path())))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                if (reader.next() != XMLEvent.START_ELEMENT)
                    continue;
                if ("nick".equals(reader.getLocalName())) {
                    nick = reader.getElementText();
                }
                if ("phone".equals(reader.getLocalName())) {
                    phone = reader.getElementText();
                }
                if ("login".equals(reader.getLocalName())) {
                    login = reader.getElementText();
                }
                if ("password".equals(reader.getLocalName())) {
                    password = reader.getElementText();
                }
                if ("role".equals(reader.getLocalName())) {
                    role = reader.getElementText().equals("true");
                }
            }
        }
        var user = new Users(login, password, role);
        profile.nick(nick);
        profile.phone(phone);
        profile.user(user);
        return profile;
    }

    public static ServiceCenters get(ServiceCenters center) throws Exception {
        String name = "";
        String address = "";
        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(java.nio.file.Paths.get(Paths.SERVICE_XML.path())))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                if (reader.next() != XMLEvent.START_ELEMENT)
                    continue;
                if ("name".equals(reader.getLocalName())) {
                    name = reader.getElementText();
                }
                if ("address".equals(reader.getLocalName())) {
                    address = reader.getElementText();
                }
            }
            center.name(name);
            center.address(address);
        }
        return center;
    }

    public static EmployeePosts get(EmployeePosts post) throws Exception {
        String position = "", description = "";
        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(java.nio.file.Paths.get(Paths.POST_XML.path())))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                if (reader.next() != XMLEvent.START_ELEMENT)
                    continue;
                if ("position".equals(reader.getLocalName())) {
                    position = reader.getElementText();
                }
                if ("description".equals(reader.getLocalName())) {
                    description = reader.getElementText();
                }
            }
            post.role(position);
            post.description(description);
        }
        return post;
    }

    public static EmployeeProfiles get(EmployeeProfiles profile) throws Exception {
        String login = "", password = "", fullname = "", phone = "";
        double experience = 0d;
        long postId = 0L, serviceCenterId = 0L;
        String position = "", description = "";

        boolean role = true;
        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(java.nio.file.Paths.get(Paths.EMPLOYEE_XML.path())))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                if (reader.next() != XMLEvent.START_ELEMENT)
                    continue;

                if ("login".equals(reader.getLocalName())) {
                    login = reader.getElementText();
                }
                if ("password".equals(reader.getLocalName())) {
                    password = reader.getElementText();
                }
                if ("fullName".equals(reader.getLocalName())) {
                    fullname = reader.getElementText();
                }
                if ("phone".equals(reader.getLocalName())) {
                    phone = reader.getElementText();
                }
                if ("experience".equals(reader.getLocalName())) {
                    experience = Double.parseDouble(reader.getElementText());
                }
                if ("role".equals(reader.getLocalName())) {
                    if (reader.getElementText().equals("true"))
                        role = true;
                    else role = false;
                }
                if ("position".equals(reader.getLocalName())) {
                    position = reader.getElementText();
                }

                if ("description".equals(reader.getLocalName())) {
                    description = reader.getElementText();
                }
                if ("id".equals(reader.getLocalName())) {
                    postId = Long.parseLong(reader.getElementText());
                }
                if ("serviceCenterId".equals(reader.getLocalName())) {
                    serviceCenterId = Long.parseLong(reader.getElementText());
                }
            }
        }
        Users user = new Users(login, password, role);
        EmployeePosts post = new EmployeePosts(postId, position, description);
        EmployeeProfiles employee = new EmployeeProfiles(fullname, phone, experience, serviceCenterId);
        employee.user(user);
        employee.post(post);
        return profile;
    }

    public static OrderDTO get(Orders order) throws Exception {

        ServiceCenters center = new ServiceCenters();
        CustomerProfiles customer = new CustomerProfiles();
        EmployeeProfiles employee = new EmployeeProfiles();
        EmployeePosts post = new EmployeePosts();
        Users userC = new Users(), userE = new Users();
        Equipments equipment = new Equipments();

        boolean customerEmpty = true, employeeEmpty = true, centerEmpty = true, equipmentEmpty = true;
        HashSet<String> problems = new HashSet<>();
        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(java.nio.file.Paths.get(Paths.ORDER_XML.path())))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                if (reader.next() != XMLEvent.START_ELEMENT) {
                    continue;
                }
                if (centerEmpty) {
                    if ("id".equals(reader.getLocalName())) {
                        center.id(Long.parseLong(reader.getElementText()));
                        continue;
                    }
                    if ("name".equals(reader.getLocalName())) {
                        center.name(reader.getElementText());
                        continue;

                    }
                    if ("address".equals(reader.getLocalName())) {
                        center.address(reader.getElementText());
                        centerEmpty = false;
                        employee.serviceCenters(center);
                        continue;
                    }
                }
                if (customerEmpty) {
                    if ("nick".equals(reader.getLocalName())) {
                        customer.nick(reader.getElementText());
                        continue;
                    }
                    if ("phone".equals(reader.getLocalName())) {
                        customer.phone(reader.getElementText());
                        continue;
                    }

                    if ("login".equals(reader.getLocalName())) {
                        userC.login(reader.getElementText());
                        continue;
                    }
                    if ("password".equals(reader.getLocalName())) {
                        userC.password(reader.getElementText());
                        continue;
                    }
                    if ("role".equals(reader.getLocalName())) {
                        userC.role(reader.getElementText().equals("true"));
                        customer.user(userC);
                        customerEmpty = false;
                        continue;
                    }
                }
                if (employeeEmpty) {
                    if ("fullName".equals(reader.getLocalName())) {
                        employee.fullName(reader.getElementText());
                        continue;
                    }
                    if ("phone".equals(reader.getLocalName())) {
                        employee.phone(reader.getElementText());
                        continue;
                    }
                    if ("experience".equals(reader.getLocalName())) {
                        employee.experience(Double.parseDouble(reader.getElementText()));
                        continue;
                    }
                    if ("login".equals(reader.getLocalName())) {
                        userE.login(reader.getElementText());
                        continue;
                    }
                    if ("password".equals(reader.getLocalName())) {
                        userE.password(reader.getElementText());
                        continue;
                    }
                    if ("role".equals(reader.getLocalName())) {
                        userE.role(reader.getElementText().equals("true"));
                        employee.user(userE);
                        continue;
                    }
                    if ("id".equals(reader.getLocalName())) {
                        post.id(Long.parseLong(reader.getElementText()));
                        continue;
                    }
                    if ("position".equals(reader.getLocalName())) {
                        post.role(reader.getElementText());
                        continue;
                    }
                    if ("description".equals(reader.getLocalName())) {
                        post.description(reader.getElementText());
                        employeeEmpty = false;
                        employee.post(post);
                        continue;
                    }
                }
                if (equipmentEmpty) {

                    if ("type".equals(reader.getLocalName())) {
                        equipment.type(reader.getElementText());
                        continue;
                    }
                    if ("producer".equals(reader.getLocalName())) {
                        equipment.producer(reader.getElementText());
                        continue;
                    }
                    if ("model".equals(reader.getLocalName())) {
                        equipment.model(reader.getElementText());
                        continue;
                    }
                    if ("cost".equals(reader.getLocalName())) {
                        equipment.price(Double.parseDouble(reader.getElementText()));
                        equipmentEmpty = false;
                        continue;
                    }
                }
                if ("service".equals(reader.getLocalName())) {
                    problems.add(reader.getElementText());
                }
            }
        }
        return new OrderDTO(center, customer, employee, post, userC, userE, equipment);
    }

}
