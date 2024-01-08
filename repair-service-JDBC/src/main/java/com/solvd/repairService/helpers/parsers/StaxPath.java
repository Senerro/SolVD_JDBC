package com.solvd.repairService.helpers.parsers;

public enum StaxPath {
    CUSTOMER("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\CustomerProfile.xml"),
    SERVICE("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\ServiceCenter.xml"),
    POST("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\EmployeePost.xml"),
    EMPLOYYEE("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\EmployeeProfile.xml"),
    ORDER("C:\\solvd\\SolVD_JDBC\\repair-service-JDBC\\target\\classes\\Stax\\xml\\Order.xml");
    private final String path;

    StaxPath(String string) {
        this.path = string;
    }

    public String path() {
        return path;
    }
}
