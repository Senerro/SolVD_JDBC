package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.EmployerPosts;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.OrderExecutions;
import com.solvd.repairService.model.ServiceCenters;

import java.util.ArrayList;
import java.util.List;

public interface IEmployerProfileDAO  extends IAbstractDAO  {
    public EmployerProfiles create(EmployerProfiles profile);
    public EmployerProfiles selectById(EmployerProfiles profile);
    public List<EmployerProfiles> findByFullname(String fullname);
    public List<EmployerProfiles> findByPhone(String phone);
    public List<EmployerProfiles> selectByExperience(double role, boolean desc);
    public EmployerProfiles updateProfile(EmployerProfiles from, EmployerProfiles to);
    public EmployerProfiles updatePost(EmployerProfiles employee, EmployerPosts post);
    public double setCost(EmployerProfiles employee, OrderExecutions orderExecution);

    int findByServiceCenter(ServiceCenters center, ArrayList<EmployerProfiles> list);
}
