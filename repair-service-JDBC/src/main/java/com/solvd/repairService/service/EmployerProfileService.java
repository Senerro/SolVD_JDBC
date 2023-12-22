package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IEmployerProfileDAO;
import com.solvd.repairService.DAO.interfaces.IEquipmentDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployerPosts;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.OrderExecutions;

import java.util.List;

public class EmployerProfileService {
    private final IEmployerProfileDAO dao;

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
        return dao.create(profile);
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
}
