package com.solvd.repairService.model;

public class Orders extends AbstractModel{
    private Long userId;
    private Long equipmentId;
    private Long executeId;

    private Users user;
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

    public Equipments equipment() {
        return equipment;
    }
    public void equipment( Equipments equipment) {
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
    public void orderExecution(OrderExecutions oe)
    {
        this.orderExecution = oe;
    }

    public Users getUser() {
        return user;
    }

    public Equipments getEquipment() {
        return equipment;
    }

    public OrderExecutions getOrderExecution() {
        return orderExecution;
    }

    public void setEquipment(Equipments equipment) {
        this.equipment = equipment;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
