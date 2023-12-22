package com.solvd.repairService.service;

import com.solvd.repairService.DAO.UsersDAO;
import com.solvd.repairService.model.Users;

import java.util.List;

public class UsersService {
    private final UsersDAO dao = new UsersDAO();

    public Users create(Users user) throws Exception {
        var isAvailable = dao.checkAvailability(user);
        if (isAvailable)
            return dao.create(user);
        else throw new Exception("user with login" + user.login() + "already existed");
    }

    public Users validateAccessData(Users user) throws Exception {
        var result = dao.checkAvailability(user);
        if (!result)
            throw new Exception("incorrect data of login or password");
        return user;
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
        if(!result) {
            var userForDelete = dao.findByLogin(login);
            if(userForDelete == null)
                throw new Exception("User with login " + login + " wasn't spotted");
            else
                throw new Exception("User with login " + login + " was spotted, but not deleted");
        }
        return result;
    }
    public int deleteUser(Users user) throws Exception {
        var result = dao.delete(user);
        if (result < 1)
            throw new Exception(user + " wasn't deleted");
        return result;
    }
    public boolean checkAvailability(Users user) throws Exception {
        var result = dao.checkAvailability(user);
        if (!result)
        {
            var tmp_user = dao.findByLogin(user.login());
            String exceptionMessage = "user " + user;
            exceptionMessage += tmp_user == null ?
                    " isn't exist in db"
                    : "exist in db, but his id is " + tmp_user.id() + " but not " + user.id() +" in input";
            throw new Exception(exceptionMessage);
        }
            return result;
    }

}
