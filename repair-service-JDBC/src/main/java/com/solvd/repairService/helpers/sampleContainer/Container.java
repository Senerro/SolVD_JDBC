package com.solvd.repairService.helpers.sampleContainer;

import com.solvd.repairService.model.*;

public class Container {
    static private final ServiceCenters center = new ServiceCenters(5L, "someAddress", "someName", null, null);
    static private final Users clientValidation = new Users("JsonloginCustomer2", "jsonPassCustomer2");
    static private final CustomerProfiles customerProfile = new CustomerProfiles("CustomerFRomOrderJson", "phoneFromOrderJson", clientValidation);
    static private final Users workerValidation = new Users("JsonloginWorker", "jsonPassWorker");
    static private final EmployeePosts post = new EmployeePosts(12L, "JsonPost", "some description");
    static private final EmployeeProfiles employeeProfile = new EmployeeProfiles("Intelov Icore the sevens", "phoneFromOrderJsob", 4, workerValidation, post, center);
    static private final Equipments equipment = new Equipments("JsonType", "Producer from json", "json model", 999);

    public static Orders order() {
        return new Orders(customerProfile, employeeProfile, equipment, center);
    }

    public static ServiceCenters serviceCenter() {
        return center;
    }

    public static EmployeePosts post() {
        return post;
    }

    public static EmployeeProfiles employee() {
        return employeeProfile;
    }

    public static CustomerProfiles customer() {
        return customerProfile;
    }
}
