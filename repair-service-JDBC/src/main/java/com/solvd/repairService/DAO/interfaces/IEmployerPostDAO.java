package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.EmployeePosts;

import java.util.ArrayList;

public interface IEmployerPostDAO  extends IAbstractDAO {
    public void create(EmployeePosts post);
    public EmployeePosts selectById(EmployeePosts post);
    public EmployeePosts changePostName(EmployeePosts from, EmployeePosts to);

    void get(ArrayList<EmployeePosts> list);
    void get(EmployeePosts post);

    void update(EmployeePosts post, EmployeePosts newPost);
}
