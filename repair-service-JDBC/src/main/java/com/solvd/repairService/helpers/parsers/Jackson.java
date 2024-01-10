package com.solvd.repairService.helpers.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.repairService.model.*;
import com.solvd.repairService.model.Test.A;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Jackson {
    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static String genericName(final AbstractModel model) {
        return Paths.FOLDER_JSON.path() + model.tableName() + ".json";
    }

    private static ArrayList<String> converter(final AbstractModel model) {
        String json = null;
        try {
            ArrayList<String> value = new ArrayList<>();
            json = objectMapper.writeValueAsString(model);
            value.add(json);
            return value;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private static ArrayList<String> converter(final A model) {
        String json = null;
        try {
            ArrayList<String> value = new ArrayList<>();
            json = objectMapper.writeValueAsString(model);
            var fields = StringUtils.split(json, ",");

            Collections.addAll(value, json);
            return value;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private static void write(final AbstractModel model, String name, ArrayList<String> value) {
        File file = new File(name);
        try {
            FileUtils.writeLines(file, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void write(final A model, String name, ArrayList<String> value) {
        File file = new File(name);
        try {
            FileUtils.writeLines(file, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ServiceCenters set(final ServiceCenters model) {
        ServiceCenters entity = new ServiceCenters(5L, "someAddress", "someName", null, null);
        write(entity, genericName(model), converter(entity));
        return model;
    }

    public static A set(final A model) {
        write(model, "test.json", converter(model));
        return model;
    }

    public static EmployeePosts set(final EmployeePosts model) {
        var entity = new EmployeePosts(12L, "JsonPost", "some description");
        write(entity, genericName(model), converter(model));
        return model;
    }

    public static EmployeeProfiles set(final EmployeeProfiles model) {

        var post = new EmployeePosts(12L, "JsonPost", "some description");
        var user = new Users("Jsonlogin", "jsonPass");
        var center = new ServiceCenters(5L, "someAddress", "someName", null, null);

        var entity = new EmployeeProfiles("SomeNick", "somePhone", 6d, user, post, center);
        write(entity, genericName(model), converter(entity));
        return model;
    }

    public static CustomerProfiles set(final CustomerProfiles model) {


        var user = new Users("JsonloginCustomer", "jsonPassCustomer");
        var entity = new CustomerProfiles("CustomerJsonick", "+375(44)...", user);
        write(entity, genericName(model), converter(entity));
        return model;
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

        write(entity, genericName(model), converter(entity));
        return model;
    }

    public ServiceCenters get(final ServiceCenters center) {
        var file = new File(Paths.SERVICE_JSON.path());
        try {
            return objectMapper.readValue(file, ServiceCenters.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static EmployeeProfiles get(final EmployeeProfiles center) {
        var file = new File("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Jsons\\employee_profiles.json");
        try {
            return objectMapper.readValue(file, EmployeeProfiles.class);
        } catch (IOException e) {
            return null;
        }
    }

}
