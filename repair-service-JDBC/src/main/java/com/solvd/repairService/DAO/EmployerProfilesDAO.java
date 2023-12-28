package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IEmployerProfileDAO;
import com.solvd.repairService.model.*;
import com.solvd.repairService.views.CustomerProfileView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployerProfilesDAO extends AbstractDAO implements IEmployerProfileDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }
    private final static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(CustomerProfileView.class);
    @Override
    public EmployerProfiles create(EmployerProfiles profile) {
        return null;
    }

    @Override
    public EmployerProfiles selectById(EmployerProfiles profile) {
        return null;
    }

    @Override
    public List<EmployerProfiles> findByFullname(String fullname) {
        return null;
    }

    @Override
    public List<EmployerProfiles> findByPhone(String fullname) {
        return null;
    }

    @Override
    public List<EmployerProfiles> selectByExperience(double role, boolean desc) {
        return null;
    }

    @Override
    public EmployerProfiles updateProfile(EmployerProfiles from, EmployerProfiles to) {
        return null;
    }

    @Override
    public EmployerProfiles updatePost(EmployerProfiles employee, EmployerPosts post) {
        return null;
    }

    @Override
    public double setCost(EmployerProfiles employee, OrderExecutions orderExecution) {
        return 0;
    }
    @Override
    public int findFreeByServiceCenter(ServiceCenters center, ArrayList<EmployerProfiles> list) {
        String query = " SELECT DISTINCT  ep.id, ep.fullName, ep.phone, ep.postId, ep.experience, ep.serviceCenterId from employer_profiles as ep\n" +
                " JOIN order_executions AS oe ON ep.id <> oe.employerId\n" +
                " JOIN service_centers AS sc ON sc.id = ep.serviceCenterId\n" +
                " WHERE sc.id = " + center.id();

        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                EmployerProfiles profile = new EmployerProfiles(resultSet.getLong("id"));
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
    public void findByServiceCenter(ServiceCenters center, ArrayList<EmployerProfiles> list) {
        String query = " SELECT DISTINCT  ep.id, ep.fullName, ep.phone, ep.postId, ep.experience, ep.serviceCenterId from employer_profiles as ep\n" +
                " JOIN service_centers AS sc ON sc.id = ep.serviceCenterId\n" +
                " WHERE sc.id = " + center.id();

        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                EmployerProfiles profile = new EmployerProfiles(resultSet.getLong("id"));
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
