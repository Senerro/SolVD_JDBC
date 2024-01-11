package com.solvd.repairService.helpers.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.repairService.model.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Jackson {
    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static ArrayList<String> converter(final AbstractModel model) {
        try {
            var value = new ArrayList<String>();
            var json = objectMapper.writeValueAsString(model);
            value.add(json);
            return value;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void write(final String name, final ArrayList<String> value) {
        var file = new File(name);
        try {
            FileUtils.writeLines(file, value);
        } catch (IOException e) {
            throw new RuntimeException("file " + name + "wasn't written");
        }
    }

    private static String genericName(final AbstractModel model) {
        return Paths.FOLDER_JSON.path() + model.tableName() + ".json";
    }

    public static Orders set(final Orders model) {
        var client = new Users("JsonloginCustomer2", "jsonPassCustomer2");
        var worker = new Users("JsonloginWorker", "jsonPassWorker");
        var center = new ServiceCenters(5L, "someAddress", "someName", null, null);
        var post = new EmployeePosts(12L, "JsonPost", "some description");
        var clientProfile = new CustomerProfiles("CustomerFRomOrderJson", "phoneFromOrderJson", client);
        var workerProfile = new EmployeeProfiles("Intelov Icore the sevens", "phoneFromOrderJsob", 4, worker, post, center);
        var equipment = new Equipments("JsonType", "Producer from json", "json model", 999);
        equipment.addProblem(new Problems(6L, 2L));
        equipment.addProblem(new Problems(7L, 3L));

        var entity = new Orders();
        LocalDate date = LocalDate.now().plusDays(7);
        entity.center(center);
        entity.customer(clientProfile);
        entity.employee(workerProfile);
        entity.equipment(equipment);
        entity.date(date);

        write(genericName(model), converter(entity));
        return model;
    }

    public static ServiceCenters set(final ServiceCenters model) {
        ServiceCenters entity = new ServiceCenters(5L, "someAddress", "someName", null, null);
        write(genericName(model), converter(entity));
        return model;
    }

    public static EmployeePosts set(final EmployeePosts model) {
        var entity = new EmployeePosts(12L, "JsonPost", "some description");
        write(genericName(model), converter(entity));
        return model;
    }

    public static EmployeeProfiles set(final EmployeeProfiles model) {

        var post = new EmployeePosts(12L, "JsonPost", "some description");
        var user = new Users("Jsonlogin", "jsonPass");
        var center = new ServiceCenters(5L, "someAddress", "someName", null, null);

        var entity = new EmployeeProfiles("SomeNick", "somePhone", 6d, user, post, center);
        write(genericName(model), converter(entity));
        return model;
    }

    public static CustomerProfiles set(final CustomerProfiles model) {
        var user = new Users("JsonloginCustomer", "jsonPassCustomer");
        var entity = new CustomerProfiles("CustomerJsonick", "+375(44)...", user);
        write(genericName(model), converter(entity));
        return model;
    }

    public static ServiceCenters get(final ServiceCenters model) {
        var file = new File(Paths.SERVICE_JSON.path());
        return (ServiceCenters) readValue(file, model.getClass());
    }

    public static EmployeeProfiles get(final EmployeeProfiles model) {
        var file = new File(Paths.EMPLOYEE_JSON.path());
        return (EmployeeProfiles) readValue(file, model.getClass());
    }

    public static CustomerProfiles get(final CustomerProfiles model) {
        var file = new File(Paths.CUSTOMER_JSON.path());
        return (CustomerProfiles) readValue(file, model.getClass());
    }

    public static EmployeePosts get(final EmployeePosts model) {
        var file = new File(Paths.POST_JSON.path());
        return (EmployeePosts) readValue(file, model.getClass());
    }

    public static Orders get(final Orders model) {
        var file = new File(Paths.ORDER_JSON.path());
        return (Orders) readValue(file, model.getClass());

    }

    private static Object readValue(File file, Class<?> clazz) {
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException("couldn't create " + clazz.getSimpleName());
        }
    }
}
