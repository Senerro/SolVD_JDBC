package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
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
        return dao.updateServiceCenter(from, to);
    }

    public List<ServiceCenters> orderByBusing(boolean desc) {
        return dao.orderByBusing(desc);
    }

    public ServiceCenters findUnoccupied() {
        ServiceCenters centers = new ServiceCenters();
        dao.findUnoccupied(centers);
        if(centers.id() <= 0)
            dao.findCrowded(centers);
        if(centers.id() <= 0)
            throw new RuntimeException("center isn't spotted");
        return centers;
    }
}
