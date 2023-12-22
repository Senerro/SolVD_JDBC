package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IEmployerProfileDAO;
import com.solvd.repairService.model.EmployerPosts;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.OrderExecutions;

import java.util.List;

public class EmployerProfilesDAO  extends AbstractDAO implements IEmployerProfileDAO {
    @Override
    public EmployerProfiles create(EmployerProfiles profile) {
        return null;
    }

    @Override
    public EmployerProfiles selectById(EmployerProfiles profile) {
        return null;
    }

    @Override
    public List<EmployerProfiles> findByFullname(String fullname) {
        return null;
    }

    @Override
    public List<EmployerProfiles> findByPhone(String fullname) {
        return null;
    }

    @Override
    public List<EmployerProfiles> selectByExperience(double role, boolean desc) {
        return null;
    }

    @Override
    public EmployerProfiles updateProfile(EmployerProfiles from, EmployerProfiles to) {
        return null;
    }

    @Override
    public EmployerProfiles updatePost(EmployerProfiles employee, EmployerPosts post) {
        return null;
    }

    @Override
    public double setCost(EmployerProfiles employee, OrderExecutions orderExecution) {
        return 0;
    }
}
