package com.solvd.repairService.helpers.parsers;

import com.solvd.repairService.model.*;
import com.solvd.repairService.model.dto.ProblemDTO;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JAXB {
    public static void set() {
        JAXBContext context;
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;

            docBuilder = builderFactory.newDocumentBuilder();
            context = JAXBContext.newInstance(Users.class, CustomerProfiles.class);
            Users user = new Users("someLog", "somePass");
            CustomerProfiles profile = new CustomerProfiles("вгениц", "+375", user);
            StringWriter writer = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(profile, writer);
            String xml = writer.toString();

            var myXML = docBuilder.parse(new InputSource(new StringReader(xml)));
            System.out.println(myXML);
        } catch (Exception e) {

        }

    }
    public static String set(Equipments equipments) {
        JAXBContext context;
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;

            docBuilder = builderFactory.newDocumentBuilder();
            context = JAXBContext.newInstance(Equipments.class, Problems.class);
            Equipments equipment = new Equipments("some type", "some model", "somesthing", 0);
            Problems problem1 = new Problems(5L, 3L);
            Problems problem2 = new Problems(6L, 2L);
            ArrayList<Problems> list = new ArrayList<>();
            list.add(problem1);
            list.add(problem2);
          /*  equipment.addProblem(problem1);
            equipment.addProblem(problem2);*/
            ProblemDTO dto = new ProblemDTO();
            dto.addProblems(list);
            equipment.problemDTO(dto);

            StringWriter writer = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(equipment, writer);
            String xml = writer.toString();

            var myXML = docBuilder.parse(new InputSource(new StringReader(xml)));
            System.out.println(myXML);
            return xml;
        } catch (Exception e) {
            return null;
        }
    }
    public static Object set(String xmlPath, String xsdPath, Class<?> model) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(xmlPath));
            String body = br.lines().collect(Collectors.joining());
            StringReader reader = new StringReader(body);
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(xsdPath));
            JAXBContext context = JAXBContext.newInstance(model);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            schema.equals("");                                                                                                                               //unmarshaller.setSchema(schema);
            Object tmp = unmarshaller.unmarshal(reader);
            return tmp;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
    public static CustomerProfiles get(CustomerProfiles model) {

        return (CustomerProfiles)
                set(StaxPath.CUSTOMER.path(),
                        StaxPath.CUSTOMER_XSD.path(),
                        model.getClass());
    }
    public static EmployeePosts get(EmployeePosts model) {

        return (EmployeePosts)
                set(StaxPath.POST.path(),
                        StaxPath.POST_XSD.path(),
                        model.getClass());
    }
    public static EmployeeProfiles get(EmployeeProfiles model) {

        return (EmployeeProfiles)
                set(StaxPath.EMPLOYEE.path(),
                        StaxPath.EMPLOYEE_XSD.path(),
                        model.getClass());
    }
    public static ServiceCenters get(ServiceCenters model) {

        return (ServiceCenters)
                set(StaxPath.SERVICE.path(),
                        StaxPath.SERVICE_XSD.path(),
                        model.getClass());
    }
    public static Orders get(Orders model) {

        return (Orders)
                set(StaxPath.ORDER.path(),
                        StaxPath.ORDER_XSD.path(),
                        model.getClass());
    }
    public static Equipments get(Equipments model) {

        return (Equipments)
                set(StaxPath.EQUIPMENT.path(),
                        StaxPath.ORDER_XSD.path(),
                        model.getClass());
    }

    private static Object setOrder(String path, String path1, Class<? extends Orders> aClass) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(StaxPath.ORDER.path()));
            String body = br.lines().collect(Collectors.joining());
            StringReader reader = new StringReader(body);
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(StaxPath.ORDER_XSD.path()));
            JAXBContext context = JAXBContext.newInstance(Orders.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            schema.equals("");                                                                                                                               //unmarshaller.setSchema(schema);
            Object tmp = unmarshaller.unmarshal(reader);
            return tmp;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

}
