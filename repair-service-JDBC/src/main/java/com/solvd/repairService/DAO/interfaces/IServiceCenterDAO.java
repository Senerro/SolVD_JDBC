package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.ServiceCenters;

import java.util.ArrayList;
import java.util.List;

public interface IServiceCenterDAO extends IAbstractDAO {
    void create(ServiceCenters serviceCenter);

    boolean update(ServiceCenters from, ServiceCenters to);

    List<ServiceCenters> orderByBusing(boolean desc);

    void findUnoccupied(ServiceCenters centers);

    void findCrowded(ServiceCenters centers);

    void get(ArrayList<ServiceCenters> serviceCenters);
    void get(ServiceCenters center);
}
