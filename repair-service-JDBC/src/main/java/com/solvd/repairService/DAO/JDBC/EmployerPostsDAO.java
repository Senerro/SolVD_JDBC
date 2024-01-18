package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IEmployeePostDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.helpers.queryConfigurationHelper.UpdateStatements;
import com.solvd.repairService.model.EmployeePosts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployerPostsDAO extends AbstractDAO implements IEmployeePostDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(UsersDAO.class);

    @Override
    public void create(EmployeePosts post) {

        ArrayList<String> fields = new ArrayList<>();
        fields.add("role");
        fields.add("description");

        ArrayList<String> values = new ArrayList<>();
        values.add(post.role());
        values.add(post.description());

        String query = InsertValuesHelper.get(post, fields, values);
        ResultSet result;
        PreparedStatement ps;
        connection = connectionPool.getConnection();
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            result = ps.getGeneratedKeys();
            connectionPool.returnConnection(connection);
            while (result.next()) {
                post.id(result.getLong("GENERATED_KEY"));
            }
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + post.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeePosts selectById(EmployeePosts post) {
        return null;
    }

    @Override
    public EmployeePosts changePostName(EmployeePosts from, EmployeePosts to) {
        return null;
    }

    @Override
    public void get(ArrayList<EmployeePosts> list) {

        String query = "SELECT * FROM employee_posts";
        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                var post = new EmployeePosts(
                        resultSet.getLong("id"),
                        resultSet.getString("role"),
                        resultSet.getString("description"));
                list.add(post);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            connectionPool.returnConnection(connection);
            LOGGER.error("Some error with table customer_profiles \n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(EmployeePosts post) {
        String query = "SELECT * FROM employee_posts WHERE id = " + post.id();
        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                post.role(resultSet.getString("role"));
                post.description(resultSet.getString("description"));
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            connectionPool.returnConnection(connection);
            LOGGER.error("Some error with table customer_profiles \n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(EmployeePosts from, EmployeePosts to) {
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
    }
}
