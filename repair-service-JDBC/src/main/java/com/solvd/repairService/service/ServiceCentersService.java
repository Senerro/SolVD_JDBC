package com.solvd.repairService.service;

import com.solvd.repairService.DAO.ServiceCentersDAO;
import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
import com.solvd.repairService.DAO.interfaces.IUserDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.ServiceCenters;

import java.util.List;

public class ServiceCentersService {
    private final IServiceCenterDAO dao;

    public ServiceCentersService(IServiceCenterDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(ServiceCenters serviceCenter) {
        return dao.checkAvailability(serviceCenter);
    }

    public int delete(ServiceCenters serviceCenter) {
        return dao.delete(serviceCenter);
    }

    public ServiceCenters create(ServiceCenters serviceCenter) {
        return dao.create(serviceCenter);
    }

    public boolean updateServiceCenter(ServiceCenters from, ServiceCenters to) {
        return false;
    }

    public List<ServiceCenters> orderByBusing(boolean desc) {
        return null;
    }
}
