package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IUserDAO;
import com.solvd.repairService.model.Users;
import java.util.List;

public class UsersDAO extends AbstractDAO implements IUserDAO {
    @Override
    public Users create(Users user) {
        return null;
    }

    @Override
    public Users findByLogin(String login) {
        return null;
    }


    @Override
    public List<Users> selectByRole(boolean role) {
        return null;
    }

    @Override
    public Users updateUser(Users from, Users to) {
        return null;
    }

    @Override
    public boolean deleteUserByLogin(String login) {
        return false;
    }
}
