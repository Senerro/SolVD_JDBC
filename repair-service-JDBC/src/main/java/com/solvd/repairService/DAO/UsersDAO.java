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
        role = user.role() ? 1 : 0;
        String query = "INSERT INTO " + user.simpleName() + " (login, password, role) VALUES ('" + user.login() + "', '" + user.password() + "', " + role + ")";
        connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            connectionPool.returnConnection(connection);
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + user.simpleName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public Users findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = " + login;
        Users user = null;
        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                user = new Users(resultSet.getLong("id"));
                user.login(resultSet.getString("login"));
                user.password(resultSet.getString("password"));
                user.role(resultSet.getInt("role") > 0);
            }
            connectionPool.returnConnection(connection);
            return user;
        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
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
