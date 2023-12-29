package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IServiceCenterDAO;
import com.solvd.repairService.model.ServiceCenters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ServiceCentersDAO extends AbstractDAO implements IServiceCenterDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(ServiceCentersDAO.class);

    @Override
    public ServiceCenters create(ServiceCenters serviceCenter) {
        return null;
    }

    @Override
    public boolean updateServiceCenter(ServiceCenters from, ServiceCenters to) {
        return false;
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
}
