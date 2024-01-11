package com.solvd.repairService.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@XmlType(name = "OrderType")
@XmlRootElement(name = "Order")
public class Orders extends AbstractModel {
    private Long userId;
    private Long equipmentId;
    private Long executeId;
    private LocalDate date;

    @JsonGetter
    @JsonFormat(pattern = "dd-MM-yyyy")
    public LocalDate date() {
        return date;
    }

    public void date(LocalDate date) {
        this.date = date;
    }

    @XmlElement(name = "user")
    private Users user;
    @XmlElement(name = "employee")

    private EmployeeProfiles employee;
    @XmlElement(name = "customer")

    private CustomerProfiles customer;
    @XmlElement(name = "center")

    private ServiceCenters center;
    @XmlElement(name = "equipment")

    private Equipments equipment;
    private OrderExecutions orderExecution;

    public Orders(Long id) {
        super(id, Orders.class);
    }

    public Orders() {
        this(0L);
    }

    public Orders(Long id, Long userId, Long equipmentId, Long executeId) {
        this(id);
        this.equipmentId = equipmentId;
        this.userId = userId;
        this.executeId = executeId;
    }

    public Orders(Long userId, Equipments equipment, OrderExecutions oe) {
        this(0L);
        this.equipment = equipment;
        this.equipmentId = equipment.id();
        this.userId = userId;
        this.orderExecution = oe;
        this.executeId = oe.id();
    }

    @JsonGetter
    public Equipments equipment() {
        return equipment;
    }

    @JsonGetter
    public EmployeeProfiles employee() {
        return employee;
    }

    public void employee(EmployeeProfiles employee) {
        this.employee = employee;
    }

    @JsonGetter
    public CustomerProfiles customer() {
        return customer;
    }

    public void customer(CustomerProfiles customer) {
        this.customer = customer;
    }

    @JsonGetter
    public ServiceCenters center() {
        return center;
    }

    public void center(ServiceCenters center) {
        this.center = center;
    }

    public void equipment(Equipments equipment) {
        this.equipment = equipment;
    }

    @JsonGetter
    public Long executeId() {
        return executeId;
    }

    public void executeId(Long executeId) {
        this.executeId = executeId;
    }


    @JsonGetter
    public Long userId() {
        return userId;
    }

    public void userId(Long userId) {
        this.userId = userId;
    }

    @JsonGetter
    public OrderExecutions orderExecution() {
        return orderExecution;
    }

    public void orderExecution(OrderExecutions oe) {
        this.orderExecution = oe;
    }

    @JsonGetter
    public Users user() {
        return user;
    }

    @JsonGetter
    public Equipments getEquipment() {
        return equipment;
    }

    @JsonGetter
    public Long equipmentId() {
        return equipmentId;
    }

    public void equipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void user(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("order information:").append("\n");
        if (id() != 0L) {
            builder.append("[").append(id()).append("] ");
        }
        if (customer() != null)
            builder.append("client: ").append(customer);
        if (employee != null)
            builder.append("worker: ").append(employee);
        if (equipment != null)
            builder.append(equipment);
        builder.append(", finish date is ").append(date);

        builder.append("\n");

        if (orderExecution != null) {
            String status = "";
            status = this.orderExecution.isReturned() > 0 ? " returned "
                    : " not returned ";
            builder.append("status: ").append(status).append(", cost: ").append(orderExecution.cost()).append("\n");
        }
        return builder.toString();
    }
}
