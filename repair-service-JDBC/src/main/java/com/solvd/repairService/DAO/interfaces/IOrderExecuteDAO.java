package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.OrderExecutions;
import com.solvd.repairService.model.Orders;

import java.util.ArrayList;

public interface IOrderExecuteDAO extends IAbstractDAO {
    OrderExecutions selectById(OrderExecutions orderExecution);

    void create(OrderExecutions orderExecution);

    boolean updateOrderExecution(OrderExecutions from, OrderExecutions to);

    void get(ArrayList<OrderExecutions> list);
}
