package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IEmployerPostDAO;
import com.solvd.repairService.DAO.interfaces.IEmployerProfileDAO;
import com.solvd.repairService.DAO.interfaces.IEquipmentDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployerPosts;

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
        return dao.create(post);
    }

    public EmployerPosts selectById(EmployerPosts post) {
        return dao.selectById(post);
    }

    public EmployerPosts changePostName(EmployerPosts from, EmployerPosts to) {
        return dao.changePostName(from, to);
    }
}
