package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.ServiceCenters;

import java.util.List;

public interface IServiceCenterDAO extends IAbstractDAO{
    public ServiceCenters create(ServiceCenters serviceCenter);
    public boolean updateServiceCenter(ServiceCenters from, ServiceCenters to);
    public List<ServiceCenters> orderByBusing(boolean desc);
}
