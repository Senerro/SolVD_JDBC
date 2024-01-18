package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IOrderDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
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
    public void create(Orders order) {

        ArrayList<String> fields = new ArrayList<>();
        fields.add("userId");
        fields.add("equipmentId");
        fields.add("executeId");

        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(order.userId()));
        values.add(String.valueOf(order.equipmentId()));
        values.add(String.valueOf(order.executeId()));

        String query = InsertValuesHelper.get(order, fields, values);
        ResultSet result;
        PreparedStatement ps;
        connection = connectionPool.getConnection();
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            result = ps.getGeneratedKeys();
            connectionPool.returnConnection(connection);
            while (result.next()) {
                order.id(result.getLong("GENERATED_KEY"));
            }
            ps.close();

        } catch (SQLException e) {
            LOGGER.error("Some error with table " + order.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectById(Orders order) {
        String query = "SELECT e.type, e.producer, e.model, pt.type AS ptype FROM orders AS o \n" +
                " JOIN equipments AS e ON e.id = o.equipmentId\n" +
                " JOIN equipment_problem AS ep ON ep.equipmentId = e.id\n" +
                " JOIN problems AS p ON p.id = ep.problemId\n" +
                " JOIN problem_type AS pt ON pt.id = p.typeId " +
                " WHERE o.id = " + order.id() + " ";

        connection = connectionPool.getConnection();
        ArrayList<Orders> orders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Equipments equipment = new Equipments();
                equipment.type(resultSet.getString("type"));
                equipment.producer(resultSet.getString("producer"));
                equipment.model(resultSet.getString("model"));

                Problems problem = new Problems();
                problem.description(resultSet.getString("ptype"));

                equipment.addProblem(problem);
                order.equipment(equipment);
            }
            connectionPool.returnConnection(connection);

        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Orders changeRepairman(Orders order, EmployeeProfiles repairman) {
        return null;
    }

    @Override
    public Orders changeServiceCenter(Orders order, ServiceCenters serviceCenter) {
        return null;
    }

    @Override
    public ArrayList<Orders> orderHistory(CustomerProfiles profiles) {
        String query = " SELECT o.id AS oId, userId, equipmentId, executeId, " +
                "oe.id AS oeID, employerId, cost, finishDate, returned, serviceCenterID, " +
                "e.id as eId, type, producer, model, price  FROM orders AS o " +
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
                orderExecutions.finishDate(resultSet.getInt("finishDate"));
                orderExecutions.isReturned(resultSet.getInt("returned"));
                orderExecutions.serviceCenterId(resultSet.getLong("serviceCenterId"));

                Equipments equipment = new Equipments(resultSet.getLong("equipmentId"));
                equipment.type(resultSet.getString("type"));
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

    @Override
    public void get(ArrayList<Orders> list) {
        String query = " SELECT o. id, o.userId, e.type, e.producer, e.model, oe.cost  FROM orders AS o\n" +
                " JOIN equipments AS e ON e.id = o.equipmentId \n" +
                " JOIN order_executions AS oe ON oe.id = o.executeId";

        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Orders order = new Orders(resultSet.getLong("id"));
                order.userId(resultSet.getLong("userId"));


                Equipments equipment = new Equipments();
                equipment.type(resultSet.getString("type"));
                equipment.type(resultSet.getString("producer"));
                equipment.model(resultSet.getString("model"));

                OrderExecutions orderExecutions = new OrderExecutions(order.executeId());
                orderExecutions.cost(resultSet.getDouble("cost"));


                order.orderExecution(orderExecutions);
                order.equipment(equipment);
                list.add(order);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void get(Orders order) {
        String query = " " +
                " SELECT cp.nick, cp.phone AS customerPhone, \n" +
                " ep.fullName, ep.phone AS phone,\n" +
                " e.type, e.producer, e.model,\n" +
                " oe.cost, oe.returned,\n" +
                " sc.name, sc.address\n" +
                " FROM orders AS o\n" +
                " JOIN equipments AS e ON e.id = o.equipmentId\n" +
                " JOIN order_executions AS oe ON oe.id = o.executeId\n" +
                " JOIN customer_profiles AS cp ON cp.id = o.userId\n" +
                " JOIN employee_profiles AS ep ON ep.id = oe.employerId\n" +
                " JOIN service_centers AS sc ON sc.id = oe.serviceCenterID" +
                " WHERE o.id = " + order.id();

        connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CustomerProfiles customer = new CustomerProfiles();
                customer.nick(resultSet.getString("nick"));
                customer.phone(resultSet.getString("customerPhone"));
                order.customer(customer);

                EmployeeProfiles employee = new EmployeeProfiles();
                employee.fullName(resultSet.getString("fullName"));
                employee.phone(resultSet.getString("phone"));
                order.employee(employee);

                Equipments equipment = new Equipments();
                equipment.type(resultSet.getString("type"));
                equipment.producer(resultSet.getString("producer"));
                equipment.model(resultSet.getString("model"));
                order.equipment(equipment);

                OrderExecutions orderExecutions = new OrderExecutions();
                orderExecutions.cost(resultSet.getDouble("cost"));
                orderExecutions.isReturned(resultSet.getInt("returned"));
                order.orderExecution(orderExecutions);

                ServiceCenters center = new ServiceCenters();
                center.name(resultSet.getString("name"));
                center.address(resultSet.getString("address"));
                order.center(center);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            LOGGER.error("Some error with table users" + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Orders order, Orders newOrder) {
        String query = "UPDATE orders SET userId = " + newOrder.userId() + " , equipmentId = " + newOrder.equipmentId() + " , executeId = " + newOrder.executeId() + " " +
                "WHERE id = " + newOrder.id();
        connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            connectionPool.returnConnection(connection);
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + order.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }
}
