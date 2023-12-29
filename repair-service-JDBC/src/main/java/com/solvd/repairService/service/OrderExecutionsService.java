package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.DAO.interfaces.IOrderExecuteDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.OrderExecutions;

public class OrderExecutionsService {
    private final IOrderExecuteDAO dao;

    public OrderExecutionsService(IOrderExecuteDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(OrderExecutions model) {
        return dao.checkAvailability(model);
    }

    public int delete(OrderExecutions model) {
        return dao.delete(model);
    }

    public OrderExecutions selectById(OrderExecutions orderExecution) {
        return dao.selectById(orderExecution);
    }

    public OrderExecutions create(OrderExecutions orderExecution) {
        return dao.create(orderExecution);
    }

    public boolean updateOrderExecution(OrderExecutions from, OrderExecutions to) {
        return dao.updateOrderExecution(from, to);
    }
}
