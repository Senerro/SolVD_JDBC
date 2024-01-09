package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IUserDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.helpers.queryConfigurationHelper.UpdateStatements;
import com.solvd.repairService.model.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends AbstractDAO implements IUserDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(UsersDAO.class);

    @Override
    public void create(Users user) {
        int role;
        role = user.role() ? 1 : 0;

        ArrayList<String> fields = new ArrayList<>();
        fields.add("login");
        fields.add("password");
        fields.add("role");

        ArrayList<String> values = new ArrayList<>();
        values.add(user.login());
        values.add(user.password());
        values.add(String.format("%d", role));

        String query = InsertValuesHelper.get(user, fields, values);
        ResultSet result;
        PreparedStatement ps;
        connection = connectionPool.getConnection();
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            result = ps.getGeneratedKeys();
            connectionPool.returnConnection(connection);
            while (result.next()) {
                user.id(result.getLong("GENERATED_KEY"));
            }
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + user.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Users findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = '" + login + "'";
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
            statement.close();
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
        String query = UpdateStatements.get(from, to);
        connection = connectionPool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            connectionPool.returnConnection(connection);
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + from.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
        return to;
    }


    @Override
    public boolean deleteUserByLogin(String login) {
        return false;
    }

    @Override
    public boolean acceptPassword(String password, Long id) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM users WHERE id = ").append(id)
                .append(" AND password = ").append("'").append(password).append("'");
        String query = builder.toString();
        connection = connectionPool.getConnection();
        boolean isDetected = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                isDetected = true;
                break;
            }
            connectionPool.returnConnection(connection);
            return isDetected;
        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }
}