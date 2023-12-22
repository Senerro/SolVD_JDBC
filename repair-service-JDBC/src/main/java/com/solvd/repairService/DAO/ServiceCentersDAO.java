package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
import com.solvd.repairService.model.ServiceCenters;

import java.util.List;

public class ServiceCentersDAO  extends AbstractDAO implements IServiceCenterDAO {
    @Override
    public ServiceCenters create(ServiceCenters serviceCenter) {
        return null;
    }

    @Override
    public boolean updateServiceCenter(ServiceCenters from, ServiceCenters to) {
        return false;
    }

    @Override
    public List<ServiceCenters> orderByBusing(boolean desc) {
        return null;
    }
}
