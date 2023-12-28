package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.EmployerPosts;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.OrderExecutions;
import com.solvd.repairService.model.ServiceCenters;

import java.util.ArrayList;
import java.util.List;

public interface IEmployerProfileDAO extends IAbstractDAO {
    EmployerProfiles create(EmployerProfiles profile);

    EmployerProfiles selectById(EmployerProfiles profile);

    List<EmployerProfiles> findByFullname(String fullname);

    List<EmployerProfiles> findByPhone(String phone);

    List<EmployerProfiles> selectByExperience(double role, boolean desc);

    EmployerProfiles updateProfile(EmployerProfiles from, EmployerProfiles to);

    EmployerProfiles updatePost(EmployerProfiles employee, EmployerPosts post);

    double setCost(EmployerProfiles employee, OrderExecutions orderExecution);

    int findFreeByServiceCenter(ServiceCenters center, ArrayList<EmployerProfiles> list);

    void findByServiceCenter(ServiceCenters center, ArrayList<EmployerProfiles> list);
}
