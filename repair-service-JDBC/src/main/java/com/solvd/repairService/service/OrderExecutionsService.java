package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IOrderExecuteDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.OrderExecutions;

public class OrderExecutionsService implements IOrderExecuteDAO {
    
    public boolean checkAvailability(AbstractModel model) {
        return false;
    }

    
    public int delete(AbstractModel model) {
        return 0;
    }

    
    public OrderExecutions selectById(OrderExecutions orderExecution) {
        return null;
    }

    
    public OrderExecutions create(OrderExecutions orderExecution) {
        return null;
    }

    
    public boolean updateOrderExecution(OrderExecutions from, OrderExecutions to) {
        return false;
    }
}
