package com.solvd.repairService.helpers.parsers;

public enum Paths {
    CUSTOMER_XML("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\CustomerProfile.xml"),
    CUSTOMER_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\CustomerProfile.xsd"),
    SERVICE_XML("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\ServiceCenter.xml"),
    SERVICE_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\ServiceCenter.xsd"),
    SERVICE_JSON("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Jsons\\service_centers.json"),
    POST_XML("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\EmployeePost.xml"),
    POST_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\EmployeePost.xsd"),
    EMPLOYEE_XML("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\EmployeeProfile.xml"),
    EMPLOYEE_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\EmployeeProfile.xsd"),
    ORDER_XML("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\Order.xml"),
    ORDER_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\Order.xsd"),
    EQUIPMENT_XML("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\Equipment.xml"),
    FOLDER_JSON("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Jsons\\")
    ;private final String path;
    Paths(String string) {
        this.path = string;
    }

    public String path() {
        return path;
    }
}
