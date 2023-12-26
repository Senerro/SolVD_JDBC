package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.model.ServiceCenters;

import java.util.List;

public interface IOrderDAO extends IAbstractDAO {
    Orders create(Orders order);

    Orders selectById(Orders order);

    Orders changeRepairman(Orders order, EmployerProfiles repairman);

    Orders changeServiceCenter(Orders order, ServiceCenters serviceCenter);

    List<Orders> orderHistory(CustomerProfiles profiles);
}
