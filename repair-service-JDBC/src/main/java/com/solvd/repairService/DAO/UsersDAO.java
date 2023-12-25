package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IUserDAO;
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
        String query = "INSERT INTO " + user.tableName() + " (login, password, role) VALUES ('" + user.login() + "', '" + user.password() + "', " + role + ")";
        connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            connectionPool.returnConnection(connection);
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + user.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public Users findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = '" + login+"'";
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

       /* ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        if(from.login().equals(to.login())) {
            fields.add("login");
            values.add(to.login());
        }
        if(from.password().equals(to.password())) {
            fields.add("password");
            values.add(to.password());
        }
        StringBuilder fieldsSQL = new StringBuilder();
        for (int i = 0; i < fields.size(); i++) {
            fieldsSQL.append(fields.get(i));
            if(i + 1 < fields.size())
                fieldsSQL.append(", ");
        }
        StringBuilder valuesSQL = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            fieldsSQL.append("( ").append( values.get(i) ).append( " )");
            if(i + 1 < values.size())
                fieldsSQL.append(", ");
        }
        StringBuilder query = new StringBuilder();
        query.append("Update users SET (");
        for(int i = 0; )*/
       /* ArrayList<String> set = new ArrayList<>();

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
            throw new RuntimeException(e);*/
        return null;
        }



    @Override
    public boolean deleteUserByLogin(String login) {
        return false;
    }
}
