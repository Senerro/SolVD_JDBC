package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.DAO.interfaces.IOrderExecuteDAO;
import com.solvd.repairService.helpers.calculateData.Calculate;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.Equipments;
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

    public OrderExecutions create(Equipments equipment, EmployerProfiles employee) {
        OrderExecutions orderExecution = new OrderExecutions();
        orderExecution.cost(Calculate.orderCost(equipment, employee));
        orderExecution.employerId(employee.id());
        orderExecution.finishDate(Calculate.workDayCount(employee));
        orderExecution.setReturned(false);
        orderExecution.serviceCenterId();

         dao.create(orderExecution);
         return orderExecution;
    }

    public boolean updateOrderExecution(OrderExecutions from, OrderExecutions to) {
        return dao.updateOrderExecution(from, to);
    }

}
