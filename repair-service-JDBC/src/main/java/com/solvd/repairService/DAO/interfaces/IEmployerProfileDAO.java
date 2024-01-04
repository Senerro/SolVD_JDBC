package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.EmployeePosts;
import com.solvd.repairService.model.EmployeeProfiles;
import com.solvd.repairService.model.OrderExecutions;
import com.solvd.repairService.model.ServiceCenters;

import java.util.ArrayList;
import java.util.List;

public interface IEmployerProfileDAO extends IAbstractDAO {
    void create(EmployeeProfiles profile);
    void get(ArrayList<EmployeeProfiles> list);
    void get(EmployeeProfiles list);

    EmployeeProfiles selectById(EmployeeProfiles profile);

    List<EmployeeProfiles> findByFullname(String fullname);

    List<EmployeeProfiles> findByPhone(String phone);

    List<EmployeeProfiles> selectByExperience(double role, boolean desc);

    EmployeeProfiles updateProfile(EmployeeProfiles from, EmployeeProfiles to);

    EmployeeProfiles updatePost(EmployeeProfiles employee, EmployeePosts post);

    double setCost(EmployeeProfiles employee, OrderExecutions orderExecution);

    int findFreeByServiceCenter(ServiceCenters center, ArrayList<EmployeeProfiles> list);

    void findByServiceCenter(ServiceCenters center, ArrayList<EmployeeProfiles> list);
}
