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

    public Long equipmentId() {
        return equipmentId;
    }

    public Long executeId() {
        return executeId;
    }

    public Long userId() {
        return userId;
    }
}
