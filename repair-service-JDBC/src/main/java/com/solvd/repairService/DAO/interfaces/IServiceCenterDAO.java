package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.ServiceCenters;

import java.util.List;

public interface IServiceCenterDAO extends IAbstractDAO {
    ServiceCenters create(ServiceCenters serviceCenter);

    boolean updateServiceCenter(ServiceCenters from, ServiceCenters to);

    List<ServiceCenters> orderByBusing(boolean desc);

    void findUnoccupied(ServiceCenters centers);

    void findCrowded(ServiceCenters centers);
}
