package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.EmployerPosts;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.model.ServiceCenters;

public interface IEmployerPostDAO {
    public EmployerPosts create(EmployerPosts post);
    public EmployerPosts selectById(EmployerPosts post);
    public EmployerPosts changePostName(EmployerPosts from, EmployerPosts to);

}
