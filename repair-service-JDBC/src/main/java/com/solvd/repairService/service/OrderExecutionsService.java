package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.DAO.interfaces.IOrderExecuteDAO;
import com.solvd.repairService.helpers.calculateData.Calculate;
import com.solvd.repairService.model.*;

import java.util.ArrayList;

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

    public OrderExecutions create(Equipments equipment, EmployerProfiles employee, ServiceCenters center) {
        OrderExecutions orderExecution = new OrderExecutions();
        orderExecution.cost(Calculate.orderCost(equipment, employee));
        orderExecution.employerId(employee.id());
        orderExecution.finishDate(Calculate.workDayCount(employee));
        orderExecution.setReturned(false);
        orderExecution.serviceCenterId(center.id());

         dao.create(orderExecution);
         return orderExecution;
    }

    public boolean updateOrderExecution(OrderExecutions from, OrderExecutions to) {
        return dao.updateOrderExecution(from, to);
    }

    public ArrayList<OrderExecutions> get()
    {
        ArrayList<OrderExecutions> list = new ArrayList<>();
        dao.get(list);
        return list;
    }
}
