package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IEmployeeProfileDAO;
import com.solvd.repairService.model.*;
import com.solvd.repairService.service.interfaces.IService;
import java.util.ArrayList;
import java.util.Random;

public class EmployerProfileService implements IService {
    private final IEmployeeProfileDAO dao;
    public ArrayList<AbstractModel> get() throws Exception {
        ArrayList<EmployeeProfiles> profiles = new ArrayList<>();
        dao.get(profiles);
        if(profiles.isEmpty())
            throw new Exception("Workers aren't exist");
        return new ArrayList<>(profiles);
    }
    public EmployeeProfiles get(Long id) {
        EmployeeProfiles profile = new EmployeeProfiles(id);
        dao.get(profile);
        if (profile.fullName() == null)
            return null;
        return profile;
    }

    @Override
    public void delete(AbstractModel model) {

    }

    public EmployerProfileService(IEmployeeProfileDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(EmployeeProfiles model) {
        return dao.checkAvailability(model);
    }

    public int delete(EmployeeProfiles model) {
        return dao.delete(model);
    }

    public EmployeeProfiles create(EmployeeProfiles profile) {

         dao.create(profile);
         return profile;
    }

    public EmployeeProfiles selectById(EmployeeProfiles profile) {
        return dao.selectById(profile);
    }

    public EmployeeProfiles findByServiceCenter(ServiceCenters center) {
        ArrayList<EmployeeProfiles> list = new ArrayList<>();
        EmployeeProfiles profile;
        dao.findFreeByServiceCenter(center, list);
        if (list.isEmpty())
            dao.findByServiceCenter(center, list);

        profile = list.get(new Random().nextInt(list.size()));
        return profile;
    }

    public void update(EmployeeProfiles worker, EmployeeProfiles newWorker) {
        dao.updateProfile(worker, newWorker);
    }
}
