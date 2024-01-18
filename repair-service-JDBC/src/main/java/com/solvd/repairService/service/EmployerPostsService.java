package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IEmployeePostDAO;
import com.solvd.repairService.model.EmployeePosts;

import java.util.ArrayList;

public class EmployerPostsService {
    private final IEmployeePostDAO dao;

    public EmployerPostsService(IEmployeePostDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(EmployeePosts model) {
        return dao.checkAvailability(model);
    }

    public int delete(EmployeePosts model) {
        return dao.delete(model);
    }

    public EmployeePosts create(EmployeePosts post) {
         dao.create(post);
         return post;
    }

    public EmployeePosts selectById(EmployeePosts post) {
        return dao.selectById(post);
    }
    public ArrayList<EmployeePosts> get()
    {
        ArrayList<EmployeePosts> list = new ArrayList<>();
        dao.get(list);
        return list;
    }
    public EmployeePosts get(Long id) {
        EmployeePosts post = new EmployeePosts(id);
        dao.get(post);
        return post;
    }

    public EmployeePosts changePostName(EmployeePosts from, EmployeePosts to) {
        return dao.changePostName(from, to);
    }


    public void update(EmployeePosts post, EmployeePosts newPost) {
        dao.update(post, newPost);
    }
}
