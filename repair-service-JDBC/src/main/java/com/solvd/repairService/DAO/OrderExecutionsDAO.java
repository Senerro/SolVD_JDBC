package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IOrderExecuteDAO;
import com.solvd.repairService.model.OrderExecutions;

public class OrderExecutionsDAO extends AbstractDAO implements IOrderExecuteDAO {
    @Override
    public OrderExecutions selectById(OrderExecutions orderExecution) {
        return null;
    }

    @Override
    public OrderExecutions create(OrderExecutions orderExecution) {
        return null;
    }

    @Override
    public boolean updateOrderExecution(OrderExecutions from, OrderExecutions to) {
        return false;
    }
}
