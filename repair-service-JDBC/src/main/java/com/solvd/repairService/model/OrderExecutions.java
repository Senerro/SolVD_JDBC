package com.solvd.repairService.model;

import java.sql.Date;

public class OrderExecutions extends AbstractModel {
    private Long employerId;
    private double cost;
    private Date finishDate;
    private boolean returned;
    private Long serviceCenterId;
    private ServiceCenters serviceCenter;
    private Long userId;


    public OrderExecutions(Long id) {
        super(id, OrderExecutions.class);
    }

    public OrderExecutions(Long id, Long employerId, double cost, Date date, boolean returned, Long serviceId) {
        this(id);
        this.employerId = employerId;
        this.cost = cost;
        this.finishDate = date;
        this.returned = returned;
        this.serviceCenterId = serviceId;
    }

    public Date finishDate() {
        return finishDate;
    }

    public void finishDate(Date date) {
        this.finishDate = date;
    }

    public double cost() {
        return cost;
    }

    public void cost(double cost) {
        this.cost = cost;
    }

    public Long employerId() {
        return employerId;
    }

    public void employerId(Long employerId) {
        this.employerId = employerId;
    }

    public Long serviceCenterId() {
        return serviceCenterId;
    }

    public void serviceCenterId(Long serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }

    public boolean isReturned() {
        return returned;
    }

    public void isReturned(boolean returned) {
        this.returned = returned;
    }

    public Long user() {
        return userId;
    }

    public void user(Long userId) {
        this.userId = userId;
    }

    public ServiceCenters serviceCenter() {
        return serviceCenter;
    }

    public void serviceCenter(ServiceCenters serviceCenter) {
        this.serviceCenter = serviceCenter;
    }
}
