package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.model.ServiceCenters;
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

    public Orders create(Orders order) {
        return dao.create(order);
    }

    public Orders selectById(Orders order) {
        return dao.selectById(order);
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
