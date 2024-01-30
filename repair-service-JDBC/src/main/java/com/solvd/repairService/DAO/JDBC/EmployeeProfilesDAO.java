package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IEmployeeProfileDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.model.*;
import com.solvd.repairService.views.CustomerProfileView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeProfilesDAO extends AbstractDAO implements IEmployeeProfileDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private static final Logger LOGGER = LogManager.getLogger(CustomerProfileView.class);
    @Override
    public void create(EmployeeProfiles profile) {

        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(profile.id()));
        values.add(profile.fullName());
        values.add(profile.phone());
        values.add(String.format("%d", profile.postId()));
        values.add(String.valueOf(profile.experience()));
        values.add(String.format("%d", profile.serviceCenterId()));

        String query = "INSERT INTO employee_profiles VALUES "+InsertValuesHelper.values(values);

        ResultSet result;
        PreparedStatement ps;
        connection = connectionPool.getConnection();
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            result = ps.getGeneratedKeys();
            connectionPool.returnConnection(connection);
            while (result.next()) {
                profile.id(result.getLong("GENERATED_KEY"));
            }
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + profile.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(ArrayList<EmployeeProfiles> list) {
        String query =
                " SELECT w.id, fullName AS name, phone, ep.role, experience " +
                " FROM employee_profiles AS w \n" +
                " JOIN employee_posts AS ep ON ep.id = postId";

        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                EmployeeProfiles profile = new EmployeeProfiles(resultSet.getLong("id"));
                profile.fullName(resultSet.getString("name"));
                profile.phone(resultSet.getString("phone"));
                profile.experience(resultSet.getInt("experience"));

                EmployeePosts post = new EmployeePosts();
                post.role(resultSet.getString("role"));

                profile.post(post);
                list.add(profile);
            }
            connectionPool.returnConnection(connection);
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(EmployeeProfiles model) {
        String query =
                " SELECT w.id, fullName AS name, phone, ep.role, experience, sc.id AS centerId," +
                        " sc.name AS centerName, sc.address FROM employee_profiles AS w \n" +
                        " LEFT JOIN service_centers AS sc ON sc.id = serviceCenterId \n" +
                        " LEFT JOIN employee_posts AS ep ON ep.id = postId \n" +
                        " WHERE w.id = " + model.id();

        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                model.fullName(resultSet.getString("name"));
                model.phone(resultSet.getString("phone"));
                model.experience(resultSet.getInt("experience"));
                model.serviceCenterId(resultSet.getLong("centerId"));

                EmployeePosts post = new EmployeePosts();
                post.role(resultSet.getString("role"));

                ServiceCenters center = new ServiceCenters(resultSet.getLong("centerId"));
                center.name(resultSet.getString("centerName"));
                center.address(resultSet.getString("address"));

                model.serviceCenters(center);
                model.post(post);
            }
            connectionPool.returnConnection(connection);
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeProfiles selectById(EmployeeProfiles profile) {
        return null;
    }

    @Override
    public List<EmployeeProfiles> findByFullname(String fullname) {
        return null;
    }

    @Override
    public List<EmployeeProfiles> findByPhone(String fullname) {
        return null;
    }

    @Override
    public List<EmployeeProfiles> selectByExperience(double role, boolean desc) {
        return null;
    }

    @Override
    public EmployeeProfiles updateProfile(EmployeeProfiles from, EmployeeProfiles to) {
        String query = "UPDATE employee_profiles SET fullName = '"+to.fullName()+"', phone = '"+to.phone()+"', experience = " + to.experience() +
                " WHERE id = " + to.id();
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
    public EmployeeProfiles updatePost(EmployeeProfiles employee, EmployeePosts post) {
        return null;
    }

    @Override
    public double setCost(EmployeeProfiles employee, OrderExecutions orderExecution) {
        return 0;
    }
    @Override
    public int findFreeByServiceCenter(ServiceCenters center, ArrayList<EmployeeProfiles> list) {
        String query = " SELECT DISTINCT  ep.id, ep.fullName, ep.phone, ep.postId, ep.experience, ep.serviceCenterId from employee_profiles as ep\n" +
                " JOIN order_executions AS oe ON ep.id <> oe.employerId\n" +
                " JOIN service_centers AS sc ON sc.id = ep.serviceCenterId\n" +
                " WHERE sc.id = " + center.id();

        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                EmployeeProfiles profile = new EmployeeProfiles(resultSet.getLong("id"));
                profile.fullName(resultSet.getString("fullName"));
                profile.phone(resultSet.getString("phone"));
                profile.postId(resultSet.getLong("postId"));
                profile.experience(resultSet.getInt("experience"));
                profile.serviceCenterId(center.id());

                list.add(profile);
            }
            connectionPool.returnConnection(connection);
            statement.close();
            return list.size();
        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findByServiceCenter(ServiceCenters center, ArrayList<EmployeeProfiles> list) {
        String query = " SELECT DISTINCT  ep.id, ep.fullName, ep.phone, ep.postId, ep.experience, ep.serviceCenterId from employee_profiles as ep\n" +
                " JOIN service_centers AS sc ON sc.id = ep.serviceCenterId\n" +
                " WHERE sc.id = " + center.id();

        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                EmployeeProfiles profile = new EmployeeProfiles(resultSet.getLong("id"));
                profile.fullName(resultSet.getString("fullName"));
                profile.phone(resultSet.getString("phone"));
                profile.postId(resultSet.getLong("postId"));
                profile.experience(resultSet.getInt("experience"));
                profile.serviceCenterId(center.id());

                list.add(profile);
            }
            connectionPool.returnConnection(connection);
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }
}
