package com.solvd.repairService.model;

import java.sql.Date;

public class OrderExecutions extends AbstractModel {
    private Long employerId;
    private double cost;
    private Date finishDate;
    private boolean returned;
    private Long serviceId;
    private Users user;
    private ServiceCenters serviceCenter;

    public OrderExecutions(Long id) {
        super(id, OrderExecutions.class);
    }

    public OrderExecutions(Long id, Long employerId, double cost, Date date, boolean returned, Long serviceId)
    {
        this(id);
        this.employerId = employerId;
        this.cost = cost;
        this.finishDate = date;
        this.returned = returned;
        this.serviceId = serviceId;
    }

    public Date finishDate() {
        return finishDate;
    }

    public double cost() {
        return cost;
    }

    public Long employerId() {
        return employerId;
    }

    public Long serviceId() {
        return serviceId;
    }

    public boolean isReturned() {
        return returned;
    }
}
