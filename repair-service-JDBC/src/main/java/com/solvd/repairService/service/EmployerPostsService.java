package com.solvd.repairService.service;

import com.solvd.repairService.DAO.interfaces.IEmployerPostDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.EmployeePosts;
import com.solvd.repairService.service.interfaces.IService;

import java.util.ArrayList;

public class EmployerPostsService implements IService {
    private final IEmployerPostDAO dao;

    public EmployerPostsService(IEmployerPostDAO dao) {
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
    /*public ArrayList<EmployeePosts> get()
    {
        ArrayList<EmployeePosts> list = new ArrayList<>();
        dao.get(list);
        return list;
    }*/
    public ArrayList<AbstractModel> get()
    {
        ArrayList<EmployeePosts> list = new ArrayList<>();
        dao.get(list);
        return new ArrayList<>(list);
    }
    public EmployeePosts get(Long id) {
        EmployeePosts post = new EmployeePosts(id);
        dao.get(post);
        return post;
    }

    @Override
    public void delete(AbstractModel model) {

    }

    public EmployeePosts changePostName(EmployeePosts from, EmployeePosts to) {
        return dao.changePostName(from, to);
    }


    public void update(EmployeePosts post, EmployeePosts newPost) {
        dao.update(post, newPost);
    }
}
