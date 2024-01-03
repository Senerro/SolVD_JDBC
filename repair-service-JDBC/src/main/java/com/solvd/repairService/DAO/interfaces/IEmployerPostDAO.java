package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.EmployerPosts;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.Orders;
import com.solvd.repairService.model.ServiceCenters;

import java.util.ArrayList;

public interface IEmployerPostDAO  extends IAbstractDAO {
    public void create(EmployerPosts post);
    public EmployerPosts selectById(EmployerPosts post);
    public EmployerPosts changePostName(EmployerPosts from, EmployerPosts to);

    void get(ArrayList<EmployerPosts> list);
    void get(EmployerPosts post);

    void update(EmployerPosts post, EmployerPosts newPost);
}
