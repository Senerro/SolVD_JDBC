package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.model.ServiceCenters;

public class OrdersDAO  extends AbstractDAO implements IOrderDAO {
    @Override
    public Orders create(Orders order) {
        return null;
    }

    @Override
    public Orders selectById(Orders order) {
        return null;
    }

    @Override
    public Orders changeRepairman(Orders order, EmployerProfiles repairman) {
        return null;
    }

    @Override
    public Orders changeServiceCenter(Orders order, ServiceCenters serviceCenter) {
        return null;
    }
}
