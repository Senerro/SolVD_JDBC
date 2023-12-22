package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.ServiceCenters;

import java.util.List;

public class ServiceCentersService  {
    public boolean checkAvailability(AbstractModel model) {
        return false;
    }

    public int delete(AbstractModel model) {
        return 0;
    }
   
    public ServiceCenters create(ServiceCenters serviceCenter) {
        return null;
    }
   
    public boolean updateServiceCenter(ServiceCenters from, ServiceCenters to) {
        return false;
    }
   
    public List<ServiceCenters> orderByBusing(boolean desc) {
        return null;
    }
}
