package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IUserDAO;
import com.solvd.repairService.Main;
import com.solvd.repairService.model.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsersDAO extends AbstractDAO implements IUserDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private static final Logger LOGGER = LogManager.getLogger(UsersDAO.class);
    @Override
    public Users create(Users user) {
        int role;
        role = user.role() ? 1:0;
        String query = "INSERT INTO " + user.simpleName() + " (login, password, role) VALUES ('" + user.login() + "', '" + user.password()+ "', "+role+")";
        connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet key =  ps.getGeneratedKeys();
//            LOGGER.debug(key.getInt(0));
            ps.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + user.simpleName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
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
