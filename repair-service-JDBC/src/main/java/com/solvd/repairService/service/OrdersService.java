package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.model.*;

import java.util.List;

public class OrdersService {
    private final IOrderDAO dao;

    public OrdersService(IOrderDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(Orders model) {
        return dao.checkAvailability(model);
    }

    public int delete(Orders model) {
        return dao.delete(model);
    }

    public Orders create(CustomerProfiles profile, Equipments equipment, OrderExecutions orderExecution) {
        var order = new Orders(profile.id(), equipment, orderExecution);
        dao.create(order);

        return order;
    }

    public Orders selectById(Long id) {
        Orders order = new Orders(id);
        dao.selectById(order);
        return order;
    }

    public Orders changeRepairman(Orders order, EmployerProfiles repairman) {
        return dao.changeRepairman(order, repairman);
    }

    public Orders changeServiceCenter(Orders order, ServiceCenters serviceCenter) {
        return dao.changeServiceCenter(order, serviceCenter);
    }

    public List<Orders> ordersHistory(CustomerProfiles profiles) {
        return dao.orderHistory(profiles);

    }
}
