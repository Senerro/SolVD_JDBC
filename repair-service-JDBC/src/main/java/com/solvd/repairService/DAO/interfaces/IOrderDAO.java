package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.CustomerProfiles;
import com.solvd.repairService.model.EmployeeProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.model.ServiceCenters;

import java.util.ArrayList;
import java.util.List;

public interface IOrderDAO extends IAbstractDAO {
    void create(Orders order);

    void selectById(Orders order);

    Orders changeRepairman(Orders order, EmployeeProfiles repairman);

    Orders changeServiceCenter(Orders order, ServiceCenters serviceCenter);

    ArrayList<Orders> orderHistory(CustomerProfiles profiles);

    void get(ArrayList<Orders> list);
    void get(Orders order);

    void update(Orders order, Orders newOrder);
}
