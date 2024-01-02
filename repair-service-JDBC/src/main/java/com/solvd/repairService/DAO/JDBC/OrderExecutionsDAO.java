package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IOrderExecuteDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.model.EmployerPosts;
import com.solvd.repairService.model.OrderExecutions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderExecutionsDAO extends AbstractDAO implements IOrderExecuteDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(UsersDAO.class);
    @Override
    public OrderExecutions selectById(OrderExecutions orderExecution) {
        return null;
    }

    @Override
    public void create(OrderExecutions orderExecution) {
        ArrayList<String> fields = new ArrayList<>();
        fields.add("employerId");
        fields.add("cost");
        fields.add("returned");
        fields.add("serviceCenterId");
        fields.add("finishDate");

        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(orderExecution.employerId()));
        values.add(String.valueOf(orderExecution.cost()));
        values.add(String.valueOf(orderExecution.isReturned()));
        values.add(String.valueOf(orderExecution.serviceCenterId()));
        values.add(String.valueOf(orderExecution.finishDate()));


        String query = InsertValuesHelper.get(orderExecution, fields, values);
        ResultSet result;
        PreparedStatement ps;
        connection = connectionPool.getConnection();
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            result = ps.getGeneratedKeys();
            connectionPool.returnConnection(connection);
            while (result.next()) {
                orderExecution.id(result.getLong("GENERATED_KEY"));
            }
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + orderExecution.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOrderExecution(OrderExecutions from, OrderExecutions to) {
        return false;
    }

    @Override
    public void get(ArrayList<OrderExecutions> list) {


    }
}
