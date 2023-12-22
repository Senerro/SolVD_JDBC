package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.model.ServiceCenters;

public interface IOrderDAO extends IAbstractDAO {
    public Orders create(Orders order);
    public Orders selectById(Orders order);
    public Orders changeRepairman(Orders order, EmployerProfiles repairman);
    public Orders changeServiceCenter(Orders order, ServiceCenters serviceCenter);
}
