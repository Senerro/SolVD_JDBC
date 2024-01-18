package com.solvd.repairService.helpers.enums;

import com.solvd.repairService.model.*;

public enum ModelsContainer {
    USER(Users.class.getName()),
    CUSTOMER(CustomerProfiles.class.getName()),
    EMPLOYEE_PROFILE(EmployeeProfiles.class.getName()),
    EMPLOYEE_POST(EmployeePosts.class.getName()),
    SERVICE_CENTER(ServiceCenters.class.getName()),
    ORDER(Orders.class.getName()),
    ;
    private final String name;
    ModelsContainer(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
