package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.ServiceCenters;
import com.solvd.repairService.service.interfaces.IService;
import java.util.ArrayList;

public class ServiceCentersService implements IService {
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

    public ServiceCenters findUnoccupied() {
        ServiceCenters centers = new ServiceCenters();
        dao.findUnoccupied(centers);
        if (centers.id() <= 0)
            dao.findCrowded(centers);
        if (centers.id() <= 0)
            throw new RuntimeException("center isn't spotted");
        return centers;
    }
   public ArrayList<AbstractModel> get() {
       ArrayList<ServiceCenters> serviceCenters = new ArrayList<>();
       dao.get(serviceCenters);
       return new ArrayList<>(serviceCenters);
   }
    public ServiceCenters get(Long id) {
        ServiceCenters serviceCenter = new ServiceCenters(id);
        dao.get(serviceCenter);
        if(serviceCenter.address() == null)
            serviceCenter.id(0L);

        return serviceCenter;
    }

    @Override
    public void delete(AbstractModel model) {

    }

    public void update(ServiceCenters center, ServiceCenters newCenter) {
        dao.update(center, newCenter);
    }
}
