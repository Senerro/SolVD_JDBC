package com.solvd.repairService.service;

import com.solvd.repairService.DAO.UsersDAO;
import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.DAO.interfaces.IUserDAO;
import com.solvd.repairService.model.AbstractModel;
import com.solvd.repairService.model.Users;

import java.util.List;

public class UsersService{
    private final IUserDAO dao;

    public UsersService(IUserDAO dao) {
        this.dao = dao;
    }

    public Users create(Users user) throws Exception {
        var users = dao.findByLogin(user.login());
        if (users == null)
            dao.create(user);
        else throw new Exception("User with login" + user.login() + "already existed");

        return dao.findByLogin(user.login());
    }

    public Users findUserByLogin(String login) throws Exception {
        Users user = dao.findByLogin(login);
        if (user == null)
            throw new Exception("User with login " + login + " wasn't spotted");
        return user;
    }

    public List<Users> selectByRole(boolean role) throws Exception {
        List<Users> usersList = dao.selectByRole(role);
        if (usersList.isEmpty()) {
            if (role)
                throw new Exception("We don't have any employee");
            else
                throw new Exception("We don't have any customers in database");
        }
        return usersList;
    }

    public Users updateUser(Users from, Users to) throws Exception {
        return dao.updateUser(findUserByLogin(from.login()), to);
    }

    public boolean deleteUserByLogin(String login) throws Exception {
        var result = dao.deleteUserByLogin(login);
        if (!result) {
            var userForDelete = dao.findByLogin(login);
            if (userForDelete == null)
                throw new Exception("User with login " + login + " wasn't spotted");
            else
                throw new Exception("User with login " + login + " was spotted, but not deleted");
        }
        return result;
    }

    public int delete(Users user) throws Exception {
        var result = dao.delete(user);
        if (result < 1)
            throw new Exception(user + " wasn't deleted");
        return result;
    }

    public boolean acceptPassword(String oldPass, Long id)
    {
        return dao.acceptPassword(oldPass, id);
    }
}
