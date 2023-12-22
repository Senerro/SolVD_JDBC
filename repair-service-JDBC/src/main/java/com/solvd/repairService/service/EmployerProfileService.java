package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IEmployerProfileDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployerPosts;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.OrderExecutions;

import java.util.List;

public class EmployerProfileService {

    public boolean checkAvailability(AbstractModel model) {
        return false;
    }


    public int delete(AbstractModel model) {
        return 0;
    }


    public EmployerProfiles create(EmployerProfiles profile) {
        return null;
    }


    public EmployerProfiles selectById(EmployerProfiles profile) {
        return null;
    }


    public List<EmployerProfiles> findByFullname(String fullname) {
        return null;
    }


    public List<EmployerProfiles> findByPhone(String phone) {
        return null;
    }


    public List<EmployerProfiles> selectByExperience(double role, boolean desc) {
        return null;
    }


    public EmployerProfiles updateProfile(EmployerProfiles from, EmployerProfiles to) {
        return null;
    }


    public EmployerProfiles updatePost(EmployerProfiles employee, EmployerPosts post) {
        return null;
    }


    public double setCost(EmployerProfiles employee, OrderExecutions orderExecution) {
        return 0;
    }
}
