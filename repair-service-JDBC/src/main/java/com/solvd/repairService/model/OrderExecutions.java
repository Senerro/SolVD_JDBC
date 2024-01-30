package com.solvd.repairService.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class OrderExecutions extends AbstractModel {
    private Long employerId;
    private double cost;
    private int finishDate;
    private int returned;
    private Long serviceCenterId;
    private ServiceCenters serviceCenter;
    private Long userId;


    public OrderExecutions(Long id) {
        super(id, OrderExecutions.class);
    }

    public OrderExecutions() {
        super(0L, OrderExecutions.class);
    }

    public OrderExecutions(Long id, Long employerId, double cost, int date, int returned, Long serviceId) {
        this(id);
        this.employerId = employerId;
        this.cost = cost;
        this.finishDate = date;
        this.returned = returned;
        this.serviceCenterId = serviceId;
    }

    @JsonGetter
    public int finishDate() {
        return finishDate;
    }

    public void finishDate(int date) {
        this.finishDate = date;
    }

    @JsonGetter
    public double cost() {
        return cost;
    }

    public void cost(double cost) {
        this.cost = cost;
    }

    @JsonGetter
    public Long employerId() {
        return employerId;
    }

    public void employerId(Long employerId) {
        this.employerId = employerId;
    }

    @JsonGetter
    public Long serviceCenterId() {
        return serviceCenterId;
    }

    public void serviceCenterId(Long serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }

    @JsonGetter
    public int isReturned() {
        return returned;
    }

    public void isReturned(int returned) {
        this.returned = returned;
    }

    @JsonGetter
    public Long user() {
        return userId;
    }

    public void user(Long userId) {
        this.userId = userId;
    }

    @JsonGetter
    public ServiceCenters serviceCenter() {
        return serviceCenter;
    }

    public void serviceCenter(ServiceCenters serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    @JsonGetter
    public int getFinishDate(int days) {
        this.finishDate = days;
        return finishDate;
    }

    public void setReturned(boolean returned) {
        if (!returned)
            this.returned = 0;
        else this.returned = 1;
    }
}
