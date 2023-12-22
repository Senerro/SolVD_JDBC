package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.OrderExecutions;
import com.solvd.repairService.model.Orders;

public interface IOrderExecuteDAO extends IAbstractDAO{
    public OrderExecutions selectById(OrderExecutions orderExecution);
    public OrderExecutions create(OrderExecutions orderExecution);
    public boolean updateOrderExecution(OrderExecutions from, OrderExecutions to);
}
