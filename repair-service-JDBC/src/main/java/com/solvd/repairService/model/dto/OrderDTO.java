package com.solvd.repairService.model.dto;

import com.solvd.repairService.model.*;

public class OrderDTO {
    ServiceCenters center;
    CustomerProfiles customer;
    EmployeeProfiles employee;
    EmployeePosts post;
    Users userC, userE;
    Equipments equipment;

    public OrderDTO(ServiceCenters center, CustomerProfiles customer, EmployeeProfiles employee, EmployeePosts post, Users userC, Users userE, Equipments equipment) {
        this.center = center;
        this.customer = customer;
        this.employee = employee;
        this.post = post;
        this.userC = userC;
        this.userE = userE;
        this.equipment = equipment;
    }

    public ServiceCenters center() {
        return center;
    }

    public CustomerProfiles customer() {
        return customer;
    }

    public EmployeeProfiles employee() {
        return employee;
    }

    public EmployeePosts post() {
        return post;
    }

    public Users userC() {
        return userC;
    }

    public Users userE() {
        return userE;
    }

    public Equipments equipment() {
        return equipment;
    }

    public void center(ServiceCenters center) {
        this.center = center;
    }

    public void customer(CustomerProfiles customer) {
        this.customer = customer;
    }

    public void employee(EmployeeProfiles employee) {
        this.employee = employee;
    }

    public void post(EmployeePosts post) {
        this.post = post;
    }

    public void userC(Users userC) {
        this.userC = userC;
    }

    public void userE(Users userE) {
        this.userE = userE;
    }

    public void equipment(Equipments equipment) {
        this.equipment = equipment;
    }
}
