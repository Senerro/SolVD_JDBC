package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IEmployerPostDAO;
import com.solvd.repairService.DAO.interfaces.IEquipmentDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployerPosts;

public class EmployerPostsService {

    public boolean checkAvailability(EmployerPosts model) {
        return false;
    }


    public int delete(EmployerPosts model) {
        return 0;
    }


    public EmployerPosts create(EmployerPosts post) {
        return null;
    }


    public EmployerPosts selectById(EmployerPosts post) {
        return null;
    }

    public EmployerPosts changePostName(EmployerPosts from, EmployerPosts to) {
        return null;
    }
}
