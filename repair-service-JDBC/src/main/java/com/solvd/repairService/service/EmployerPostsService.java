package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IEmployerPostDAO;
import com.solvd.repairService.DAO.interfaces.IEmployerProfileDAO;
import com.solvd.repairService.DAO.interfaces.IEquipmentDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployerPosts;

import java.util.ArrayList;

public class EmployerPostsService {
    private final IEmployerPostDAO dao;

    public EmployerPostsService(IEmployerPostDAO dao) {
        this.dao = dao;
    }

    public boolean checkAvailability(EmployerPosts model) {
        return dao.checkAvailability(model);
    }

    public int delete(EmployerPosts model) {
        return dao.delete(model);
    }

    public EmployerPosts create(EmployerPosts post) {
         dao.create(post);
         return post;
    }

    public EmployerPosts selectById(EmployerPosts post) {
        return dao.selectById(post);
    }
    public ArrayList<EmployerPosts> get()
    {
        ArrayList<EmployerPosts> list = new ArrayList<>();
        dao.get(list);
        return list;
    }
    public EmployerPosts get(Long id) {
        EmployerPosts post = new EmployerPosts(id);
        dao.get(post);
        return post;
    }

    public EmployerPosts changePostName(EmployerPosts from, EmployerPosts to) {
        return dao.changePostName(from, to);
    }


    public void update(EmployerPosts post, EmployerPosts newPost) {
        dao.update(post, newPost);
    }
}
