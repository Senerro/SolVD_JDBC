package com.solvd.repairService.DAO.JDBC;

import com.solvd.repairService.DAO.interfaces.IEquipmentProblemDAO;
import com.solvd.repairService.helpers.queryConfigurationHelper.InsertValuesHelper;
import com.solvd.repairService.model.EquipmentProblem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EquipmentProblemDAO extends AbstractDAO implements IEquipmentProblemDAO {
    static {
        System.setProperty("log4j.configurationFile", "log4j.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(UsersDAO.class);

    @Override
    public void create(EquipmentProblem ep) {
        ArrayList<String> fields = new ArrayList<>();
        fields.add("equipmentId");
        fields.add("problemId");

        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(ep.equipmentId()));
        values.add(String.valueOf(ep.problemId()));

        String query = InsertValuesHelper.get(ep, fields, values);
        PreparedStatement ps;
        connection = connectionPool.getConnection();
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            connectionPool.returnConnection(connection);
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Some error with table " + ep.tableName() + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            throw new RuntimeException(e);
        }

    }
}
