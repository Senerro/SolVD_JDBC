package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.helpers.queryConfigurationHelper.UpdateStatements;
import com.solvd.repairService.model.EmployerPosts;
import com.solvd.repairService.model.EmployerProfiles;
import com.solvd.repairService.model.ServiceCenters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceCentersDAO extends AbstractDAO implements IServiceCenterDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(ServiceCentersDAO.class);

    @Override
    public void create(ServiceCenters center) {
        ArrayList<String> fields = new ArrayList<>();
        fields.add("name");
        fields.add("address");
        fields.add("description");

        ArrayList<String> values = new ArrayList<>();
        values.add(center.name());
        values.add(center.address());
        values.add(center.description());

        String query = InsertValuesHelper.get(center, fields, values);
        ResultSet result;
        PreparedStatement ps;
        connection = connectionPool.getConnection();
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            result = ps.getGeneratedKeys();
            connectionPool.returnConnection(connection);
            while (result.next()) {
                center.id(result.getLong("GENERATED_KEY"));
            }
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + center.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(ServiceCenters from, ServiceCenters to) {
        String query = UpdateStatements.get(from, to, to.id());
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
        return true;
    }

    @Override
    public List<ServiceCenters> orderByBusing(boolean desc) {
        return null;
    }

    @Override
    public void findUnoccupied(ServiceCenters center) {
        String query = "\n" +
                " SELECT sc.id, sc.name, sc.address, sc.photo, sc.description, MIN(myCount) FROM \n" +
                " (SELECT *, COUNT(serviceCenterID) as myCount FROM order_executions WHERE returned = 0 GROUP BY serviceCenterID) \n" +
                " AS result\n" +
                " JOIN service_centers AS sc ON sc.id = result.serviceCenterId";

        PreparedStatement ps;
        ResultSet result;
        ServiceCenters serviceCenter;
        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                center.id(resultSet.getLong("id"));
                center.name(resultSet.getString("name"));
                center.address(resultSet.getString("address"));
                center.photo(resultSet.getBytes("photo"));
                center.description(resultSet.getString("description"));
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
    public void findCrowded(ServiceCenters center) {
        String query = "SELECT *, MAX(myCount) FROM\n " +
                "( \n" +
                " SELECT sc.id, sc.name, sc.address, sc.photo, sc.description, COUNT(sc.id) as myCount FROM service_centers AS sc\n" +
                " JOIN employer_profiles AS ep ON ep.serviceCenterId = sc.id\n" +
                " GROUP BY serviceCenterId\n" +
                " ) AS f";

        PreparedStatement ps;
        ResultSet resultSet;
        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                center.id(resultSet.getLong("id"));
                center.name(resultSet.getString("name"));
                center.address(resultSet.getString("address"));
                center.photo(resultSet.getBytes("photo"));
                center.description(resultSet.getString("description"));
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
    public void get(ArrayList<ServiceCenters> list) {
        String query =
                " SELECT * FROM service_centers";

        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ServiceCenters center = new ServiceCenters(resultSet.getLong("id"));
                center.address(resultSet.getString("address"));
                center.name(resultSet.getString("name"));
                list.add(center);
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
    public void get(ServiceCenters center) {
        String query = " SELECT * FROM service_centers";
        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                center.address(resultSet.getString("address"));
                center.name(resultSet.getString("name"));
                center.photo(resultSet.getBytes("photo"));
                center.description(resultSet.getString("description"));
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
