package com.solvd.repairService.helpers.parsers;

public enum StaxPath {
    CUSTOMER("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\CustomerProfile.xml"),
    CUSTOMER_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\CustomerProfile.xsd"),
    SERVICE("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\ServiceCenter.xml"),
    SERVICE_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\ServiceCenter.xsd"),
    POST("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\EmployeePost.xml"),
    POST_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\EmployeePost.xsd"),
    EMPLOYEE("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\EmployeeProfile.xml"),
    EMPLOYEE_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\EmployeeProfile.xsd"),
    ORDER("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\Order.xml"),
    ORDER_XSD("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\xsd\\Order.xsd"),
    EQUIPMENT("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\src\\main\\resources\\Stax\\xml\\Equipment.xml"),
    ;private final String path;
    StaxPath(String string) {
        this.path = string;
    }

    public String path() {
        return path;
    }
}
