package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IEmployeeProfileDAO;
import com.solvd.repairService.model.*;
import com.solvd.repairService.service.interfaces.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployerProfileService implements IService {
    private final IEmployeeProfileDAO dao;

    public ArrayList<EmployeeProfiles> get() throws Exception {
        ArrayList<EmployeeProfiles> profiles = new ArrayList<>();
        dao.get(profiles);
        if(profiles.isEmpty())
            throw new Exception("Workers aren't exist");
        return profiles;
    }
    public EmployeeProfiles get(Long id) {
        EmployeeProfiles profile = new EmployeeProfiles(id);
        dao.get(profile);
        if (profile.fullName() == null)
            return null;
        return profile;
    }

    @Override
    public void add(AbstractModel model) {

    }

    @Override
    public void update(AbstractModel model) {

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

    public List<EmployeeProfiles> findByFullname(String fullname) {
        return dao.findByFullname(fullname);
    }

    public List<EmployeeProfiles> findByPhone(String phone) {
        return dao.findByPhone(phone);
    }

    public List<EmployeeProfiles> selectByExperience(double role, boolean desc) {
        return dao.selectByExperience(role, desc);
    }

    public EmployeeProfiles updateProfile(EmployeeProfiles from, EmployeeProfiles to) {
        return dao.updateProfile(from, to);
    }

    public EmployeeProfiles updatePost(EmployeeProfiles employee, EmployeePosts post) {
        return dao.updatePost(employee, post);
    }

    public double setCost(EmployeeProfiles employee, OrderExecutions orderExecution) {
        return dao.setCost(employee, orderExecution);
    }

    public EmployeeProfiles findByServiceCenter(ServiceCenters center) {
        ArrayList<EmployeeProfiles> list = new ArrayList<>();
        EmployeeProfiles profile = null;
        dao.findFreeByServiceCenter(center, list);
        if (list.isEmpty())
            dao.findByServiceCenter(center, list);

        profile = list.get(new Random().nextInt(list.size()));
        return profile;
    }

    public void detectEquipmentProblem(EmployeeProfiles employee, Equipments equipment, ServiceCenters center) {


    }

    public void update(EmployeeProfiles worker, EmployeeProfiles newWorker) {
        dao.updateProfile(worker, newWorker);
    }
}
