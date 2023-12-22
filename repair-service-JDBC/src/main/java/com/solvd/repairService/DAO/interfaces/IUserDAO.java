package com.solvd.repairService.DAO.interfaces;

import com.solvd.repairService.model.Users;

import java.util.List;

public interface IUserDAO extends IAbstractDAO{
    public Users create(Users user);
    public Users findByLogin(String login);
    public List<Users> selectByRole(boolean role);
    public Users updateUser(Users from, Users to);
    public boolean deleteUserByLogin(String login);
}
