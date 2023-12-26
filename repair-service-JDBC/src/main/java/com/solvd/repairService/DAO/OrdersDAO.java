package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.QueryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO extends AbstractDAO implements IOrderDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(UsersDAO.class);

    @Override
    public Orders create(Orders order) {
        return null;
    }

    @Override
    public Orders selectById(Orders order) {
        return null;
    }

    @Override
    public Orders changeRepairman(Orders order, EmployerProfiles repairman) {
        return null;
    }

    @Override
    public Orders changeServiceCenter(Orders order, ServiceCenters serviceCenter) {
        return null;
    }

    @Override
    public List<Orders> orderHistory(CustomerProfiles profiles) {
        String query = " SELECT o.id AS oId, userId, equipmentId, executeId, " +
                "oe.id AS oeID, employerId, cost, finishDate, returned, serviceCenterID, " +
                "e.id as eId, equipmentType, producer, model, price  from orders AS o " +
                "JOIN order_executions AS oe on executeId = oe.id " +
                "JOIN equipments AS e on equipmentId = e.id " +
                "WHERE userId = " + profiles.id();

        connection = connectionPool.getConnection();
        ArrayList<Orders> orders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Orders order = new Orders(resultSet.getLong("oId"));
                order.userId(profiles.id());
                order.executeId(resultSet.getLong("executeId"));

                OrderExecutions orderExecutions = new OrderExecutions(order.executeId());
                orderExecutions.employerId(resultSet.getLong("employerId"));
                orderExecutions.cost(resultSet.getDouble("cost"));
                orderExecutions.finishDate(resultSet.getDate("finishDate"));
                orderExecutions.isReturned(resultSet.getBoolean("returned"));
                orderExecutions.serviceCenterId(resultSet.getLong("serviceCenterId"));

                Equipments equipment = new Equipments(resultSet.getLong("equipmentId"));
                equipment.type(resultSet.getString("equipmentType"));
                equipment.model(resultSet.getString("model"));

                order.orderExecution(orderExecutions);
                order.equipment(equipment);
                orders.add(order);
            }
            connectionPool.returnConnection(connection);
            return orders;
        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }
}
