package com.solvd.repairService.model;

public class Orders extends AbstractModel{
    private Long userId;
    private Long equipmentId;
    private Long executeId;

    private Users user;
    private EmployerProfiles employee;
    private CustomerProfiles customer;
    private ServiceCenters center;
    private Equipments equipment;
    private OrderExecutions orderExecution;
    public Orders(Long id) {
        super(id, Orders.class);
    }
    public Orders(Long id, Long userId, Long equipmentId, Long executeId)
    {
        this(id);
        this.equipmentId = equipmentId;
        this.userId = userId;
        this.executeId = executeId;
    }
    public Orders(Long userId, Equipments equipment, OrderExecutions oe)
    {
        this(0L);
        this.equipment = equipment;
        this.equipmentId = equipment.id();
        this.userId = userId;
        this.orderExecution = oe;
        this.executeId = oe.id();
    }

    public Equipments equipment() {
        return equipment;
    }

    public EmployerProfiles employee() {
        return employee;
    }

    public void employee(EmployerProfiles employee) {
        this.employee = employee;
    }

    public CustomerProfiles customer() {
        return customer;
    }

    public void customer(CustomerProfiles customer) {
        this.customer = customer;
    }

    public ServiceCenters center() {
        return center;
    }

    public void center(ServiceCenters center) {
        this.center = center;
    }

    public void equipment(Equipments equipment) {
        this.equipment = equipment;
    }

    public Long executeId() {
        return executeId;
    }
    public void executeId(Long executeId) {
       this.executeId = executeId;
    }


    public Long userId() {
        return userId;
    }
    public void userId(Long userId) {
        this.userId = userId;
    }
    public OrderExecutions getOrderExecution() {
        return orderExecution;
    }
    public void orderExecution(OrderExecutions oe)
    {
        this.orderExecution = oe;
    }
    public Users user() {
        return user;
    }

    public Equipments getEquipment() {
        return equipment;
    }

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
        String status = this.orderExecution.isReturned() > 0 ? " returned "
                                                             : " not returned ";
        return "Number of order is " + this.id() + ". Device is " + this.equipment.type() + " " + this.equipment.producer() + " " + this.equipment.model() + "\n"
                + " Status: " + status + ", repair duration is " + this.orderExecution.finishDate() + ", cost is " + this.orderExecution.cost() + "\n";
    }
}
