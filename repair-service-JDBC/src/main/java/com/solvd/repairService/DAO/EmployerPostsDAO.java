package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IEmployerPostDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployerPosts;

public class EmployerPostsDAO implements IEmployerPostDAO {
    @Override
    public EmployerPosts create(EmployerPosts post) {
        return null;
    }

    @Override
    public EmployerPosts selectById(EmployerPosts post) {
        return null;
    }

    @Override
    public EmployerPosts changePostName(EmployerPosts from, EmployerPosts to) {
        return null;
    }

    @Override
    public boolean checkAvailability(AbstractModel model) {
        return false;
    }
    @Override
    public int delete(AbstractModel model) {
        return 0;
    }
}