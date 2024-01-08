package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
import com.solvd.repairService.model.ServiceCenters;

import java.util.ArrayList;
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
        dao.create(serviceCenter);
        return serviceCenter;
    }

    public boolean updateServiceCenter(ServiceCenters from, ServiceCenters to) {
        return dao.update(from, to);
    }

    public List<ServiceCenters> orderByBusing(boolean desc) {
        return dao.orderByBusing(desc);
    }

    public ServiceCenters findUnoccupied() {
        ServiceCenters centers = new ServiceCenters();
        dao.findUnoccupied(centers);
        if (centers.id() <= 0)
            dao.findCrowded(centers);
        if (centers.id() <= 0)
            throw new RuntimeException("center isn't spotted");
        return centers;
    }

    public ArrayList<ServiceCenters> get() {
        ArrayList<ServiceCenters> serviceCenters = new ArrayList<>();
        dao.get(serviceCenters);
        return serviceCenters;
    }
    public ServiceCenters get(Long id) {
        ServiceCenters serviceCenter = new ServiceCenters(id);
        dao.get(serviceCenter);
        if(serviceCenter.address() == null)
            serviceCenter.id(0L);

        return serviceCenter;
    }

    public void update(ServiceCenters center, ServiceCenters newCenter) {
        dao.update(center, newCenter);
    }
}
