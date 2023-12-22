package com.solvd.repairService.DAO;

import com.solvd.repairService.DAO.interfaces.IAbstractDAO;
import com.solvd.repairService.model.AbstractModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class AbstractDAO implements IAbstractDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    protected Connection connection;
    protected final ConnectionPool connectionPool = ConnectionPool.getConnectionPool(5);

    @Override
    public boolean checkAvailability(AbstractModel model) {
        String query = "SELECT * FROM " + model.simpleName() + " WHERE id = " + model.id();
        connection = connectionPool.getConnection();
        ResultSet result;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
            connectionPool.returnConnection(connection);
            return result.next();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + model.simpleName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(AbstractModel model) {
        String query = "DELETE FROM " + model.simpleName() + " WHERE id = " + model.id();
        connection = connectionPool.getConnection();
        int result;
        try {
            Statement statement = null;
            statement = connection.createStatement();
            result = statement.executeUpdate(query);
            connectionPool.returnConnection(connection);
            statement.close();
            return result;
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + model.simpleName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }

    }
}
