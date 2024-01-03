package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IEmployerProfileDAO;
import com.solvd.repairService.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployerProfileService {
    private final IEmployerProfileDAO dao;

    public ArrayList<EmployerProfiles> get() throws Exception {
        ArrayList<EmployerProfiles> profiles = new ArrayList<>();
        dao.get(profiles);
        if(profiles.isEmpty())
            throw new Exception("Workers aren't exist");
        return profiles;
    }
    public EmployerProfiles get(Long id) {
        EmployerProfiles profile = new EmployerProfiles(id);
        dao.get(profile);
        return profile;
    }

    public EmployerProfileService(IEmployerProfileDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(EmployerProfiles model) {
        return dao.checkAvailability(model);
    }

    public int delete(EmployerProfiles model) {
        return dao.delete(model);
    }

    public EmployerProfiles create(EmployerProfiles profile) {

         dao.create(profile);
         return profile;
    }

    public EmployerProfiles selectById(EmployerProfiles profile) {
        return dao.selectById(profile);
    }

    public List<EmployerProfiles> findByFullname(String fullname) {
        return dao.findByFullname(fullname);
    }

    public List<EmployerProfiles> findByPhone(String phone) {
        return dao.findByPhone(phone);
    }

    public List<EmployerProfiles> selectByExperience(double role, boolean desc) {
        return dao.selectByExperience(role, desc);
    }

    public EmployerProfiles updateProfile(EmployerProfiles from, EmployerProfiles to) {
        return dao.updateProfile(from, to);
    }

    public EmployerProfiles updatePost(EmployerProfiles employee, EmployerPosts post) {
        return dao.updatePost(employee, post);
    }

    public double setCost(EmployerProfiles employee, OrderExecutions orderExecution) {
        return dao.setCost(employee, orderExecution);
    }

    public EmployerProfiles findByServiceCenter(ServiceCenters center) {
        ArrayList<EmployerProfiles> list = new ArrayList<>();
        EmployerProfiles profile = null;
        dao.findFreeByServiceCenter(center, list);
        if (list.isEmpty())
            dao.findByServiceCenter(center, list);

        profile = list.get(new Random().nextInt(list.size()));
        return profile;
    }

    public void detectEquipmentProblem(EmployerProfiles employee, Equipments equipment, ServiceCenters center) {


    }

    public void update(EmployerProfiles worker, EmployerProfiles newWorker) {
        dao.updateProfile(worker, newWorker);
    }
}
