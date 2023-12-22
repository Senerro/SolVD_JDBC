package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.DAO.interfaces.IProblemDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.model.ServiceCenters;

public class OrdersService {
    private final IOrderDAO dao;

    public OrdersService(IOrderDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(AbstractModel model) {
        return false;
    }

    public int delete(AbstractModel model) {
        return 0;
    }

    public Orders create(Orders order) {
        return null;
    }

    public Orders selectById(Orders order) {
        return null;
    }

    public Orders changeRepairman(Orders order, EmployerProfiles repairman) {
        return null;
    }

    public Orders changeServiceCenter(Orders order, ServiceCenters serviceCenter) {
        return null;
    }
}
