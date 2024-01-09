package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.ICustomerProfileDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.helpers.queryConfigurationHelper.UpdateStatements;
import com.solvd.repairService.model.CustomerProfiles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerProfilesDAO extends AbstractDAO implements ICustomerProfileDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(CustomerProfilesDAO.class);

    @Override
    public void create(CustomerProfiles profile) {
        ArrayList<String> values = new ArrayList<>();
        values.add(profile.id().toString());
        values.add(profile.nick());
        values.add(profile.phone());
        String query = "INSERT INTO " + profile.tableName() + "  " +
                "VALUES " + InsertValuesHelper.values(values);
        connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            connectionPool.returnConnection(connection);
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + profile.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(CustomerProfiles model) {

        String query = "SELECT * FROM " + model.tableName() + " WHERE id = '" + model.id() + "'";
        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                model.nick(resultSet.getString("nick"));
                model.phone(resultSet.getString("phone"));
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            connectionPool.returnConnection(connection);
            LOGGER.error("Some error with table " + model.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void get(ArrayList<CustomerProfiles> profiles) {

        String query = "SELECT * FROM customer_profiles";
        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                var profile = new CustomerProfiles(
                        resultSet.getLong("id"),
                        resultSet.getString("nick"),
                        resultSet.getString("phone"));
                profiles.add(profile);
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
    public List<CustomerProfiles> selectByNick(String nick) {
        return null;
    }

    @Override
    public List<CustomerProfiles> selectByPhone(String phone) {
        return null;
    }

    @Override
    public CustomerProfiles updateProfile(CustomerProfiles from, CustomerProfiles to) {
        String query = UpdateStatements.get(from, to, from.user().id());
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
}
