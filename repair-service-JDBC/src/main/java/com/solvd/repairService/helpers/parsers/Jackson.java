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

        write(genericName(model), converter(model));
        return model;
    }

    public static ServiceCenters set(final ServiceCenters model) {
        write(genericName(model), converter(model));
        return model;
    }

    public static EmployeePosts set(final EmployeePosts model) {
        write(genericName(model), converter(model));
        return model;
    }

    public static EmployeeProfiles set(final EmployeeProfiles model) {

        write(genericName(model), converter(model));
        return model;
    }

    public static CustomerProfiles set(final CustomerProfiles model) {
        write(genericName(model), converter(model));
        return model;
    }

    public static ServiceCenters get(final ServiceCenters model) {
        return (ServiceCenters) readValue(new File(Paths.SERVICE_JSON.path()), model.getClass());
    }

    public static EmployeeProfiles get(final EmployeeProfiles model) {
        return (EmployeeProfiles) readValue(new File(Paths.EMPLOYEE_JSON.path()), model.getClass());
    }

    public static CustomerProfiles get(final CustomerProfiles model) {
        return (CustomerProfiles) readValue(new File(Paths.CUSTOMER_JSON.path()), model.getClass());
    }

    public static EmployeePosts get(final EmployeePosts model) {
        return (EmployeePosts) readValue( new File(Paths.POST_JSON.path()), model.getClass());
    }

    public static Orders get(final Orders model) {
        return (Orders) readValue(new File(Paths.ORDER_JSON.path()), model.getClass());
    }

    private static Object readValue(File file, Class<?> clazz) {
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException("couldn't create " + clazz.getSimpleName());
        }
    }
}
